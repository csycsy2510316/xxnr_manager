package com.app.xxnr;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import db.DaoMaster;
import db.DaoSession;

/**
 * 数据库管理类
 */
public class DBManager {
    private final static String dbName = "xxnr_db";
    private static DBManager mInstance;
    private DaoMaster.DevOpenHelper openHelper;
    private Context context;

    private DBManager(Context context) {
        this.context = context;
        openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
    }

    /**
     * 获取单例引用
     */
    public static DBManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (DBManager.class) {
                if (mInstance == null) {
                    mInstance = new DBManager(context);
                }
            }
        }
        return mInstance;
    }

    /**
     * 获取可读数据库
     */
    private SQLiteDatabase getReadableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        return openHelper.getReadableDatabase();
    }

    /**
     * 获取可写数据库
     */
    private SQLiteDatabase getWritableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        return openHelper.getWritableDatabase();
    }


    /**
     * 获取可写Session
     */
    public DaoSession getWritableDaoSession() {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        return daoMaster.newSession();
    }

    /**
     * 获取可读Session
     */
    public DaoSession getReadableDaoSession() {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        return daoMaster.newSession();
    }
}