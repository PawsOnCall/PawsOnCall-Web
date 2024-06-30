package com.pawsoncall.web.domain;

import java.time.LocalDateTime;
import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

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
    private LocalDateTime birthday;
    private String photo;
    private String phoneNumber;
    private String emergencyContactName;
    private String emergencyContactPhoneNumber;
    private String paymentMethod;

    @OneToMany(mappedBy = "user")
    private Set<Review> reviews;

    public User() {}

    public User(String firstName, String lastName, String email, String phone, String country,
            String postCode, String password, String role, String provider,
            LocalDateTime birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.country = country;
        this.postCode = postCode;
        this.password = password;
        this.role = role;
        this.provider = provider;
        this.birthday = birthday;
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

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
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

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
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

    public String toString() {
        return "User(id=" + this.getId() + ", fisrtName=" + this.getFirstName() + ", lastName="
                + this.getLastName() + ", phone=" + this.getPhone() + ", country="
                + this.getCountry() + ", postCode=" + this.getPostCode() + ", email="
                + this.getEmail() + ", password=" + this.getPassword() + ", role=" + this.getRole()
                + ", provider=" + this.getProvider() + ", birthday=" + this.getBirthday()
                + ", photo=" + this.getPhoto() + ", phoneNumber=" + this.getPhoneNumber()
                + ", emergencyContactName=" + this.getEmergencyContactName()
                + ", emergencyContactPhoneNumber=" + this.getEmergencyContactPhoneNumber()
                + ", paymentMethod=" + this.getPaymentMethod() + ")";

    }
}
