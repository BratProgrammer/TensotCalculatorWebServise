package ru.tensors.Tensors.controllers.data_classes;

//import lombok.Data;

import lombok.Data;

// TODO Заресёрчить проикол с @Data
@Data
public class BinOperationTensorsRequest {
    private String operation;
    private int dimension;
    private int rankOfTensor1;
    private int rankOfTensor2;
    private double[] elementsOfTensor1;
    private double[] elementsOfTensor2;
}