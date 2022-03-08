package com.unknown.supportapp.client.common.service.factory;

import com.unknown.supportapp.client.common.service.account.*;
import com.unknown.supportapp.client.common.service.impl.account.*;
import com.unknown.supportapp.client.common.service.impl.manager.ManagerLoadIdByEmailImpl;
import com.unknown.supportapp.client.common.service.impl.manager.ManagerLoginServiceImpl;
import com.unknown.supportapp.client.common.service.impl.ownedProduct.*;
import com.unknown.supportapp.client.common.service.impl.product.LoadAllProductTypesImpl;
import com.unknown.supportapp.client.common.service.impl.product.LoadAllProductsImpl;
import com.unknown.supportapp.client.common.service.impl.product.LoadProductsByTypeImpl;
import com.unknown.supportapp.client.common.service.impl.ticket.*;
import com.unknown.supportapp.client.common.service.manager.ManagerLoadIdByEmailService;
import com.unknown.supportapp.client.common.service.manager.ManagerLoginService;
import com.unknown.supportapp.client.common.service.ownedProduct.*;
import com.unknown.supportapp.client.common.service.product.LoadAllProductTypesService;
import com.unknown.supportapp.client.common.service.product.LoadAllProductsService;
import com.unknown.supportapp.client.common.service.product.LoadProductsByTypeService;
import com.unknown.supportapp.client.common.service.ticket.*;

public class ClientServicesFactory {

    private static final ClientServicesFactory FACTORY = new ClientServicesFactory();

    private final LogInService logInService;
    private final RegistrationService registrationService;
    private final ConfirmationService confirmationService;
    private final CheckAccountExistenceService checkAccountExistenceService;
    private final ConfirmationCodeService confirmationCodeService;
    private final ChangePasswordService changePasswordService;
    private final LoadUsersProductsService loadUsersProductsService;
    private final DeleteUserProductSevice deleteUserProductSevice;
    private final ChangeSerialService changeSerialService;
    private final LoadAllProductTypesService loadAllProductsTypesService;
    private final LoadAllProductsService loadAllProductsService;
    private final LoadProductsByTypeService loadProductsByTypeService;
    private final SaveUserProductService saveProductService;
    private final LoadIdByEmailService loadIdByEmailService;
    private final CheckSerialService checkSerialService;
    private final LoadAccountByEmail loadAccountByEmail;
    private final UpdateAccountService updateAccountService;
    private final DeleteById deleteAccountById;
    private final LoadUserTicketsByIdService loadUserTicketsByIdService;
    private final LoadUserTicketsByEmailService loadUserTicketsByEmailService;
    private final SaveNewTicketService saveNewTicketService;
    private final UpdateTicketService updateTicketService;
    private final LoadUserClosedTicketsService loadUserClosedTicketsService;
    private final LoadUserOpenedTicketsService loadUserOpenedTicketsService;
    private final CloseTicketService closeTicketService;
    private final ManagerLoginService managerLoginService;
    private final LoadUnAssignedTicketsService loadUnAssignedTicketsService;
    private final LoadOwnedProductByIdService loadOwnedProductByIdService;
    private final TicketSetManagerIdService ticketSetManagerIdService;
    private final ManagerLoadIdByEmailService managerLoadIdByEmailService;
    private final OwnedProductLoadModelByIdService ownedProductLoadModelByIdService;
    private final LoadManagedTicketsService loadManagedTicketsService;

    private ClientServicesFactory() {

        logInService = new LogInServiceImpl();
        confirmationService = new ConfirmationServiceImpl();
        registrationService = new RegistrationServiceImpl();
        checkAccountExistenceService = new CheckAccountExistenceServiceImpl();
        confirmationCodeService = new ConfirmationCodeServiceImpl();
        changePasswordService = new ChangePasswordServiceImpl();
        loadUsersProductsService = new LoadUsersProductsImpl();
        deleteUserProductSevice = new DeleteUserProductImpl();
        changeSerialService = new ChangeSerialImpl();
        loadAllProductsTypesService = new LoadAllProductTypesImpl();
        loadAllProductsService = new LoadAllProductsImpl();
        loadProductsByTypeService = new LoadProductsByTypeImpl();
        saveProductService = new SaveProductImpl();
        loadIdByEmailService = new LoadIdByEmailImpl();
        checkSerialService = new CheckSerialImpl();
        loadAccountByEmail = new LoadAccountByEmailImpl();
        updateAccountService = new UpdateAccountImpl();
        deleteAccountById = new DeleteByIdImpl();
        loadUserTicketsByIdService = new LoadUserTicketByIdImpl();
        saveNewTicketService = new SaveNewTicketImpl();
        updateTicketService = new UpdateTicketImpl();
        loadUserOpenedTicketsService = new LoadUserOpenedTicketsImpl();
        loadUserClosedTicketsService = new LoadUserClosedTicketsImpl();
        loadUserTicketsByEmailService = new LoadUserTicketsByEmailImpl();
        closeTicketService = new CloseTicketServiceImpl();
        managerLoginService = new ManagerLoginServiceImpl();
        loadUnAssignedTicketsService = new LoadUnAssignedTicketsImpl();
        loadOwnedProductByIdService = new LoadOwnedProductByIdImpl();
        ticketSetManagerIdService = new TicketSetManagerIdImpl();
        managerLoadIdByEmailService = new ManagerLoadIdByEmailImpl();
        ownedProductLoadModelByIdService = new OwnedProductLoadModelByIdImpl();
        loadManagedTicketsService = new LoadManagedTicketsImpl();

    }

