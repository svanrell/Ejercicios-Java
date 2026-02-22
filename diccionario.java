import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class diccionario {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<Character, ArrayList<String>> diccionario = new HashMap<>();

        String palabra1 = "casa";
        String palabra2 = "coche";
        String palabra3 = "barco";
        String palabra = sc.nextLine().toLowerCase();

        agregarPalabra(diccionario, palabra1);
        agregarPalabra(diccionario, palabra2);
        agregarPalabra(diccionario, palabra3);
        agregarPalabra(diccionario, palabra);
    
        sc.close();
        System.out.println(diccionario);
    }

    public static void agregarPalabra(HashMap<Character, ArrayList<String>> diccionario , String palabra) {
        char letra = palabra.charAt(0);

        if (!diccionario.containsKey(letra)) {
            diccionario.put(letra, new ArrayList<String>());
        }
        diccionario.get(letra).add(palabra);
    }
}