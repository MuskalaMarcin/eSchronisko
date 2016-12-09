package com.eschronisko.database.dto;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Created by Marek on 08.12.2016.
 */
@Entity
@Table(name = "app_user", schema = "public", catalog = "eschronisko")
public class AppUserDTO {
    private String login;
    private String password;
    private String eMail;
    private int isActive;
    private String userRole;
    private ClientDTO client;
    private VetDTO vet;
    private AnimalKeeperDTO animalKeeper;
    private AdministratorDTO administrator;

    @Id
    @Column(name = "login", nullable = false, length = 128)
    @SequenceGenerator(name = "appuserSeq", sequenceName = "app_user_id_seq", allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="appuserSeq")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 256)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "e_mail", nullable = false, length = 128)
    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    @Basic
    @Column(name = "is_active", nullable = false)
    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    @Basic
    @Column(name = "user_role", nullable = false, length = 64)
    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppUserDTO that = (AppUserDTO) o;

        if (isActive != that.isActive) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (eMail != null ? !eMail.equals(that.eMail) : that.eMail != null) return false;
        if (userRole != null ? !userRole.equals(that.userRole) : that.userRole != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = login != null ? login.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (eMail != null ? eMail.hashCode() : 0);
        result = 31 * result + isActive;
        result = 31 * result + (userRole != null ? userRole.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }

    @OneToOne
    @JoinColumn(name = "vet_id", referencedColumnName = "id")
    public VetDTO getVet() {
        return vet;
    }

    public void setVet(VetDTO vet) {
        this.vet = vet;
    }

    @OneToOne
    @JoinColumn(name = "animal_keeper_id", referencedColumnName = "id")
    public AnimalKeeperDTO getAnimalKeeper() {
        return animalKeeper;
    }

    public void setAnimalKeeper(AnimalKeeperDTO animalKeeper) {
        this.animalKeeper = animalKeeper;
    }

    @OneToOne
    @JoinColumn(name = "administrator_id", referencedColumnName = "id")
    public AdministratorDTO getAdministrator() {
        return administrator;
    }

    public void setAdministrator(AdministratorDTO administrator) {
        this.administrator = administrator;
    }
}
