package com.fengkai.zhouyang.yangyanghongkong.db;


import com.fengkai.zhouyang.yangyanghongkong.addprodut.model.Product;
import com.speedystone.greendaodemo.db.DaoSession;

import org.greenrobot.greendao.AbstractDao;

public class ProductDatabase extends BaseDataBase<Product> {
    private static ProductDatabase sDatabase;

    public static ProductDatabase getInstance() {
        if (sDatabase == null) {
            synchronized (ProductDatabase.class) {
                if (sDatabase == null) {
                    sDatabase = new ProductDatabase();
                }
            }
        }
        return sDatabase;
    }

    @Override
    protected AbstractDao getDao(DaoSession daosession) {
        return daosession.getProductDao();
    }

}
