package com.eschronisko.database.dto;

import javax.persistence.*;

/**
 * Created by Marek on 08.12.2016.
 */
@Entity
@Table(name = "food_rations", schema = "public", catalog = "eschronisko")
public class FoodRationsDTO extends ParentDTO {
    private int id;
    private int amount;
    private WarehouseDTO warehouse;
    private AnimalDTO animal;

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "foodRationSeq", sequenceName = "food_rations_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "foodRationSeq")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "amount", nullable = false)
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FoodRationsDTO that = (FoodRationsDTO) o;

        if (id != that.id) return false;
        if (amount != that.amount) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + amount;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id", nullable = false)
    public WarehouseDTO getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(WarehouseDTO warehouse) {
        this.warehouse = warehouse;
    }

    @OneToOne
    @JoinColumn(name = "animal_id", referencedColumnName = "registration_number", nullable = false)
    public AnimalDTO getAnimal() {
        return animal;
    }

    public void setAnimal(AnimalDTO animal) {
        this.animal = animal;
    }
}
