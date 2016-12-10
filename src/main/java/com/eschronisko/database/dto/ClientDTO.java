package com.eschronisko.database.dto;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Marek on 08.12.2016.
 */
@Entity
@Table(name = "client", schema = "public", catalog = "eschronisko")
public class ClientDTO {
    private int id;
    private String name;
    private String surname;
    private String address;
    private AppUserDTO appUser;
    private List<ApplicationDTO> applications;
    private List<DonationDTO> donations;

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "clientSeq", sequenceName = "client_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clientSeq")
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

        ClientDTO clientDTO = (ClientDTO) o;

        if (id != clientDTO.id) return false;
        if (name != null ? !name.equals(clientDTO.name) : clientDTO.name != null) return false;
        if (surname != null ? !surname.equals(clientDTO.surname) : clientDTO.surname != null) return false;
        if (address != null ? !address.equals(clientDTO.address) : clientDTO.address != null) return false;

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

    @OneToOne(mappedBy = "client")
    public AppUserDTO getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUserDTO appUser) {
        this.appUser = appUser;
    }

    @OneToMany(mappedBy = "client")
    public List<ApplicationDTO> getApplications() {
        return applications;
    }

    public void setApplications(List<ApplicationDTO> applications) {
        this.applications = applications;
    }

    @OneToMany(mappedBy = "client")
    public List<DonationDTO> getDonations() {
        return donations;
    }

    public void setDonations(List<DonationDTO> donations) {
        this.donations = donations;
    }
}
