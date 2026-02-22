import java.util.Scanner;

public class raya {

    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);
        char[][] tablero = new char[3][3];
        char jugadorActual = 'X';
        int turno = 0;

        System.out.println("=== TRES EN RAYA ===");

        while (turno < 9) {
            mostrarTablero(tablero);
            System.out.println("Turno del jugador " + jugadorActual);

            int fila = -1;
            int columna = -1;
            while (true) {
                System.out.print("Introduce fila (0-2): ");
                if (lector.hasNextInt()) {
                    fila = lector.nextInt();
                    if (fila >= 0 && fila <= 2) break;
                    else System.out.println("Fila fuera de rango. Intenta de nuevo.");
                } else {
                    System.out.println("Debes introducir un número.");
                    lector.next();
                }
            }


            while (true) {
                System.out.print("Introduce columna (0-2): ");
                if (lector.hasNextInt()) {
                    columna = lector.nextInt();
                    if (columna >= 0 && columna <= 2) break;
                    else System.out.println("Columna fuera de rango. Intenta de nuevo.");
                } else {
                    System.out.println("Debes introducir un número.");
                    lector.next();
                }
            }


            if (tablero[fila][columna] != '\0') {
                System.out.println("Casilla ocupada, elige otra.");
                continue;
            }


            tablero[fila][columna] = jugadorActual;
            turno++;


            if (hayVictoria(tablero, jugadorActual)) {
                mostrarTablero(tablero);
                System.out.println("¡Ha ganado el jugador " + jugadorActual + "!");
                lector.close();
                return;
            }


            jugadorActual = (jugadorActual == 'X') ? 'O' : 'X';
        }

        mostrarTablero(tablero);
        System.out.println("¡Empate!");
        lector.close();
    }


    public static void mostrarTablero(char[][] tablero) {
        System.out.println("\n  0   1   2");
        for (int i = 0; i < tablero.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < tablero[i].length; j++) {
                char casilla = tablero[i][j];
                System.out.print((casilla == '\0') ? "·" : casilla);
                if (j < 2) System.out.print(" | ");
            }
            System.out.println();
            if (i < 2) System.out.println("  ---------");
        }
        System.out.println();
    }


    public static boolean hayVictoria(char[][] tablero, char jugador) {
        return victoriaHorizontal(tablero, jugador) ||
               victoriaVertical(tablero, jugador) ||
               victoriaDiagonal(tablero, jugador);
    }

    public static boolean victoriaHorizontal(char[][] tablero, char jugador) {
        for (int i = 0; i < tablero.length; i++) {
            if (tablero[i][0] == jugador &&
                tablero[i][1] == jugador &&
                tablero[i][2] == jugador) {
                return true;
            }
        }
        return false;
    }

    public static boolean victoriaVertical(char[][] tablero, char jugador) {
        for (int j = 0; j < tablero[0].length; j++) {
            if (tablero[0][j] == jugador &&
                tablero[1][j] == jugador &&
                tablero[2][j] == jugador) {
                return true;
            }
        }
        return false;
    }

    public static boolean victoriaDiagonal(char[][] tablero, char jugador) {
        if (tablero[0][0] == jugador &&
            tablero[1][1] == jugador &&
            tablero[2][2] == jugador) {
            return true;
        }
        if (tablero[0][2] == jugador &&
            tablero[1][1] == jugador &&
            tablero[2][0] == jugador) {
            return true;
        }
        return false;
    }
}
