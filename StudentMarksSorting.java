import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentMarksSorting {
    static int max = 10;
    static Scanner sc = new Scanner(System.in);
    static int[] stdMarks = new int[10];
    static int count = 0;
    static boolean running = true;

    // Sorting work with stdMarks Array copy
    private static void printResult(String title, int[] arr, int comparisons, int swaps) {
        System.out.println("\n" + title);
        System.out.println("Sorted Array:");
        for (int v : arr) {
            System.out.print(v + " ");
        }
        System.out.println("\n");
        System.out.println("Number of Comparisons: " + comparisons);
        System.out.println("Number of Swaps: " + swaps + "\n");
    }
    private static void bubbleSort() {
        if (count == 0) {
            System.out.println("No Records Found. \n");
            return;
        }
        int[] arr = Arrays.copyOf(stdMarks, count);
        int n = arr.length;
        int comparisons = 0, swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                comparisons++;                      // every neighbour check
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swaps++;
                }
            }
        }
        printResult("Bubble Sort", arr, comparisons, swaps);
    }
    private static void selectionSort() {
        if (count == 0) {
            System.out.println("No Records Found. \n");
            return;
        }
        int[] arr = Arrays.copyOf(stdMarks, count);
        int n = arr.length;
        int comparisons = 0, swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                comparisons++;
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            if (minIdx != i) {                      // count only real swaps
                int temp = arr[minIdx];
                arr[minIdx] = arr[i];
                arr[i] = temp;
                swaps++;
            }
        }
        printResult("Selection Sort", arr, comparisons, swaps);
    }
    private static void insertionSort() {
        if (count == 0) {
            System.out.println("No Records Found. \n");
            return;
        }
        int[] arr = Arrays.copyOf(stdMarks, count);
        int n = arr.length;
        int comparisons = 0, swaps = 0;

        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0) {
                comparisons++;                      // compare arr[j] with key
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];            // shift right
                    swaps++;
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
        }
        printResult("Insertion Sort", arr, comparisons, swaps);
    }

    //Adding StdMarks elements
    private static int readMark(int studentNo) {
        while (true) {
            System.out.print("Enter " + studentNo + " Student Mark : ");
            int marks = sc.nextInt();      // letters go to the catch in the caller
            sc.nextLine();
            if (marks >= 0 && marks <= 100) {
                return marks;
            }
            System.out.println("Enter marks in range 0 to 100");
        }
    }
    private static void addingStdMarks() {
        if(count == max){
            System.out.println("Array is Full");
            return;
        }
        int room  = max - count;
        try {
            int length;
            System.out.print("How many marks to add (1 to " + room + ") : ");
            length = sc.nextInt();
            sc.nextLine();
            if (length < 1 || length > room) {
                System.out.println("Invalid Length input \n");
                return;
            }
            int startCount = count;
                try {
                    for (int i = 0; i < length; i++) {
                        stdMarks[count] = readMark(count + 1);
                        count++;
                    }
                } catch (InputMismatchException e) {
                    sc.nextLine();
                    count=startCount;
                    System.out.println("Invalid Mark Input. This batch was not saved. \n");
                    return;
                }
            System.out.println("Student Marks Entered Successfully. \n");
        }
        catch (InputMismatchException e) {
            sc.nextLine();
            System.out.println("Invalid Length Input \n");
        }
    }

    //Display
    private static void printArray(String label, int[] arr) {
        System.out.print(label + " [ ");
        for (int v : arr) {
            System.out.print(v + " ");
        }
        System.out.println("]\n");
    }
    private static void displayStudentMarks() {
        if (count == 0) {
            System.out.println("No Records Found. \n");
            return;
        }
        printArray("Marks :", Arrays.copyOf(stdMarks, count));
    }

    //Menu
    private static void menu(){
        int result;
        System.out.println("1. Enter Student Marks\n" +
                "2. Display Marks\n" +
                "3. Bubble Sort\n" +
                "4. Selection Sort\n" +
                "5. Insertion Sort\n" +
                "6. Clear Student Marks\n" +
                "7. Exit");
        System.out.print("Enter your choice : ");
        try{
            result = sc.nextInt();
            sc.nextLine();
            switch(result) {
                case 1 :
                    addingStdMarks();
                    break;
                case 2 :
                    displayStudentMarks();
                    break;
                case 3 :
                    bubbleSort();
                    break;
                case 4 :
                    selectionSort();
                    break;
                case 5 :
                    insertionSort();
                    break;
                case 6:
                    count = 0;
                    System.out.println("Student Marks cleared. \n");
                    break;
                case 7 :
                    System.out.println("Exiting...");
                    running=false;
                    break;
                default:
                    System.out.println("\u001B[31mInvalid Choice...\u001B[0m\n");
                    break;
            }
        }catch(InputMismatchException e){
            sc.nextLine();
            System.out.println("Invalid Input \n");
        }
    }

    public static void main(String[] args) {

        while(running){
            menu();
        }
    }

}
