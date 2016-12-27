package com.eschronisko.database.dto;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Marek on 08.12.2016.
 */
@Entity
@Table(name = "warehouse", schema = "public", catalog = "eschronisko")
public class WarehouseDTO extends ParentDTO {
    private int id;
    private int amountLeft;
    private int capacity;
    private List<FoodRationDTO> foodRationses;

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "warehouseSeq", sequenceName = "warehouse_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "warehouseSeq")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "amount_left", nullable = false)
    public int getAmountLeft() {
        return amountLeft;
    }

    public void setAmountLeft(int amountLeft) {
        this.amountLeft = amountLeft;
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
        if (amountLeft != that.amountLeft) return false;
        if (capacity != that.capacity) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + amountLeft;
        result = 31 * result + capacity;
        return result;
    }

    @OneToMany(mappedBy = "warehouse")
    public List<FoodRationDTO> getFoodRationses() {
        return foodRationses;
    }

    public void setFoodRationses(List<FoodRationDTO> foodRationses) {
        this.foodRationses = foodRationses;
    }
}
