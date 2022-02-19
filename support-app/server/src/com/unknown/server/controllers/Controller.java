package com.unknown.server.controllers;

import com.fasterxml.jackson.databind.JsonNode;


import java.io.BufferedWriter;

public interface Controller {

    public  void process(BufferedWriter writer, JsonNode requestBody);

}
