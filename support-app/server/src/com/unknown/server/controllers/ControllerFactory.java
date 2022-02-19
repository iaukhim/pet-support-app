package com.unknown.server.controllers;

import com.unknown.server.controllers.impl.account.*;
import com.unknown.server.controllers.impl.manager.LoginManagerController;
import com.unknown.server.controllers.impl.manager.ManagerLoadIdByEmailController;
import com.unknown.server.controllers.impl.ownedProduct.*;
import com.unknown.server.controllers.impl.product.LoadAllProductTypesController;
import com.unknown.server.controllers.impl.product.LoadAllProductsController;
import com.unknown.server.controllers.impl.product.LoadProductsByTypeController;
import com.unknown.server.controllers.impl.ticket.*;
import com.unknown.server.controllers.impl.UnknownRequestController;

import java.util.HashMap;
import java.util.Map;

public class ControllerFactory {

    private static final ControllerFactory factory = new ControllerFactory();

    private Map <String, Controller> allControllers;

    private ControllerFactory() {
        allControllers = new HashMap<>();

        allControllers.put("log-in", new LogInController());
        allControllers.put("confirmation", new ConfirmationController());
        allControllers.put("registration", new RegistrationController());
        allControllers.put("check-existence", new CheckAccountExistenceController());
        allControllers.put("confirmation-code", new ConfirmationCodeController());
        allControllers.put("change-password", new ChangePasswordController());
        allControllers.put("load-users-products", new LoadUsersProductsController());
        allControllers.put("delete-user-product", new DeleteUserProductController());
        allControllers.put("change-serial", new ChangeSerialController());
        allControllers.put("load-product-types", new LoadAllProductTypesController());
        allControllers.put("load-all-products", new LoadAllProductsController());
        allControllers.put("load-products-by-type", new LoadProductsByTypeController());
        allControllers.put("save-product", new SaveUserProductController());
        allControllers.put("load-id-by-email", new LoadIdByEmailController());
        allControllers.put("check-serial", new CheckSerialController());
        allControllers.put("load-account-by-email", new LoadAccountByEmailController());
        allControllers.put("update-account", new UpdateAccountController());
        allControllers.put("delete-account-by-id", new DeleteAccountByIdController());
        allControllers.put("load-user-tickets-by-id", new LoadUserTicketsByIdController());
        allControllers.put("load-user-tickets-by-email", new LoadUserTicketsByEmailController());
        allControllers.put("save-new-ticket", new SaveNewTicketController());
        allControllers.put("update-ticket", new UpdateTicketController());
        allControllers.put("load-user-closed-tickets", new LoadUserClosedTicketsController());
        allControllers.put("load-user-opened-tickets", new LoadUserOpenedTicketsController());
        allControllers.put("close-ticket", new CloseTicketController());
        allControllers.put("manager-log-in", new LoginManagerController());
        allControllers.put("load-unassigned-tickets", new LoadUnAssignedTicketsController());
        allControllers.put("load-owned-product-by-id", new LoadOwnedProductByIdController());
        allControllers.put("ticket-set-manager-id", new TicketSetManagerIdController());
        allControllers.put("manager-load-id-by-email", new ManagerLoadIdByEmailController());
        allControllers.put("owned-product-load-model-by-id", new OwnedProductLoadModelByIdController());
        allControllers.put("load-managed-tickets", new LoadManagedTicketsController());
    }

    public static ControllerFactory getFactory(){
        return  factory;
    }

    public Controller getControllerByName(String name){
        Controller controller = allControllers.get(name);

        if (controller== null) {
            return new UnknownRequestController();
        }

        return controller;
    }
}
