package com.unknown.supportapp.server.common;

public class Response {

    private int code;

    private String message;

    private Response(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }

    public static Response getOkResponse(){
        return new Response(200, "OK");
    }

    public static Response getContinueResponse(){
        return new Response(100, "Continue");
    }

    public static Response getProcessingResponse(){
        return new Response(102, "Processing");
    }

    public static Response getAcceptedResponse(){
        return new Response(202, "Accepted");
    }

    public static Response getBadRequestResponse(){
        return new Response(400, "Bad Request");
    }

    public static Response getUnauthorizedResponse(){
        return new Response(401, "Unauthorized");
    }

    public static Response getForbiddenResponse(){
        return new Response(403, "Forbidden");
    }

    public static Response getNotFoundResponse(){
        return new Response(404, "NotFound");
    }

    public static Response getRequestTimeoutResponse(){
        return new Response(408, "RequestTimeout");
    }

    public static Response getInternalServerErrorResponse(){
        return new Response(500, "InternalServerError");
    }
}
