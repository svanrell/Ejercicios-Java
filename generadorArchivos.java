import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class generadorArchivos {
    static class LogEntry implements Serializable {
        private static final long serialVersionUID = 1L;

        private final String fechaHora;
        private final String mensaje;

        public LogEntry(String mensaje) {
            this.fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            this.mensaje = mensaje;
        }

        @Override
        public String toString() {
            return "[" + fechaHora + "] " + mensaje;
        }
    }

    public static void guardarLogTxt(List<LogEntry> entradas, String rutaFichero) {
        try {
            FileWriter escritor = new FileWriter(rutaFichero, true); 
            BufferedWriter escritorBuffer = new BufferedWriter(escritor);

            for (LogEntry entrada : entradas) {
                escritorBuffer.write(entrada.toString());
                escritorBuffer.newLine();
            }

            escritorBuffer.close();
            System.out.println("Log .txt guardado en: " + rutaFichero);

        } catch (IOException error) {
            System.out.println("Error al escribir el fichero .txt: " + error.getMessage());
        }
    }

    public static void leerLogTxt(String rutaFichero) {
        try {
            FileReader lector = new FileReader(rutaFichero);
            BufferedReader lectorBuffer = new BufferedReader(lector);
            String linea;

            while ((linea = lectorBuffer.readLine()) != null) {
                System.out.println(linea);
            }

            lectorBuffer.close();
        } catch (IOException error) {
            System.out.println("Error al leer el fichero .txt: " + error.getMessage());
        }
    }

    public static void guardarLogSer(List<LogEntry> entradas, String rutaFichero) {
        try {
            FileOutputStream flujoSalida = new FileOutputStream(rutaFichero);
            ObjectOutputStream escritorObjetos = new ObjectOutputStream(flujoSalida);

            escritorObjetos.writeObject(entradas);
            escritorObjetos.close();

            System.out.println("Log .ser guardado en: " + rutaFichero);

        } catch (IOException error) {
            System.out.println("Error al serializar el fichero .ser: " + error.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static void leerLogSer(String rutaFichero) {
        try {
            FileInputStream flujoEntrada = new FileInputStream(rutaFichero);
            ObjectInputStream lectorObjetos = new ObjectInputStream(flujoEntrada);

            List<LogEntry> entradas = (List<LogEntry>) lectorObjetos.readObject();
            lectorObjetos.close();

            System.out.println("\n--- Contenido del log serializado ---");
            for (LogEntry entrada : entradas) {
                System.out.println(entrada);
            }

        } catch (IOException | ClassNotFoundException error) {
            System.out.println("Error al deserializar el fichero .ser: " + error.getMessage());
        }
    }
    public static void main(String[] args) {
        List<LogEntry> registros = new ArrayList<>();
        registros.add(new LogEntry("Aplicación iniciada correctamente"));
        registros.add(new LogEntry("Usuario 'admin' ha iniciado sesión"));
        registros.add(new LogEntry("Intento de acceso a recurso restringido"));
        registros.add(new LogEntry("Conexión con la base de datos fallida"));
        registros.add(new LogEntry("Aplicación cerrada"));

        String rutaTxt = "registro.log.txt";
        guardarLogTxt(registros, rutaTxt);

        System.out.println("\n--- Contenido del log .txt ---");
        leerLogTxt(rutaTxt);

        String rutaSer = "registro.log.ser";
        guardarLogSer(registros, rutaSer);
        leerLogSer(rutaSer);
    }
}