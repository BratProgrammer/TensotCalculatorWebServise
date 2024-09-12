package ru.tensors.Tensors.services;

import ru.tensors.Tensors.models.Tensor;

public interface OperationsWithTensors {
    public Tensor multiply(Tensor tensor1, Tensor tensor2) throws Exception;
    public Tensor asymmetrize(Tensor tensor) throws Exception;
    public Tensor sum(Tensor tensor1, Tensor tensor2) throws Exception;
    public Tensor subtraction(Tensor tensor1, Tensor tensor2) throws Exception;
    public Tensor symmetrize(Tensor tensor) throws Exception;
}
