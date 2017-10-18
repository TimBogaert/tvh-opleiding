package be.tvh.my_company;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by tim036 on 5/10/2017.
 */
public class Cxo extends Worker {
    private enum CxoType {
        CFO("Chief Financial Officer"),
        CEO("Chief Executive Officer"),
        CIO("Chief Information Officer");
        private String fullName;

        CxoType(String name) {
            this.fullName = name;
        }

        public String getFullName() {
            return fullName;
        }
    }

    private CxoType cxoType;

    public Cxo(String firstName, String lastName, Gender gender, Nationality nationality, Date dateOfBirth, Date dateEmployed, String nationalRegistrationNumber,
               DriversLicense driversLicence, Department department, ArrayList<Project> projects, double salary, CxoType cxoType) {
        super(firstName, lastName, gender, nationality, dateOfBirth, dateEmployed, nationalRegistrationNumber, driversLicence, department, projects, salary);
        this.cxoType = cxoType;
    }

    public Cxo(String firstName, String middleName, String lastName, Gender gender, Nationality nationality, Date dateOfBirth, Date dateEmployed, String nationalRegistrationNumber,
               DriversLicense driversLicence, Department department, ArrayList<Project> projects, double salary, CxoType cxoType) {
        super(firstName, middleName, lastName, gender, nationality, dateOfBirth, dateEmployed, nationalRegistrationNumber, driversLicence, department, projects, salary);
        this.cxoType = cxoType;
    }

    @Override
    public double calculateTotalIncentiveCost() {
        System.out.println(cxoType.getFullName() + " has no incentive cost.");
        return 0;
    }
}
