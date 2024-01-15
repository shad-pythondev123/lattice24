package Lattice.LatticeAssignment24.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
        // Handle and customize the response for validation errors
        BindingResult bindingResult = ex.getBindingResult();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong details! Please ensure that the name entered is more than 3 characters" +
                ", Email id is valid" +
                ", Phone number given is valid" +
                "and City entered is not more than 20 characters!");
    }
}
