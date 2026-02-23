import java.util.Random;
import javax.swing.JOptionPane;

public class raya {
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        menu_principal();
    }
    public static void jugarDosJugadores() {
        char[][] tablero = new char[3][3];
        char jugadorActual = 'X';
        int turno = 0;

        while (turno < 9) {
            JOptionPane.showMessageDialog(null, tableroComoTexto(tablero), "Tablero", JOptionPane.INFORMATION_MESSAGE);

            int fila = pedirEnteroEnRango("Turno de " + jugadorActual + "\nIntroduce fila (0-2):", 0, 2);
            if (fila == Integer.MIN_VALUE) {
                return;
            }

            int columna = pedirEnteroEnRango("Turno de " + jugadorActual + "\nIntroduce columna (0-2):", 0, 2);
            if (columna == Integer.MIN_VALUE) {
                return;
            }

            if (tablero[fila][columna] != '\0') {
                JOptionPane.showMessageDialog(null, "Casilla ocupada, elige otra.", "Movimiento inválido", JOptionPane.WARNING_MESSAGE);
                continue;
            }

            tablero[fila][columna] = jugadorActual;
            turno++;

            if (hayVictoria(tablero, jugadorActual)) {
                JOptionPane.showMessageDialog(null, tableroComoTexto(tablero), "Tablero final", JOptionPane.INFORMATION_MESSAGE);
                JOptionPane.showMessageDialog(null, "Ha ganado el jugador " + jugadorActual + "!", "Fin de partida", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            jugadorActual = (jugadorActual == 'X') ? 'O' : 'X';
        }

        JOptionPane.showMessageDialog(null, tableroComoTexto(tablero), "Tablero final", JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(null, "Empate!", "Fin de partida", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void jugarContraRobot() {
        char[][] tablero = new char[3][3];
        char jugadorHumano = 'X';
        char jugadorRobot = 'O';
        int turno = 0;

        while (turno < 9) {
            JOptionPane.showMessageDialog(null, tableroComoTexto(tablero), "Tablero", JOptionPane.INFORMATION_MESSAGE);

            if (turno % 2 == 0) {
                int fila = pedirEnteroEnRango("Tu turno (" + jugadorHumano + ")\nIntroduce fila (0-2):", 0, 2);
                if (fila == Integer.MIN_VALUE) {
                    return;
                }

                int columna = pedirEnteroEnRango("Tu turno (" + jugadorHumano + ")\nIntroduce columna (0-2):", 0, 2);
                if (columna == Integer.MIN_VALUE) {
                    return;
                }

                if (tablero[fila][columna] != '\0') {
                    JOptionPane.showMessageDialog(null, "Casilla ocupada, elige otra.", "Movimiento inválido", JOptionPane.WARNING_MESSAGE);
                    continue;
                }

                tablero[fila][columna] = jugadorHumano;
                if (hayVictoria(tablero, jugadorHumano)) {
                    JOptionPane.showMessageDialog(null, tableroComoTexto(tablero), "Tablero final", JOptionPane.INFORMATION_MESSAGE);
                    JOptionPane.showMessageDialog(null, "Has ganado!", "Fin de partida", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            } else {
                if (!jugarRobot(tablero, jugadorRobot)) {
                    break;
                }

                if (hayVictoria(tablero, jugadorRobot)) {
                    JOptionPane.showMessageDialog(null, tableroComoTexto(tablero), "Tablero final", JOptionPane.INFORMATION_MESSAGE);
                    JOptionPane.showMessageDialog(null, "El robot ha ganado!", "Fin de partida", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }

            turno++;
        }

        JOptionPane.showMessageDialog(null, tableroComoTexto(tablero), "Tablero final", JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(null, "Empate!", "Fin de partida", JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean jugarRobot(char[][] tablero, char fichaRobot) {
        int filas = tablero.length;
        int columnas = tablero[0].length;
        int libres = 0;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (tablero[i][j] == '\0') {
                    libres++;
                }
            }
        }

        if (libres == 0) {
            return false;
        }

        int fila;
        int columna;

        do {
            fila = RANDOM.nextInt(filas);
            columna = RANDOM.nextInt(columnas);
        } while (tablero[fila][columna] != '\0');

        tablero[fila][columna] = fichaRobot;
        JOptionPane.showMessageDialog(null, "El robot juega en: [" + fila + ", " + columna + "]", "Turno del robot", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }

    public static boolean hayVictoria(char[][] tablero, char jugador) {
        return victoriaHorizontal(tablero, jugador)
            || victoriaVertical(tablero, jugador)
            || victoriaDiagonal(tablero, jugador);
    }

    public static boolean victoriaHorizontal(char[][] tablero, char jugador) {
        for (int i = 0; i < tablero.length; i++) {
            if (tablero[i][0] == jugador
                && tablero[i][1] == jugador
                && tablero[i][2] == jugador) {
                return true;
            }
        }
        return false;
    }

    public static boolean victoriaVertical(char[][] tablero, char jugador) {
        for (int j = 0; j < tablero[0].length; j++) {
            if (tablero[0][j] == jugador
                && tablero[1][j] == jugador
                && tablero[2][j] == jugador) {
                return true;
            }
        }
        return false;
    }

    public static boolean victoriaDiagonal(char[][] tablero, char jugador) {
        if (tablero[0][0] == jugador
            && tablero[1][1] == jugador
            && tablero[2][2] == jugador) {
            return true;
        }
        if (tablero[0][2] == jugador
            && tablero[1][1] == jugador
            && tablero[2][0] == jugador) {
            return true;
        }
        return false;
    }

    public static void menu_principal() {
        String[] opciones = {
            "Un jugador (maquina)",
            "Multijugador (dos jugadores)",
            "Salir"
        };

        int opcion = JOptionPane.showOptionDialog(
            null,
            "=== TRES EN RAYA ===\nElige una opcion:",
            "Menu principal",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            opciones,
            opciones[0]
        );

        switch (opcion) {
            case 0:
                jugarContraRobot();
                break;
            case 1:
                jugarDosJugadores();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Saliendo del juego.", "Tres en raya", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static int pedirEnteroEnRango(String mensaje, int min, int max) {
        while (true) {
            String entrada = JOptionPane.showInputDialog(null, mensaje, "Entrada", JOptionPane.QUESTION_MESSAGE);

            if (entrada == null) {
                int confirmarSalida = JOptionPane.showConfirmDialog(
                    null,
                    "Quieres salir de la partida?",
                    "Confirmar salida",
                    JOptionPane.YES_NO_OPTION
                );
                if (confirmarSalida == JOptionPane.YES_OPTION) {
                    return Integer.MIN_VALUE;
                }
                continue;
            }

            try {
                int valor = Integer.parseInt(entrada.trim());
                if (valor >= min && valor <= max) {
                    return valor;
                }
            } catch (NumberFormatException e) {
                // Repetir entrada si no es un numero.
            }

            JOptionPane.showMessageDialog(
                null,
                "Debes introducir un numero entre " + min + " y " + max + ".",
                "Entrada invalida",
                JOptionPane.WARNING_MESSAGE
            );
        }
    }

    private static String tableroComoTexto(char[][] tablero) {
        StringBuilder sb = new StringBuilder();
        sb.append("  0   1   2\n");

        for (int i = 0; i < tablero.length; i++) {
            sb.append(i).append(" ");
            for (int j = 0; j < tablero[i].length; j++) {
                char casilla = tablero[i][j];
                sb.append((casilla == '\0') ? '.' : casilla);
                if (j < 2) {
                    sb.append(" | ");
                }
            }
            sb.append("\n");
            if (i < 2) {
                sb.append("  ---------\n");
            }
        }
        return sb.toString();
    }
}
