package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación genérica de un árbol binario de búsqueda (BST).
 *
 * @param <E> tipo de elemento que debe implementar Comparable
 */

// Cuando algo es recursivo se refiere a sí mismo, 
// como en este caso el árbol que se llama a sí mismo 
// para insertar o buscar.

public class BinaryTree<E extends Comparable<E>> {

    private BSTNode<E> root;
    private int size;

    /** Crea un BST vacío. */
    public BinaryTree() {
        root = null;
        size = 0;
    }

    /**
     * Inserta el elemento en el BST.
     * Si la clave ya existe, reemplaza el elemento anterior.
     *
     * @param element el elemento a insertar
     */
    public void insert(E element) {
        root = insertRecursive(root, element);
        size++;
    }

    /**
     * Ayuda recursiva para la inserción.
     *
     * @param node    raíz del subárbol actual
     * @param element elemento a insertar
     * @return la raíz del subárbol actualizado
     */
    private BSTNode<E> insertRecursive(BSTNode<E> node, E element) {
        if (node == null) {
            return new BSTNode<>(element);
        }

        int cmp = element.compareTo(node.getData());

        if (cmp < 0) {
            node.setLeft(insertRecursive(node.getLeft(), element));
        } else if (cmp > 0) {
            node.setRight(insertRecursive(node.getRight(), element));
        } else {
            node.setData(element);
            size--; // cancelar el incremento previo en insert()
        }

        return node;
    }

    /**
     * Busca un elemento en el BST.
     *
     * @param element el elemento a buscar
     * @return el elemento encontrado, o {@code null} si no existe
     */
    public E search(E element) {
        BSTNode<E> found = searchRecursive(root, element);
        return (found == null) ? null : found.getData();
    }

    /**
     * Ayuda recursiva para la búsqueda.
     *
     * @param node    raíz del subárbol actual
     * @param element elemento a buscar
     * @return el nodo que contiene el elemento, o {@code null}
     */
    private BSTNode<E> searchRecursive(BSTNode<E> node, E element) {
        if (node == null) {
            return null;
        }

        int cmp = element.compareTo(node.getData());

        if (cmp == 0) {
            return node;
        } else if (cmp < 0) {
            return searchRecursive(node.getLeft(), element);
        } else {
            return searchRecursive(node.getRight(), element);
        }
    }

    /**
     * Devuelve los elementos en orden ascendente.
     *
     * @return lista ordenada de elementos
     */
    public List<E> inOrder() {
        List<E> result = new ArrayList<>();
        inOrderRecursive(root, result);
        return result;
    }

    /**
     * Recorrido recursivo in-order (Izquierda → Nodo → Derecha).
     *
     * @param node   raíz del subárbol actual
     * @param result lista acumuladora
     */
    private void inOrderRecursive(BSTNode<E> node, List<E> result) {
        if (node == null) return;
        inOrderRecursive(node.getLeft(), result);
        result.add(node.getData());
        inOrderRecursive(node.getRight(), result);
    }

    /**
     * Devuelve el número de elementos almacenados en el árbol.
     *
     * @return tamaño del árbol
     */
    public int size() {
        return size;
    }

    /**
     * Devuelve {@code true} si el árbol está vacío.
     *
     * @return {@code true} si no contiene elementos
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Devuelve el nodo raíz (visible en el paquete para pruebas).
     *
     * @return nodo raíz del BST
     */
    BSTNode<E> getRoot() {
        return root;
    }
}