    public LoadUserTicketsByEmailService getLoadUserTicketsByEmailService() {
        return loadUserTicketsByEmailService;
    }

    public OwnedProductLoadModelByIdService getOwnedProductLoadModelByIdService() {
        return ownedProductLoadModelByIdService;
    }

    public LoadManagedTicketsService getLoadManagedTicketsService() {
        return loadManagedTicketsService;
    }

    public LoadUnAssignedTicketsService getLoadUnAssignedTicketsService() {
        return loadUnAssignedTicketsService;
    }

    public TicketSetManagerIdService getTicketSetManagerIdService() {
        return ticketSetManagerIdService;
    }

    public ManagerLoadIdByEmailService getManagerLoadIdByEmailService() {
        return managerLoadIdByEmailService;
    }

    public LoadOwnedProductByIdService getLoadOwnedProductByIdService() {
        return loadOwnedProductByIdService;
    }

    public CloseTicketService getCloseTicketService() {
        return closeTicketService;
    }

    public ManagerLoginService getManagerLoginService() {
        return managerLoginService;
    }

    public LoadUserClosedTicketsService getLoadUserClosedTicketsService() {
        return loadUserClosedTicketsService;
    }

    public LoadUserOpenedTicketsService getLoadUserOpenedTicketsService() {
        return loadUserOpenedTicketsService;
    }

    public SaveNewTicketService getSaveNewTicketService() {
        return saveNewTicketService;
    }

    public UpdateTicketService getUpdateTicketService() {
        return updateTicketService;
    }

    public LoadUserTicketsByIdService getLoadUserTicketsByIdService() {
        return loadUserTicketsByIdService;
    }

    public DeleteById getDeleteAccountById() {
        return deleteAccountById;
    }

    public UpdateAccountService getUpdateAccountService() {
        return updateAccountService;
    }

    public CheckSerialService getCheckSerialService() {
        return checkSerialService;
    }

    public LoadAccountByEmail getLoadAccountByEmail() {
        return loadAccountByEmail;
    }

    public LoadIdByEmailService getLoadIdByEmailService() {
        return loadIdByEmailService;
    }

    public SaveUserProductService getSaveProductService() {
        return saveProductService;
    }

    public LoadProductsByTypeService getLoadProductsByTypeService() {
        return loadProductsByTypeService;
    }

    public LoadAllProductsService getLoadAllProductsService() {
        return loadAllProductsService;
    }

    public LoadAllProductTypesService getLoadAllProductsTypesService() {
        return loadAllProductsTypesService;
    }

    public static ClientServicesFactory getFactory() {
        return FACTORY;
    }

    public ConfirmationCodeService getConfirmationCodeService() {
        return confirmationCodeService;
    }

    public LoadUsersProductsService getLoadUsersProductsService() {
        return loadUsersProductsService;
    }

    public ChangePasswordService getChangePasswordService() {
        return changePasswordService;
    }

    public ConfirmationService getConfirmationService() {
        return confirmationService;
    }

    public CheckAccountExistenceService getCheckAccountExistenceService() {
        return checkAccountExistenceService;
    }

    public LogInService getLogInService() {
        return logInService;
    }

    public RegistrationService getRegistrationService() {
        return registrationService;
    }

    public DeleteUserProductSevice getDeleteUserProduct() {
        return deleteUserProductSevice;
    }

    public ChangeSerialService getChangeSerialService() {
        return changeSerialService;
    }
}
