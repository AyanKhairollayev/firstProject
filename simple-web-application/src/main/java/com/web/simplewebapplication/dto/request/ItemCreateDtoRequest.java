package com.web.simplewebapplication.dto.request;

public class ItemCreateDtoRequest {
    private String name;
    private Double price;
    private String description;

    public ItemCreateDtoRequest(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
