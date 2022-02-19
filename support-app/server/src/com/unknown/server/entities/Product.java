package com.unknown.server.entities;

import java.util.Objects;

public class Product {

    private int id;

    private String type;

    private String model;

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", model='" + model + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product that = (Product) o;
        return id == that.id && type.equals(that.type) && model.equals(that.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, model);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Product() {
    }

    public Product(int id, String type, String model) {
        this.id = id;
        this.type = type;
        this.model = model;
    }
}
