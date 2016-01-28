package com.peiyuan.model.db;

import java.util.Objects;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by hexun on 2016/1/28.
 */
public class RealmUtil {

    /**
     * 存储
     */
    public static void saveWithTransaction(Realm realm, Object object){
        realm.beginTransaction();
        
        realm.commitTransaction();
    }


}
