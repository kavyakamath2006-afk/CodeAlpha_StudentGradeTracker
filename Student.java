public class Student {

private String name;  
private int rollNumber;  
private int marks;  

public Student(String name, int rollNumber, int marks) {  
    this.name = name;  
    this.rollNumber = rollNumber;  
    this.marks = marks;  
}  

public String getName() {  
    return name;  
}  

public int getRollNumber() {  
    return rollNumber;  
}  

public int getMarks() {  
    return marks;  
}  

public void setMarks(int marks) {  
    this.marks = marks;  
}  

public String getGrade() {  
    if (marks >= 90) {  
        return "A+";  
    } else if (marks >= 80) {  
        return "A";  
    } else if (marks >= 70) {  
        return "B";  
    } else if (marks >= 60) {  
        return "C";  
    } else if (marks >= 40) {  
        return "D";  
    } else {  
        return "F";  
    }  
}  

public String getResult() {  
    if (marks >= 40) {  
        return "PASS";  
    } else {  
        return "FAIL";  
    }  
}

}
