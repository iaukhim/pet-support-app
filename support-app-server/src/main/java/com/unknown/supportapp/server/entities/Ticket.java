package com.unknown.supportapp.server.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(schema = "pet_db", name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "starter_id")
    private int starterId;

    @Column(name = "manager_id")
    private int managerId;

    @Column(name = "product_id")
    private int productId;

    private String theme;

    private String text;

    private boolean status;

    @ManyToOne
    @JoinColumn(name = "starter_id", insertable = false, updatable = false)
    private Account starter;

    @ManyToOne
    @JoinColumn(name = "manager_id", insertable = false, updatable = false)
    private Manager manager;

    public Ticket() {
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

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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
        return "Ticket{" +
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
        Ticket ticket = (Ticket) o;
        return id == ticket.id && starterId == ticket.starterId && managerId == ticket.managerId && productId == ticket.productId && status == ticket.status && Objects.equals(theme, ticket.theme) && Objects.equals(text, ticket.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, starterId, managerId, productId, theme, text, status);
    }
}
