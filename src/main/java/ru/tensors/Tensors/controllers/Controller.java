package ru.tensors.Tensors.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.tensors.Tensors.controllers.data_classes.BinOperationTensorsRequest;
import ru.tensors.Tensors.controllers.data_classes.ResultResponse;
import ru.tensors.Tensors.controllers.data_classes.UnaryOperationTensorRequest;
import ru.tensors.Tensors.service.OperationsProcessorService;

@RestController
@Validated
@RequestMapping("/api/v1/tensors")
public class Controller {
    @Autowired
    OperationsProcessorService service;

    @PostMapping("/unary_operation")
    public ResultResponse unaryOperationMapping(@RequestBody UnaryOperationTensorRequest request) {
        return service.processUnaryOperation(request);
    }

    @PostMapping("/binary_operation")
    public ResultResponse binOperationMapping(@RequestBody BinOperationTensorsRequest request) throws Exception {
        return service.processBinOperation(request);
    }
}

