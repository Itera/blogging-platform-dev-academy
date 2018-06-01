package no.itera.bloggingplatform.utils;

import java.util.Collection;

public final class StringUtils {

    private static final String EMPTY_STRING = "";
    private static final String DEFAULT_SEPARATOR = ",";

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

        String sep = separator == null ? DEFAULT_SEPARATOR : separator;

        StringBuilder sb = new StringBuilder();

        for (String element : elements) {
            if (element != null) {
                sb.append(element).append(sep);
            }
        }

        if (!elements.isEmpty()) {
            sb.delete(sb.length() - sep.length(), sb.length());
        }

        return sb.toString();
    }
}
