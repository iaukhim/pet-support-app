package com.unknown.supportapp.server.entities;

import java.util.Objects;

public class OwnedProduct {

    private int id;

    private int ownerId;

    private String type;

    private String model;

    private String serialNumber;


    public OwnedProduct() {
    }

    public OwnedProduct(int id, int ownerId, String type, String model, String serialNumber) {
        this.id = id;
        this.ownerId = ownerId;
        this.type = type;
        this.model = model;
        this.serialNumber = serialNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
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

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnedProduct that = (OwnedProduct) o;
        return id == that.id && ownerId == that.ownerId && Objects.equals(type, that.type) && Objects.equals(model, that.model) && Objects.equals(serialNumber, that.serialNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ownerId, type, model, serialNumber);
    }

    @Override
    public String toString() {
        return "OwnedProduct{" +
                "id=" + id +
                ", ownerId=" + ownerId +
                ", type='" + type + '\'' +
                ", model='" + model + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                '}';
    }
}
