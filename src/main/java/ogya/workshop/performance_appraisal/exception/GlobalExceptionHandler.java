package ogya.workshop.performance_appraisal.exception;


import jakarta.servlet.http.HttpServletRequest;
import ogya.workshop.performance_appraisal.dto.ManagerDto;
import ogya.workshop.performance_appraisal.util.ServerResponseList;
import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ServerResponseList {
    final static Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ManagerDto<?>> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            WebRequest request) {
        HttpServletRequest servletRequest = ((ServletWebRequest) request).getRequest();

        LOG.info("Validation error during request - URL: {}",
                StringEscapeUtils.escapeJava(servletRequest.getRequestURL().toString()));

        Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

        ManagerDto<Map<String, String>> managerDto = new ManagerDto<>();
        managerDto.setContent(errors);
        return new ResponseEntity<>(managerDto, HttpStatus.BAD_REQUEST);
    }

    // Catch all other unhandled exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ManagerDto<String>> handleAllExceptions(Exception ex, WebRequest request) {
        LOG.info("An unexpected error occurred: " + ex.getMessage());
        ManagerDto<String> managerDto = new ManagerDto<>();
        ex.printStackTrace();
        managerDto.setInfo(getInfoInternalServerError("An unexpected error occurred : " + ex.getMessage()));
        return new ResponseEntity<>(managerDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ManagerDto<String>> myExceptionHandler(IllegalArgumentException pe, WebRequest req) {
        ManagerDto<String> managerDto = new ManagerDto<>();
        managerDto.setInfo(getInfoBadRequest(pe.getMessage()));
        return new ResponseEntity<>(managerDto, HttpStatus.BAD_REQUEST);
    }
}
