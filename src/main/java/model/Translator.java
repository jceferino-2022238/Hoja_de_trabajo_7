package model;

/**
 * Traduce oraciones del francés al español usando un diccionario.
 *
 * Las palabras conocidas se reemplazan por su traducción.
 * Las palabras desconocidas se devuelven entre asteriscos.
 */
public class Translator {

    private final Dictionary dictionary;

    /**
     * Crea un traductor con el diccionario proporcionado.
     *
     * @param dictionary el diccionario francés-español
     */
    public Translator(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    /**
     * Traduce una oración francesa al español.
     *
     * @param sentence la oración en francés
     * @return la oración traducida
     */
    public String translateSentence(String sentence) {
        if (sentence == null || sentence.isEmpty()) return "";

        String[] tokens = sentence.split("\\s+");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            String translated = translateToken(token);
            result.append(translated);
            if (i < tokens.length - 1) result.append(' ');
        }

        return result.toString();
    }

    /**
     * Traduce un token individual (palabra con puntuación adjunta).
     *
     * @param token el token original
     * @return el token traducido
     */
    private String translateToken(String token) {
        if (token.isEmpty()) return token;

        int start = 0;
        while (start < token.length() && !Character.isLetter(token.charAt(start))) start++;

        int end = token.length();
        while (end > start && !Character.isLetter(token.charAt(end - 1))) end--;

        String leading    = token.substring(0, start);
        String word       = token.substring(start, end);
        String trailing   = token.substring(end);

        if (word.isEmpty()) {
            return token;
        }

        String translation = dictionary.translate(word);

        if (translation != null) {
            String translated = restoreCapitalisation(word, translation);
            return leading + translated + trailing;
        } else {
            return leading + "*" + word + "*" + trailing;
        }
    }

    /**
     * Si la palabra original empieza con mayúscula, 
     * capitaliza la traducción. Capitalizar significa poner la primera 
     * letra en mayúscula y el resto en minúscula.
     *
     * @param original    palabra francesa original
     * @param translation traducción al español en minúsculas
     * @return traducción con mayúscula inicial si corresponde
     */
    private String restoreCapitalisation(String original, String translation) {
        if (translation.isEmpty()) return translation;
        if (Character.isUpperCase(original.charAt(0))) {
            return Character.toUpperCase(translation.charAt(0)) + translation.substring(1);
        }
        return translation;
    }
}
