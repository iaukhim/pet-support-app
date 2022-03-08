package com.unknown.supportapp.server.dao.mysql;

import com.unknown.supportapp.server.dao.TicketDao;
import com.unknown.supportapp.server.entities.Ticket;
import com.unknown.supportapp.server.db.mysql.DbConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlTicketDao implements TicketDao {

    public MySqlTicketDao() {
    }

    private final String LOAD_ALL_TICKETS = "SELECT * FROM pet_db.tickets";
    private final String LOAD_USER_TICKETS_BY_ID = "SELECT * FROM pet_db.tickets WHERE `starter_id` = ?";
    private final String SAVE_TICKET = "INSERT INTO pet_db.tickets(`status`, `theme`, `text`, `starter_id`, `product_id`) VALUES (?, ?, ?, ?, ?)";
    private final String UPDATE_TICKET = "UPDATE pet_db.tickets SET `status` = ?, `product_id` = ?, `theme` = ?, `text` = ?, `starter_id` = ?, `manager_id` = ?  WHERE `id` = ?";
    private final String LOAD_USER_OPENED_TICKETS = "SELECT * FROM pet_db.tickets WHERE `starter_id` = ? AND `status` = true";
    private final String LOAD_USER_CLOSED_TICKETS = "SELECT * FROM pet_db.tickets WHERE `starter_id` = ? AND `status` = false";
    private final String LOAD_UNASSIGNED_TICKETS = "SELECT * FROM pet_db.tickets WHERE `manager_id` = 0 AND `status` = true";
    private final String LOAD_MANAGED_TICKETS = "SELECT * FROM pet_db.tickets WHERE `manager_id` = ? AND `status` = true";
    private final String SET_MANAGER_ID = "UPDATE pet_db.tickets SET `manager_id` = ?  WHERE `id` = ?";
    private final String CLOSE_TICKET = "UPDATE pet_db.tickets SET `status` = false WHERE `id` = ?";

    @Override
    public List<Ticket> loadAll() {
        Connection connection = DbConnectionManager.getManager().getConnection();

        List<Ticket> tickets = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(LOAD_ALL_TICKETS);
            preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int starterId = resultSet.getInt("starter_id");
                int managerId = resultSet.getInt("manager_id");
                boolean status = resultSet.getBoolean("status");
                String theme = resultSet.getString("theme");
                String text = resultSet.getString("text");
                int productId = resultSet.getInt("product_id");

                Ticket ticket = new Ticket();
                ticket.setId(id);
                ticket.setManagerId(managerId);
                ticket.setStatus(status);
                ticket.setTheme(theme);
                ticket.setText(text);
                ticket.setProductId(productId);

                tickets.add(ticket);
            }
        } catch (SQLException e) {
            throw new RuntimeException("SQL exception in AccountDao", e);
        }

        DbConnectionManager.getManager().closeDbResources(connection, preparedStatement, resultSet);
        return tickets;
    }

    @Override
    public List<Ticket> loadUserTickets(int userId) {
        Connection connection = DbConnectionManager.getManager().getConnection();

        List<Ticket> tickets = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(LOAD_USER_TICKETS_BY_ID);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int starterId = resultSet.getInt("starter_id");
                int managerId = resultSet.getInt("manager_id");
                int productId = resultSet.getInt("product_id");
                boolean status = resultSet.getBoolean("status");
                String theme = resultSet.getString("theme");
                String text = resultSet.getString("text");

                Ticket ticket = new Ticket();
                ticket.setId(id);
                ticket.setManagerId(managerId);
                ticket.setStatus(status);
                ticket.setTheme(theme);
                ticket.setText(text);
                ticket.setStarterId(starterId);
                ticket.setProductId(productId);

                tickets.add(ticket);
            }
        } catch (SQLException e) {
            throw new RuntimeException("SQL exception in AccountDao", e);
        }

        DbConnectionManager.getManager().closeDbResources(connection, preparedStatement, resultSet);
        return tickets;
    }

    @Override
    public void save(Ticket ticket) {
        Connection connection = DbConnectionManager.getManager().getConnection();

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(SAVE_TICKET);
            preparedStatement.setBoolean(1, ticket.getStatus());
            preparedStatement.setString(2, ticket.getTheme());
            preparedStatement.setString(3, ticket.getText());
            preparedStatement.setInt(4, ticket.getStarterId());
            preparedStatement.setInt(5, ticket.getProductId());
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException("SQL exception in AccountDao", e);
        }

        DbConnectionManager.getManager().closeDbResources(connection, preparedStatement);
    }

    @Override
    public void update(Ticket ticket) {
        Connection connection = DbConnectionManager.getManager().getConnection();

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(UPDATE_TICKET);

            preparedStatement.setBoolean(1, ticket.getStatus());
            preparedStatement.setInt(2, ticket.getProductId());
            preparedStatement.setString(3, ticket.getTheme());
            preparedStatement.setString(4, ticket.getText());
            preparedStatement.setInt(5, ticket.getStarterId());
            preparedStatement.setInt(6, ticket.getManagerId());
            preparedStatement.setInt(7, ticket.getId());
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException("SQL exception in AccountDao", e);
        }

        DbConnectionManager.getManager().closeDbResources(connection, preparedStatement);
    }

    @Override
    public List<Ticket> loadUserOpenedTickets(int userId) {
        Connection connection = DbConnectionManager.getManager().getConnection();

        List<Ticket> tickets = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(LOAD_USER_OPENED_TICKETS);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int starterId = resultSet.getInt("starter_id");
                int managerId = resultSet.getInt("manager_id");
                int productId = resultSet.getInt("product_id");
                boolean status = resultSet.getBoolean("status");
                String theme = resultSet.getString("theme");
                String text = resultSet.getString("text");

                Ticket ticket = new Ticket();
                ticket.setId(id);
                ticket.setManagerId(managerId);
                ticket.setStatus(status);
                ticket.setTheme(theme);
                ticket.setText(text);
                ticket.setStarterId(starterId);
                ticket.setProductId(productId);

                tickets.add(ticket);
            }
        } catch (SQLException e) {
            throw new RuntimeException("SQL exception in AccountDao", e);
        }

        DbConnectionManager.getManager().closeDbResources(connection, preparedStatement, resultSet);
        return tickets;
    }

    @Override
    public List<Ticket> loadUserClosedTickets(int userId) {
        Connection connection = DbConnectionManager.getManager().getConnection();

        List<Ticket> tickets = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(LOAD_USER_CLOSED_TICKETS);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int starterId = resultSet.getInt("starter_id");
                int managerId = resultSet.getInt("manager_id");
                int productId = resultSet.getInt("product_id");
                boolean status = resultSet.getBoolean("status");
                String theme = resultSet.getString("theme");
                String text = resultSet.getString("text");

                Ticket ticket = new Ticket();
                ticket.setId(id);
                ticket.setManagerId(managerId);
                ticket.setStatus(status);
                ticket.setTheme(theme);
                ticket.setText(text);
                ticket.setStarterId(starterId);
                ticket.setProductId(productId);

                tickets.add(ticket);
            }
        } catch (SQLException e) {
            throw new RuntimeException("SQL exception in AccountDao", e);
        }

        DbConnectionManager.getManager().closeDbResources(connection, preparedStatement, resultSet);
        return tickets;
    }

    @Override
    public List<Ticket> loadUnAssignedTickets() {
        Connection connection = DbConnectionManager.getManager().getConnection();

        List<Ticket> tickets = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(LOAD_UNASSIGNED_TICKETS);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int starterId = resultSet.getInt("starter_id");
                int managerId = resultSet.getInt("manager_id");
                int productId = resultSet.getInt("product_id");
                boolean status = resultSet.getBoolean("status");
                String theme = resultSet.getString("theme");
                String text = resultSet.getString("text");

                Ticket ticket = new Ticket();
                ticket.setId(id);
                ticket.setManagerId(managerId);
                ticket.setStatus(status);
                ticket.setTheme(theme);
                ticket.setText(text);
                ticket.setStarterId(starterId);
                ticket.setProductId(productId);

                tickets.add(ticket);
            }
        } catch (SQLException e) {
            throw new RuntimeException("SQL exception in AccountDao", e);
        }

        DbConnectionManager.getManager().closeDbResources(connection, preparedStatement, resultSet);
        return tickets;
    }

    @Override
    public List<Ticket> loadManagedTickets(int managerId) {
        Connection connection = DbConnectionManager.getManager().getConnection();

        List<Ticket> tickets = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(LOAD_MANAGED_TICKETS);
            preparedStatement.setInt(1, managerId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int starterId = resultSet.getInt("starter_id");
                int productId = resultSet.getInt("product_id");
                boolean status = resultSet.getBoolean("status");
                String theme = resultSet.getString("theme");
                String text = resultSet.getString("text");

                Ticket ticket = new Ticket();
                ticket.setId(id);
                ticket.setManagerId(managerId);
                ticket.setStatus(status);
                ticket.setTheme(theme);
                ticket.setText(text);
                ticket.setStarterId(starterId);
                ticket.setProductId(productId);

                tickets.add(ticket);
            }
        } catch (SQLException e) {
            throw new RuntimeException("SQL exception in AccountDao", e);
        }

        DbConnectionManager.getManager().closeDbResources(connection, preparedStatement, resultSet);
        return tickets;
    }

    @Override
    public void setManagerId(Ticket ticket) {
        Connection connection = DbConnectionManager.getManager().getConnection();

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(SET_MANAGER_ID);

            preparedStatement.setInt(1, ticket.getManagerId());
            preparedStatement.setInt(2, ticket.getId());
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException("SQL exception in AccountDao", e);
        }

        DbConnectionManager.getManager().closeDbResources(connection, preparedStatement);
    }

    @Override
    public void closeTicket(int id) {
        Connection connection = DbConnectionManager.getManager().getConnection();

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(CLOSE_TICKET);

            preparedStatement.setInt(1, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException("SQL exception in AccountDao", e);
        }

        DbConnectionManager.getManager().closeDbResources(connection, preparedStatement);
    }
}
