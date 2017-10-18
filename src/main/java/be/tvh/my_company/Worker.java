package be.tvh.my_company;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;

/**
 * Created by tim036 on 29/09/2017.
 */
public abstract class Worker extends BaseEntity {
    protected static int minRemunerations = 1;

    private String firstName;
    private String middleName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    private String lastName;
    private Gender gender;
    private Nationality nationality;
    private Date dateOfBirth;
    private Date dateEmployed;
    private Address address;
    private String nationalRegistrationNumber;
    private boolean hasDriversLicense; //gets set by setDriversLicense
    private DriversLicense driversLicence;
    private Department department;
    private ArrayList<Project> projects;
    private ArrayList<Remuneration> remunerations = new ArrayList<>();

    //!! Address not present in constructor
    public Worker(String firstName, String lastName, Gender gender, Nationality nationality, Date dateOfBirth, Date dateEmployed,
                  String nationalRegistrationNumber, DriversLicense driversLicence, Department department, ArrayList<Project> projects, double salary) {
//        super(); //create UUID
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.nationality = nationality;
        setDateOfBirth(dateOfBirth);
        setDateEmployed(dateEmployed);
        this.nationalRegistrationNumber = nationalRegistrationNumber;
        setDriversLicence(driversLicence);
        this.department = department;
        this.projects = projects;
        addRemuneration(new Salary(salary));
    }

    public Worker(String firstName, String middleName, String lastName, Gender gender, Nationality nationality, Date dateOfBirth, Date dateEmployed,
                  String nationalRegistrationNumber, DriversLicense driversLicence, Department department, ArrayList<Project> projects, double salary) {
//        super(); //create UUID
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.nationality = nationality;
        setDateOfBirth(dateOfBirth);
        setDateEmployed(dateEmployed);
        this.nationalRegistrationNumber = nationalRegistrationNumber;
        setDriversLicence(driversLicence);
        this.department = department;
        this.projects = projects;
        addRemuneration(new Salary(salary));
    }

    public Worker(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Worker(String firstName, String lastName, Gender gender, Nationality nationality) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.nationality = nationality;
    }

    public abstract double calculateTotalIncentiveCost();

    public ArrayList<Remuneration> getRemunerations() {
        if (this.remunerations == null) {
            ArrayList<Remuneration> myRems = new ArrayList<>();
            return myRems;
        } else {
            return this.remunerations;
        }
    }

    public /*abstract*/ void addRemuneration(Remuneration r) {
        if (amountOfRemunerationType(r) < r.getMaxAmount()) {
            remunerations.add(r);
        } else {
            System.out.printf("Can't add %s, %s %s already has %d.\n", r.getClass().getSimpleName(), this.getClass().getSimpleName(), getFullName(), amountOfRemunerationType(r));
        }
        //TODO
        System.out.printf("%s:\t %s has %d of %d remunerations.", r.getClass().getSimpleName(), getFullName(), remunerations.size(), minRemunerations);
        if (remunerations.size() < minRemunerations) {
            System.out.printf(" Please add %d more remuneration(s).", minRemunerations - remunerations.size());
        }
        System.out.println();


    }

    public String getFullName() {
        String fullName = firstName;
        if (middleName != null && !middleName.isEmpty()) {
            fullName += " " + middleName;
        }
        fullName += " " + lastName;
        return fullName;
    }

    private int amountOfRemunerationType(Remuneration r) {
        int count = 0;
        for (Remuneration remuneration : remunerations) {
            if (remuneration.getClass() == r.getClass()) {
                count++;
            }
        }
        return count;
    }

    public int getDaysOfSeniority() {
        int daysOfSeniority = Period.between(Utility.convertDate(dateEmployed), LocalDate.now()).getDays();
        return daysOfSeniority < 0 ? 0 : daysOfSeniority;
    }

    public boolean isValidNationalRegistrationNumber(String natRegNr) {
        if (natRegNr.length() != 11) {
            return false;
        }
/*Een eerste groep van zes cijfers, gevormd door de geboortedatum in de volgorde: jaar, maand, dag.
!!TODO:Maand en/of dag kunnen nul zijn indien de exacte geboortedatum niet bekend is.
    TODO:Indien de persoon niet ingeschreven is in het Rijksregister, maar er toch gegevens moeten worden bijgehouden voor de sociale zekerheid, bijvoorbeeld buitenlandse werknemers die minder dan drie maanden in
    TODO:België verblijven of grensarbeiders, dan wordt een bisnummer toegekend. Bij het bisnummer wordt de geboortemaand verhoogd met 20 of 40. Als bij de aanvraag het geslacht bekend is,
    TODO:wordt de geboortemaand verhoogd met 40, anders wordt ze verhoogd met 20. Indien de persoon vluchteling is en de geboortedatum niet gekend is, wordt de geboortemaand op 00 gezet en de geboortedag op 00 gezet.*/
        int year = Integer.parseInt(natRegNr.substring(0, 2));
        int month = Integer.parseInt(natRegNr.substring(2, 4));
        int day = Integer.parseInt(natRegNr.substring(4, 6));
        LocalDate localDateDoB = Utility.convertDate(dateOfBirth);
        if (localDateDoB.getYear() - 1900 != year && dateOfBirth.getYear() - 2000 != year) {
            return false;
        }
        if (localDateDoB.getMonthValue() != month) {
            return false;
        }
        if (localDateDoB.getDayOfMonth() != day) {
            return false;
        }
/*Een tweede groep van drie cijfers dient als herkenning van de personen die op dezelfde dag geboren zijn. Dit reeksnummer is even voor een vrouw en oneven voor een man. Het is de dagteller van de geboortes.
    Voor een man van 001 tot 997 en voor een vrouw van 002 tot 998.*/
        int dayCounter = Integer.parseInt(natRegNr.substring(6, 9));
        if ((gender == Gender.MALE && dayCounter % 2 == 0) || (gender == Gender.FEMALE && dayCounter % 2 != 0)) {
            return false;
        }
/*Een derde groep van twee cijfers is een controlegetal op basis van de 9 voorafgaande cijfers. Dat wordt berekend door het getal van negen cijfers, dat gevormd wordt door de aaneenschakeling van de
    geboortedatum en het reeksnummer, te delen door 97. De rest van deze deling ("modulo") wordt van 97 afgetrokken. Het aldus verkregen verschil is het controlenummer. Voor personen geboren in of na 2000
    moet men een 2 voor het getal van negen cijfers zetten (+ 2000000000) alvorens te delen door 97.*/
        int verificationNumber = Integer.parseInt(natRegNr.substring(9));
        long toVerify = Integer.parseInt(natRegNr.substring(0, 9));
        if (dateOfBirth.getYear() >= 2000) {
            toVerify += 2_000_000_000;
        }
        return 97 - toVerify % 97 == verificationNumber;
    }

