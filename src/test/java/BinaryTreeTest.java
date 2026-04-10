package test;

import model.Association;
import model.BinaryTree;

public class BinaryTreeTest {

    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {
        testInsertUnElemento();
        testInsertVariosElementos();
        testBuscarElementoExistente();
        testBuscarElementoNoExistente();

        System.out.println();
        System.out.println("Pruebas completadas: " + passed + " correctas, " + failed + " fallidas.");
        if (failed > 0) System.exit(1);
    }

    static void testInsertUnElemento() {
        BinaryTree<Association<String, String>> arbol = newTree();
        arbol.insert(assoc("oui", "si"));
        assertEqual("Insertar un elemento", 1, arbol.size());
    }

    static void testInsertVariosElementos() {
        BinaryTree<Association<String, String>> arbol = newTree();
        arbol.insert(assoc("maison", "casa"));
        arbol.insert(assoc("chien", "perro"));
        arbol.insert(assoc("ville", "pueblo"));
        assertEqual("Insertar varios elementos", 3, arbol.size());
    }

    static void testBuscarElementoExistente() {
        BinaryTree<Association<String, String>> arbol = newTree();
        arbol.insert(assoc("maison", "casa"));
        Association<String, String> resultado = arbol.search(assoc("maison", null));
        assertNotNull("Buscar elemento existente", resultado);
        assertEqual("Buscar elemento existente - valor", "casa", resultado.getValue());
    }

    static void testBuscarElementoNoExistente() {
        BinaryTree<Association<String, String>> arbol = newTree();
        arbol.insert(assoc("maison", "casa"));
        Association<String, String> resultado = arbol.search(assoc("chien", null));
        assertNull("Buscar elemento no existente", resultado);
    }

    private static BinaryTree<Association<String, String>> newTree() {
        return new BinaryTree<>();
    }

    private static Association<String, String> assoc(String key, String value) {
        return new Association<>(key, value);
    }

    private static void assertEqual(String label, Object expected, Object actual) {
        if (expected == null ? actual == null : expected.equals(actual)) {
            pass(label);
        } else {
            fail(label, "esperado <" + expected + "> pero fue <" + actual + ">");
        }
    }

    private static void assertNotNull(String label, Object obj) {
        if (obj != null) {
            pass(label);
        } else {
            fail(label, "resultado nulo");
        }
    }

    private static void assertNull(String label, Object obj) {
        if (obj == null) {
            pass(label);
        } else {
            fail(label, "se esperaba nulo pero no lo fue");
        }
    }

    private static void pass(String label) {
        System.out.println("OK: " + label);
        passed++;
    }

    private static void fail(String label, String message) {
        System.out.println("ERROR: " + label + " - " + message);
        failed++;
    }
}
