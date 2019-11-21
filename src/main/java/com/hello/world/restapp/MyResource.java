package com.hello.world.restapp;

import com.google.gson.Gson;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.server.resources.CoapExchange;

public class MyResource extends CoapResource {
    public MyResource(String name) {
        super(name);
    }

    @Override
    public void handleGET(CoapExchange exchange) {
        Book book = new Book("bla", 90);
        String jsonBook = new Gson().toJson(book);
        exchange.respond(ResponseCode.CONTENT, jsonBook, MediaTypeRegistry.APPLICATION_JSON);
    }

    @Override
    public void handlePOST(CoapExchange exchange) {
        exchange.accept();
        System.out.println(exchange.getRequestOptions().getContentFormat());
        if (exchange.getRequestOptions().getContentFormat() == MediaTypeRegistry.APPLICATION_JSON) {
            Book book = new Gson().fromJson(exchange.getRequestText(), Book.class);
            System.out.println(book.getTitle());
        }

        System.out.println(exchange.getRequestOptions());

        exchange.respond(ResponseCode.CREATED);
    }
}
