/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ca1.sba23349;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * GitHub : https://github.com/ana-proenca/CA1-sba23349
 */
public class CA1Sba23349 {

    public static void main(String[] args) {
        String inputFile = "student.txt"; // name of the file to read
        String outputFile = "status.txt"; // name of the file to write

        // READING FROM A FILE
        // creating a variable from reading method
        String[] dataFile = readingMethod(inputFile);

        // STUDENT NAME
        // variable for the first line of the file
        String fullName = dataFile[0];
        boolean isStudentNameValid = studentNameValidation(fullName); // variable to verify student name

        // NUMBER OF CLASSES
        int numberClasses = Integer.parseInt(dataFile[1]); // variable to use to verify type of workload, needed to
        // parse to int.
        boolean isNumberClassesValid = workloadValidation(numberClasses); // variable to verify number of classes
        // variable to get method Workload
        String resultWorkload = workload(numberClasses);

        // STUDENT NUMBER
        // String to verify student number
        String studentNumber = dataFile[2];

        // validating minimun lenght of Student number and printing error message
        boolean isLenghtStudentNumber = studentNumberValidation(studentNumber);

        // getting string to verify if two first characters are numbers.
        String firstTwoStudentNumber = studentNumber.substring(0, 1);
        boolean isFirstTwoNumber = firstTwoNumberValidation(firstTwoStudentNumber);

        // verify if position 2 and 3 are letters
        String firstTwoLettersStudentNumber = studentNumber.substring(2, 3);
        boolean isLettersStudentNumber = firstTwoLettersValidation(firstTwoLettersStudentNumber);

        // getting position 4 for validation
        String letterOrNumberStudentNumber = studentNumber.substring(4); 
        int endIndex = studentNumber.length(); // getting the total lenght of the String studentNumber
        // variable to check if character 2 and 3 are letters
        boolean isStudentNumberValid = studentNumberValidation(letterOrNumberStudentNumber,studentNumber,endIndex);

        // WRITING IN A FILE
        if (isStudentNameValid && isNumberClassesValid && isLenghtStudentNumber && isFirstTwoNumber && isLettersStudentNumber && isStudentNumberValid) {
            // geting second name from a fullName's substring and adding 1 to skip single space
            String secondName = fullName.substring(fullName.indexOf(" ") + 1); 
            try {
                BufferedWriter br = new BufferedWriter(new FileWriter(outputFile));
                br.write(studentNumber + "-" + secondName);
                br.newLine();
                br.write(resultWorkload);
                br.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    // Reading Method
    public static String[] readingMethod(String inputFile) {
        String[] dataFile = new String[3];
        try {
            Scanner sc = new Scanner(new FileReader(inputFile));

            int index = 0;
            while (sc.hasNextLine()) {
                dataFile[index] = sc.nextLine();
                index++;
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return dataFile;
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
            System.out.println("Enter a valid number of classes: between 1 and 8");
            return false;
        }
    }

    // Student Number Method
    // validating if number of characters in Student Number
    public static boolean studentNumberValidation(String studentNumber) {
        if (studentNumber.length() >= 6) {
            return true;
        } else {
            System.out.println("Student number must be a minimum of 6 characters.");
            return false;
        }
    }

    // validating if first two characters in Student Number are numbers
    public static boolean firstTwoNumberValidation(String firstTwoStudentNumber) {
        if (firstTwoStudentNumber.matches("[0-9]+")) {
            return true;
        } else {
            System.out.println("First two characters of Student Number must be numbers.");
            return false;
        }
    }

    public static boolean studentNumberValidation(String letterOrNumberStudentNumber, String studentNumber,
            int endIndex) {
        // verify if position 4 of studentNumber String is number
        if (letterOrNumberStudentNumber.matches("[0-9]+")) {
            System.out.println("Position 4 is a number");
            // getting a String of numbers from position 4 until total lenght.
            String isPosition4Number = studentNumber.substring(4, endIndex);
            // verify if last characters are numbers
            if (!isPosition4Number.matches("[0-9]+")) {
                System.out.println(studentNumber.substring(4, endIndex));
                System.out.println("Last characters are not numbers and position 4 is a number");
                return false;
            } else {
                return true;
            }

        } else {
            // getting a String of numbers from position 5 until total lenght as position 4 is a letter
            String isPosition4Letter = studentNumber.substring(5, endIndex);
            // verify if last characters are numbers
            if (!isPosition4Letter.matches("[0-9]+")) {
                System.out.println(studentNumber.substring(5, endIndex));
                System.out.println("Last characters are not numbers and position 4 is a letter");
                return false;
            } else {
                return true;
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
    
}
