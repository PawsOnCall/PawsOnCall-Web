package com.pawsoncall.web.domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NaturalId
    private String orderNumber;

    @ManyToOne
    @JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "CUSTOMER_ID_FK"))
    private User customer;

    @ManyToOne
    @JoinColumn(name = "provider_id", foreignKey = @ForeignKey(name = "PROVIDER_ID_FK"))
    private User provider;

    @ManyToOne
    @JoinColumn(name = "pet_id", foreignKey = @ForeignKey(name = "PET_ID_FK"))
    private Pet pet;

    private String status;
    private String notes;
    private String metaData;

    // manyToMany way borrow from
    // https://docs.jboss.org/hibernate/orm/6.0/userguide/html_single/Hibernate_User_Guide.html#associations-many-to-many-bidirectional-with-link-entity
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderService> services = new ArrayList<>();

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.LAZY)
    private Payment payment;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.LAZY)
    private Review review;

    public Order() {}

    public void setReview(Review review) {
        this.review = review;
    }

    public Review getReview() {
        return review;
    }

    public void addReview(Review review) {
        review.setOrder(this);
        this.review = review;
    }

    public void removeReview() {
        if (review != null) {
            review.setOrder(null);
            this.review = null;
        }
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Payment getPayment() {
        return payment;
    }

    public void addPayment(Payment payment) {
        payment.setOrder(this);
        this.payment = payment;
    }

    public void removePayment() {
        if (payment != null) {
            payment.setOrder(null);
            this.payment = null;
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }

    public String getMetaData() {
        return metaData;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User user) {
        this.customer = user;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public User getProvider() {
        return provider;
    }

    public void setProvider(User provider) {
        this.provider = provider;
    }

    public List<OrderService> getServices() {
        return services;
    }

    public void setServices(List<OrderService> services) {
        this.services = services;
    }

    public void addService(Service svc) {
        OrderService orderService = new OrderService(this, svc);
        services.add(orderService);
        svc.getOrders().add(orderService);
    }

    public void removeService(Service svc) {
        OrderService orderService = new OrderService(this, svc);
        services.remove(orderService);
        svc.getOrders().remove(orderService);
        orderService.setOrder(null);
        orderService.setService(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        return Objects.equals(orderNumber, order.orderNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNumber);
    }
}
