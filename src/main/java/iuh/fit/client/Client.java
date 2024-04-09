/**
 * 
 */
package iuh.fit.client;

import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

import iuh.fit.entity.Course;
import iuh.fit.entity.Student;
import iuh.fit.entity.StudentGrade;

/**
 * @author Trần Nguyên Vũ
 * @version 1.0
 * @created 2 Apr 2024 - 2:43:39 pm
 */
public class Client {
	public static void main(String[] args) {
		try (Socket socket = new Socket("DUZ-PC", 8201);
				Scanner sc = new Scanner(System.in);
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());) {

			dos.flush();
			while (true) {
				System.out.print("\\033\\143");
				System.out.println("=".repeat(40));
				System.out.println("Welcome to the School Management System");
				System.out.println("=".repeat(40));
				System.out.println("1. Get all courses");
				System.out.println("2. Get course by ID");
				System.out.println("3. Get course by name (Absolute)");
				System.out.println("4. Get all students");
				System.out.println("5. Get a student's grades by student ID");
				System.out.println("6. Exit");
				System.out.println("=".repeat(40));
				System.out.print("Enter your choice (1-6): ");

				int choice = sc.nextInt();
				System.out.println("Client: Choice: " + choice);
				dos.writeInt(choice);

				switch (choice) {
				case 1:
//					 Get all courses
					@SuppressWarnings("unchecked")
					List<Course> courses = (List<Course>) ois.readObject();
					for (Course course : courses) {
						System.out.println(course);
					}
					// Pause the console to read the courses, press Enter to continue
					System.out.println("Press Enter to continue...");
					sc.nextLine();
					sc.nextLine();
					break;
				case 2:
					sc.nextLine();
					System.out.println("Enter course ID:");
					int id = sc.nextInt();
					System.out.println("Client: ID: " + id);
					dos.writeInt(id);
					dos.flush();

					System.out.println((Course) ois.readObject());
					// Pause the console to read the courses, press Enter to continue
					System.out.println("Press Enter to continue...");
					sc.nextLine();
					sc.nextLine();
					break;
				case 3:
					sc.nextLine();
					System.out.println("Enter course name:");
					String name = sc.nextLine();
					System.out.println("Client: Name: " + name);
					dos.writeUTF(name);
					dos.flush();

					System.out.println((Course) ois.readObject());
					// Pause the console to read the courses, press Enter to continue
					System.out.println("Press Enter to continue...");
					sc.nextLine();
					break;
				case 4:
					@SuppressWarnings("unchecked")
					List<Student> students = (List<Student>) ois.readObject();
					for (Student student : students) {
						System.out.println(student);
					}
					// Pause the console to read the courses, press Enter to continue
					System.out.println("Press Enter to continue...");
					sc.nextLine();
					sc.nextLine();
					break;
				case 5:
					sc.nextLine();
					System.out.println("Enter student ID:");
					int studentId = sc.nextInt();
					System.out.println("Client: ID: " + studentId);
					dos.writeInt(studentId);
					dos.flush();

					@SuppressWarnings("unchecked")
					List<StudentGrade> studentGrades = (List<StudentGrade>) ois.readObject();
					for (StudentGrade studentGrade : studentGrades) {
						System.out.println(studentGrade);
					}
					// Pause the console to read the courses, press Enter to continue
					System.out.println("Press Enter to continue...");
					sc.nextLine();
					sc.nextLine();
					break;
				case 6:
					// Confirm exit by pressing Y/y
					System.out.println("Are you sure you want to exit? (Y/N)");
					sc.nextLine();
					String confirm = sc.nextLine();
					if (confirm.equalsIgnoreCase("Y") || confirm.equalsIgnoreCase("y")) {
						System.out.println("Client: Exit");
						System.exit(0);
					} else {
						System.out.println("Client: Continue");
					}
					break;
				default:
					System.out.println("Invalid choice");
					break;
				}
			}

		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}
}
