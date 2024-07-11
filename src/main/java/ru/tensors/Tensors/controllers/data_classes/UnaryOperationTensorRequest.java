package ru.tensors.Tensors.controllers.data_classes;

import lombok.Data;

@Data
public class UnaryOperationTensorRequest {
    private String operation;
    private int rank;
    private int dimension;
    private double[] tensorElements;
}
