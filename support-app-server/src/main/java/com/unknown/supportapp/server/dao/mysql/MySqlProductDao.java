package com.unknown.supportapp.server.dao.mysql;

import com.unknown.supportapp.server.dao.ProductDao;
import com.unknown.supportapp.server.entities.Product;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.List;

public class MySqlProductDao implements ProductDao {

    public MySqlProductDao() {
    }

    public MySqlProductDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    private EntityManager entityManager;


    @Override
    public int loadIdByModel(String model) {
        entityManager.joinTransaction();
        TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM Product as p WHERE p.model = :model", Product.class);
        query.setParameter("model", model);

        try {
            Product product = query.getSingleResult();
            return product.getId();
        } catch (Exception e) {
            throw new RuntimeException("Dao exception", e);
        }
    }

    @Override
    public List<Product> loadProductsByType(String type) {
        entityManager.joinTransaction();
        TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM Product as p WHERE p.type = :type", Product.class);
        query.setParameter("type", type);

        try {
            List<Product> productList = query.getResultList();
            return productList;
        } catch (Exception e) {
            throw new RuntimeException("Dao exception", e);
        }
    }

    @Override
    public List<Product> loadAllProducts() {
        entityManager.joinTransaction();
        TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM Product as p", Product.class);
        try {
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Dao exception", e);
        }
    }

    @Override
    public List<String> loadAllProductTypes() {
        entityManager.joinTransaction();
        TypedQuery<String> query = entityManager.createQuery("SELECT distinct p.type FROM Product as p", String.class);
        try {
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Dao exception", e);
        }
    }
}
