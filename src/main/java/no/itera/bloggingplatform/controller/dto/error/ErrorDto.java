package no.itera.bloggingplatform.controller.dto.error;

import java.util.Date;

import org.immutables.value.Value;

@Value.Immutable
public interface ErrorDto {

  @Value.Parameter
  String getMessage();

  @Value.Parameter
  Date getTimestamp();

  @Value.Parameter
  String getErrorCode();
}
