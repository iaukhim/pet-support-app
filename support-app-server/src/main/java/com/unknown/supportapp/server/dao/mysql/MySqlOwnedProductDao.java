package com.unknown.supportapp.server.dao.mysql;

import com.unknown.supportapp.server.dao.OwnedProductDao;
import com.unknown.supportapp.server.entities.OwnedProduct;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class MySqlOwnedProductDao implements OwnedProductDao {

    public MySqlOwnedProductDao() {
    }

    public MySqlOwnedProductDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    private EntityManager entityManager;

    @Override
    public List<OwnedProduct> loadUsersProducts(String email) {
        TypedQuery<OwnedProduct> query = entityManager.createQuery("SELECT o FROM OwnedProduct as o WHERE o.ownerId IN (SELECT a.id FROM Account as a WHERE a.email = :email)", OwnedProduct.class);
        query.setParameter("email", email);
        try {
            List<OwnedProduct> resultList = query.getResultList();
            return resultList;
        } catch (Exception e) {
            throw new RuntimeException("Dao exception", e);
        }
    }

    @Override
    public void saveProduct(OwnedProduct product) {
        entityManager.joinTransaction();
        try {
            entityManager.merge(product);
        } catch (Exception e) {
            throw new RuntimeException("Dao exception", e);
        }
    }
    @Override
    public boolean changeSerial(String oldValue, String newValue) {
        entityManager.joinTransaction();
        TypedQuery<OwnedProduct> loadProductQuery = entityManager.createQuery("SELECT o FROM OwnedProduct as o WHERE o.serialNumber = :serialNumber", OwnedProduct.class);
        loadProductQuery.setParameter("serialNumber", oldValue);
        OwnedProduct product = loadProductQuery.getSingleResult();

        if(checkSerial(newValue)){
            product.setSerialNumber(newValue);
            entityManager.merge(product);
            return true;
        }

        return false;
    }

    @Override
    public void deleteUserProduct(OwnedProduct product) {
        entityManager.joinTransaction();
        try {
            OwnedProduct entityForDelete = entityManager.find(OwnedProduct.class, product.getId());
            entityManager.remove(entityForDelete);
        } catch (Exception e) {
            throw new RuntimeException("Dao exception", e);
        }
    }

    @Override
    public boolean checkSerial(String serialNumber) {
        entityManager.joinTransaction();
        TypedQuery<OwnedProduct> query = entityManager.createQuery("SELECT o FROM OwnedProduct as o WHERE o.serialNumber = :serialNumber", OwnedProduct.class);
        query.setParameter("serialNumber", serialNumber);
        try {
            if(query.getResultList().isEmpty()){
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException("Dao exception", e);
        }

        return false;
    }

    @Override
    public OwnedProduct loadById(int id) {
        entityManager.joinTransaction();
        try {
            return entityManager.find(OwnedProduct.class, id);
        } catch (Exception e) {
            throw new RuntimeException("Dao exception", e);
        }
    }

    @Override
    public String loadModelById(int id) {
        entityManager.joinTransaction();
        OwnedProduct product = entityManager.find(OwnedProduct.class, id);
        return product.getModel();
    }
}
