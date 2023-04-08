package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class FileHandler
 * Manages all interactions with files
 * */
public class FileHandler {
    public static Heap getHeapFromFile(String file_name) {
        try (Scanner s = new Scanner(new File(file_name))) {
            List<Integer> arr = new ArrayList<>();
            int counter = 0;
            while (s.hasNextInt()){
                arr.add(s.nextInt());
                ++counter;
            }
            return new Heap(arr, counter);
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            return null;
        }
    }
}
