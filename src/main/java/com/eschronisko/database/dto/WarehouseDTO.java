package com.eschronisko.database.dto;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by Marek on 08.12.2016.
 */
@Entity
@Table(name = "warehouse", schema = "public", catalog = "eschronisko")
public class WarehouseDTO {
    private int id;
    private int amoutLeft;
    private int capacity;
    private List<FoodRationsDTO> foodRationses;

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "warehouseSeq", sequenceName = "warehouse_id_seq", allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="warehouseSeq")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "amout_left", nullable = false)
    public int getAmoutLeft() {
        return amoutLeft;
    }

    public void setAmoutLeft(int amoutLeft) {
        this.amoutLeft = amoutLeft;
    }

    @Basic
    @Column(name = "capacity", nullable = false)
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WarehouseDTO that = (WarehouseDTO) o;

        if (id != that.id) return false;
        if (amoutLeft != that.amoutLeft) return false;
        if (capacity != that.capacity) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + amoutLeft;
        result = 31 * result + capacity;
        return result;
    }

    @OneToMany(mappedBy = "warehouse")
    public List<FoodRationsDTO> getFoodRationses() {
        return foodRationses;
    }

    public void setFoodRationses(List<FoodRationsDTO> foodRationses) {
        this.foodRationses = foodRationses;
    }
}
