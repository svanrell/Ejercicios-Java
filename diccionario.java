import java.util.ArrayList;
import java.util.HashMap; 
import java.util.Scanner;

public class diccionario {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Scanner para leer lo que escribe el usuario.
        HashMap<Character, ArrayList<String>> diccionario = new HashMap<>(); // Diccionario principal.

        String palabra1 = "casa"; // Palabra fija de ejemplo.
        String palabra2 = "coche"; // Palabra fija de ejemplo.
        String palabra3 = "barco"; // Palabra fija de ejemplo.
        String palabra = sc.nextLine().toLowerCase(); // Lee palabra del usuario y la pasa a minúsculas.

        agregarPalabra(diccionario, palabra1); // Guarda "casa".
        agregarPalabra(diccionario, palabra2); // Guarda "coche".
        agregarPalabra(diccionario, palabra3); // Guarda "barco".
        agregarPalabra(diccionario, palabra); // Guarda la palabra del usuario.
    
        sc.close(); // Cierra el scanner.
        System.out.println(diccionario); // Muestra el contenido final del diccionario.
    }

    public static void agregarPalabra(HashMap<Character, ArrayList<String>> diccionario , String palabra) {
        char letra = palabra.charAt(0); // Toma la primera letra de la palabra.

        if (!diccionario.containsKey(letra)) { // Si esa letra no existe todavía...
            diccionario.put(letra, new ArrayList<String>()); // ...crea una lista vacía para esa letra.
        }
        diccionario.get(letra).add(palabra); // Añade la palabra en la lista de su letra.
    }
}
