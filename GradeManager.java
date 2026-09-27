import java.util.ArrayList;

public class GradeManager {

    private ArrayList<Student> students;

    public GradeManager() {
        students = new ArrayList<>();
    }

    public boolean addStudent(Student student) {
        if (findStudent(student.getRollNumber()) != null) {
            return false;
        }

        students.add(student);
        return true;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public Student findStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }

        return null;
    }

    public boolean deleteStudent(int rollNumber) {
        Student student = findStudent(rollNumber);

        if (student != null) {
            students.remove(student);
            return true;
        }

        return false;
    }

    public boolean updateMarks(int rollNumber, int newMarks) {
        Student student = findStudent(rollNumber);

        if (student != null) {
            student.setMarks(newMarks);
            return true;
        }

        return false;
    }

    public double calculateAverage() {
        if (students.isEmpty()) {
            return 0;
        }

        int total = 0;

        for (Student student : students) {
            total += student.getMarks();
        }

        return (double) total / students.size();
    }

    public int getHighestMarks() {
        if (students.isEmpty()) {
            return 0;
        }

        int highest = students.get(0).getMarks();

        for (Student student : students) {
            if (student.getMarks() > highest) {
                highest = student.getMarks();
            }
        }

        return highest;
    }

    public int getLowestMarks() {
        if (students.isEmpty()) {
            return 0;
        }

        int lowest = students.get(0).getMarks();

        for (Student student : students) {
            if (student.getMarks() < lowest) {
                lowest = student.getMarks();
            }
        }

        return lowest;
    }

    public int getPassCount() {
        int count = 0;

        for (Student student : students) {
            if (student.getResult().equals("PASS")) {
                count++;
            }
        }

        return count;
    }

    public int getFailCount() {
        int count = 0;

        for (Student student : students) {
            if (student.getResult().equals("FAIL")) {
                count++;
            }
        }

        return count;
    }
public Student getTopStudent() {

    if (students.isEmpty()) {
        return null;
    }

    Student topStudent = students.get(0);

    for (Student student : students) {
        if (student.getMarks() > topStudent.getMarks()) {
            topStudent = student;
        }
    }

    return topStudent;
}
public int getGradeCount(String grade) {

    int count = 0;

    for (Student student : students) {
        if (student.getGrade().equals(grade)) {
            count++;
        }
    }

    return count;
}

public ArrayList<Student> getTopStudents(int count) {

    ArrayList<Student> sortedStudents = new ArrayList<>(students);

    sortedStudents.sort((s1, s2) ->
            Integer.compare(s2.getMarks(), s1.getMarks()));

    if (sortedStudents.size() > count) {
        return new ArrayList<>(sortedStudents.subList(0, count));
    }

    return sortedStudents;
}

}

