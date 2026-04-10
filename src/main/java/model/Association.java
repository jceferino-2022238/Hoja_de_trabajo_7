package model;

/**
 * Representa una asociación clave-valor para el diccionario francés-español.
 *
 * @param <K> tipo de clave que debe ser Comparable
 * @param <V> tipo de valor de la traducción
 */

// Se hace uso de genericos para permitir flexibilidad en el 
// tipo de clave y valor.
public class Association<K extends Comparable<K>, V> implements Comparable<Association<K, V>> {

    private K key;
    private V value;

    /**
     * Crea una asociación con clave y valor.
     *
     * @param key   la clave (palabra en francés)
     * @param value el valor (traducción al español)
     */
    public Association(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Devuelve la clave de esta asociación.
     *
     * @return la clave
     */
    public K getKey() {
        return key;
    }

    /**
     * Devuelve el valor de esta asociación.
     *
     * @return el valor
     */
    public V getValue() {
        return value;
    }

    /**
     * Actualiza el valor de la asociación.
     *
     * @param value el nuevo valor
     */
    public void setValue(V value) {
        this.value = value;
    }

    /**
     * Compara esta asociación con otra según su clave.
     *
     * @param other la otra asociación
     * @return negativo, cero o positivo según el orden de las claves
     */
    @Override
    public int compareTo(Association<K, V> other) {
        return this.key.compareTo(other.key);
    }

    @Override
    public String toString() {
        return "(" + key + ", " + value + ")";
    }
    // equals y hashCode se basan solo en la clave para permitir 
    // reemplazo de traducciones
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Association)) return false;
        Association<?, ?> other = (Association<?, ?>) obj;
        return key.equals(other.key);
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }
}
