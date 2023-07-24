import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MaxMin A= new MaxMin();
        Scanner option=new Scanner(System.in);
        System.out.println("Hello and welcome to the Max-Min Heap toolbox.");
        System.out.println("In order to build from the Array.txt, it must be in the same folder as this program");
        System.out.println("And must be formatted with each value in a separate line");
        System.out.println("1. Build heap from Array.txt file");
        System.out.println("2. Exit");
        System.out.print("Please select an option: ");
        switch (option.nextInt()){
            case 1:
                A.buildHeap(readArrayFromFile("Array.txt"));
                System.out.println("\nPrinting the Heap:");
                A.printHeap();
                break;
            case 2:
                System.out.println("Have a nice day!");
                return;
        }
        while(true){
            System.out.println("\nOptions menu:");
            System.out.println("1. Extract the maximum value of the heap");
            System.out.println("2. Extract the minimum value of the heap");
            System.out.println("3. Insert a new element into the heap");
            System.out.println("4. Delete an element from the heap");
            System.out.println("5. Print the heap");
            System.out.println("6. Exit");
            System.out.print("Please select an option: ");

            switch (option.nextInt()){
                case 1:
                    System.out.print("\nThe maximum value is: ");
                    System.out.print(A.heapExtractMax());
                    System.out.print("\nThe corrected heap after extracting is:\n");
                    A.printHeap();
                    break;
                case 2:
                    System.out.print("\nThe minimum value is: ");
                    System.out.print(A.heapExtractMin());
                    System.out.print("\nThe corrected heap after extracting is:\n");
                    A.printHeap();
                    break;
                case 3:
                    System.out.print("\nPlease type the value you would like to insert: ");
                    A.heapInsert(option.nextInt());
                    break;
                case 4:
                    System.out.print("\nPlease type the value you would like to delete: ");
                    A.heapDelete(option.nextInt());
                    break;
                case 5:
                    System.out.println("\nPrinting the Heap:");
                    A.printHeap();
                    break;
                case 6:
                    System.out.println("Have a nice day!");
                    return;
            }
        }
    }
    public static int[] readArrayFromFile(String fileName){
        int count = 0;
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                scanner.nextLine();
                count++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            // Handle file not found error
        }

        int[] array = new int[count];
        try (Scanner scanner = new Scanner(new File(fileName))) {
            for (int i = 0; i < count; i++) {
                String line = scanner.nextLine();
                int element = Integer.parseInt(line);
                array[i] = element;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            // Handle file not found error
        }

        return array;
    }
}
