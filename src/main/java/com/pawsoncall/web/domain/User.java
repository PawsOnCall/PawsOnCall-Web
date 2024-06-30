package com.pawsoncall.web.domain;

import java.time.Instant;
import java.util.Date;
import java.util.Set;
import org.springframework.data.annotation.CreatedDate;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String phone;
    private String country;
    private String postCode;
    private String provider;
    private String password;
    private String role;
    private Date birthday;
    private String photo;
    private String phoneNumber;
    private String emergencyContactName;
    private String emergencyContactPhoneNumber;
    private String paymentMethod;
    private String metaData;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.LAZY)
    private ServiceProvider serviceProvider;

    public User() {}

    public void setServiceProvider(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public ServiceProvider getServiceProvider() {
        return serviceProvider;
    }

    public void addServiceProvider(ServiceProvider svcProvider) {
        svcProvider.setUser(this);
        this.serviceProvider = svcProvider;
    }

    public void removeServiceProvider() {
        if (serviceProvider != null) {
            serviceProvider.setUser(null);
            this.serviceProvider = null;
        }
    }

    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }

    public String getMetaData() {
        return metaData;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public String getEmergencyContactPhoneNumber() {
        return emergencyContactPhoneNumber;
    }

    public void setEmergencyContactPhoneNumber(String emergencyContactPhoneNumber) {
        this.emergencyContactPhoneNumber = emergencyContactPhoneNumber;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    // public String toString() {
    // return "User(id=" + this.getId() + ", fisrtName=" + this.getFirstName() + ", lastName="
    // + this.getLastName() + ", phone=" + this.getPhone() + ", country="
    // + this.getCountry() + ", postCode=" + this.getPostCode() + ", email="
    // + this.getEmail() + ", password=" + this.getPassword() + ", role=" + this.getRole()
    // + ", provider=" + this.getProvider() + ", birthday=" + this.getBirthday()
    // + ", photo=" + this.getPhoto() + ", phoneNumber=" + this.getPhoneNumber()
    // + ", emergencyContactName=" + this.getEmergencyContactName()
    // + ", emergencyContactPhoneNumber=" + this.getEmergencyContactPhoneNumber()
    // + ", paymentMethod=" + this.getPaymentMethod() + ")";

    // }
}
