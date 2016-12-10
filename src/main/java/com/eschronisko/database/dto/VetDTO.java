package com.eschronisko.database.dto;

import javax.persistence.*;

/**
 * Created by Marek on 08.12.2016.
 */
@Entity
@Table(name = "vet", schema = "public", catalog = "eschronisko")
public class VetDTO {
    private int id;
    private String name;
    private String surname;
    private String address;
    private AppUserDTO appUser;

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "vetSeq", sequenceName = "vet_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vetSeq")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 256)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "surname", nullable = false, length = 256)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "address", nullable = false, length = 512)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VetDTO vetDTO = (VetDTO) o;

        if (id != vetDTO.id) return false;
        if (name != null ? !name.equals(vetDTO.name) : vetDTO.name != null) return false;
        if (surname != null ? !surname.equals(vetDTO.surname) : vetDTO.surname != null) return false;
        if (address != null ? !address.equals(vetDTO.address) : vetDTO.address != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @OneToOne(mappedBy = "vet")
    public AppUserDTO getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUserDTO appUser) {
        this.appUser = appUser;
    }
}
