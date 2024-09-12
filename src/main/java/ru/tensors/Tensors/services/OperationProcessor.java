package ru.tensors.Tensors.services;

import ru.tensors.Tensors.DAO.BinOperationTensorsRequest;
import ru.tensors.Tensors.DAO.ResultResponse;
import ru.tensors.Tensors.DAO.UnaryOperationTensorRequest;

public interface OperationProcessor {
    ResultResponse processUnaryOperation(UnaryOperationTensorRequest requestData) throws Exception;
    ResultResponse processBinOperation(BinOperationTensorsRequest requestData) throws Exception;
}
