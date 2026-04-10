package model;

import java.util.List;

/**
 * Utilidad para medir el tiempo de inserción y búsqueda en el diccionario.
 */
public class Profiler {

    /** Constructor privado: clase utilitaria. */
    private Profiler() {}

    /**
     * Mide la inserción de asociaciones en un diccionario.
     *
     * @param associations lista de asociaciones a insertar
     * @return resultado de perfilado con datos de tiempo
     */
    public static ProfilingResult profileInsert(List<Association<String, String>> associations) {
        Dictionary dict = new Dictionary();
        long start = System.nanoTime();

        for (Association<String, String> a : associations) {
            dict.insert(a.getKey(), a.getValue());
        }

        long elapsed = System.nanoTime() - start;
        return new ProfilingResult("Insert", associations.size(), elapsed);
    }

    /**
     * Mide la búsqueda de palabras en un diccionario ya cargado.
     *
     * @param dictionary  diccionario ya poblado
     * @param searchWords palabras francesas para buscar
     * @return resultado de perfilado con datos de tiempo
     */
    public static ProfilingResult profileSearch(Dictionary dictionary, List<String> searchWords) {
        long start = System.nanoTime();

        for (String word : searchWords) {
            dictionary.translate(word);
        }

        long elapsed = System.nanoTime() - start;
        return new ProfilingResult("Search", searchWords.size(), elapsed);
    }

    // Resultado del profiling con datos de tiempo y 
    // conteo de operaciones
    public static class ProfilingResult {
        private final String operation;
        private final int    count;
        private final long   elapsedNanos;

        public ProfilingResult(String operation, int count, long elapsedNanos) {
            this.operation    = operation;
            this.count        = count;
            this.elapsedNanos = elapsedNanos;
        }

        public String getOperation()    { return operation;    }
        public int    getCount()        { return count;        }
        public long   getElapsedNanos() { return elapsedNanos; }
        public double getElapsedMs()    { return elapsedNanos / 1_000_000.0; }
        public double getAvgNsPerOp()   { return count == 0 ? 0 : (double) elapsedNanos / count; }

        @Override
        public String toString() {
            return String.format(
                "[Profiler] %s — %d operations | Total: %.3f ms | Avg per op: %.2f ns",
                operation, count, getElapsedMs(), getAvgNsPerOp());
        }
    }
}
