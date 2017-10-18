package be.tvh.my_company;

import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by tim036 on 29/09/2017.
 */
public class MyCompanyApp {
    private static final Logger LOGGER = Logger.getLogger(MyCompanyApp.class);

    public static void main(String[] args) {
        Employee[] employees = new Employee[10];
        employees[0] = new Employee("Anna-Elsa", "Frozen", Gender.FEMALE, Nationality.BELGIAN);
        employees[0].setDateOfBirth(Utility.convertDate(LocalDate.of(1955, 5, 5)));
        employees[1] = new Employee("Boris", "JJ", Gender.MALE, Nationality.DUTCH);
        employees[1].setDateOfBirth(Utility.convertDate(LocalDate.of(1980, 8, 2)));
        employees[2] = new Employee("Cletus", "Banjo-Boy", Gender.MALE, Nationality.ENGLISH);
//        employees[2].setDateOfBirth(Utility.convertDate(LocalDate.of(1980, 8, 2)));
        employees[3] = new Employee("Derec", "Bastard", Gender.MALE, Nationality.GERMAN);
        employees[3].setDateOfBirth(Utility.convertDate(LocalDate.of(1999, 1, 1)));
        employees[4] = new Employee("Eva", "Adam", Gender.FEMALE, Nationality.FRENCH);
//        employees[4].setDateOfBirth(Utility.convertDate(LocalDate.of(1999, 5, 2)));
        employees[5] = new Employee("Franklin", "Roosevelt", Gender.MALE, Nationality.BELGIAN);
        employees[5].setDateOfBirth(Utility.convertDate(LocalDate.of(1955, 5, 6)));
        employees[6] = new Employee("Gust", "Pidgey", Gender.MALE, Nationality.ENGLISH);
        employees[6].setDateOfBirth(Utility.convertDate(LocalDate.of(1988, 8, 28)));
        employees[7] = new Employee("Horus", "Ra", Gender.MALE, Nationality.GERMAN);
//        employees[7].setDateOfBirth(Utility.convertDate(LocalDate.of(1977, 4, 4)));
        employees[8] = new Employee("Ine", "Eni", Gender.FEMALE, Nationality.FRENCH);
        employees[8].setDateOfBirth(Utility.convertDate(LocalDate.of(1955, 5, 4)));
        employees[9] = new Employee("Jenny", "Joy", Gender.FEMALE, Nationality.BELGIAN);
        employees[9].setDateOfBirth(Utility.convertDate(LocalDate.of(1999, 1, 1)));

        Map<Nationality, Integer> employeesOfNationality = new TreeMap<>();
        for (Nationality nationality : Nationality.values()) {
            employeesOfNationality.put(nationality, 0);
        }
        for (Employee employee : employees) {
            employeesOfNationality.put(employee.getNationality(), employeesOfNationality.get(employee.getNationality()) + 1);
        }
        Stream.of(employeesOfNationality).forEach(System.out::print);
        System.out.println("\n---------");

        Map<Nationality, Map<Gender, Integer>> empNationality = new TreeMap<Nationality, Map<Gender, Integer>>();
        for (Nationality nationality : Nationality.values()) {
            Map<Gender, Integer> empNatPerGender = new TreeMap<>();
            for (Gender gender : Gender.values()) {
                empNatPerGender.put(gender, 0);
                empNationality.put(nationality, empNatPerGender);
            }
        }
        Stream.of(empNationality).forEach(System.out::print);
        for (Employee e : employees) {
            Map<Gender, Integer> empNatPerGender = empNationality.get(e.getNationality());
            empNatPerGender.replace(e.getGender(), empNatPerGender.get(e.getGender()) + 1);
        }
        Stream.of(empNationality).forEach(System.out::print);

        System.out.println("\n---------");

        Stream str = Stream.of(employees).filter(e -> e.getFirstName().length() >= 5 && e.getLastName().length() >= 5);
        str.forEach(System.out::println);

        System.out.println("\n---------");
//        Stream.of(employees).sorted(Comparator.nullsLast(Comparator.comparing(Worker::getDateOfBirth))).collect(Collectors.toList()).forEach(System.out::println);
        Stream.of(employees).filter(e -> e.getDateOfBirth() != null).sorted(Comparator.comparing(Worker::getDateOfBirth)).collect(Collectors.toList()).forEach(System.out::println);

        System.out.println("\n---------");
        Stream.of(employees).map(Worker::getNationality).distinct().forEach(n -> System.out.print(n + ", "));

        System.out.println("\n---------");
        List<Nationality> n = Stream.of(employees).map(Worker::getNationality).distinct().collect(Collectors.toList());
        Stream.of(n).forEach(System.out::print);

        System.out.println("\n---------");
        System.out.println(Stream.of(employees).anyMatch(e -> e.getGender() == Gender.FEMALE));

        System.out.println("\n---------");
        for (int i = 0; i < employees.length; i++) {
            employees[i].addRemuneration(new Salary(i * 100));
        }
//        Stream.of(employees).allMatch(e -> e.getRemunerations().stream().filter(r -> "Salary".equals(r.getClass().getSimpleName())).max());



    }

