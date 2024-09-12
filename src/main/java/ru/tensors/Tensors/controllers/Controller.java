package ru.tensors.Tensors.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.tensors.Tensors.DAO.BinOperationTensorsRequest;
import ru.tensors.Tensors.DAO.ResultResponse;
import ru.tensors.Tensors.DAO.UnaryOperationTensorRequest;
import ru.tensors.Tensors.services.OperationProcessor;

@RestController
@Validated
@RequestMapping("/api/v1/tensors")
public class Controller {
    @Autowired
    OperationProcessor service;

    @PostMapping("/unary_operation")
    public ResponseEntity<ResultResponse> unaryOperationMapping(@RequestBody UnaryOperationTensorRequest request) throws Exception {
        return ResponseEntity.ok(service.processUnaryOperation(request));
    }

    @PostMapping("/binary_operation")
    public ResponseEntity<ResultResponse> binOperationMapping(@RequestBody BinOperationTensorsRequest request) throws Exception {
        return ResponseEntity.ok(service.processBinOperation(request));
    }
}

