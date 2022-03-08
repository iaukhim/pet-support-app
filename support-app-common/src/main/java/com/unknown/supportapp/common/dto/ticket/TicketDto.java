package com.unknown.supportapp.common.dto.ticket;

import java.util.Objects;

public class TicketDto {

    private int id;

    private int starterId;

    private int managerId;

    private int productId;

    private String theme;

    private String text;

    private boolean status;

    public TicketDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStarterId() {
        return starterId;
    }

    public void setStarterId(int starterId) {
        this.starterId = starterId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TicketDto{" +
                "id=" + id +
                ", starterId=" + starterId +
                ", managerId=" + managerId +
                ", productId=" + productId +
                ", theme='" + theme + '\'' +
                ", text='" + text + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketDto ticketDto = (TicketDto) o;
        return id == ticketDto.id && starterId == ticketDto.starterId && managerId == ticketDto.managerId && productId == ticketDto.productId && status == ticketDto.status && Objects.equals(theme, ticketDto.theme) && Objects.equals(text, ticketDto.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, starterId, managerId, productId, theme, text, status);
    }
}
