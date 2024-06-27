package ru.tensors.Tensors.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.tensors.Tensors.controllers.records.BinOperationTensorsRequest;
import ru.tensors.Tensors.controllers.records.UnaryOperationTensorRequest;

@RestController
@RequestMapping("/api/v1/tensors")
public class Controller {

    @PostMapping("/unary_operation/calculate")
    @ResponseStatus(HttpStatus.CREATED)
    public void unaryOperationMapping(@Valid @RequestBody UnaryOperationTensorRequest request) {
        //TODO
    }

    @PostMapping("/binary_operation/calculate")
    @ResponseStatus(HttpStatus.CREATED)
    public void binaryOperationMapping(@Valid @RequestBody BinOperationTensorsRequest request) {
        //TODO
    }
}

