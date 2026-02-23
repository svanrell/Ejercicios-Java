import java.util.Random;
import javax.swing.JOptionPane;
public class raya {
    private static final Random RANDOM = new Random(); // Creamos Random

    public static void main(String[] args) {
        menu_principal(); // Empieza mostrando el menú.
    }
    public static void jugarDosJugadores() {
        char[][] tablero = new char[3][3]; // Tablero 3x3 vacío.
        char jugadorActual = 'X'; // Empieza X.
        int turno = 0; // Contador de turnos.

        while (turno < 9) {
            JOptionPane.showMessageDialog(null, tableroComoTexto(tablero), "Tablero", JOptionPane.INFORMATION_MESSAGE); // Muestra estado actual.

            int fila = pedirEnteroEnRango("Turno de " + jugadorActual + "\nIntroduce fila (0-2):", 0, 2); // Pide fila.
            if (fila == Integer.MIN_VALUE) {
                return; // Sale si el usuario decide salir.
            }

            int columna = pedirEnteroEnRango("Turno de " + jugadorActual + "\nIntroduce columna (0-2):", 0, 2); // Pide columna.
            if (columna == Integer.MIN_VALUE) {
                return; // Sale si el usuario decide salir.
            }

            if (tablero[fila][columna] != '\0') {
                JOptionPane.showMessageDialog(null, "Casilla ocupada, elige otra.", "Movimiento inválido", JOptionPane.WARNING_MESSAGE); // Casilla usada.
                continue; // Repite turno.
            }

            tablero[fila][columna] = jugadorActual; // Marca la casilla elegida.
            turno++; // Suma turno.

            if (hayVictoria(tablero, jugadorActual)) {
                JOptionPane.showMessageDialog(null, tableroComoTexto(tablero), "Tablero final", JOptionPane.INFORMATION_MESSAGE); // Muestra tablero final.
                JOptionPane.showMessageDialog(null, "Ha ganado el jugador " + jugadorActual + "!", "Fin de partida", JOptionPane.INFORMATION_MESSAGE); // Mensaje de victoria.
                return; // Termina partida.
            }

            jugadorActual = (jugadorActual == 'X') ? 'O' : 'X'; // Cambia de jugador.
        }

        JOptionPane.showMessageDialog(null, tableroComoTexto(tablero), "Tablero final", JOptionPane.INFORMATION_MESSAGE); // Muestra tablero final.
        JOptionPane.showMessageDialog(null, "Empate!", "Fin de partida", JOptionPane.INFORMATION_MESSAGE); // Mensaje de empate.
    }

    public static void jugarContraRobot() {
        char[][] tablero = new char[3][3]; // Tablero 3x3 vacío.
        char jugadorHumano = 'X'; // Ficha del jugador.
        char jugadorRobot = 'O'; // Ficha del robot.
        int turno = 0; // Contador de turnos.

        while (turno < 9) {
            JOptionPane.showMessageDialog(null, tableroComoTexto(tablero), "Tablero", JOptionPane.INFORMATION_MESSAGE); // Muestra estado actual.

            if (turno % 2 == 0) {
                int fila = pedirEnteroEnRango("Tu turno (" + jugadorHumano + ")\nIntroduce fila (0-2):", 0, 2); // Pide fila al humano.
                if (fila == Integer.MIN_VALUE) {
                    return; // Sale si el usuario decide salir.
                }

                int columna = pedirEnteroEnRango("Tu turno (" + jugadorHumano + ")\nIntroduce columna (0-2):", 0, 2); // Pide columna al humano.
                if (columna == Integer.MIN_VALUE) {
                    return; // Sale si el usuario decide salir.
                }

                if (tablero[fila][columna] != '\0') {
                    JOptionPane.showMessageDialog(null, "Casilla ocupada, elige otra.", "Movimiento inválido", JOptionPane.WARNING_MESSAGE); // Casilla ya usada.
                    continue; // Repite turno humano.
                }

                tablero[fila][columna] = jugadorHumano; // Marca jugada del humano.
                if (hayVictoria(tablero, jugadorHumano)) {
                    JOptionPane.showMessageDialog(null, tableroComoTexto(tablero), "Tablero final", JOptionPane.INFORMATION_MESSAGE); // Muestra tablero final.
                    JOptionPane.showMessageDialog(null, "Has ganado!", "Fin de partida", JOptionPane.INFORMATION_MESSAGE); // Victoria humana.
                    return; // Termina partida.
                }
            } else {
                if (!jugarRobot(tablero, jugadorRobot)) {
                    break; // Si no hay huecos, se sale.
                }

                if (hayVictoria(tablero, jugadorRobot)) {
                    JOptionPane.showMessageDialog(null, tableroComoTexto(tablero), "Tablero final", JOptionPane.INFORMATION_MESSAGE); // Muestra tablero final.
                    JOptionPane.showMessageDialog(null, "El robot ha ganado!", "Fin de partida", JOptionPane.INFORMATION_MESSAGE); // Victoria del robot.
                    return; // Termina partida.
                }
            }

            turno++; // Suma turno.
        }

        JOptionPane.showMessageDialog(null, tableroComoTexto(tablero), "Tablero final", JOptionPane.INFORMATION_MESSAGE); // Muestra tablero final.
        JOptionPane.showMessageDialog(null, "Empate!", "Fin de partida", JOptionPane.INFORMATION_MESSAGE); // Empate si nadie ganó.
    }

