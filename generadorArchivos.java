import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter; 
import java.util.ArrayList;
import java.util.List; 

public class generadorArchivos {
    static class LogEntry implements Serializable {
        private static final long serialVersionUID = 1L; // ID de versión para serialización.

        private final String fechaHora; // Guarda la fecha/hora del log.
        private final String mensaje; // Guarda el mensaje del log.

        public LogEntry(String mensaje) {
            this.fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); // Fecha/hora actual con formato.
            this.mensaje = mensaje; // Mensaje recibido por parámetro.
        }

        @Override
        public String toString() {
            return "[" + fechaHora + "] " + mensaje; // Texto final de cada entrada de log.
        }
    }

    public static void guardarLogTxt(List<LogEntry> entradas, String rutaFichero) {
        try {
            FileWriter escritor = new FileWriter(rutaFichero, true); // Abre/crea archivo txt en modo append.
            BufferedWriter escritorBuffer = new BufferedWriter(escritor); // Buffer para escribir más eficiente.

            for (LogEntry entrada : entradas) {
                escritorBuffer.write(entrada.toString()); // Escribe una entrada.
                escritorBuffer.newLine(); // Salto de línea.
            }

            escritorBuffer.close(); // Cierra el buffer (y también el writer interno).
            System.out.println("Log .txt guardado en: " + rutaFichero); // Mensaje de éxito.

        } catch (IOException error) {
            System.out.println("Error al escribir el fichero .txt: " + error.getMessage()); // Muestra error de escritura.
        }
    }

    public static void leerLogTxt(String rutaFichero) {
        try {
            FileReader lector = new FileReader(rutaFichero); // Abre archivo txt para leer.
            BufferedReader lectorBuffer = new BufferedReader(lector); // Buffer de lectura.
            String linea; // Variable para guardar cada línea leída.

            while ((linea = lectorBuffer.readLine()) != null) {
                System.out.println(linea); // Imprime cada línea del archivo.
            }

            lectorBuffer.close(); // Cierra el buffer (y el reader).
        } catch (IOException error) {
            System.out.println("Error al leer el fichero .txt: " + error.getMessage()); // Muestra error de lectura.
        }
    }

    public static void guardarLogSer(List<LogEntry> entradas, String rutaFichero) {
        try {
            FileOutputStream flujoSalida = new FileOutputStream(rutaFichero); // Abre archivo binario para salida.
            ObjectOutputStream escritorObjetos = new ObjectOutputStream(flujoSalida); // Writer para objetos serializados.

            escritorObjetos.writeObject(entradas); // Serializa toda la lista de entradas.
            escritorObjetos.close(); // Cierra el stream.

            System.out.println("Log .ser guardado en: " + rutaFichero); // Mensaje de éxito.

        } catch (IOException error) {
            System.out.println("Error al serializar el fichero .ser: " + error.getMessage()); // Muestra error de serialización.
        }
    }

    @SuppressWarnings("unchecked")
    public static void leerLogSer(String rutaFichero) {
        try {
            FileInputStream flujoEntrada = new FileInputStream(rutaFichero); // Abre archivo .ser para leer.
            ObjectInputStream lectorObjetos = new ObjectInputStream(flujoEntrada); // Reader de objetos.

            List<LogEntry> entradas = (List<LogEntry>) lectorObjetos.readObject(); // Recupera la lista serializada.
            lectorObjetos.close(); // Cierra el stream.

            System.out.println("\n--- Contenido del log serializado ---"); // Título en consola.
            for (LogEntry entrada : entradas) {
                System.out.println(entrada); // Muestra cada entrada deserializada.
            }

        } catch (IOException | ClassNotFoundException error) {
            System.out.println("Error al deserializar el fichero .ser: " + error.getMessage()); // Muestra error de lectura .ser.
        }
    }
    public static void main(String[] args) {
        List<LogEntry> registros = new ArrayList<>(); // Lista donde se guardan los logs de ejemplo.
        registros.add(new LogEntry("Aplicación iniciada correctamente")); // Entrada 1.
        registros.add(new LogEntry("Usuario 'admin' ha iniciado sesión")); // Entrada 2.
        registros.add(new LogEntry("Intento de acceso a recurso restringido")); // Entrada 3.
        registros.add(new LogEntry("Conexión con la base de datos fallida")); // Entrada 4.
        registros.add(new LogEntry("Aplicación cerrada")); // Entrada 5.

        String rutaTxt = "registro.log.txt"; // Nombre del archivo txt.
        guardarLogTxt(registros, rutaTxt); // Guarda los logs en txt.

        System.out.println("\n--- Contenido del log .txt ---"); // Título para mostrar el txt.
        leerLogTxt(rutaTxt); // Lee e imprime el txt.

        String rutaSer = "registro.log.ser"; // Nombre del archivo serializado.
        guardarLogSer(registros, rutaSer); // Guarda la lista en .ser.
        leerLogSer(rutaSer); // Lee el .ser y lo muestra.
    }
}
