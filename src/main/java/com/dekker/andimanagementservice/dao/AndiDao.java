package com.dekker.andimanagementservice.dao;

import javax.persistence.*;

@Entity
@Table(name = "andis")
public class AndiDao {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String role;


    protected AndiDao(){};

    public AndiDao(final String firstName, final String lastName, final String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return this.firstName + " " + this.lastName + " working as a " + this.role;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) { return true; }
        if (obj == null || obj.getClass() != this.getClass()) { return false; }

        final AndiDao andiDao = (AndiDao) obj;
        return this.firstName.equals(andiDao.firstName) && this.lastName.equals(andiDao.lastName) && this.role.equals(andiDao.role);
    }

    private static class Builder {


    }
}
