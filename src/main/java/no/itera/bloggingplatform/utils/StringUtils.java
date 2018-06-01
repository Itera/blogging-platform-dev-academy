package no.itera.bloggingplatform.utils;

import java.util.Collection;

public final class StringUtils {

    private static final String EMPTY_STRING = "";

    private StringUtils() {
    }

    /**
     * Joins Strings in elements into one String
     * by using separator between elements.
     */
    public static String join(Collection<String> elements, String separator) {
        if (elements == null) {
            return EMPTY_STRING;
        }

        StringBuilder sb = new StringBuilder();

        for (String element : elements) {
            if (element != null) {
                sb.append(element).append(separator);
            }
        }

        if (!elements.isEmpty()) {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }
}
