package be.tvh.my_company;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by tim036 on 2/10/2017.
 */
public class Employee extends Worker {
    static {
        minRemunerations = 2;
    }

    private Manager manager;

    public Employee(String firstName, String lastName, Gender gender, Nationality nationality, Date dateOfBirth, Date dateEmployed,
                    String nationalRegistrationNumber, DriversLicense driversLicence, Department department, ArrayList<Project> projects, double salary, Manager manager) {
        super(firstName, lastName, gender, nationality, dateOfBirth, dateEmployed, nationalRegistrationNumber, driversLicence, department, projects, salary);
        this.manager = manager;
    }

    public Employee(String firstName, String middleName, String lastName, Gender gender, Nationality nationality, Date dateOfBirth, Date dateEmployed,
                    String nationalRegistrationNumber, DriversLicense driversLicence, Department department, ArrayList<Project> projects, double salary, Manager manager) {
        super(firstName, middleName, lastName, gender, nationality, dateOfBirth, dateEmployed, nationalRegistrationNumber, driversLicence, department, projects, salary);
        this.manager = manager;
    }

    public Employee(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public Employee(String firstName, String lastName, Gender gender, Nationality nationality) {
        super(firstName, lastName, gender, nationality);
    }

    @Override
    public double calculateTotalIncentiveCost() {
        double tic = 0;
        for (Remuneration remun : getRemunerations()) {
            tic += remun.getCost();
        }
        return tic;
    }


    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "Employee{" + super.toString() +
                "manager=" + manager +
                '}';
    }
}
