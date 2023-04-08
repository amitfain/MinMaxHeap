package src;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Class UserInterface
 * Provides simple API to interact with the user
 * */
public class UserInterface {
    private final List<Map<Integer, String>> m_options;
    private final Scanner m_scanner;

    public UserInterface(List<Map<Integer, String>> options) {
        m_options = options;
        m_scanner = new Scanner(System.in);
    }

    public void printMenu(int menu) {
        getMenuMap(menu).forEach((k,v) -> System.out.println(k + ": " + v));
    }

    private Map<Integer, String> getMenuMap(int menu) {
        return m_options.get(menu);
    }

    public int getIntInput() {
        while (!m_scanner.hasNextInt()) {
            System.out.println("Input is not a number.");
            m_scanner.nextLine();
        }
        int input = m_scanner.nextInt();
        m_scanner.nextLine();
        return input;
    }

    public String getStringInput() {
        return m_scanner.nextLine();
    }
}
