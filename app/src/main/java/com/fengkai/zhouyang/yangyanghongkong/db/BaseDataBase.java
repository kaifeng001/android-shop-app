package com.fengkai.zhouyang.yangyanghongkong.db;

import com.fengkai.zhouyang.yangyanghongkong.application.MainAplication;
import com.speedystone.greendaodemo.db.DaoSession;

import org.greenrobot.greendao.AbstractDao;

import java.util.List;

public abstract class BaseDataBase<T> {
    private AbstractDao mDao;

    public BaseDataBase() {
        DaoSession daosession = MainAplication.getInstance().getDaosession();
        mDao = getDao(daosession);
    }

    protected abstract AbstractDao getDao(DaoSession daosession);

    public void insertDb(T obj) {
        mDao.insert(obj);
    }

    public void deleteById(long id) {
        mDao.deleteByKey(id);
    }

    public void updateDb(T pro) {
        mDao.update(pro);
    }

    public List<T> queryAllProduct() {
        return mDao.loadAll();
    }

    public long queryCount() {
        return mDao.count();
    }
}
