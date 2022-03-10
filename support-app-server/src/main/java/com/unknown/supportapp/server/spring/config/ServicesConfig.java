package com.unknown.supportapp.server.spring.config;

import com.unknown.supportapp.server.services.*;
import com.unknown.supportapp.server.services.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DaoConfig.class)
public class ServicesConfig {

    @Autowired
    private DaoConfig daoConfig;

    @Bean
    public AccountService accountService(){
        return new AccountServiceImpl(daoConfig.accountDao());
    }

    @Bean
    public ManagerService managerService(){
        return new ManagerServiceImpl(daoConfig.managerDao());
    }

    @Bean
    public OwnedProductService ownedProductService(){
        return new OwnedProductServiceImpl(daoConfig.ownedProductDao());
    }

    @Bean
    public ProductService productService(){
        return new ProductServiceImpl(daoConfig.productDao());
    }

    @Bean
    public TicketService ticketService(){
        return new TicketServiceImpl(daoConfig.ticketDao(), daoConfig.accountDao());
    }
}
