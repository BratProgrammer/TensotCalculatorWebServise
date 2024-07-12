package ru.tensors.Tensors.service;

import ru.tensors.Tensors.controllers.data_classes.BinOperationTensorsRequest;
import ru.tensors.Tensors.controllers.data_classes.ResultResponse;
import ru.tensors.Tensors.controllers.data_classes.UnaryOperationTensorRequest;

public interface IOperationProcessorService {
    ResultResponse processUnaryOperation(UnaryOperationTensorRequest requestData) throws Exception;
    ResultResponse processBinOperation(BinOperationTensorsRequest requestData) throws Exception;
}
