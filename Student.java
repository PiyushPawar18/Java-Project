package Components;

import java.util.List;

public class Student {
    private int rollno;
    private String std_name;
    private double marks;

    public Student(int rollno, String std_name, double marks) {
        this.rollno = rollno;
        this.std_name = std_name;
        this.marks = marks;
    }
    public void displayStudent() {
        System.out.println(rollno + " " + std_name + " " + marks);
    }

    public int getRollno() {
        return rollno;
    }

    public String getStd_name() {
        return std_name;
    }

    public double getMarks() {
        return marks;
    }

    @Override
    public String toString() {
        return "Student{" +
                "rollno=" + rollno +
                ", std_name='" + std_name + '\'' +
                ", marks=" + marks +
                '}';
    }
}
