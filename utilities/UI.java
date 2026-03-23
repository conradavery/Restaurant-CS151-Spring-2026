package utilities;

public class UI {
    
    public static void printHeader(String title) {
            System.out.println();
            System.out.println("===============================================");
            System.out.println(centerText(title, 47));
            System.out.println("===============================================");
    }

    public static void printSection(String title) {
        System.out.println();
        System.out.println(title);
        System.out.println("-----------------------------------------------");
    }

    public static void success(String msg) {
        System.out.println("[SUCCESS] " + msg);
    }

    public static void error(String msg) {
        System.out.println("[ERROR] " + msg);
    }

    public static void info(String msg) {
        System.out.println("[INFO] " + msg);
    }

    public static String money(double amount) {
        return String.format("$%.2f", amount);
    }

    private static String centerText(String text, int width) {
        if (text.length() >= width) return text;
        int padding = (width - text.length()) / 2;
        return " ".repeat(padding) + text;
    }
}
