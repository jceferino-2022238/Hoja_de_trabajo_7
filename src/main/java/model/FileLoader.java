package model;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase para cargar archivos de datos.
 * El diccionario usa líneas con asociaciones en formato (francés, español).
 */
public class FileLoader {

    /**
     * Captura líneas con el formato (francés, español).
     */
    private static final Pattern ASSOC_PATTERN =
            Pattern.compile("\\(\\s*([^,]+?)\\s*,\\s*([^)]+?)\\s*\\)");

    /** Constructor privado: no se instancia esta clase. */
    private FileLoader() {}

    /**
     * Carga asociaciones desde un archivo de diccionario.
     *
     * @param filePath   ruta al archivo de diccionario
     * @param dictionary diccionario que se va a rellenar
     * @throws IOException si no se puede leer el archivo
     */
    public static void loadDictionary(String filePath, Dictionary dictionary) throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                Matcher matcher = ASSOC_PATTERN.matcher(line);
                if (matcher.find()) {
                    String french  = matcher.group(1).trim();
                    String spanish = matcher.group(2).trim();
                    dictionary.insert(french, spanish);
                }
            }
        }
    }

    /**
     * Lee todas las líneas de un archivo de texto.
     *
     * @param filePath ruta al archivo de texto
     * @return lista de líneas leídas
     * @throws IOException si no se puede leer el archivo
     */
    public static List<String> loadTextFile(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }
}
