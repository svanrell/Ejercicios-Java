import java.util.Scanner; // Importa Scanner para leer datos introducidos por teclado.

public class palindromo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Crea el objeto Scanner asociado a la entrada estándar.
        System.out.println("Ingrese una palabra:"); // Pedir una palabra al usuario.
        String s = sc.nextLine(); // Lee la línea escrita por el usuario y la guarda en s.

        s = s.toLowerCase().replace(" ", ""); // Convierte la cadena a minúsculas y elimina espacios.

        String palabraDerecha = "";
        for (int i = s.length() - 1; i >= 0; i--) { // Recorre la cadena desde el último carácter al primero.
            palabraDerecha += s.charAt(i); // Añade cada carácter al final de palabraDerecha para invertirla.
        } 

        boolean isPalindrome = s.equals(palabraDerecha); // Compara la palabra original con la invertida.
        if (isPalindrome) { // Comprueba si ambas cadenas son iguales.
            System.out.println("\"" + s + "\" es un palíndromo.");
        } else { // Se ejecuta cuando la palabra no coincide con su versión invertida.
            System.out.println("\"" + s + "\" no es un palíndromo.");
        } 
        sc.close(); // Cierra el Scanner para liberar el recurso de entrada.
    } 
} 
