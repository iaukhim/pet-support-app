package com.unknown.supportapp.server.dao.factory;


import com.unknown.supportapp.server.dao.*;

public abstract class DaoFactory {

    public static DaoFactory getFactory(){
        return MySqlDaoFactory.getFactory();
    }

    public abstract AccountDao getAccountDao();
    public abstract ProductDao getProductDao();
    public abstract OwnedProductDao getOwnedProductDao();
    public abstract TicketDao getTicketDao();
    public abstract ManagerDao getManagerDao();
}
