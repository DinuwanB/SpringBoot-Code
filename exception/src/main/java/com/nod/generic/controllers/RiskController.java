package com.nod.generic.controllers;

import com.nod.generic.exceptions.CustomException;
import com.nod.generic.services.RiskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "api/v1/risks")
public class RiskController {

    private final RiskService riskService;

    public RiskController(final RiskService riskService) {
        this.riskService = riskService;
    }

    /*
     using try-catch -> Method 1
     @GetMapping()
     public ResponseEntity riskEndpoint(){

         try {
             riskService.takeRisk();
         } catch (CustomException e) {
             throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT);
         }

         return new ResponseEntity<>(HttpStatus.OK);
     }

     using annotation -> Method 2
         @GetMapping()
         public ResponseEntity riskEndpoint(){
            riskService.takeRisk();
            return new ResponseEntity<>(HttpStatus.OK);
         }

         @ExceptionHandler(CustomException.class)
         public ResponseEntity handleCustomException(final CustomException customException){
            return new ResponseEntity<>("No Risks here!", HttpStatus.OK);
         }
    */

    // using global exception handler -> Method 3
    @GetMapping()
    public ResponseEntity riskEndpoint(){
        riskService.takeRisk();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
