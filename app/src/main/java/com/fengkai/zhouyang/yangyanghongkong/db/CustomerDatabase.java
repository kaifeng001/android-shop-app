package com.fengkai.zhouyang.yangyanghongkong.db;

import com.fengkai.zhouyang.yangyanghongkong.customer.model.Customer;
import com.speedystone.greendaodemo.db.DaoSession;

import org.greenrobot.greendao.AbstractDao;

public class CustomerDatabase extends BaseDataBase<Customer> {
    private static CustomerDatabase sDatabase;

    public static CustomerDatabase getInstance() {
        if (sDatabase == null) {
            synchronized (CustomerDatabase.class) {
                if (sDatabase == null) {
                    sDatabase = new CustomerDatabase();
                }
            }
        }
        return sDatabase;
    }

    @Override
    protected AbstractDao getDao(DaoSession daosession) {
        return daosession.getCustomerDao();
    }
}
