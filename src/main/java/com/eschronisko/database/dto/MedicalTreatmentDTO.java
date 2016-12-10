package com.eschronisko.database.dto;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Marek on 08.12.2016.
 */
@Entity
@Table(name = "medical_treatment", schema = "public", catalog = "eschronisko")
public class MedicalTreatmentDTO {
    private int id;
    private String name;
    private String description;
    private int importance;
    private Timestamp startDate;
    private Timestamp endDate;
    private MedicalCardDTO medicalCard;

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "medtreatSeq", sequenceName = "medical_treatment_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medtreatSeq")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 64)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 256)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "importance", nullable = false)
    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    @Basic
    @Column(name = "start_date", nullable = false)
    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "end_date", nullable = true)
    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MedicalTreatmentDTO that = (MedicalTreatmentDTO) o;

        if (id != that.id) return false;
        if (importance != that.importance) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + importance;
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "medical_card_id", referencedColumnName = "id", nullable = false)
    public MedicalCardDTO getMedicalCard() {
        return medicalCard;
    }

    public void setMedicalCard(MedicalCardDTO medicalCard) {
        this.medicalCard = medicalCard;
    }
}
