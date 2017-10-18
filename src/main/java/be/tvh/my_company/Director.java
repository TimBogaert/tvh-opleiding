package be.tvh.my_company;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by tim036 on 2/10/2017.
 */
public class Director extends Worker {
    static {
        minRemunerations = 4;
    }

    private static final double BONUS_INCENTIVE_PERCENTAGE = 0.10;

    private HashMap<Integer, Double> goals = new HashMap<>();


    //4 remuneraties
    public Director(String firstName, String lastName, Gender gender, Nationality nationality, Date dateOfBirth, Date dateEmployed,
                    String nationalRegistrationNumber, DriversLicense driversLicence, Department department, ArrayList<Project> projects, double salary) {
        super(firstName, lastName, gender, nationality, dateOfBirth, dateEmployed, nationalRegistrationNumber, driversLicence, department, projects, salary);
    }

    public Director(String firstName, String middleName, String lastName, Gender gender, Nationality nationality, Date dateOfBirth, Date dateEmployed,
                    String nationalRegistrationNumber, DriversLicense driversLicence, Department department, ArrayList<Project> projects, double salary) {
        super(firstName, middleName, lastName, gender, nationality, dateOfBirth, dateEmployed, nationalRegistrationNumber, driversLicence, department, projects, salary);
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

    public HashMap<Integer, Double> getGoals() {
        return goals;
    }

    public double getGoal(int year) {
        return goals.get(year);
    }

    public void addGoal(int year, double goal) {
        goals.put(year, goal);
    }

    @Override
    public String toString() {
        return "Director{" + super.toString() +
                "goals=" + goals +
                '}';
    }
}
