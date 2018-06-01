package no.itera.bloggingplatform.utils;

import java.util.Collection;
import java.util.Objects;

import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.joining;

public final class StringUtils {

    private static final String DEFAULT_SEPARATOR = ",";

    private StringUtils() {
    }

    /**
     * Joins Strings in elements into one String
     * by using separator between elements.
     */
    public static String join(Collection<String> elements, String separator) {
        String sep = ofNullable(separator).orElse(DEFAULT_SEPARATOR);

        return ofNullable(elements).orElse(emptyList()).stream()
                .filter(Objects::nonNull)
                .collect(joining(sep));
    }
}
