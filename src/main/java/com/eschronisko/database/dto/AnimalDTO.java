package com.eschronisko.database.dto;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Marek on 08.12.2016.
 */
@Entity
@Table(name = "animal", schema = "public", catalog = "eschronisko")
public class AnimalDTO extends ParentDTO {
    private int registrationNumber;
    private String name;
    private int age;
    private String sex;
    private String species;
    private String linkToImage;
    private int roomNumber;
    private int adoptionPossible;
    private Timestamp acceptanceDate;
    private Timestamp adoptionDate;
    private FoodRationsDTO foodRationses;
    private MedicalCardDTO medicalCard;
    private List<ApplicationDTO> applications;

    @Id
    @Column(name = "registration_number", nullable = false)
    @SequenceGenerator(name = "animalSeq", sequenceName = "animal_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "animalSeq")
    public int getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(int registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 128)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "age", nullable = false)
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Basic
    @Column(name = "sex", nullable = false, length = 32)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "species", nullable = false, length = 64)
    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    @Basic
    @Column(name = "link_to_image", nullable = true, length = 256)
    public String getLinkToImage() {
        return linkToImage;
    }

    public void setLinkToImage(String linkToImage) {
        this.linkToImage = linkToImage;
    }

    @Basic
    @Column(name = "room_number", nullable = false)
    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Basic
    @Column(name = "adoption_possible", nullable = false)
    public int getAdoptionPossible() {
        return adoptionPossible;
    }

    public void setAdoptionPossible(int adoptionPossible) {
        this.adoptionPossible = adoptionPossible;
    }

    @Basic
    @Column(name = "acceptance_date", nullable = false)
    public Timestamp getAcceptanceDate() {
        return acceptanceDate;
    }

    public void setAcceptanceDate(Timestamp acceptanceDate) {
        this.acceptanceDate = acceptanceDate;
    }

    @Basic
    @Column(name = "adoption_date", nullable = true)
    public Timestamp getAdoptionDate() {
        return adoptionDate;
    }

    public void setAdoptionDate(Timestamp adoptionDate) {
        this.adoptionDate = adoptionDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnimalDTO animalDTO = (AnimalDTO) o;

        if (registrationNumber != animalDTO.registrationNumber) return false;
        if (age != animalDTO.age) return false;
        if (roomNumber != animalDTO.roomNumber) return false;
        if (adoptionPossible != animalDTO.adoptionPossible) return false;
        if (name != null ? !name.equals(animalDTO.name) : animalDTO.name != null) return false;
        if (sex != null ? !sex.equals(animalDTO.sex) : animalDTO.sex != null) return false;
        if (species != null ? !species.equals(animalDTO.species) : animalDTO.species != null) return false;
        if (acceptanceDate != null ? !acceptanceDate.equals(animalDTO.acceptanceDate) : animalDTO.acceptanceDate != null)
            return false;
        if (adoptionDate != null ? !adoptionDate.equals(animalDTO.adoptionDate) : animalDTO.adoptionDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = registrationNumber;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (species != null ? species.hashCode() : 0);
        result = 31 * result + roomNumber;
        result = 31 * result + adoptionPossible;
        result = 31 * result + (acceptanceDate != null ? acceptanceDate.hashCode() : 0);
        result = 31 * result + (adoptionDate != null ? adoptionDate.hashCode() : 0);
        return result;
    }

    @OneToOne(mappedBy = "animal")
    public FoodRationsDTO getFoodRationses() {
        return foodRationses;
    }

    public void setFoodRationses(FoodRationsDTO foodRationses) {
        this.foodRationses = foodRationses;
    }

    @OneToOne(mappedBy = "animal")
    public MedicalCardDTO getMedicalCard() {
        return medicalCard;
    }

    public void setMedicalCard(MedicalCardDTO medicalCard) {
        this.medicalCard = medicalCard;
    }

    @OneToMany(mappedBy = "client")
    public List<ApplicationDTO> getApplications() {
        return applications;
    }

    public void setApplications(List<ApplicationDTO> applications) {
        this.applications = applications;
    }

}
