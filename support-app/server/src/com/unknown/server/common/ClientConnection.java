package com.unknown.server.common;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.io.*;
import java.net.Socket;

public class ClientConnection {

    private Socket connection;
    private BufferedReader reader;
    private BufferedWriter writer;
    private ByteArrayOutputStream memoryOs;
    private OutputStreamWriter osw;
    private String jsonRequest;

    public ClientConnection(Socket connection) {
        this.connection = connection;
        try {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        memoryOs = new ByteArrayOutputStream();
        osw = new OutputStreamWriter(memoryOs);
        writer = new BufferedWriter(osw);

    }

    public OutputStream getOutputStream(){
        try {
            return connection.getOutputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getJsonRequest() {
        if(jsonRequest != null){
            return jsonRequest;
        }
        JsonMapper jsonMapper = new JsonMapper();
        JsonNode rootNode = null;

        try {
            char[] chars = new char[3000];
            reader.read(chars);
            rootNode = jsonMapper.readTree(new String(chars));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String request = rootNode.toString();
        jsonRequest = request;
        return jsonRequest;
    }

    public BufferedWriter getWriter() {
        return writer;
    }

    public ByteArrayOutputStream getMemoryOs() {
        return memoryOs;
    }

    public void setMemoryOs(ByteArrayOutputStream memoryOs) {
        this.memoryOs = memoryOs;
    }

    public String getRequestType(){
        String jsonRequest = getJsonRequest();

        JsonMapper jsonMapper = new JsonMapper();
        JsonNode rootNode = null;
        try {
            rootNode = jsonMapper.readTree(jsonRequest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JsonNode head = rootNode.get("request-header");
        String request = head.get("request-type").asText();
        return request;
    }

    public JsonNode getRequestBody(){
        JsonMapper jsonMapper = new JsonMapper();

        JsonNode rootNode = null;
        try {
            rootNode = jsonMapper.readTree(jsonRequest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JsonNode requestBody = rootNode.get("request-body");
        return requestBody;
    }

    public void flush(){
        try {
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
