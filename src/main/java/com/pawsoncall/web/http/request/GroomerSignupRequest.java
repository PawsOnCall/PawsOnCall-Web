package com.pawsoncall.web.http.request;

import java.util.Set;

import com.pawsoncall.web.domain.ServiceProvider;

import jakarta.validation.constraints.*;

public class GroomerSignupRequest {
  @NotBlank
  @Size(min = 3, max = 20)
  private String firstName;

  @NotBlank
  @Size(min = 3, max = 20)
  private String lastName;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  private Set<String> role;

  @NotBlank
  @Size(min = 6, max = 40)
  private String password;

  private String mailingAddress;
  private String experience;
  private String phoneNumber;
  private String city;
  private String postCode;
  private String serviceType;

  private int numberOfPetGroomedOverPast2Years;
  private String petType;
  private boolean gotCertification;
  private String certificationDescription;
  private String weeklyAvailability;
  private String backGround;
  private String facebookAccount;
  private String instagramAccount;

  public String getMailingAddress() {
    return mailingAddress;
  }

  public void setMailingAddress(String mailingAddress) {
    this.mailingAddress = mailingAddress;
  }

  public String getExperience() {
    return experience;
  }

  public void setExperience(String experience) {
    this.experience = experience;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getPostCode() {
    return postCode;
  }

  public void setPostCode(String postCode) {
    this.postCode = postCode;
  }

  public String getServiceType() {
    return serviceType;
  }

  public void setServiceType(String serviceType) {
    this.serviceType = serviceType;
  }

  public int getNumberOfPetGroomedOverPast2Years() {
    return numberOfPetGroomedOverPast2Years;
  }

  public void setNumberOfPetGroomedOverPast2Years(int y) {
    this.numberOfPetGroomedOverPast2Years = y;
  }

  public String getPetType() {
    return petType;
  }

  public void setPetType(String petType) {
    this.petType = petType;
  }

  public boolean isGotCertification() {
    return gotCertification;
  }

  public void setGotCertification(boolean gotCertification) {
    this.gotCertification = gotCertification;
  }

  public String getCertificationDescription() {
    return certificationDescription;
  }

  public void setCertificationDescription(String certificationDescription) {
    this.certificationDescription = certificationDescription;
  }

  public String getWeeklyAvailability() {
    return weeklyAvailability;
  }

  public void setWeeklyAvailability(String weeklyAvailability) {
    this.weeklyAvailability = weeklyAvailability;
  }

  public String getBackGround() {
    return backGround;
  }

  public void setBackGround(String backGround) {
    this.backGround = backGround;
  }

  public String getFacebookAccount() {
    return facebookAccount;
  }

  public void setFacebookAccount(String facebookAccount) {
    this.facebookAccount = facebookAccount;
  }

  public String getInstagramAccount() {
    return this.instagramAccount;
  }

  public void setInstagramAccount(String account) {
    this.instagramAccount = account;
  }

  public GroomerSignupRequest() {
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String username) {
    this.firstName = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<String> getRole() {
    return this.role;
  }

  public void setRole(Set<String> role) {
    this.role = role;
  }
}
