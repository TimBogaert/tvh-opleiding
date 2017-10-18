package be.tvh.my_company;

import java.util.ArrayList;
import java.util.Date;

public class EmployeeBuilder {
    private String firstName;
    private String lastName;
    private Gender gender;
    private Nationality nationality;
    private Date dateOfBirth;
    private Date dateEmployed;
    private String nationalRegistrationNumber;
    private DriversLicense driversLicence;
    private Department department;
    private ArrayList<Project> projects;
    private double salary;
    private Manager manager;

    public EmployeeBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public EmployeeBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public EmployeeBuilder setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public EmployeeBuilder setNationality(Nationality nationality) {
        this.nationality = nationality;
        return this;
    }

    public EmployeeBuilder setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public EmployeeBuilder setDateEmployed(Date dateEmployed) {
        this.dateEmployed = dateEmployed;
        return this;
    }

    public EmployeeBuilder setNationalRegistrationNumber(String nationalRegistrationNumber) {
        this.nationalRegistrationNumber = nationalRegistrationNumber;
        return this;
    }

    public EmployeeBuilder setDriversLicence(DriversLicense driversLicence) {
        this.driversLicence = driversLicence;
        return this;
    }

    public EmployeeBuilder setDepartment(Department department) {
        this.department = department;
        return this;
    }

    public EmployeeBuilder setProjects(ArrayList<Project> projects) {
        this.projects = projects;
        return this;
    }

    public EmployeeBuilder setSalary(double salary) {
        this.salary = salary;
        return this;
    }

    public EmployeeBuilder setManager(Manager manager) {
        this.manager = manager;
        return this;
    }

    public Employee createEmployee() {
        return new Employee(firstName, lastName, gender, nationality, dateOfBirth, dateEmployed, nationalRegistrationNumber, driversLicence, department, projects, salary, manager);
    }
}