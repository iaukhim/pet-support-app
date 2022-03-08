package com.unknown.supportapp.server.dao.factory;

import com.unknown.supportapp.server.dao.*;
import com.unknown.supportapp.server.dao.mysql.*;


public class MySqlDaoFactory extends DaoFactory {

    private static final MySqlDaoFactory factory = new MySqlDaoFactory();

    private final AccountDao accountDao;
    private final ProductDao productDao;
    private final OwnedProductDao ownedProductDao;
    private final TicketDao ticketDao;
    private final ManagerDao managerDao;

    private MySqlDaoFactory(){
        accountDao = new MySqlAccountDao();
        productDao = new MySqlProductDao();
        ownedProductDao = new MySqlOwnedProductDao();
        ticketDao = new MySqlTicketDao();
        managerDao = new MySqlManagerDao();
    }

    public static MySqlDaoFactory getFactory(){
        return factory;
    }

    @Override
    public ProductDao getProductDao() {
        return productDao;
    }

    @Override
    public AccountDao getAccountDao(){
        return accountDao;
    }

    @Override
    public OwnedProductDao getOwnedProductDao() {
        return ownedProductDao;
    }

    @Override
    public TicketDao getTicketDao() {
        return ticketDao;
    }

    @Override
    public ManagerDao getManagerDao() {
        return managerDao;
    }
}
