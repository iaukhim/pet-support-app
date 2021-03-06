package com.unknown.supportapp.server.spring.config;

import com.unknown.supportapp.server.dao.*;
import com.unknown.supportapp.server.dao.mysql.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.persistence.EntityManagerFactory;

@Configuration
@Import(JpaConfig.class)
public class DaoConfig {

    @Autowired
    private EntityManagerFactory emf;

    @Bean
    public AccountDao accountDao(){
        return new MySqlAccountDao(emf.createEntityManager());
    }

    @Bean
    public ManagerDao managerDao(){
        return new MySqlManagerDao(emf.createEntityManager());
    }

    @Bean
    public OwnedProductDao ownedProductDao(){
        return new MySqlOwnedProductDao(emf.createEntityManager());
    }

    @Bean
    public ProductDao productDao(){
        return new MySqlProductDao(emf.createEntityManager());
    }

    @Bean
    public TicketDao ticketDao(){
        return new MySqlTicketDao(emf.createEntityManager());
    }
}
