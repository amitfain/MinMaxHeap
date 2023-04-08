package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static src.MinMaxHeapUtils.*;

public class Main {
    public static List<Map<Integer, String>> options;

    public static void main(String[] args) {
        initMaps();
        UserInterface ui = new UserInterface(options);
        handleEntryMenu(ui);
    }

    public static void handleEntryMenu(UserInterface ui) {
        loop:while (true) {
            ui.printMenu(0);
            int choice = ui.getIntInput();
            switch (choice) {
                case 1 -> {
                    System.out.println("Enter file name:");
                    Heap a = FileHandler.getHeapFromFile(ui.getStringInput());
                    if (a != null) {
                        handleMainMenu(ui, a);
                        break loop;
                    }
                }
                case 0 -> {
                    break loop;
                }
                default -> System.out.println("Invalid number");
            }
        }
    }

    public static void handleMainMenu(UserInterface ui, Heap a) {
        loop: while (true) {
            System.out.println();
            ui.printMenu(1);
            int choice = ui.getIntInput();
            int i = 0;
            switch (choice) {
                case 1,5,6-> {
                    System.out.println("Enter param:");
                    i = ui.getIntInput();
                }
                case 2,3,4,7,8-> {}
                case 0-> {
                    break loop;
                }
                default -> {
                    System.out.println("Invalid number");
                    continue;
                }
            }
            switch (choice) {
                case 1-> heapify(a, i);
                case 2-> buildHeap(a);
                case 3-> System.out.println("Extracted " + heapExtractMax(a));
                case 4-> System.out.println("Extracted " + heapExtractMin(a));
                case 5-> heapInsert(a, i);
                case 6-> heapDelete(a, i);
                case 7-> {
                    heapSort(a);
                    System.out.print("sorted heap: ");
                    a.printArray();
                    System.out.println();
                }
                case 8-> {
                    a.printHeap();
                    System.out.println();
                }
            }
        }
    }

    public static void initMaps() {
        options = new ArrayList<>();
        Map<Integer, String> entry_options = new HashMap<>();
        entry_options.put(1, "LOAD HEAP");
        entry_options.put(0, "Exit");
        options.add(entry_options);

        Map<Integer, String> main_options = new HashMap<>();
        main_options.put(1, "HEAPIFY(A,i)");
        main_options.put(2, "BUILD-HEAP(A)");
        main_options.put(3, "HEAP-EXTRACT-MAX(A)");
        main_options.put(4, "HEAP-EXTRACT-MIN(A)");
        main_options.put(5, "HEAP-INSERT(A,key)");
        main_options.put(6, "HEAP-DELETE(A,i)");
        main_options.put(7, "HEAP-SORT(A)");
        main_options.put(8, "PRINT");
        main_options.put(0, "EXIT");
        options.add(main_options);
    }
}