    public static int getMinRemunerations() {
        return minRemunerations;
    }

    static void setMinRemunerations(int minRemunerations) {
        Worker.minRemunerations = minRemunerations;
    }

    public String getfirstName() {
        return firstName;
    }

    public void setfirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Nationality getNationality() {
        return nationality;
    }

    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        if (Utility.isOver18(dateOfBirth)) {
            this.dateOfBirth = dateOfBirth;
        } else {
            System.out.println("!! dateOfBirth < 18y");
        }
    }

    public Date getDateEmployed() {
        return dateEmployed;
    }

    public void setDateEmployed(Date dateEmployed) {
        if (dateEmployed.compareTo(dateOfBirth) > 0 && Utility.convertDate(dateEmployed).isBefore(LocalDate.now().plusMonths(2))) {
            this.dateEmployed = dateEmployed;
        } else {
            System.out.println("!!dateEmployed not valid");
        }
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(String country, String city, String postalCode, String street, String houseNumber) {
        this.address = new Address(country, city, postalCode, street, houseNumber);
    }

    public String getNationalRegistrationNumber() {
        return nationalRegistrationNumber;
    }

    public void setNationalRegistrationNumber(String nationalRegistrationNumber) {
        this.nationalRegistrationNumber = nationalRegistrationNumber;
    }

    public boolean hasDriversLicense() {
        return hasDriversLicense;
    }

    public DriversLicense getDriversLicence() {
        return driversLicence;
    }

    public void setDriversLicence(DriversLicense driversLicence) {
        this.driversLicence = driversLicence;
        hasDriversLicense = driversLicence != DriversLicense.NONE;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public ArrayList<Project> getProjects() {
        if (this.projects == null) {
            ArrayList<Project> myProjects = new ArrayList<>();
            return myProjects;
        } else {
            return this.projects;
        }
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }

    /*@Override
    public String toString() {
        String str = "Worker{" + getIdentifier() +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", nationality=" + nationality;
        if (dateOfBirth != null) {
            str += ", dateOfBirth=" + dateOfBirth +
                    ", dateEmployed=" + dateEmployed +
                    ", address=" + address +
                    ", nationalRegistrationNumber='" + nationalRegistrationNumber + '\'' +
                    ", hasDriversLicense=" + hasDriversLicense +
                    ", driversLicence=" + driversLicence +
                    ", department=" + department +
                    ", projects=" + projects.size() +
                    '}';
        }
        return str;


    }*/

    @Override
    public String toString() {
        return "Worker{" +
                "firstName='" + (firstName == null ? "" : firstName) + '\'' +
                ", middleName='" + (middleName == null ? "" : middleName) + '\'' +
                ", lastName='" + (lastName == null ? "" : lastName) + '\'' +
                ", gender=" + (gender == null ? "" : gender) +
                ", nationality=" + nationality +
                ", dateOfBirth=" + (dateOfBirth == null ? "" : dateOfBirth) +
                ", dateEmployed=" + (dateEmployed == null ? "" : dateEmployed) +
                ", address=" + (address == null ? "" : address) +
                ", nationalRegistrationNumber='" + (nationalRegistrationNumber == null ? "" : nationalRegistrationNumber) + '\'' +
                ", hasDriversLicense=" + hasDriversLicense +
                ", driversLicence=" + (driversLicence == null ? "" : driversLicence) +
                ", department=" + (department == null ? "" : department) +
                ", projects=" + (projects == null ? "" : projects) +
                ", remunerations=" + (remunerations == null ? "" : remunerations) +
                '}';
    }

    public class Address {
        private String country;
        private String city;
        private String postalCode;
        private String street;
        private String houseNumber;

        public Address(String country, String city, String postalCode, String street, String houseNumber) {
            this.country = country;
            this.city = city;
            this.postalCode = postalCode;
            this.street = street;
            this.houseNumber = houseNumber;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getPostalCode() {
            return postalCode;
        }

        public void setPostalCode(String postalCode) {
            this.postalCode = postalCode;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getHouseNumber() {
            return houseNumber;
        }

        public void setHouseNumber(String houseNumber) {
            this.houseNumber = houseNumber;
        }

        @Override
        public String toString() {
            Formatter formatter = new Formatter();
            formatter.format("%s %s, %s %s, %s", street, houseNumber, postalCode, city, country);
            return formatter.toString();
        }
    }
}
