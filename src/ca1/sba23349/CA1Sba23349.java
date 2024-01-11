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

        try {
            // Reading the data from the file.
            Scanner sc = new Scanner(new FileReader("test/student.txt"));
            
            
            while (sc.hasNextLine()) {
                String fileData = sc.nextLine();
                System.out.println(fileData);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
