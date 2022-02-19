package com.unknown.server.services.factory;


import com.unknown.server.services.*;
import com.unknown.server.services.impl.*;

public class ServiceFactory {

    private static final ServiceFactory factory = new ServiceFactory();

    private AccountService accountService;
    private ProductService productService;
    private OwnedProductService ownedProductService;
    private TicketService ticketService;
    private ManagerService managerService;

    private ServiceFactory(){

        accountService = new AccountServiceImpl();
        productService = new ProductServiceImpl();
        ownedProductService = new OwnedProductServiceImpl();
        ticketService = new TicketServiceImpl();
        managerService = new ManagerServiceImpl();
    }

    public AccountService getAccountService() {
        return accountService;
    }

    public ProductService getProductService() {
        return productService;
    }

    public ManagerService getManagerService() {
        return managerService;
    }

    public OwnedProductService getOwnedProductService() {
        return ownedProductService;
    }

    public TicketService getTicketService() {
        return ticketService;
    }

    public static ServiceFactory getFactory (){
        return  factory;
    }
}
