/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ca1.sba23349;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
// * GitHub : https://github.com/ana-proenca/CA1-sba23349
 */
public class CA1Sba23349 {

    public static void main(String[] args) {
    
        // READING FROM A FILE 
        String inputFile = "students.txt"; 
        ArrayList<String> dataFile = new ArrayList<String>();

        // MENU
        System.out.println("Please enter an option: ");
        System.out.println("1 - Read from the file, or");
        System.out.println("2 - Read from the console.");
        try ( Scanner sc1 = new Scanner(System.in)) {
            String userOption = sc1.nextLine();
            if ((userOption.contains("1"))) {
                dataFile = readFromFile(inputFile);
            } else if (!userOption.contains("1") && !userOption.contains("2")) {
                System.out.println("Please enter a valid option: 1 or 2");
            } else {
                dataFile = readFromInput(sc1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        //Loop to send the correct line to correct ArrayList 
        for (int i = 0; i < dataFile.size(); i += 3) {
            // STUDENT NAME
            String fullName = dataFile.get(i);
            boolean isStudentNameValid = studentNameValidation(fullName); 

            // NUMBER OF CLASSES
            int numberClasses = Integer.parseInt(dataFile.get(i + 1));
            boolean isNumberClassesValid = workloadValidation(numberClasses); 

            // STUDENT NUMBER
            String studentNumber = dataFile.get(i + 2);
            boolean isLenghtStudentNumber = studentNumberValidation(studentNumber); // validating minimun lenght of Student number 
            String firstTwoStudentNumber = studentNumber.substring(0, 2); // verify if two first characters are numbers.
            int yearValidation = Integer.parseInt(firstTwoStudentNumber);
            boolean isFirstTwoNumber = firstTwoNumberValidation(firstTwoStudentNumber, yearValidation); // year is at least 2020

            // verify if position 2 and 3 are letters
            String firstTwoLettersStudentNumber = studentNumber.substring(2, 4);
            boolean isLettersStudentNumber = firstTwoLettersValidation(firstTwoLettersStudentNumber);
            // getting position 4 for validation
            String letterOrNumberStudentNumber = studentNumber.substring(4);
            int endIndex = studentNumber.length(); // getting the total lenght of the String studentNumber
            boolean isStudentNumberValid = studentNumberValidation(letterOrNumberStudentNumber, studentNumber,
                    endIndex);

            //Writing. if a block of data is correct, then it will be written into the file status.txt
            if (isStudentNameValid && isNumberClassesValid && isLenghtStudentNumber && isFirstTwoNumber
                    && isLettersStudentNumber && isStudentNumberValid) {
                System.out.println("Data is written in status.txt");
                System.out.println("\n");
                writingFile(fullName, studentNumber, numberClasses);
            }
        }
    }
    
    // Reading Method from the file
    public static ArrayList<String> readFromFile(String inputFile) {
        ArrayList<String> dataFile = new ArrayList<String>();

        try {
            Scanner sc = new Scanner(new FileReader(inputFile));

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                dataFile.add(line);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return dataFile;
    }

    //Reading Method from the console
    public static ArrayList<String> readFromInput(Scanner sc1) {
        ArrayList<String> dataInput = new ArrayList<String>();
        System.out.println("Please, enter name and surname of the student: ");
        dataInput.add(sc1.nextLine());

        System.out.println("Enter the number of classes between 1 and 8:");
        dataInput.add(sc1.nextLine());

        System.out.println("Enter the student number: ");
        dataInput.add(sc1.nextLine());

        return dataInput;
    }

    // Student Name Method
    public static boolean studentNameValidation(String fullName) {
        // verification if string contains a single space between name and surname
        if (!fullName.contains(" ")) {
            System.out.println("First and second names need to be separated by a single space.");
            return false;
        }

        // getting first name string from a fullName's substring
        String firstName = fullName.substring(0, fullName.indexOf(" "));

        // verify if name has letters only
        if (!firstName.matches("[A-Za-z]+")) {
            System.out.println("First name must be letters only");
            return false;
        }
        return true;
    }

    // Number of Classes Method
    public static String workload(int numberClasses) {
        if (numberClasses == 1) {
            return "Very Light";
        } else if (numberClasses == 2) {
            return "Light";
        } else if (numberClasses >= 3 && numberClasses <= 5) {
            return "Part Time";
        } else {
            return "Full Time";
        }
    }

    // Workload validation method checking if number of classes is between 1 and 8
    public static boolean workloadValidation(int numberClasses) {
        if (numberClasses > 0 && numberClasses <= 8) {
            return true;
        } else {
            System.out.println("Number of classes must be between 1 and 8");
            return false;
        }
    }

    // Student Number Method
    // validating the lenght of characters in Student Number
    public static boolean studentNumberValidation(String studentNumber) {
        if (studentNumber.length() >= 6) {
            return true;
        } else {
            System.out.println("Student number must be a minimum of 6 characters.");
            return false;
        }
    }

    // validating if first two characters in Student Number are numbers
    public static boolean firstTwoNumberValidation(String firstTwoStudentNumber, int yearValidation) {
        if (firstTwoStudentNumber.matches("[0-9]+")) {
            if (yearValidation >= 20) {
                return true;
            } else {
                System.out.println("Year from student number need to be at least 2020.");
                return false;
            }
        } else {
            System.out.println("First two characters of Student Number must be numbers.");
            return false;
        }
    }

    // verify if position 4 is letter or number
    public static boolean studentNumberValidation(String letterOrNumberStudentNumber, String studentNumber,
            int endIndex) {
        // verify if position 4 of studentNumber String is number
        if (letterOrNumberStudentNumber.matches("[0-9]+")) {
            // as position 4 is a number - String of numbers from position 4 until total lenght.
            String studentNumberNumbers = studentNumber.substring(4, endIndex);
            // verify if last characters are numbers
            if (studentNumberNumbers.matches("[0-9]+")) {
                int studentNumberInt = Integer.parseInt(studentNumberNumbers);

                if (studentNumberInt >= 1 && studentNumberInt <= 200) {
                    return true;
                } else {
                    System.out.println("Number after the letters need to be between 1 and 200");
                    return false;
                }
            } else {
                System.out.println(studentNumber.substring(4, endIndex));
                return false;
            }

        } else {
            // as position 4 is a letter - String of numbers from position 5 until total lenght as position 4 is a letter
            String studentNumberNumbers = studentNumber.substring(5, endIndex);
            // verify if last characters are numbers
            if (studentNumberNumbers.matches("[0-9]+")) {
                int studentNumberInt = Integer.parseInt(studentNumberNumbers);

                if (studentNumberInt >= 1 && studentNumberInt <= 200) {
                    return true;
                } else {
                    System.out.println("Number after the letters in student number need to be between 1 and 200.");
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    // verify if after student year are letters
    public static boolean firstTwoLettersValidation(String firstTwoLettersStudentNumber) {
        if (firstTwoLettersStudentNumber.matches("[0-9]+")) {
            System.out.println("Information after student year needs to be letters.");
            return false;
        } else {
            return true;
        }
    }

    // Writing Method
    public static void writingFile(String fullName, String studentNumber, int numberClasses) {
        String outputFile = "status.txt"; 
        // geting second name from a fullName's substring and adding 1 to skip single space
        String secondName = fullName.substring(fullName.indexOf(" ") + 1);
        String resultWorkload = workload(numberClasses);
        try {
            BufferedWriter br = new BufferedWriter(new FileWriter(outputFile, true));
            br.write(studentNumber.toUpperCase() + "-" + secondName);
            br.newLine();
            br.write(resultWorkload);
            br.newLine();
            br.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}