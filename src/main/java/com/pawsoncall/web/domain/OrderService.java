package com.pawsoncall.web.domain;

import java.util.Objects;
import jakarta.persistence.*;

@Entity(name = "order_services")
public class OrderService {
    @Id
    @ManyToOne
    private Order order;

    @Id
    @ManyToOne
    private Service service;

    OrderService() {}

    OrderService(Order order, Service service) {
        this.order = order;
        this.service = service;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderService that = (OrderService) o;
        return Objects.equals(order, that.order) && Objects.equals(service, that.service);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, service);
    }
}
