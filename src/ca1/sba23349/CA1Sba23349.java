/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ca1.sba23349;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
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

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
