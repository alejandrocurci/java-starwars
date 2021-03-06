package com.spring.starwars.controllers;

import com.spring.starwars.dtos.ErrorDTO;
import com.spring.starwars.exceptions.CharacterException;
import com.spring.starwars.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class StarWarsController {

    @Autowired
    private CharacterService service;

    @PostMapping("/find")
    public ResponseEntity filter(@RequestParam Map<String, String> params) throws CharacterException {
        return new ResponseEntity(service.findCharacters(params), HttpStatus.OK);
    }

    @ExceptionHandler(CharacterException.class)
    public ResponseEntity characterNotFound(CharacterException e) {
        ErrorDTO err = new ErrorDTO();
        err.setDescription(e.getMessage());
        return new ResponseEntity(err, HttpStatus.NOT_FOUND);
    }

}
