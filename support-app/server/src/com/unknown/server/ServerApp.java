package com.unknown.server;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.unknown.server.common.ServerSettings;
import com.unknown.server.controllers.Controller;
import com.unknown.server.controllers.ControllerFactory;
import com.unknown.server.db.mysql.DbConnectionManager;
import com.unknown.server.common.ClientConnection;
import com.unknown.server.exceptions.ServerStartFailedException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {

    public static void main(String[] args) {
        DbConnectionManager manager = DbConnectionManager.getManager();

        ServerSettings settings = ServerSettings.getSettings();
        int appPort = settings.getServerPort();

        ServerSocket server = null;
        try {
            server = new ServerSocket(appPort);
        } catch (IOException e) {
            throw new ServerStartFailedException(e);
        }

        ByteArrayOutputStream memoryOs = null;
        Socket clientRequest = null;
        ClientConnection clientConnection = null;

        while (true) {
            try {
                clientRequest = server.accept();
                clientConnection = new ClientConnection(clientRequest);
                String requestType = clientConnection.getRequestType();

                Controller controller = ControllerFactory.getFactory().getControllerByName(requestType);
                controller.process(clientConnection.getWriter(), clientConnection.getRequestBody());

            } catch (Exception e) {
                memoryOs = new ByteArrayOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(memoryOs);
                BufferedWriter writer = new BufferedWriter(osw);

                ObjectNode response = JsonNodeFactory.instance.objectNode();
                ObjectNode header = response.putObject("response-header");
                header.put("response-code", 500);
                ObjectNode body = response.putObject("body");
                body.put("response-message", e.getMessage());
                try {
                    writer.write(response.toString(), 0, response.toString().length());
                    clientConnection.setMemoryOs(memoryOs);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } finally {
                try {
                    OutputStream clientOs = clientConnection.getOutputStream();
                    clientOs.write(clientConnection.getMemoryOs().toByteArray());
                    clientOs.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

