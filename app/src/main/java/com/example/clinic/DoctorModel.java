package com.example.clinic;

import java.util.ArrayList;

public class DoctorModel {
    String firstname, lastname, email, phone, specialty, gender;
    ArrayList<String> listDayOfWork = new ArrayList<>();

    public DoctorModel(String firstname, String lastname, String email, String phone, String specialty, String gender, ArrayList<String> listDayOfWork) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.specialty = specialty;
        this.gender = gender;
        this.listDayOfWork = listDayOfWork;
    }

    public DoctorModel(String firstname, String lastname, String email, String phone, String specialty, String gender) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.specialty = specialty;
        this.gender = gender;
    }

    public DoctorModel() {
    }

    public ArrayList<String> getListDayOfWork() {
        return listDayOfWork;
    }

    public void setListDayOfWork(ArrayList<String> listDayOfWork) {
        this.listDayOfWork = listDayOfWork;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


}
