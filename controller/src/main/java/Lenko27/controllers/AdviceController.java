package Lenko27.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import Lenko27.exceptions.NoEntityExistedException;

@ControllerAdvice
public class AdviceController {

    @ExceptionHandler(value = {NoEntityExistedException.class})
    public ResponseEntity<Object> handleEntityExceptions(RuntimeException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleOtherExceptions(Exception ex) {
        return ResponseEntity.badRequest().body("Error occurred. Bad request.");
    }
}
