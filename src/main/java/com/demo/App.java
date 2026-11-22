package com.demo;

import java.util.List;
import java.util.Scanner;

import com.demo.dao.StudentDao;
import com.demo.model.Student;

public class App {
	@SuppressWarnings("resource")
	public static void main(String[] args) {

		StudentDao sd = new StudentDao();

		Scanner sc = new Scanner(System.in);
		System.out.println("******************Welcome to Student Management System******************");
		System.out.println("************************************************************************");
		System.out.println(
				"Enter 1: For Add student.\n" + "Enter 2: For Remove student. \n" + "Enter 3: For Find student by Id \n"
						+ "" + "Enter 4: Display All student \n" + "Enter 5: Update Student");
		System.out.print("Enter you choice :: ");
		int choice = sc.nextInt();
		Student s = new Student();
		int id = 0;
		switch (choice) {
		case 1:
			System.out.print("Enter Student Name: ");
			s.setName(sc.next());

			System.out.print("Enter Student City: ");
			s.setCity(sc.next());

			System.out.print("Enter Student Percentage: ");
			s.setPercentage(sc.nextDouble());

			int studentInserted = sd.insertStudent(s);

			if (studentInserted > 0) {
				System.out.println("Student added successfully!");
			} else {
				System.out.println("Failed to add student.");
			}
			break;

		case 2:
			System.out.print("Enter Student Id :: ");
			id = sc.nextInt();
			int check = sd.deleteStudentById(id);
			if (check != 0) {
				System.out.println("Student deleted sucessfully!!!");
			} else {
				System.out.println("Student does not exist!!!");
			}
			break;
		case 3:
			System.out.print("Enter Student Id :: ");
			id = sc.nextInt();
			s = sd.getStudentById(id);
			if (s != null) {
				System.out.println("Id :" + s.getId() + "\nName :" + s.getName() + "\nCity :" + s.getCity()
						+ "\nPercentage :" + s.getPercentage());
			} else {
				System.out.println("Sorry, Student does not exist!");
			}
			break;

		case 4:
			List<Student> students = sd.getAllStudent();
			if (students == null) {
				System.out.println("Unable to retrieve student records.");
			} else if (students.isEmpty()) {
				System.out.println("No student records found.");
			} else {
				for (Student s1 : students) {
					System.out.println("Id: " + s1.getId() + "\nName: " + s1.getName() + "\nCity: " + s1.getCity()
							+ "\nPercentage: " + s1.getPercentage());
					System.out.println("----------------------------------------");
				}
			}

			break;

		case 5:
			System.out.print("Enter Student Id :: ");
			id = sc.nextInt();

			s = sd.getStudentById(id);

			if (s != null) {
				s.setName("Sindhu");
				s.setCity("Jaipur");

				check = sd.updateStudent(s);

				if (check != 0) {
					System.out.println("Student information updated successfully!");
				} else {
					System.out.println("Failed to update student!");
				}
			} else {
				System.out.println("Student does not exist!");
			}
			break;
		default:
			System.out.println("Invalid choice! Please enter a number from 1 to 5.");
		}

	}
}
