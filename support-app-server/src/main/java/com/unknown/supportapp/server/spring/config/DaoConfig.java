package com.unknown.supportapp.server.spring.config;

import com.unknown.supportapp.server.dao.*;
import com.unknown.supportapp.server.dao.mysql.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoConfig {

    @Bean
    public AccountDao accountDao(){
        return new MySqlAccountDao();
    }

    @Bean
    public ManagerDao managerDao(){
        return new MySqlManagerDao();
    }

    @Bean
    public OwnedProductDao ownedProductDao(){
        return new MySqlOwnedProductDao();
    }

    @Bean
    public ProductDao productDao(){
        return new MySqlProductDao();
    }

    @Bean
    public TicketDao ticketDao(){
        return new MySqlTicketDao();
    }
}
