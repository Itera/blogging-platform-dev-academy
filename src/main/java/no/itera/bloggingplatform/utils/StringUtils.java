package no.itera.bloggingplatform.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

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
        String sep = Optional.ofNullable(separator).orElse(DEFAULT_SEPARATOR);

        return Optional.ofNullable(elements).orElse(Collections.emptyList()).stream()
                .filter(Objects::nonNull)
                .reduce((left, right) -> left + sep + right)
                .orElse(EMPTY_STRING);
    }
}
