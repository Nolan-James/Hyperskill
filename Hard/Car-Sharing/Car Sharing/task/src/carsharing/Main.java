package carsharing;

import java.util.List;
import java.util.Scanner;

public class Main {
    final static CompanyDao companyDao = new CompanyDaoImpl();
    final static CarDao carDao = new CarDaoImpl();
    static int companyId = -1;
    static String companyName = "";

    static {
        Database database = new Database();
        database.dropTable("car");
        database.dropTable("company");

        database.createCompanyTable();
        database.createCarTable();
    }

    public static void main(String[] args) {
        boolean isRunning = true;
        boolean loggedIn = false;
        boolean companySelected = false;
        boolean choosingCar = false;

//        createDatabase();
//        dropDatabase();

        Scanner scanner = new Scanner(System.in);


        while (isRunning) {
            int result = showMenu(scanner);
            switch (result) {
                case 0:
                    isRunning = false;
                    break;
                case 1:
                    loggedIn = true;
                    while (loggedIn) {
                        int managerResult = logInMenu(scanner);

                        switch (managerResult) {
                            case 1:
                                companySelected = true;
                                while (companySelected) {
                                    int companyResult = selectCompany(scanner);

                                    if (companyResult == 0) {
                                        companySelected = false;
                                    } else {
                                        choosingCar = true;

                                        while (choosingCar) {
                                            int carResult = showCars(scanner);
                                            companySelected = false;

                                            switch (carResult) {
                                                case 1:
                                                    listAllCars();
                                                    break;
                                                case 2:
                                                    createCar(scanner);
                                                    break;
                                                case 0:
                                                    choosingCar = false;
                                                    break;
                                            }
                                        }

                                    }
                                }

                                break;
                            case 2:
                                createCompany(scanner);
                                break;
                            case 0:
                                loggedIn = false;
                                break;
                        }
                    }
            }
        }

    }

    private static void listAllCars() {
        List<Car> cars = carDao.listCars(companyId);
        if (cars.isEmpty()) {
            System.out.println("The car list is empty!");
        } else {
            System.out.println("Car list:");
            for (int i = 0; i < cars.size(); i++) {
                System.out.println((i + 1) + ". " + cars.get(i).getName());
            }
//            cars.forEach(System.out::println);
        }
    }

    private static void createCar(Scanner scanner) {
        System.out.println("Enter the car name:");
        String carName = scanner.nextLine();
        carDao.addCar(carName, companyId);
    }

    private static int showCars(Scanner scanner) {
        System.out.println("'" + companyName + "' company");
        System.out.println("1. Car list");
        System.out.println("2. Create a car");
        System.out.println("0. Back");

        String result = scanner.nextLine();
        return Integer.parseInt(result);
    }

    private static void createCompany(Scanner scanner) {
        System.out.println("Enter the company name:");
        String companyName = scanner.nextLine();
        companyDao.createCompany(companyName);
    }

    private static int selectCompany(Scanner scanner) {
        List<Company> companies = companyDao.getCompanies();
        String result = "";

        if (companies.isEmpty()) {
            System.out.println("The company list is empty!");
            result = "0";
        } else {
            System.out.println("Choose the company:");
            companies.forEach(System.out::println);
            System.out.println("0. Back");
            result = scanner.nextLine();
            Company company = companyDao.getCompany(Integer.parseInt(result));

            companyId = company.getId();
            companyName = company.getName();
        }
        return Integer.parseInt(result);
    }

    private static int logInMenu(Scanner scanner) {
        System.out.println("1. Company list");
        System.out.println("2. Create a company");
        System.out.println("0. Back");

        String stringResult = scanner.nextLine();

        return Integer.parseInt(stringResult);
    }

    private static int showMenu(Scanner scanner) {
        System.out.println("1. Log in as a manager");
        System.out.println("0. Exit");

        String stringResult = scanner.nextLine();

        return Integer.parseInt(stringResult);
    }
}