import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        GradeManager manager = new GradeManager();

        while (true) {

            System.out.println("\n===== STUDENT GRADE TRACKER =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Marks");
            System.out.println("5. Delete Student");
            System.out.println("6. Show Statistics");
            System.out.println("7. Summary Report");
            System.out.println("8. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    addStudent(scanner, manager);
                    break;

                case 2:
                    viewStudents(manager);
                    break;

                case 3:
                    searchStudent(scanner, manager);
                    break;

                case 4:
                    updateMarks(scanner, manager);
                    break;

                case 5:
                    deleteStudent(scanner, manager);
                    break;

                case 6:
                    showStatistics(manager);
                    break;

                case 7:
                    showSummary(manager);
                    break;

                case 8:
                    System.out.println("Thank you for using Student Grade Tracker!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void addStudent(Scanner scanner, GradeManager manager) {

        scanner.nextLine();
        String name;
        while(true){

        System.out.print("Enter student name: ");
        name = scanner.nextLine();
        if(!name.trim().isEmpty()){
            break;
        }
        System.out.println("Name cannot be empty.Please enter a valid name.");
        }
        int rollNumber;
        while(true){
        System.out.print("Enter roll number: ");
        rollNumber = scanner.nextInt();
        if(manager.findStudent(rollNumber)==null){
            break;
        }
            System.out.println("Roll number alread exists.please enter different roll number.");
        }

        int marks;
        while(true){
        System.out.print("Enter marks: ");
        marks = scanner.nextInt();

        if (marks >= 0 && marks <= 100) {
            break;
        }
            System.out.println("Invalid marks.please enter Marks between 0 and 100.");
        }

        Student student = new Student(name, rollNumber, marks);

        if (manager.addStudent(student)) {
            System.out.println("Student added successfully!");
        } else {
            System.out.println("Roll number already exists.");
        }
    }

    public static void viewStudents(GradeManager manager) {

    if (manager.getStudents().isEmpty()) {
        System.out.println("No students available.");
        return;
    }

    System.out.println("\n================ ALL STUDENTS ================");
    System.out.printf("%-12s %-12s %-8s %-8s %-8s%n",
            "Name", "Roll No", "Marks", "Grade", "Result");
    System.out.println("-----------------------------------------------");

    for (Student student : manager.getStudents()) {

        System.out.printf("%-12s %-12d %-8d %-8s %-8s%n",
                student.getName(),
                student.getRollNumber(),
                student.getMarks(),
                student.getGrade(),
                student.getResult());
    }

    System.out.println("===============================================");
}


    public static void searchStudent(Scanner scanner, GradeManager manager) {

        System.out.print("Enter roll number to search: ");
        int rollNumber = scanner.nextInt();

        Student student = manager.findStudent(rollNumber);

        if (student != null) {

            System.out.println("\nStudent Found!");
            System.out.println("Name: " + student.getName());
            System.out.println("Roll Number: " + student.getRollNumber());
            System.out.println("Marks: " + student.getMarks());
            System.out.println("Grade: " + student.getGrade());
            System.out.println("Result: " + student.getResult());

        } else {
            System.out.println("Student not found.");
        }
    }

    public static void updateMarks(Scanner scanner, GradeManager manager) {

        System.out.print("Enter roll number: ");
        int rollNumber = scanner.nextInt();

        System.out.print("Enter new marks: ");
        int newMarks = scanner.nextInt();

        if (newMarks < 0 || newMarks > 100) {
            System.out.println("Invalid marks. Marks must be between 0 and 100.");
            return;
        }

        if (manager.updateMarks(rollNumber, newMarks)) {
            System.out.println("Marks updated successfully!");
        } else {
            System.out.println("Student not found.");
        }
    }

    public static void deleteStudent(Scanner scanner, GradeManager manager) {

        System.out.print("Enter roll number to delete: ");
        int rollNumber = scanner.nextInt();

        if (manager.deleteStudent(rollNumber)) {
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student not found.");
        }
    }

    public static void showStatistics(GradeManager manager) {

        if (manager.getStudents().isEmpty()) {
            System.out.println("No student data available.");
            return;
        }

        System.out.println("\n----- CLASS STATISTICS -----");
        System.out.println("Total Students: " + manager.getStudents().size());
        System.out.println("Class Average score: " + manager.calculateAverage());
        System.out.println("Highest Marks: " + manager.getHighestMarks());
        System.out.println("Lowest Marks: " + manager.getLowestMarks());
        System.out.println("Passed Students: " + manager.getPassCount());
        System.out.println("Failed Students: " + manager.getFailCount());
        double passPercentage =
        (double) manager.getPassCount() / manager.getStudents().size() * 100;

        System.out.printf("Pass Percentage: %.2f%%%n", passPercentage);
        Student topStudent = manager.getTopStudent();

        System.out.println("\nTop Performing Student:");
        System.out.println("Name: " + topStudent.getName());
        System.out.println("Roll Number: " + topStudent.getRollNumber());
        System.out.println("Marks: " + topStudent.getMarks());
        System.out.println("Grade: " + topStudent.getGrade());
        System.out.println("\n===== GRADE DISTRIBUTION =====");
System.out.println("A+ : " + manager.getGradeCount("A+") + " students");
System.out.println("A : " + manager.getGradeCount("A") + " students");
System.out.println("B : " + manager.getGradeCount("B") + " students");
System.out.println("C : " + manager.getGradeCount("C") + " students");
System.out.println("D : " + manager.getGradeCount("D") + " students");
System.out.println("F : " + manager.getGradeCount("F") + " students");

    }

public static void showSummary(GradeManager manager) {

    if (manager.getStudents().isEmpty()) {
        System.out.println("No student data available.");
        return;
    }

    int totalStudents = manager.getStudents().size();
    int passed = manager.getPassCount();
    int failed = manager.getFailCount();

    double average = manager.calculateAverage();
    double passRate = (double) passed / totalStudents * 100;

    System.out.println("\n===============================================================");
    System.out.println("                    CLASS SUMMARY REPORT");
    System.out.println("===============================================================");

    // Overall Performance
    System.out.println("\n1. Overall Performance");
    System.out.println("---------------------------------------------------------------");
    System.out.println("Total Students       : " + totalStudents);
    System.out.printf("Class Average        : %.2f%%%n", average);
    System.out.printf("Pass Rate            : %.2f%%%n", passRate);
    System.out.println("Passed / Failed      : " + passed + " / " + failed);

    // Top 3 Performers
    System.out.println("\n2. Top 3 Performers");
    System.out.println("---------------------------------------------------------------");

    System.out.printf("%-7s %-8s %-14s %-12s %-14s %-8s%n",
            "Rank", "ID", "Name", "Marks", "Percentage", "Grade");

    var topStudents = manager.getTopStudents(3);

    int rank = 1;

    for (Student student : topStudents) {

        System.out.printf("%-7d %-8d %-14s %-12s %-14s %-8s%n",
                rank,
                student.getRollNumber(),
                student.getName(),
                student.getMarks() + "/100",
                String.format("%.2f%%",(double) student.getMarks()),
                student.getGrade());

        rank++;
    }

    // Students Needing Attention
    System.out.println("\n3. Students Needing Attention");
    System.out.println("---------------------------------------------------------------");

    System.out.printf("%-8s %-14s %-12s %-10s%n",
            "ID", "Name", "Marks", "Status");

    boolean failedStudentFound = false;

    for (Student student : manager.getStudents()) {

        if (student.getResult().equals("FAIL")) {

            System.out.printf("%-8d %-14s %-12s %-10s%n",
                    student.getRollNumber(),
                    student.getName(),
                    student.getMarks() + "/100",
                    student.getResult());

            failedStudentFound = true;
        }
    }

    if (!failedStudentFound) {
        System.out.println("No students need attention.");
    }

    // Grade Distribution
    System.out.println("\n4. Grade Distribution");
    System.out.println("---------------------------------------------------------------");

    System.out.println("A+     : " + manager.getGradeCount("A+") + " students");
    System.out.println("A      : " + manager.getGradeCount("A") + " students");
    System.out.println("B      : " + manager.getGradeCount("B") + " students");
    System.out.println("C      : " + manager.getGradeCount("C") + " students");
    System.out.println("D      : " + manager.getGradeCount("D") + " students");
    System.out.println("F      : " + manager.getGradeCount("F") + " students");

    System.out.println("\n===============================================================");
}


}
