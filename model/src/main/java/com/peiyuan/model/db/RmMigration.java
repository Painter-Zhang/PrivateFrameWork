package com.peiyuan.model.db;

import com.orhanobut.logger.Logger;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;

/**
 * Created by hexun on 2016/1/25.
 */
public class RmMigration implements RealmMigration {

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

        Logger.d("存在oldversion为" + oldVersion + "的数据库");
        if (oldVersion == 0) {
        }

        if (oldVersion == 1) {

        }
    }
}
