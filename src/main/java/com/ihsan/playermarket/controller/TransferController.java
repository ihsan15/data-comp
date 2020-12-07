package com.ihsan.playermarket.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ihsan.playermarket.controller.request.CalculateContractRequest;
import com.ihsan.playermarket.service.TransferService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/playermarket/transfer", consumes = {MediaType.APPLICATION_JSON_VALUE})
@Api(value = "api documentation")
public class TransferController {

    private final TransferService transferService;

    @Autowired
    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping(value = "/calculateContractAmount", consumes = {MediaType.ALL_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "", notes = "insert Team and player")
    public ResponseEntity<String> calculateContractAmount(@Valid @RequestBody CalculateContractRequest request){
        return ResponseEntity.ok(transferService.calculateTransferAmount(request.getTeamId(), request.getPlayerId()));
    }
}
