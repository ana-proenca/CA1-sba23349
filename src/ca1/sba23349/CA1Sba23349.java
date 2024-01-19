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
 *
 * @author anaclaudiaproenca
 */
public class CA1Sba23349 {

    public static void main(String[] args) {

        String inputFile = "student.txt";
        String outputFile = "status.txt";

        String[] dataFile = new String[3];

        try {
            Scanner sc = new Scanner(new FileReader(inputFile));

            int index = 0;
            while (sc.hasNextLine()) {
                dataFile[index] = sc.nextLine();
                index++;
            }

            System.out.println(dataFile[0]);
            System.out.println(dataFile[1]);
            System.out.println(dataFile[2]);

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

        String fullName = dataFile[0];

        if (!fullName.contains(" ")) {
            System.out.println("First and second names need to be separated by a single space.");
        }
        
        String firstName = fullName.substring(0, fullName.indexOf(" "));
        System.out.println(firstName);

        if (!firstName.matches("[A-Za-z]+")) {
            System.out.println("First name must be letters only");
        }
        if (!fullName.contains(" ")) {
            System.out.println("First and second names need to be separated by a single space.");
        } else {
            String secondName = fullName.substring(fullName.indexOf(" ")+1);
            System.out.println(secondName);
        }

    int numberClasses = Integer.parseInt(dataFile[1]);
    String resultWorkload = workload(numberClasses);
    System.out.println(resultWorkload);

    }
    public static String workload(int numberClasses) {
        if (numberClasses == 1) {
            return "Very Light";
        } else if (numberClasses == 2) {
            return "Light";
        } else if (numberClasses >= 3 || numberClasses <= 5 ) {
            return "Part Time";
        } else if (numberClasses <=  0 || numberClasses > 8) {
            return "Enter a valid number of classes";
        } else {
            return "Full Time";
        }
    }
}