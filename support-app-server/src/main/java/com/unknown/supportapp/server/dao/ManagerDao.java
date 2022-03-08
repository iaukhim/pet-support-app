package com.unknown.supportapp.server.dao;

import com.unknown.supportapp.server.entities.Manager;

public interface ManagerDao {

    boolean login(Manager manager);

    int loadIdByEmail(String email);
}
