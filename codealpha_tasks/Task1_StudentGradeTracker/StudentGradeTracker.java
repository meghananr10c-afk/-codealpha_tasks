package Task1_StudentGradeTracker;
import java.util.Scanner;

public class StudentGradeTracker {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        sc.nextLine();

        if (n <= 0) {
            System.out.println("Number of students must be positive.");
            sc.close();
            return;
        }

        String[] names = new String[n];
        double[] marks = new double[n];

        double total = 0;
        double highest = -1;
        double lowest = 101;

        for (int i = 0; i < n; i++) {

            System.out.print("Enter student name: ");
            names[i] = sc.nextLine();

            System.out.print("Enter marks out of 100: ");
            marks[i] = sc.nextDouble();
            sc.nextLine();

            if (marks[i] < 0 || marks[i] > 100) {
                System.out.println("Invalid marks. Enter marks from 0 to 100.");
                i--;
                continue;
            }

            total += marks[i];

            if (marks[i] > highest) {
                highest = marks[i];
            }

            if (marks[i] < lowest) {
                lowest = marks[i];
            }
        }

        double average = total / n;

        System.out.println("\n===== STUDENT GRADE REPORT =====");

        for (int i = 0; i < n; i++) {
            System.out.println(
                names[i] + " : " + marks[i]
            );
        }

        System.out.println("Average Marks: " + average);
        System.out.println("Highest Marks: " + highest);
        System.out.println("Lowest Marks: " + lowest);

        sc.close();
    }
}