package com.unknown.server.dao;

import com.unknown.server.entities.Manager;

public interface ManagerDao {

    boolean login(Manager manager);

    int loadIdByEmail(String email);
}
