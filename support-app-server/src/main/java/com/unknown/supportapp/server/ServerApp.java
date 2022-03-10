package com.unknown.supportapp.server;

import com.fasterxml.jackson.databind.JsonNode;
import com.unknown.supportapp.server.common.Response;
import com.unknown.supportapp.server.common.ResponseFactory;
import com.unknown.supportapp.server.common.ServerSettings;
import com.unknown.supportapp.server.controllers.Controller;
import com.unknown.supportapp.server.db.mysql.DbConnectionManager;
import com.unknown.supportapp.server.common.ClientConnection;
import com.unknown.supportapp.server.exceptions.ServerStartFailedException;
import com.unknown.supportapp.server.spring.AppContext;
import org.springframework.context.ApplicationContext;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {

    public static void main(String[] args) {
        DbConnectionManager.getManager();

        int serverPort = ServerSettings.getSettings().getServerPort();
        ServerSocket server = null;

        try {
            server = new ServerSocket(serverPort);
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

                ApplicationContext context = AppContext.getAppContext().getContext();
                Controller controller = context.getBean(requestType, Controller.class);
                controller.process(clientConnection.getWriter(), clientConnection.getRequestBody());

            } catch (Exception e) {
                memoryOs = new ByteArrayOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(memoryOs);
                BufferedWriter writer = new BufferedWriter(osw);

                JsonNode response = ResponseFactory.getFactory().formResponse(Response.getInternalServerErrorResponse());
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

