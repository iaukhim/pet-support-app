package com.unknown.server.dao.factory;


import com.unknown.server.dao.*;

public abstract class DaoFactory {

    public static final String  XML = "xml";

    public static final String  MYSQL = "mysql";

    public static DaoFactory getFactory(){
        return MySqlDaoFactory.getFactory();
    }


    public abstract AccountDao getAccountDao();
    public abstract ProductDao getProductDao();
    public abstract OwnedProductDao getOwnedProductDao();
    public abstract TicketDao getTicketDao();
    public abstract ManagerDao getManagerDao();



}
