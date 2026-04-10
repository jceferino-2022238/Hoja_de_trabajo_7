package model;

import java.util.List;

/**
 * Diccionario francés-español que usa un árbol binario de búsqueda.
 */
public class Dictionary {

    private final BinaryTree<Association<String, String>> bst;

    /** Crea un diccionario vacío. */
    public Dictionary() {
        bst = new BinaryTree<>();
    }

    /**
     * Inserta una asociación francés -> español.
     * Las palabras se almacenan en minúsculas para búsquedas sin 
     * distinción de mayúsculas.
     *
     * @param french  la palabra en francés
     * @param spanish la traducción en español
     */
    public void insert(String french, String spanish) {
        Association<String, String> assoc =
                new Association<>(french.toLowerCase(), spanish.toLowerCase());
        bst.insert(assoc);
    }

    /**
     * Busca la traducción de una palabra francesa.
     *
     * @param french la palabra en francés
     * @return la traducción al español, o {@code null} si no se encuentra
     */
    public String translate(String french) {
        Association<String, String> key =
                new Association<>(french.toLowerCase(), null);
        Association<String, String> result = bst.search(key);
        return (result == null) ? null : result.getValue();
    }

    /**
     * Devuelve todas las asociaciones ordenadas alfabéticamente.
     *
     * @return lista ordenada de asociaciones
     */
    public List<Association<String, String>> inOrderList() {
        return bst.inOrder();
    }

    /**
     * Devuelve el número de entradas del diccionario.
     *
     * @return tamaño del diccionario
     */
    public int size() {
        return bst.size();
    }

    /**
     * Indica si el diccionario está vacío.
     *
     * @return {@code true} si no contiene entradas
     */
    public boolean isEmpty() {
        return bst.isEmpty();
    }
}
