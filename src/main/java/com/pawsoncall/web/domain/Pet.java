package com.pawsoncall.web.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pets")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    private String name;
    private String type;
    private String breed;
    private LocalDateTime birthday;
    private String sex;
    private Long weight;
    private String microchipped;
    private String spayedNeutered;
    private String houseTrained;
    private String friendlyWithChildren;
    private String friendlyWithDogs;
    private String friendlyWithCats;
    private LocalDateTime adoptionDate;
    private String description;
    private String pottyBreakSchedule;
    private String energyLevel;
    private String feedingSchedule;
    private String canBeLeftAlone;
    private String medication;
    private String additionalInfo;
    private String veterinaryInfo;
    private String petInsuranceProvider;
    private String photo;

    public Long getPetId() {
        return id;
    }

    public void setPetId(Long petId) {
        this.id = petId;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String petName) {
        this.name = petName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public LocalDateTime getBirthday() {
        return this.birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public String getMicrochipped() {
        return microchipped;
    }

    public void setMicrochipped(String microchipped) {
        this.microchipped = microchipped;
    }

    public String getSpayedNeutered() {
        return spayedNeutered;
    }

    public void setSpayedNeutered(String spayedNeutered) {
        this.spayedNeutered = spayedNeutered;
    }

    public String getHouseTrained() {
        return houseTrained;
    }

    public void setHouseTrained(String houseTrained) {
        this.houseTrained = houseTrained;
    }

    public String getFriendlyWithChildren() {
        return friendlyWithChildren;
    }

    public void setFriendlyWithChildren(String friendlyWithChildren) {
        this.friendlyWithChildren = friendlyWithChildren;
    }

    public String getFriendlyWithDogs() {
        return friendlyWithDogs;
    }

    public void setFriendlyWithDogs(String friendlyWithDogs) {
        this.friendlyWithDogs = friendlyWithDogs;
    }

    public String getFriendlyWithCats() {
        return friendlyWithCats;
    }

    public void setFriendlyWithCats(String friendlyWithCats) {
        this.friendlyWithCats = friendlyWithCats;
    }

    public LocalDateTime getAdoptionDate() {
        return adoptionDate;
    }

    public void setAdoptionDate(LocalDateTime adoptionDate) {
        this.adoptionDate = adoptionDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPottyBreakSchedule() {
        return pottyBreakSchedule;
    }

    public void setPottyBreakSchedule(String pottyBreakSchedule) {
        this.pottyBreakSchedule = pottyBreakSchedule;
    }

    public String getEnergyLevel() {
        return energyLevel;
    }

    public void setEnergyLevel(String energyLevel) {
        this.energyLevel = energyLevel;
    }

    public String getFeedingSchedule() {
        return feedingSchedule;
    }

    public void setFeedingSchedule(String feedingSchedule) {
        this.feedingSchedule = feedingSchedule;
    }

    public String getCanBeLeftAlone() {
        return canBeLeftAlone;
    }

    public void setCanBeLeftAlone(String canBeLeftAlone) {
        this.canBeLeftAlone = canBeLeftAlone;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getVeterinaryInfo() {
        return veterinaryInfo;
    }

    public void setVeterinaryInfo(String veterinaryInfo) {
        this.veterinaryInfo = veterinaryInfo;
    }

    public String getPetInsuranceProvider() {
        return petInsuranceProvider;
    }

    public void setPetInsuranceProvider(String petInsuranceProvider) {
        this.petInsuranceProvider = petInsuranceProvider;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
