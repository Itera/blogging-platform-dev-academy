package no.itera.bloggingplatform.exception;

import static java.lang.String.format;

public class ResourceNotFoundException extends RuntimeException {

  public ResourceNotFoundException(Class clazz, Long id) {
    super(format("Resource of type %s not found for id=%s", clazz.getSimpleName(), id));
  }
}
