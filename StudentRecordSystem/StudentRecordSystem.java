import Components.Student;

import java.sql.SQLOutput;
import java.util.*;

public class StudentRecordSystem {
    static Scanner sc = new Scanner(System.in);
    static List<Student> stdList = new ArrayList<>();
    static boolean running = true;
    public static void createStudent(){
        System.out.println("Enter a new Student");
        int rollNo;
        String stdName;
        double stdMarks;
        try{
            System.out.print("Enter std Roll no : ");
            rollNo=sc.nextInt();
            sc.nextLine();
            for (int i = 0; i < stdList.size(); i++) {
                if (stdList.get(i).getRollno() == rollNo) {
                    System.out.println("\u001B[31mWarning: Roll no already exists\u001B[0m\n");
                    return;
                }
            }
            System.out.print("Enter Student Name : ");
            stdName=sc.nextLine();
            if(stdName.trim().isEmpty()){
                System.out.println("Invalid Student Name..");
                return;
            }
            System.out.print("Enter Student Marks : ");
            stdMarks=sc.nextDouble();
            sc.nextLine();
            if(stdMarks < 0 || stdMarks > 100){
                System.out.println("Enter student marks in range 0 to 100");
                return;
            }

            stdList.add(new Student(rollNo,stdName,stdMarks));
            sortByRollNo();
            System.out.println("\u001B[32m✔ Student created successfully!\u001B[0m\n");
        }catch (InputMismatchException e){
            sc.nextLine();
            System.out.println("Invalid input. Roll no = number, Name = String , Marks = number");
            return;
        }
    }
    static public void displayStudent(){
        if(stdList.isEmpty()){
            System.out.println("\u001B[31mWarning: No Record Found\u001B[0m\n");
            return;
        }

        printHeader();
        for (Student s : stdList) {
            printRow(s);
        }
        printFooter();
    }
    static public void sortByRollNo(){
        //sorting the array
//        stdList.sort(Comparator.comparingInt(Student::getRollno));
//        stdList.sort(Comparator.comparingInt(s -> s.getRollno()));
        stdList.sort((a,b) -> a.getRollno() - b.getRollno());
    }
    static public void linearSearch(int choice, String value){
        boolean found = false;
        for (int i = 0; i < stdList.size(); i++) {
            Student s = stdList.get(i);
            boolean match;
            if (choice == 1) {
                match = s.getStd_name().equalsIgnoreCase(value.trim());
            } else {
                match = String.valueOf(s.getRollno()).equals(value.trim());
            }
            if (match) {
                System.out.println("Using Linear Search Student Found at " + i +" idx");
                printHeader();
                printRow(s);
                printFooter();
                found = true;
                break;
            }
        }
        if(!found){
            System.out.println("\u001B[31mWarning: Student Not Found\u001B[0m\n");
        }
    }
    static public void binarySearch(int key){
        boolean studentFound = false;
        int str = 0;
        int max = stdList.size()-1;
            while(str <= max){
//                int mid = str+(max-str) / 2;
                int mid = (max+str) / 2;
//                System.out.println("mid : "+mid);
                if(stdList.get(mid).getRollno() == key){
                    studentFound = true;
                    System.out.println("Using Binary Search Student Found at " + mid +" idx");
                    printHeader();
                    printRow(stdList.get(mid));
                    printFooter();
                    break;
                }
                else if(stdList.get(mid).getRollno() < key){
                    str = mid+1;
                }
                else {
                    max = mid-1;
                }

            }
            if(!studentFound){
                System.out.println("\u001B[31mWarning: Student Not Found\u001B[0m\n");
            }
    }

    static void printHeader() {
        System.out.println("-----------------------------------");
        System.out.printf("%-10s %-15s %-10s%n", "Roll No", "Name", "Marks");
        System.out.println("-----------------------------------");
    }
    static void printRow(Student s) {
        System.out.printf("%-10d %-15s %-10.2f%n",
                s.getRollno(), s.getStd_name(), s.getMarks());
    }
    static void printFooter() {
        System.out.println("----------------------------------- \n");
    }

    public static void menu(){
        int result;
        int key;
        System.out.println("1. Add Student Records\n" +
                "2. Display Student Records\n" +
                "3. Search using Linear Search\n" +
                "4. Search using Binary Search\n" +
                "5. Exit");
        System.out.print("Enter your choice : ");
        try{
            result = sc.nextInt();
            sc.nextLine();
            switch(result) {
                case 1 :
                    createStudent();
                    break;
                case 2 :
                    displayStudent();
                    break;
                case 3 :
                    try{
                        System.out.println("Find the Student \n"+"1. By Student Name\n" +
                                "2. By Student Roll No\n" +
                                "3. Exit");
                        System.out.print("Input : ");
                        int no = sc.nextInt();
                        sc.nextLine();
                        switch(no) {
                            case 1:
                                    System.out.print("Enter Student Name : ");
                                    String input = sc.nextLine();
                                    if(input.trim().isEmpty()) {
                                        System.out.println("Invalid student Name");
                                        break;
                                    }
                                    linearSearch(no,input);
                                break;
                            case 2:
                                try{
                                    System.out.print("Enter Student Roll No : ");
                                    int rollNo = sc.nextInt();
                                    sc.nextLine();
                                    linearSearch(no,String.valueOf(rollNo));
                                }
                                catch (InputMismatchException e){
                                    sc.nextLine();
                                    System.out.println("Invalid Input");
                                }
                                break;
                            case 3:
                                System.out.println("Exiting Linear Search option..");
                                break;
                            default:
                                System.out.println("Invalid Input");
                                break;
                        }
                    }catch(InputMismatchException e){
                        sc.nextLine();
                        System.out.println("Invalid Input");
                    }
                    break;
                case 4 :
                    try{
                        System.out.print("Enter a Student Roll No to Search : ");
                        key = sc.nextInt();
                        sc.nextLine();
                        binarySearch(key);
                    }catch(InputMismatchException e){
                        sc.nextLine();
                        System.out.println("Invalid Input");
                    }
                    break;
                case 5 :
                    System.out.println("\u001B[31mExiting...\u001B[0m\n");
                    running = false;
                    break;
                default:
                    System.out.println("\u001B[31mInvalid Choice...\u001B[0m\n");
                    break;
            }
        }catch(InputMismatchException e){
            sc.nextLine();
            System.out.println("Invalid Input");
        }
    }

    public static void main(String[] args) {
//        Student std = new Student(1,"piyush",93.23);
//        std.displayStudent();
//        stdList.add(std);
//        stdList.add(new Student(2,"atharv",88.23));
//        System.out.println(stdList);

        while(running){
            menu();
        }
        sc.close();
    }
}