    public static boolean jugarRobot(char[][] tablero, char fichaRobot) {
        int filas = tablero.length; // Total de filas del tablero.
        int columnas = tablero[0].length; // Total de columnas.
        int libres = 0; // Cuenta casillas libres.

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (tablero[i][j] == '\0') {
                    libres++; // Suma una casilla vacía.
                }
            }
        }

        if (libres == 0) {
            return false; // No se puede jugar.
        }

        int fila; // Fila aleatoria candidata.
        int columna; // Columna aleatoria candidata.

        do {
            fila = RANDOM.nextInt(filas); // Elige fila al azar.
            columna = RANDOM.nextInt(columnas); // Elige columna al azar.
        } while (tablero[fila][columna] != '\0'); // Repite hasta hallar casilla libre.

        tablero[fila][columna] = fichaRobot; // Marca jugada del robot.
        JOptionPane.showMessageDialog(null, "El robot juega en: [" + fila + ", " + columna + "]", "Turno del robot", JOptionPane.INFORMATION_MESSAGE); // Muestra dónde jugó.
        return true; // Jugada hecha correctamente.
    }

    public static boolean hayVictoria(char[][] tablero, char jugador) {
        return victoriaHorizontal(tablero, jugador) // Comprueba línea horizontal.
            || victoriaVertical(tablero, jugador)
            || victoriaDiagonal(tablero, jugador);
    }

    public static boolean victoriaHorizontal(char[][] tablero, char jugador) {
        for (int i = 0; i < tablero.length; i++) {
            if (tablero[i][0] == jugador
                && tablero[i][1] == jugador
                && tablero[i][2] == jugador) {
                return true; // Hay 3 iguales en una fila.
            }
        }
        return false; // No encontró victoria horizontal.
    }

    public static boolean victoriaVertical(char[][] tablero, char jugador) {
        for (int j = 0; j < tablero[0].length; j++) {
            if (tablero[0][j] == jugador
                && tablero[1][j] == jugador
                && tablero[2][j] == jugador) {
                return true; // Hay 3 iguales en una columna.
            }
        }
        return false; // No encontró victoria vertical.
    }

    public static boolean victoriaDiagonal(char[][] tablero, char jugador) {
        if (tablero[0][0] == jugador
            && tablero[1][1] == jugador
            && tablero[2][2] == jugador) {
            return true; // Diagonal principal completa.
        }
        if (tablero[0][2] == jugador
            && tablero[1][1] == jugador
            && tablero[2][0] == jugador) {
            return true; // Diagonal secundaria completa.
        }
        return false; // No hay victoria diagonal.
    }

    public static void menu_principal() {
        String[] opciones = {
            "Un jugador (maquina)", // Modo vs robot.
            "Multijugador (dos jugadores)", // Modo 2 jugadores.
            "Salir" // Salir del juego.
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
        ); // Muestra menú y devuelve opción elegida.

        switch (opcion) {
            case 0:
                jugarContraRobot(); // Lanza modo un jugador.
                break;
            case 1:
                jugarDosJugadores(); // Lanza modo dos jugadores.
                break;
            default:
                JOptionPane.showMessageDialog(null, "Saliendo del juego.", "Tres en raya", JOptionPane.INFORMATION_MESSAGE); // Cierre simple.
        }
    }

    private static int pedirEnteroEnRango(String mensaje, int min, int max) {
        while (true) {
            String entrada = JOptionPane.showInputDialog(null, mensaje, "Entrada", JOptionPane.QUESTION_MESSAGE); // Pide dato al usuario.

            if (entrada == null) {
                int confirmarSalida = JOptionPane.showConfirmDialog(
                    null,
                    "Quieres salir de la partida?",
                    "Confirmar salida",
                    JOptionPane.YES_NO_OPTION
                ); // Confirma salida si cancela input.
                if (confirmarSalida == JOptionPane.YES_OPTION) {
                    return Integer.MIN_VALUE; // Valor especial para salir.
                }
                continue; // Si no sale, vuelve a pedir.
            }

            try {
                int valor = Integer.parseInt(entrada.trim()); // Convierte texto a número.
                if (valor >= min && valor <= max) {
                    return valor; // Número válido.
                }
            } catch (NumberFormatException e) {
                // Repetir entrada si no es un numero.
            }

            JOptionPane.showMessageDialog(
                null,
                "Debes introducir un numero entre " + min + " y " + max + ".",
                "Entrada invalida",
                JOptionPane.WARNING_MESSAGE
            ); // Aviso si el número no está en rango.
        }
    }

    private static String tableroComoTexto(char[][] tablero) {
        StringBuilder sb = new StringBuilder(); // Construye el tablero como texto.
        sb.append("  0   1   2\n"); // Cabecera de columnas.

        for (int i = 0; i < tablero.length; i++) {
            sb.append(i).append(" "); // Número de fila.
            for (int j = 0; j < tablero[i].length; j++) {
                char casilla = tablero[i][j]; // Valor de la casilla actual.
                sb.append((casilla == '\0') ? '.' : casilla); // Si está vacía, pinta '.', si no, la ficha.
                if (j < 2) {
                    sb.append(" | "); // Separador entre columnas.
                }
            }
            sb.append("\n"); // Salto de línea al final de cada fila.
            if (i < 2) {
                sb.append("  ---------\n"); 
            }
        }
        return sb.toString(); // Devuelve el tablero formateado.
    }
}
