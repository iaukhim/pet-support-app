package com.unknown.supportapp.common.dto.product;

import java.util.Objects;

public class ProductDto {

    private int id;

    private String type;

    private String model;

    public ProductDto() {
    }

    public ProductDto(int id, String type, String model) {
        this.id = id;
        this.type = type;
        this.model = model;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDto that = (ProductDto) o;
        return id == that.id && Objects.equals(type, that.type) && Objects.equals(model, that.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, model);
    }

    @Override
    public String toString() {
        return "ProductDtoTmp{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
