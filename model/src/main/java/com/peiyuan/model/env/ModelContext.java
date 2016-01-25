package com.peiyuan.model.env;

import android.content.Context;
import android.content.ContextWrapper;

import com.peiyuan.model.db.LibraryModule;
import com.peiyuan.model.db.RmChangeListener;
import com.peiyuan.model.db.RmMigration;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Painter-Zhang on 2016/1/25.
 */
public class ModelContext extends ContextWrapper {

    private static ModelContext instance = null;

    //内存配置
    private static Realm memoryRealM;

    private ModelContext(Context context) {
        super(context);
        initDefaultRealM(context);
        initMemoryRealM(context);
    }

    public static ModelContext init(Context context) {
        if (instance == null) {
            synchronized (ModelContext.class) {
                if (instance == null) {
                    instance = new ModelContext(context);
                }
            }
        }
        return instance;
    }

    public static ModelContext getInstance() {
        return instance;
    }


    /**
     * 初始化数据库
     *
     * @param context
     */
    private void initDefaultRealM(Context context) {
        RealmConfiguration config = new RealmConfiguration.Builder(context)
                .name("default.realm")
                .schemaVersion(0)
                .migration(new RmMigration())
                .setModules(new LibraryModule())
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }

    /**
     * 初始化内存数据
     *
     * @param context
     */
    private void initMemoryRealM(Context context) {
        RealmConfiguration memoryConfig = new RealmConfiguration.Builder(context)
                .name("memory.realm")
                .setModules(new LibraryModule())
                .inMemory()
                .build();
        memoryRealM = Realm.getInstance(memoryConfig);
        memoryRealM.addChangeListener(RmChangeListener.getListener());
    }

    private Realm getMemoryRealM() {
        return memoryRealM;
    }

}
