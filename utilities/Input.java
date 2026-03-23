package utilities;

import java.util.Scanner;
public class Input {
    
    public static Scanner scanner = new Scanner(System.in);

    public static double getDouble(){
        String input = scanner.nextLine();
        checkExit(input);
        return Double.parseDouble(input);
    }
    public static int getInt(){
        String input = scanner.nextLine();
        checkExit(input);
        return Integer.parseInt(input);
    }
    public static String getString(){
        String input = scanner.nextLine();
        checkExit(input);
        return input;
    }
    private static void checkExit(String input){
        if (input.equals("EXIT")){
            System.exit(0);
            
        }
    }
}
