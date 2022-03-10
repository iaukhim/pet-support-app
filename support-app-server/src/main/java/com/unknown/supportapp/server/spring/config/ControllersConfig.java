package com.unknown.supportapp.server.spring.config;

import com.unknown.supportapp.server.controllers.Controller;
import com.unknown.supportapp.server.controllers.impl.UnknownRequestController;
import com.unknown.supportapp.server.controllers.impl.account.*;
import com.unknown.supportapp.server.controllers.impl.manager.LoginManagerController;
import com.unknown.supportapp.server.controllers.impl.manager.ManagerLoadIdByEmailController;
import com.unknown.supportapp.server.controllers.impl.ownedProduct.*;
import com.unknown.supportapp.server.controllers.impl.product.LoadAllProductTypesController;
import com.unknown.supportapp.server.controllers.impl.product.LoadAllProductsController;
import com.unknown.supportapp.server.controllers.impl.product.LoadProductsByTypeController;
import com.unknown.supportapp.server.controllers.impl.ticket.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ServicesConfig.class)
public class ControllersConfig {

    @Autowired
    private ServicesConfig servicesConfig;

    @Bean(name = "change-password")
    public Controller changePasswordController(){
        return new ChangePasswordController(servicesConfig.accountService());
    }

    @Bean(name = "check-existence")
    public Controller checkAccountExistenceController(){
        return new CheckAccountExistenceController(servicesConfig.accountService());
    }

    @Bean(name = "confirmation-code")
    public Controller confirmationCodeController(){
        return new ConfirmationCodeController(servicesConfig.accountService());
    }

    @Bean(name = "confirmation")
    public Controller confirmationController(){
        return new ConfirmationController(servicesConfig.accountService());
    }

    @Bean(name = "delete-account-by-id")
    public Controller deleteAccountByIdController(){
        return new DeleteAccountByIdController(servicesConfig.accountService());
    }

    @Bean(name = "load-account-by-email")
    public Controller loadAccountByEmailController(){
        return new LoadAccountByEmailController(servicesConfig.accountService());
    }

    @Bean(name = "load-id-by-email")
    public Controller loadIdByEmailController(){
        return new LoadIdByEmailController(servicesConfig.accountService());
    }

    @Bean(name = "log-in")
    public Controller logInController(){
        return new LogInController(servicesConfig.accountService());
    }

    @Bean(name = "registration")
    public Controller registrationController(){
        return new RegistrationController(servicesConfig.accountService());
    }

    @Bean(name = "update-account")
    public Controller updateAccountController(){
        return new UpdateAccountController(servicesConfig.accountService());
    }

    @Bean(name = "manager-log-in")
    public Controller loginManagerController(){
        return new LoginManagerController(servicesConfig.managerService());
    }

    @Bean(name = "manager-load-id-by-email")
    public Controller managerLoadIdByEmailController(){
        return new ManagerLoadIdByEmailController(servicesConfig.managerService());
    }

    @Bean(name = "change-serial")
    public Controller changeSerialController(){
        return new ChangeSerialController(servicesConfig.ownedProductService());
    }

    @Bean(name = "check-serial")
    public Controller checkSerialController(){
        return new CheckSerialController(servicesConfig.ownedProductService());
    }

    @Bean(name = "delete-user-product")
    public Controller deleteUserProductController(){
        return new DeleteUserProductController(servicesConfig.ownedProductService());
    }

    @Bean(name = "load-owned-product-by-id")
    public Controller loadOwnedProductByIdController(){
        return new LoadOwnedProductByIdController(servicesConfig.ownedProductService());
    }

    @Bean(name = "load-users-products")
    public Controller loadUsersProductController(){
        return new LoadUsersProductsController(servicesConfig.ownedProductService());
    }

    @Bean(name = "owned-product-load-model-by-id")
    public Controller ownedProductLoadModelByIdController(){
        return new OwnedProductLoadModelByIdController(servicesConfig.ownedProductService());
    }

    @Bean(name = "save-product")
    public Controller saveUserProductController(){
        return new SaveUserProductController(servicesConfig.ownedProductService());
    }

    @Bean(name = "load-all-products")
    public Controller loadAllProductsController(){
        return new LoadAllProductsController(servicesConfig.productService());
    }

    @Bean(name = "load-product-types")
    public Controller loadAllProductTypesController(){
        return new LoadAllProductTypesController(servicesConfig.productService());
    }

    @Bean(name = "load-products-by-type")
    public Controller loadProductsByTypeController(){
        return new LoadProductsByTypeController(servicesConfig.productService());
    }

    @Bean(name = "close-ticket")
    public Controller closeTicketController(){
        return new CloseTicketController(servicesConfig.ticketService());
    }

    @Bean(name = "load-managed-tickets")
    public Controller loadManagedTicketsController(){
        return new LoadManagedTicketsController(servicesConfig.ticketService());
    }

    @Bean(name = "load-unassigned-tickets")
    public Controller loadUnAssignedTicketsController(){
        return new LoadUnAssignedTicketsController(servicesConfig.ticketService());
    }

    @Bean(name = "load-user-closed-tickets")
    public Controller loadUserClosedTicketsController(){
        return new LoadUserClosedTicketsController(servicesConfig.ticketService());
    }

    @Bean(name = "load-user-opened-tickets")
    public Controller loadUserOpenedTicketsController(){
        return new LoadUserOpenedTicketsController(servicesConfig.ticketService());
    }

    @Bean(name = "load-user-tickets-by-email")
    public Controller loadUserTicketsByEmailController(){
        return new LoadUserTicketsByEmailController(servicesConfig.ticketService());
    }

    @Bean(name = "load-user-tickets-by-id")
    public Controller loadUserTicketsByIdController(){
        return new LoadUserTicketsByIdController(servicesConfig.ticketService());
    }

    @Bean(name = "save-new-ticket")
    public Controller saveNewTicketController(){
        return new SaveNewTicketController(servicesConfig.ticketService());
    }

    @Bean(name = "ticket-set-manager-id")
    public Controller ticketSetManagerIdController(){
        return new TicketSetManagerIdController(servicesConfig.ticketService());
    }

    @Bean(name = "update-ticket")
    public Controller updateTicketController(){
        return new UpdateTicketController(servicesConfig.ticketService());
    }

    @Bean(name = "unknown-request")
    public Controller unknownRequestController(){
        return new UnknownRequestController();
    }

}
