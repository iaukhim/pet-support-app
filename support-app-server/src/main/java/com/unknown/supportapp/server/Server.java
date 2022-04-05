package com.unknown.supportapp.server;

import com.fasterxml.jackson.databind.JsonNode;
import com.unknown.supportapp.server.common.ClientConnection;
import com.unknown.supportapp.server.common.Response;
import com.unknown.supportapp.server.common.ResponseFactory;
import com.unknown.supportapp.server.common.ServerSettings;
import com.unknown.supportapp.server.controllers.Controller;
import com.unknown.supportapp.server.exceptions.ServerStartFailedException;
import com.unknown.supportapp.server.spring.AppContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

@Component
public class Server {

    private ServerSettings serverSettings;

    private ServerSocket serverSocket;

    private ResponseFactory responseFactory;

    @Autowired
    public Server(ServerSettings serverSettings, ResponseFactory responseFactory) {
        this.serverSettings = serverSettings;
        this.responseFactory = responseFactory;
    }

    public Server() {
    }

    public  void runServer() {

        int serverPort = serverSettings.getServerPort();

        try {
            serverSocket = new ServerSocket(serverPort);
        } catch (IOException e) {
            throw new ServerStartFailedException(e);
        }

        ByteArrayOutputStream memoryOs = null;
        Socket clientRequest = null;
        ClientConnection clientConnection = null;
        ApplicationContext context = AppContext.getAppContext().getContext();

        while (true) {
            try {
                clientRequest = serverSocket.accept();
                clientConnection = new ClientConnection(clientRequest);
                String requestType = clientConnection.getRequestType();


                Controller controller = context.getBean(requestType, Controller.class);
                controller.process(clientConnection.getWriter(), clientConnection.getRequestBody());

            } catch (Exception e) {
                memoryOs = new ByteArrayOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(memoryOs);
                BufferedWriter writer = new BufferedWriter(osw);

                JsonNode response = responseFactory.formResponse(Response.getInternalServerErrorResponse(), e);
                try {
                    writer.write(response.toString());
                    writer.flush();
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
