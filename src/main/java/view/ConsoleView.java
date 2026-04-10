package view;

import controller.DictionaryController;
import model.Association;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Vista de consola para la aplicación de diccionario francés-español.
 *
 * Gestiona la entrada y salida de usuario y delega la lógica al controlador.
 */
public class ConsoleView {

    private static final String DICT_FILE = "data/diccionario.txt";
    private static final String TEXT_FILE = "data/texto.txt";

    private final DictionaryController controller;
    private final Scanner              scanner;

    /**
     * Crea una vista de consola con su propio controlador.
     */
    public ConsoleView() {
        this.controller = new DictionaryController();
        this.scanner    = new Scanner(System.in);
    }

    /**
     * Inicia la aplicación interactiva.
     */
    public void start() {
        initialLoad();

        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();
            System.out.println();

            switch (choice) {
                case "1" -> handleInsertWord();
                case "2" -> handleSearchWord();
                case "3" -> handleInOrder();
                case "4" -> handleTranslateSentence();
                case "5" -> handleTranslateFile();
                case "6" -> handleProfiling();
                case "0" -> running = false;
                default  -> System.out.println("Opción inválida.\n");
            }
        }

        System.out.println("Hasta luego.\n");
        scanner.close();
    }

    private void initialLoad() {
        System.out.println("Cargando diccionario...");
        try {
            controller.loadDictionary(DICT_FILE);
            System.out.printf("Diccionario cargado: %d palabras\n\n", controller.getDictionarySize());
        } catch (IOException e) {
            System.out.println("No se pudo cargar diccionario: " + e.getMessage());
            System.out.println("Continúe sin diccionario.\n");
        }
    }


    private void handleInsertWord() {
        System.out.print("Palabra en francés: ");
        String french = scanner.nextLine().trim();
        System.out.print("Traducción al español: ");
        String spanish = scanner.nextLine().trim();

        if (french.isEmpty() || spanish.isEmpty()) {
            System.out.println("Campo vacío.\n");
            return;
        }

        controller.insertWord(french, spanish);
        System.out.println("Insertado: " + french + " -> " + spanish + "\n");
    }

    private void handleSearchWord() {
        System.out.print("Palabra en francés: ");
        String french = scanner.nextLine().trim();

        String result = controller.searchWord(french);
        if (result != null) {
            System.out.println(french + " = " + result + "\n");
        } else {
            System.out.println(french + " no encontrado.\n");
        }
    }

    private void handleInOrder() {
        List<Association<String, String>> list = controller.getInOrderList();

        if (list.isEmpty()) {
            System.out.println("Diccionario vacío.\n");
            return;
        }

        for (Association<String, String> a : list) {
            System.out.println(a);
        }
        System.out.println("\nTotal: " + list.size() + " palabras\n");
    }

    private void handleTranslateSentence() {
        System.out.print("Oración en francés: ");
        String sentence = scanner.nextLine().trim();

        if (sentence.isEmpty()) {
            System.out.println("Oración vacía.\n");
            return;
        }

        String translated = controller.translateSentence(sentence);
        System.out.println("Original: " + sentence);
        System.out.println("Traducido: " + translated + "\n");
    }

    private void handleTranslateFile() {
        try {
            List<String> lines = controller.loadAndTranslateFile(TEXT_FILE);
            System.out.println();
            for (String line : lines) {
                System.out.println(line);
            }
            System.out.println();
        } catch (IOException e) {
            System.out.println("Error al leer " + TEXT_FILE + ": " + e.getMessage() + "\n");
        }
    }

    private void handleProfiling() {
        List<Association<String, String>> sample = controller.getInOrderList();

        if (sample.size() < 10) {
            System.out.println("Diccionario muy pequeño para profiling.\n");
            return;
        }

        List<String> searchWords = new java.util.ArrayList<>();
        for (Association<String, String> a : sample) {
            searchWords.add(a.getKey());
        }

        List<String> results = controller.runProfiling(sample, searchWords);
        System.out.println();
        for (String r : results) {
            System.out.println(r);
        }
        System.out.println();
    }

    private void printMenu() {
        System.out.println("\n1. Insertar palabra");
        System.out.println("2. Buscar palabra");
        System.out.println("3. Mostrar diccionario");
        System.out.println("4. Traducir oración");
        System.out.println("5. Traducir archivo");
        System.out.println("6. Profiling");
        System.out.println("0. Salir");
        System.out.print("\nOpción: ");
    }
}
