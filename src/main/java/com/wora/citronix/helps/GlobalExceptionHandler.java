package com.wora.citronix.helps;

import com.wora.citronix.exceptions.*;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }


    @ExceptionHandler(InsufficientFarmSurfaceException.class)
    public ResponseEntity<String> handleInsufficientFarmSurfaceException(InsufficientFarmSurfaceException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(InsufficientFieldSurfaceException.class)
    public ResponseEntity<String> handleInsufficientFieldSurfaceException(InsufficientFieldSurfaceException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(PlantingDateException.class)
    public ResponseEntity<String> handlePlantingDateException(PlantingDateException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(NotSameSeasonException.class)
    public ResponseEntity<String> handleNotSameSeasonException(NotSameSeasonException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(HarvestAlreadyExistsException.class)
    public ResponseEntity<String> handleHarvestAlreadyExistsException(HarvestAlreadyExistsException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
