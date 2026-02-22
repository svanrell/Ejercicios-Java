import java.util.Scanner;

public class palindromo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese una palabra:");
        String s = sc.nextLine();

        s = s.toLowerCase().replace(" ", "");

        String palabraDerecha = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            palabraDerecha += s.charAt(i);
        }

        boolean isPalindrome = s.equals(palabraDerecha);
        if (isPalindrome) {
            System.out.println("\"" + s + "\" es un palíndromo.");
        } else {
            System.out.println("\"" + s + "\" no es un palíndromo.");
        }
        sc.close();
    }
}
