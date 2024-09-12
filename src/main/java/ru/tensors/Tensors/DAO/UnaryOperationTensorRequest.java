package ru.tensors.Tensors.DAO;

import lombok.Data;

@Data
public class UnaryOperationTensorRequest {
    private String operation;
    private int rank;
    private int dimension;
    private double[] tensorElements;
}