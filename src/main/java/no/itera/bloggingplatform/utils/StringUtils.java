package no.itera.bloggingplatform.utils;

import java.util.Collection;

public final class StringUtils {

    private StringUtils() {
    }

    /**
     * Joins Strings in elements into one String
     * by using separator between elements.
     */
    public static String join(Collection<String> elements, String separator) {
        StringBuilder sb = new StringBuilder();

        for (String element : elements) {
            sb.append(element).append(separator);
        }

        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }
}
