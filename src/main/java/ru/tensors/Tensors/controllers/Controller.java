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

    @PostMapping("/unary_operation/symmetrize")
    public ResultResponse symmetrizeOperationMapping(@RequestBody UnaryOperationTensorRequest request) {
        return service.symmetrize(request);
    }

    @PostMapping("/unary_operation/asymmetrize")
    public ResultResponse a_symmetrizeOperationMapping(@RequestBody UnaryOperationTensorRequest request) {
        return service.asymmetrize(request);
    }

    @PostMapping("/binary_operation/plus")
    public ResultResponse plusOperationMapping(@RequestBody BinOperationTensorsRequest request) throws Exception {
        return service.plus(request);
    }

    @PostMapping("/binary_operation/minus")
    public ResultResponse minusOperationMapping(@RequestBody BinOperationTensorsRequest request) throws Exception {
        return service.minus(request);
    }

    @PostMapping("/binary_operation/multiply")
    public ResultResponse multiplyOperationMapping(@RequestBody BinOperationTensorsRequest request) throws Exception {
        return service.multiply(request);
    }
}

