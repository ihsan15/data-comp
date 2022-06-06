package com.ihsan.datacomp.controller;

import com.ihsan.datacomp.service.ArithmeticCompService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/datacomp")
@Api(value = "api documentation")
public class DataCompController {

    private ArithmeticCompService arithmeticCompService;

    @Autowired
    public DataCompController(ArithmeticCompService arithmeticCompService) {
        this.arithmeticCompService = arithmeticCompService;
    }

    @PostMapping(value = "/compress")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "", notes = "compress XML JSON File")
    public ResponseEntity<String> compress(@RequestParam("file") MultipartFile file){
        try {
            return ResponseEntity.ok(arithmeticCompService.compress(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("Not OK");
    }


    @PostMapping(value = "/de-compress")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "", notes = "de-compress compressed XML JSON File")
    public ResponseEntity<String> deCompress(@RequestParam("file") MultipartFile file){
        try {
            return ResponseEntity.ok(arithmeticCompService.deCompress(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("Not OK");
    }
}
