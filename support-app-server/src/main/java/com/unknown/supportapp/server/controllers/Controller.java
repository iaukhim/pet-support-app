package com.unknown.supportapp.server.controllers;

import com.fasterxml.jackson.databind.JsonNode;


import java.io.BufferedWriter;

public interface Controller {

    void process(BufferedWriter writer, JsonNode requestBody);

}
