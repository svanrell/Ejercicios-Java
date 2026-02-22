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
            System.out.print("Introduce fila (0-2): ");
            int fila = lector.nextInt();
            System.out.print("Introduce columna (0-2): ");
            int columna = lector.nextInt();

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

            if (jugadorActual == 'X') {
                jugadorActual = 'O';
            } else {
                jugadorActual = 'X';
            }
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
                if (casilla == '\0') {
                    System.out.print("·");
                } else {
                    System.out.print(casilla);
                }
                if (j < 2) System.out.print(" | ");
            }
            System.out.println();
            if (i < 2) System.out.println("  ---------");
        }
        System.out.println();
    }

    public static boolean hayVictoria(char[][] tablero, char jugador) {
        return victoriaHorizontal(tablero) ||
               victoriaVertical(tablero) ||
               victoriaDiagonal(tablero);
    }

    public static boolean victoriaHorizontal(char[][] matriz) {
        boolean hayVictoria = false;
        for (int i = 0; i < matriz.length; i++) {
            char primeraCasilla = matriz[i][0];

            if (primeraCasilla == '\0' || primeraCasilla == ' ') {
                continue;
            }

            boolean filaCompleta = true;
            for (int j = 1; j < matriz[i].length; j++) {
                if (matriz[i][j] != primeraCasilla) {
                    filaCompleta = false;
                    break;
                }
            }

            if (filaCompleta) {
                hayVictoria = true;
                break;
            }
        }
        return hayVictoria;
    }

    public static boolean victoriaVertical(char[][] matriz) {
        for (int j = 0; j < matriz[0].length; j++) {
            char primeraCasilla = matriz[0][j];

            if (primeraCasilla == '\0' || primeraCasilla == ' ') {
                continue;
            }

            boolean columnaCompleta = true;
            for (int i = 1; i < matriz.length; i++) {
                if (matriz[i][j] != primeraCasilla) {
                    columnaCompleta = false;
                    break;
                }
            }

            if (columnaCompleta) {
                return true;
            }
        }
        return false;
    }

    public static boolean victoriaDiagonal(char[][] matriz) {
        char esquinaIzquierda = matriz[0][0];
        if (esquinaIzquierda != '\0' && esquinaIzquierda != ' ') {
            if (matriz[1][1] == esquinaIzquierda && matriz[2][2] == esquinaIzquierda) {
                return true;
            }
        }
        char esquinaDerecha = matriz[0][2];
        if (esquinaDerecha != '\0' && esquinaDerecha != ' ') {
            if (matriz[1][1] == esquinaDerecha && matriz[2][0] == esquinaDerecha) {
                return true;
            }
        }

        return false;
    }
}