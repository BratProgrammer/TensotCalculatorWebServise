package ru.tensors.Tensors.controllers.data_classes;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultResponse {
    private int rank;
    private int dimension;
    private double[] tensorElements;
}
