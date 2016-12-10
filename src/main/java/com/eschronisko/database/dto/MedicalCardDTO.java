package com.eschronisko.database.dto;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Marek on 08.12.2016.
 */
@Entity
@Table(name = "medical_card", schema = "public", catalog = "eschronisko")
public class MedicalCardDTO extends ParentDTO {
    private int id;
    private String healthState;
    private String notes;
    private AnimalDTO animal;
    private List<MedicalTreatmentDTO> medicalTreatments;

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "medcardSeq", sequenceName = "medical_card_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medcardSeq")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "health_state", nullable = false, length = 32)
    public String getHealthState() {
        return healthState;
    }

    public void setHealthState(String healthState) {
        this.healthState = healthState;
    }

    @Basic
    @Column(name = "notes", nullable = true, length = 512)
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MedicalCardDTO that = (MedicalCardDTO) o;

        if (id != that.id) return false;
        if (healthState != null ? !healthState.equals(that.healthState) : that.healthState != null) return false;
        if (notes != null ? !notes.equals(that.notes) : that.notes != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (healthState != null ? healthState.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "animal_id", referencedColumnName = "registration_number", nullable = false)
    public AnimalDTO getAnimal() {
        return animal;
    }

    public void setAnimal(AnimalDTO animal) {
        this.animal = animal;
    }

    @OneToMany(mappedBy = "medicalCard")
    public List<MedicalTreatmentDTO> getMedicalTreatments() {
        return medicalTreatments;
    }

    public void setMedicalTreatments(List<MedicalTreatmentDTO> medicalTreatments) {
        this.medicalTreatments = medicalTreatments;
    }
}
