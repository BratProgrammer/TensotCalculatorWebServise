package ru.tensors.Tensors.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/unary_operation/symmetrize")
    @ResponseStatus(HttpStatus.CREATED)
    public void symmetrizeOperationMapping(@Valid @RequestBody UnaryOperationTensorRequest request) {
        //TODO
    }

    @PostMapping("/unary_operation/a_symmetrize")
    @ResponseStatus(HttpStatus.CREATED)
    public void a_symmetrizeOperationMapping(@Valid @RequestBody UnaryOperationTensorRequest request) {
        //TODO
    }

    @PostMapping("/binary_operation/plus")
    public ResultResponse plusOperationMapping(@Valid @RequestBody BinOperationTensorsRequest request) throws Exception {
        return service.plus(request);
    }

    @PostMapping("/binary_operation/minus")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultResponse minusOperationMapping(@Valid @RequestBody BinOperationTensorsRequest request) throws Exception {
        return service.minus(request);
    }
}

