package no.itera.bloggingplatform.controller.exception;

import java.util.Date;

import no.itera.bloggingplatform.constant.ErrorCode;
import no.itera.bloggingplatform.controller.dto.error.ImmutableErrorDto;
import no.itera.bloggingplatform.exception.ResourceNotFoundException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResourceNotFoundExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {ResourceNotFoundException.class})
  protected ResponseEntity<Object> handleResourceNotFoundException(
      ResourceNotFoundException exception,
      WebRequest request
  ) {
    return handleExceptionInternal(
        exception,
        ImmutableErrorDto.of(exception.getMessage(), new Date(), ErrorCode.RESOURCE_NOT_FOUND),
        new HttpHeaders(),
        HttpStatus.BAD_REQUEST, request
    );
  }
}
