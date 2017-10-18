package be.tvh.my_company;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by tim036 on 2/10/2017.
 */
public class Manager extends Worker {
    static {
        minRemunerations = 3;
    }

    private static final double BONUS_INCENTIVE_PERCENTAGE = 0.05;

    private Director director;
    private Set<Employee> employees = new HashSet<Employee>();

    //3 remuneraties
    public Manager(String firstName, String lastName, Gender gender, Nationality nationality, Date dateOfBirth, Date dateEmployed,
                   String nationalRegistrationNumber, DriversLicense driversLicence, Department department, ArrayList<Project> projects, double salary, Director director) {
        super(firstName, lastName, gender, nationality, dateOfBirth, dateEmployed, nationalRegistrationNumber, driversLicence, department, projects, salary);
        this.director = director;
    }

    public Manager(String firstName, String middleName, String lastName, Gender gender, Nationality nationality, Date dateOfBirth, Date dateEmployed,
                   String nationalRegistrationNumber, DriversLicense driversLicence, Department department, ArrayList<Project> projects, double salary, Director director) {
        super(firstName, middleName, lastName, gender, nationality, dateOfBirth, dateEmployed, nationalRegistrationNumber, driversLicence, department, projects, salary);
        this.director = director;
    }

    @Override
    public double calculateTotalIncentiveCost() {
        if (getRemunerations().size() == 0) {
            addRemuneration(new Salary(1600)); //!zou niet voor mogen komen ... maar toch!
        }
        double tic = 0;
        for (Remuneration remun : getRemunerations()) {
            tic += remun.getCost();
        }
        return tic * (1 + BONUS_INCENTIVE_PERCENTAGE);
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    @Override
    public String toString() {
        return "Manager{" + super.toString() +
                "director=" + director +
                ", employees=" + employees +
                '}';
    }
}
