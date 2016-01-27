package com.peiyuan.model.env;

import android.content.Context;
import android.content.ContextWrapper;

import com.peiyuan.model.db.LibraryModule;
import com.peiyuan.model.db.RmMigration;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import timber.log.Timber;

/**
 * Created by Painter-Zhang on 2016/1/25.
 */
public class ModelContext extends ContextWrapper {

    private static ModelContext instance = null;

    //内存配置
//    private static Realm memoryRealM;
    private RealmConfiguration memoryConfig;

    private ModelContext(Context context) {
        super(context);
        initDefaultRealM(context);
        initMemoryRealM(context);
        initLogger();
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
                .deleteRealmIfMigrationNeeded() //开发过程中 便于调试
                .build();
        Realm.setDefaultConfiguration(config);
    }

    /**
     * 初始化内存数据库
     *
     * @param context
     */
    private void initMemoryRealM(Context context) {
        memoryConfig = new RealmConfiguration.Builder(context)
                .name("memory.realm")
                .setModules(new LibraryModule())
                .inMemory()
                .build();
    }

    private Realm getMemoryRealM() {
        return Realm.getInstance(memoryConfig);
    }

    /**
     * 初始化log
     */
    private void initLogger() {
        if (true){
            Timber.plant(new Timber.DebugTree());
            Timber.tag(getClass().getSimpleName());
        }else {
            // TODO: 2016/1/26 增加错误上报功能
        }
    }


}
