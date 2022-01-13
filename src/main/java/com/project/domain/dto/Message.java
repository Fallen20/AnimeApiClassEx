package com.project.domain.dto;

public class Message {

    public String mensaje;

    public static Message message(String mensaje) {
        Message message = new Message();
        message.mensaje = mensaje;
        return message;
    }
}