    private static void test1() {
        Department department1 = new Department("Dep 1", "Department 1 description");
        ArrayList<Project> projects1 = new ArrayList<Project>();
        projects1.add(new Project("Proj 1", "Proj1-1"));
        projects1.add(new Project("Proj 2", "A155-C2"));
        Director director1 = new Director("James", "Jameson", Gender.MALE, Nationality.ENGLISH, Utility.convertDate(LocalDate.of(1990, 4, 9)), Utility.convertDate(LocalDate.now().minusMonths(40)),
                "92071509182", DriversLicense.B, department1, projects1, 1800);
        director1.setAddress("Belgium", "Gent", "9000", "Ottergemsesteenweg", "265");
        director1.addGoal(2017, 7_000_000d);
        director1.addGoal(2016, 6_500_000d);
        director1.addGoal(2015, 6_250_000d);
        director1.addGoal(2014, 6_000_000d);
        director1.addRemuneration(new Salary(2500.00));
        GSM gsm = new GSM(30);
        gsm.setType("NOKIA 3310");
        gsm.setMsisdn("0498784072");
        director1.addRemuneration(gsm);
        director1.addRemuneration(new HospitalisationInsurance(60));
        director1.addRemuneration(new MealVoucher(140));
        director1.addRemuneration(new MealVoucher(140));
        System.out.println("Cost: " + director1.calculateTotalIncentiveCost());

        System.out.println(director1.toString());

        Manager manager1 = new Manager("James", "Jameson", Gender.MALE, Nationality.ENGLISH, Utility.convertDate(LocalDate.of(1990, 4, 9)), Utility.convertDate(LocalDate.now().minusMonths(40)),
                "92071509182", DriversLicense.B, department1, projects1, 1500, director1);
        director1.setAddress("Belgium", "Gent", "9000", "Ottergemsesteenweg", "265");
        manager1.addRemuneration(new Salary(4500));
        manager1.addRemuneration(new GSM(800, "Type A", "012345678"));
        manager1.addRemuneration(new GSM(600, "T B", "087321654"));
        manager1.addRemuneration(new GSM(600, "T C", "087421654"));
        manager1.addRemuneration(new MealVoucher(140));
        manager1.addRemuneration(new HospitalisationInsurance(87.99));
        System.out.println(manager1);


        Worker employee1 = new EmployeeBuilder().setFirstName("James").setLastName("Jameson").setGender(Gender.MALE).setNationality(Nationality.ENGLISH).setDateOfBirth(Utility.convertDate(LocalDate.of(1990, 4, 9)))
                .setDateEmployed(Utility.convertDate(LocalDate.now().minusMonths(40))).setNationalRegistrationNumber("92071509182").setDriversLicence(DriversLicense.B).setDepartment(department1).setProjects(projects1)
                .setManager(null).createEmployee();
        Worker employee2 = new EmployeeBuilder().setFirstName("Jane").setLastName("Janeson").setGender(Gender.FEMALE).setNationality(Nationality.DUTCH).setDateOfBirth(Utility.convertDate(LocalDate.of(1996, 12, 1)))
                .setDateEmployed(Utility.convertDate(LocalDate.now().plusMonths(1))).setNationalRegistrationNumber("92071509182").setDriversLicence(DriversLicense.NONE).setDepartment(department1).setProjects(projects1)
                .setManager(null).createEmployee();
        employee1.setAddress("Belgium", "Gent", "9000", "Ottergemsesteenweg", "265");
        employee1.addRemuneration(new Salary(2500.00));
//        GSM gsm = new GSM(240);
//        gsm.setType("NOKIA 3310");
//        gsm.setMsisdn("0498784072");
        employee1.addRemuneration(gsm);
        employee1.addRemuneration(new HospitalisationInsurance(60));
        employee1.addRemuneration(new MealVoucher(140));
        System.out.println("Cost: " + employee1.calculateTotalIncentiveCost());
        System.out.println(employee1.toString());
        System.out.println(employee1.getRemunerations().size() + "  -  " + employee1.getRemunerations());


//        System.out.println("Dir "+director1.calculateTotalIncentiveCost()+"\tMan "+director1.calculateTotalIncentiveCost()+"\tEmpl "+employee1.calculateTotalIncentiveCost());
        System.out.println(director1.getRemunerations());
        System.out.println(director1.calculateTotalIncentiveCost());
        System.out.println(manager1.getRemunerations());
        System.out.println(manager1.calculateTotalIncentiveCost());
        System.out.println(employee1.getRemunerations());
        System.out.println(employee1.calculateTotalIncentiveCost());
    }

}
