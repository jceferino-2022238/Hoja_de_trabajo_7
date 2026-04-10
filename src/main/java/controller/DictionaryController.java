package controller;

import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador de la aplicación de diccionario francés-español.
 *
 * Enlaza la vista con el modelo sin contener lógica de interfaz.
 */
public class DictionaryController {

    private final Dictionary  dictionary;
    private final Translator  translator;

    /**
     * Crea el controlador e inicializa el diccionario y el traductor.
     */
    public DictionaryController() {
        this.dictionary = new Dictionary();
        this.translator = new Translator(dictionary);
    }

    /**
     * Carga el archivo de diccionario en el BST.
     *
     * @param filePath ruta al archivo de diccionario
     * @throws IOException si no se puede leer el archivo
     */
    public void loadDictionary(String filePath) throws IOException {
        FileLoader.loadDictionary(filePath, dictionary);
    }

    /**
     * Carga un archivo de texto francés y devuelve las líneas traducidas.
     *
     * @param filePath ruta al archivo de texto
     * @return lista de líneas traducidas
     * @throws IOException si no se puede leer el archivo
     */
    public List<String> loadAndTranslateFile(String filePath) throws IOException {
        List<String> rawLines = FileLoader.loadTextFile(filePath);
        List<String> translated = new ArrayList<>();
        for (String line : rawLines) {
            translated.add(translator.translateSentence(line));
        }
        return translated;
    }

    /**
     * Inserta un par de palabras en el diccionario.
     *
     * @param french  la palabra en francés
     * @param spanish la traducción en español
     */
    public void insertWord(String french, String spanish) {
        dictionary.insert(french, spanish);
    }

    /**
     * Busca la traducción de una palabra francesa.
     *
     * @param french la palabra en francés
     * @return la traducción en español, o {@code null} si no existe
     */
    public String searchWord(String french) {
        return dictionary.translate(french);
    }

    /**
     * Devuelve todas las entradas del diccionario en orden alfabético.
     *
     * @return lista ordenada de asociaciones
     */
    public List<Association<String, String>> getInOrderList() {
        return dictionary.inOrderList();
    }

    /**
     * Traduce una oración francesa.
     *
     * @param sentence la oración en francés
     * @return la oración traducida
     */
    public String translateSentence(String sentence) {
        return translator.translateSentence(sentence);
    }


    /**
     * Ejecuta profiling de inserción y búsqueda.
     *
     * @param associations asociaciones para insertar durante el profiling
     * @param searchWords  palabras para buscar durante el profiling
     * @return lista de resultados en formato de texto
     */
    public List<String> runProfiling(
            List<Association<String, String>> associations,
            List<String> searchWords) {

        List<String> results = new ArrayList<>();

        Profiler.ProfilingResult insertResult = Profiler.profileInsert(associations);
        results.add(insertResult.toString());

        Dictionary profilingDict = new Dictionary();
        for (Association<String, String> a : associations) {
            profilingDict.insert(a.getKey(), a.getValue());
        }

        Profiler.ProfilingResult searchResult = Profiler.profileSearch(profilingDict, searchWords);
        results.add(searchResult.toString());

        return results;
    }

    /**
     * Devuelve la cantidad de entradas en el diccionario.
     *
     * @return tamaño del diccionario
     */
    public int getDictionarySize() {
        return dictionary.size();
    }
}
