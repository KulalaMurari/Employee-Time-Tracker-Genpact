package com.time_tracker.model;

public class Employee {
    private int empId;
    private String empName;
    private int age;
    private String role;
    private String phoneNumber;
    private String email;
    private String password;
    private String empPassword;

    public Employee(int empId, String empName, int age, String role, String phoneNumber, String email, String password, String empPassword) {
        this.empId = empId;
        this.empName = empName;
        this.age = age;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.empPassword = empPassword;
    }

    // Getters and setters
    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmpPassword() {
        return empPassword;
    }

    public void setEmpPassword(String empPassword) {
        this.empPassword = empPassword;
    }
}



