package com.ihsan.playermarket.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import com.ihsan.playermarket.controller.request.CalculateContractRequest;
import com.ihsan.playermarket.datahelper.EntityFactory;
import com.ihsan.playermarket.service.TransferService;

public class TransferControllerTest {

    private TransferService transferService;
    private TransferController transferController;
    private CalculateContractRequest request;
    private EntityFactory entityFactory = new EntityFactory();

    @Before
    public void setup(){
        transferService = Mockito.mock(TransferService.class);
        transferController = new TransferController(transferService);
        request = entityFactory.calculateContractRequest();
    }

    @Test
    public void calculateContractAmount() {
        String result= "tutar 200";

        Mockito.when(transferService.calculateTransferAmount(Mockito.anyLong(), Mockito.anyLong())).thenReturn(result);


        assertEquals(transferController.calculateContractAmount(request), ResponseEntity.ok(result));
    }
}