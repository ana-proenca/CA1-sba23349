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

        // creating a variable from reading method
        String[] dataFile = readingMethod(inputFile);

        // variable for the first line of the file
        String fullName = dataFile[0];
        boolean isStudentNameValid = studentNameValidation(fullName); // variable to verify student name

        int numberClasses = Integer.parseInt(dataFile[1]); // variable to use to verify type of workload, needed to
        // parse to int.

        boolean isNumberClassesValid = workloadValidation(numberClasses); // variable to verify number of classes

        // validation if Student Name and Number of Classes from file are valid
        if (isStudentNameValid && isNumberClassesValid) {
            System.out.println("Student Name and Number of Classes are valid");
        } else {
            System.out.println("Student Name and Number of Classes are  NOT valid");
        }

        // variable to get method Workload
        String resultWorkload = workload(numberClasses);
        System.out.println(resultWorkload); // printing result of workload method

        // writing
        try {
            BufferedWriter br = new BufferedWriter(new FileWriter(outputFile));

            int i = 0;
            while (i < dataFile.length) {
                br.write(dataFile[i]);
                br.newLine();
                i++;
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e);
        }




        // String to verify student number
        String studentNumber = dataFile[2];

        // validating Student number
        if (studentNumber.length() < 6) {
            System.out.println("Student number must be a minimum of 6 characters.");
        }

        String firstTwoStudentNumber = studentNumber.substring(0, 1);
        if (!firstTwoStudentNumber.matches("[0-9]+")) {
            System.out.println("First two characters of Student Number must be numbers.");
        }




        String letterOrNumberStudentNumber = studentNumber.substring(4); // getting position 4 for validation
        int endIndex = studentNumber.length(); // getting the total lenght of the String studentNumber

        // verify if position 4 of studentNumber String is number
        if (letterOrNumberStudentNumber.matches("[0-9]+")) {
            System.out.println("Position 4 is a number");
            // getting a String of numbers from position 4 until total lenght.
            String isPosition4Number = studentNumber.substring(4, endIndex);
            // verify if last characters are numbers
            if (!isPosition4Number.matches("[0-9]+")) {
                System.out.println(studentNumber.substring(4, endIndex));
                System.out.println("Last characters are not numbers and position 4 is a number");
            }
        
        } else {
            System.out.println("Position 4 is a letter");
            // getting a String of numbers from position 5 until total lenght as position 4 is a letter
            String isPosition4Letter = studentNumber.substring(5, endIndex);
            // verify if last characters are numbers
            if (!isPosition4Letter.matches("[0-9]+")) {
                System.out.println(studentNumber.substring(5, endIndex));
                System.out.println("Last characters are not numbers and position 4 is a letter");
            }
        }
    }

    // reading method
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

    // Validation for student Name
    public static boolean studentNameValidation(String fullName) {
        // verification if string contains a single space between name and surname
        if (!fullName.contains(" ")) {
            System.out.println("First and second names need to be separated by a single space.");
            return false;
        }

        // getting first name string from a fullName's substring
        String firstName = fullName.substring(0, fullName.indexOf(" "));
        System.out.println(firstName);

        // verify if name has letters only
        if (!firstName.matches("[A-Za-z]+")) {
            System.out.println("First name must be letters only");
            return false;
        }

        // geting second name from a fullName's substring and adding 1 to skip single
        // space
        String secondName = fullName.substring(fullName.indexOf(" ") + 1);
        System.out.println(secondName);
        return true;
    }

    // Getting Workload
    public static String workload(int numberClasses) {
        if (numberClasses == 1) {
            return "Very Light";
        } else if (numberClasses == 2) {
            return "Light";
        } else if (numberClasses >= 3 && numberClasses <= 5) {
            return "Part Time";
        } else if (numberClasses <= 0 || numberClasses > 8) {
            return "Enter a valid number of classes: between 1 and 8";
        } else {
            return "Full Time";
        }
    }

    // Workload validation method checking if number of classes is between 1 and 8
    public static boolean workloadValidation(int numberClasses) {
        return numberClasses > 0 && numberClasses <= 8;
    }

    // public static void studentNumberChecker(String studentNumber) {
    //
    // }
    // public static String writingMethod() {
    // if
    // }
}
