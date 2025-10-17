package PartC.view;

import java.util.*;
import PartC.model.Student;
import PartC.controller.StudentDAO;

public class StudentView {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            StudentDAO dao = new StudentDAO();

            while (true) {
                System.out.println("\n1. Add Student\n2. View All\n3. Update Marks\n4. Delete\n5. Exit");
                System.out.print("Enter choice: ");
                int ch = sc.nextInt();

                switch (ch) {
                    case 1:
                        System.out.print("Enter ID, Name, Department, Marks: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        String name = sc.nextLine();
                        String dept = sc.nextLine();
                        double marks = sc.nextDouble();
                        dao.addStudent(new Student(id, name, dept, marks));
                        break;

                    case 2:
                        dao.viewStudents();
                        break;

                    case 3:
                        System.out.print("Enter ID and new Marks: ");
                        int sid = sc.nextInt();
                        double newMarks = sc.nextDouble();
                        dao.updateMarks(sid, newMarks);
                        break;

                    case 4:
                        System.out.print("Enter ID to delete: ");
                        int delId = sc.nextInt();
                        dao.deleteStudent(delId);
                        break;

                    case 5:
                        System.out.println("Exiting...");
                        System.exit(0);
                        sc.close();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

