package com.peiyuan.common.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SharePreferenceManager {

    
    private static final String OBJECT_KEY = "OBJECT_KEY";
    private static final String NORMAL_KEY = "NORMAL_KEY";
    
    /**
     * 获取对象。
     *
     * @param context 上下文。
     * @param key     保存用的唯一 id。
     * @return 获取的对象。
     */
    public static <T> T getObjetct(Context context, String key, Class<T> clazz) {

        if (TextUtils.isEmpty(key) || context == null) {
            throw new IllegalArgumentException("key or context is null。");
        }

        T t = null;

        SharedPreferences sharedPreferences = context.getSharedPreferences(OBJECT_KEY, Context.MODE_PRIVATE);

        if (sharedPreferences.contains(key)) {
            String objStr = sharedPreferences.getString(key, null);

            if (!TextUtils.isEmpty(objStr)) {
                byte[] objArray = Base64.decode(objStr, Base64.NO_WRAP);
                ByteArrayInputStream bais = new ByteArrayInputStream(objArray);
                ObjectInputStream ois = null;

                try {
                    ois = new ObjectInputStream(bais);
                    t = (T) ois.readObject();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (bais != null) {
                            bais.close();
                        }
                        if (ois != null) {
                            ois.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }

        return t;
    }

    /**
     * 保存对象。
     *
     * @param context 上下文。
     * @param key     保存用的唯一 id。
     * @param obj     要保存的对象。
     */
    public static void saveObject(Context context, String key, Object obj) {

        if (context == null || TextUtils.isEmpty(key) || obj == null) {
            throw new IllegalArgumentException("parameters is null。");
        }

        SharedPreferences sharedPreferences = context.getSharedPreferences(OBJECT_KEY, Context.MODE_PRIVATE);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;

        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            String objStr = new String(Base64.encodeToString(baos.toByteArray(), Base64.NO_WRAP));
            sharedPreferences.edit().putString(key, objStr).commit();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 根据 key 获取字符串。
     *
     * @param context 上下文。
     * @param key     保存用的唯一 id。
     * @return 获取的字符串。
     */
    public static <T> T getValue(Context context, String key, Class<T> clazz) {

        if (context == null || TextUtils.isEmpty(key)) {
            throw new IllegalArgumentException("context or key is null。");
        }

        T t = null;

        SharedPreferences sharedPreferences = context.getSharedPreferences(NORMAL_KEY, Context.MODE_PRIVATE);

        String className = clazz.getSimpleName();

        if (!TextUtils.isEmpty(className)) {
            if (Integer.class.getSimpleName().equals(className)) {
                return (T) Integer.valueOf(sharedPreferences.getInt(key, 0));
            } else if (String.class.getSimpleName().equals(className)) {
                return (T) sharedPreferences.getString(key, "");
            } else if (Long.class.getSimpleName().equals(className)) {
                return (T) Long.valueOf(sharedPreferences.getLong(key, 0));
            } else if (Boolean.class.getSimpleName().equals(className)) {
                return (T) Boolean.valueOf(sharedPreferences.getBoolean(key, false));
            } else if (Float.class.getSimpleName().equals(className)) {
                return (T) Float.valueOf(sharedPreferences.getFloat(key, 0));
            } else {
                throw new RuntimeException("clazz not parser!");
            }
        }

        return null;
    }

    /**
     * 保存字符串。
     *
     * @param context 上下文。
     * @param key     保存用的唯一 id。
     * @param value   要保存的内容。
     */
    public static <T> void saveValue(Context context, String key, Object value) {

        if (context == null || TextUtils.isEmpty(key)) {
            throw new IllegalArgumentException("context or key is null。");
        }

        SharedPreferences sharedPreferences = context.getSharedPreferences(NORMAL_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (value instanceof Integer) {
            editor.putInt(key, (Integer) value).commit();
        } else if (value instanceof String) {
            editor.putString(key, (String) value).commit();
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value).commit();
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value).commit();
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value).commit();
        }

    }

    /**
     * 获取 sharePreference 对象。
     *
     * @param context
     * @return
     */
    public static SharedPreferences getSharePreferences(Context context) {

        if (context == null) {
            throw new IllegalArgumentException("context  is null。");
        }

        return context.getSharedPreferences(NORMAL_KEY, Context.MODE_PRIVATE);
    }

    /**
     * 清除缓存数据。
     *
     * @param context
     */
    public static void clearSharedPreferences(Context context) {

        if (context == null) {
            throw new IllegalArgumentException("context  is null。");
        }

        SharedPreferences general = context.getSharedPreferences(NORMAL_KEY, Context.MODE_PRIVATE);
        SharedPreferences object = context.getSharedPreferences(OBJECT_KEY, Context.MODE_PRIVATE);
        general.edit().clear().commit();
        object.edit().clear().commit();

    }

    /**
     * 按 key 清除缓存数据
     *
     * @param context
     * @param keys
     */
    public static void clearSharedPreferences(Context context, String... keys) {

        if (context == null)
            throw new IllegalArgumentException("context  is null。");

        if (keys == null)
            throw new IllegalArgumentException("keys  is null。");

        SharedPreferences general = context.getSharedPreferences(NORMAL_KEY, Context.MODE_PRIVATE);
        SharedPreferences object = context.getSharedPreferences(OBJECT_KEY, Context.MODE_PRIVATE);

        SharedPreferences.Editor generalEditor = general.edit();
        SharedPreferences.Editor objectEditor = object.edit();

        for (String key : keys) {

            if (general.contains(key))
                generalEditor.remove(key);

            if (object.contains(key))
                objectEditor.remove(key);
        }

        generalEditor.commit();
        objectEditor.commit();

    }


}
