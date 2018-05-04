/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author celos
 */
@Component
public class testMain {

    public static void main(String[] args) {
        //get bean studentManager     
        String FILENAME = "C:\\Users\\celos\\Desktop\\java\\project2\\dao2\\src\\main\\java\\com\\mycompany\\dao2\\text.txt";
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "Spring-Module.xml");

        employeeDAO manager = (employeeDAO) context.getBean("msemployeDAO");
List<employee> customerAs = manager.findAll();
        Scanner scanner = new Scanner(System.in);
       
        while (true) {

            System.out.println("1. View data ");
            System.out.println("2. Update data ");
            System.out.println("3. Delete data ");
            System.out.println("4. Search ");
            System.out.println("5. Save data to file txt");
            System.out.println("6. Exit ");
            System.out.println("Choose your option: ");
            int option = Integer.parseInt(scanner.nextLine());
            switch (option) {
                case 1:
                     customerAs = manager.findAll();
                    for (employee cust : customerAs) {
                        System.out.println("Employee : " + cust.toString());
                    }
                    break;
                case 2:
                    System.out.println("What is employee ID you want to update?");
                    int a = Integer.parseInt(scanner.nextLine());
                    System.out.println("Salary you want to change?");
                    int b = Integer.parseInt(scanner.nextLine());
                    manager.update(a, b);
                    break;
                case 3:
                    System.out.println("What is your employe ID you want to delete?");
                    int c = Integer.parseInt(scanner.nextLine());
                    manager.delete(c);
                    break;
                case 4:
                    System.out.println("Input employee ID to search: ");
                    int d = Integer.parseInt(scanner.nextLine());
                    employee ee = manager.findByCustomerId(d);
                    System.out.println("Employee : " + ee.toString());
                    break;
                case 5:                
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            fw = new FileWriter(FILENAME);
            bw = new BufferedWriter(fw);
               for (employee cust : customerAs) {
               bw.write(cust.toString()+"\r\n");          
                    }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (bw != null) {
                    bw.close();
                }

                if (fw != null) {
                    fw.close();
                }

            } catch (IOException ex) {

                ex.printStackTrace();
            }
        }

                     
                     System.out.println("Done!!!");
                    break;
                case 6:
                    System.exit(0);
            }
        }

    }
    
   
  

    
}
