package com.fengkai.zhouyang.yangyanghongkong.db;


import com.fengkai.zhouyang.yangyanghongkong.addprodut.model.Product;
import com.fengkai.zhouyang.yangyanghongkong.application.MainAplication;
import com.speedystone.greendaodemo.db.DaoSession;
import com.speedystone.greendaodemo.db.ProductDao;

import java.util.List;

public class ProductDatabase {
    private static ProductDao getDao(){
        DaoSession daosession = MainAplication.getInstance().getDaosession();
        ProductDao productDao = daosession.getProductDao();
        return productDao;
    }

    public static void insertDb(Product product) {
        ProductDao productDao = getDao();
        productDao.insert(product);
    }

    public static void deleteById(long id) {
        ProductDao productDao = getDao();
        productDao.deleteByKey(id);
    }

    public static void updateById(Product pro, long id) {
        pro.id = id;
        ProductDao productDao = getDao();
        productDao.update(pro);
    }

    public static List<Product> queryAllProduct() {
        ProductDao dao = getDao();
        List<Product> products = dao.loadAll();
        return products;
    }

    public static long queryCount() {
        ProductDao dao = getDao();
        long count = dao.count();
        return count;
    }
}
