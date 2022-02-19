package com.unknown.supportapp.client.common.net;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.Socket;

public class ServerConnection {

    private Socket connection;

    private BufferedWriter bufferedWriter;

    private BufferedReader reader;

    private String response;

    private ObjectMapper objectMapper;

    public ServerConnection() {
        String serverIp = ClientSettings.getSettings().getServerIp();
        int serverPort = ClientSettings.getSettings().getServerPort();
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            this.connection = new Socket(serverIp, serverPort);
            OutputStream os = connection.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            bufferedWriter = new BufferedWriter(osw);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void flush() {
        try {
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.response = null;
    }

    public BufferedWriter getBufferedWriter() {
        return bufferedWriter;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public String getResponse() {
        try {
            InputStream is = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            reader = new BufferedReader(inputStreamReader);
            char[] chars = new char[3000];
            reader.read(chars);
            response = new String(chars);
            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public JsonNode getResponseHeader() {
        if (response == null) {
            getResponse();
        }
        JsonNode rootNode = null;
        try {
            rootNode = objectMapper.readTree(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return rootNode.get("response-header");

    }

    public JsonNode getResponseBody() {
        if (response == null) {
            getResponse();
        }
        JsonNode rootNode = null;
        try {
            rootNode = objectMapper.readTree(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return rootNode.get("response-body");
    }

    public void writeRequest(JsonNode request){
        try {
            bufferedWriter.write(request.toString());
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        try {
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
