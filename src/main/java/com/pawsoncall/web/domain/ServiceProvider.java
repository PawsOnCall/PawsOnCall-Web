package com.pawsoncall.web.domain;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "service_providers")
public class ServiceProvider extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "USER_ID_FK"))
    private User user;

    private String serviceType; // Groomer, Bather
    private String experience;
    private Integer numberOfPetGroomedOverPast2Years;
    private String petType;
    private Boolean gotCertification;
    private String certificationDescription;
    private Boolean availableDoGroomingTrial;
    private Boolean ownGroomingTools;
    private String weeklyAvailability;
    private String backGround;
    private String facebookAccount;
    private String instagramAccount;

    private String metaData;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }

    public String getMetaData() {
        return metaData;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public Integer getNumberOfPetGroomedOverPast2Years() {
        return numberOfPetGroomedOverPast2Years;
    }

    public void setNumberOfPetGroomedOverPast2Years(Integer numberOfPetGroomedOverPast2Years) {
        this.numberOfPetGroomedOverPast2Years = numberOfPetGroomedOverPast2Years;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public Boolean getGotCertification() {
        return gotCertification;
    }

    public void setGotCertification(Boolean gotCertification) {
        this.gotCertification = gotCertification;
    }

    public String getCertificationDescription() {
        return certificationDescription;
    }

    public void setCertificationDescription(String certificationDescription) {
        this.certificationDescription = certificationDescription;
    }

    public Boolean getAvailableDoGroomingTrial() {
        return availableDoGroomingTrial;
    }

    public void setAvailableDoGroomingTrial(Boolean availableDoGroomingTrial) {
        this.availableDoGroomingTrial = availableDoGroomingTrial;
    }

    public Boolean getOwnGroomingTools() {
        return ownGroomingTools;
    }

    public void setOwnGroomingTools(Boolean ownGroomingTools) {
        this.ownGroomingTools = ownGroomingTools;
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

    public void setBackGround(String whyLoveGrooming) {
        this.backGround = whyLoveGrooming;
    }

    public String getFacebookAccount() {
        return facebookAccount;
    }

    public void setFacebookAccount(String facebookAccount) {
        this.facebookAccount = facebookAccount;
    }

    public String getInstagramAccount() {
        return instagramAccount;
    }

    public void setInstagramAccount(String instagramAccount) {
        this.instagramAccount = instagramAccount;
    }
}
