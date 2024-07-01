package ru.tensors.Tensors.controllers.data_classes;

//import lombok.Data;

// TODO Заресёрчить проикол с @Data
//@Data
public class BinOperationTensorsRequest {
    private int rankOfTensor1;
    private int dimensionOfTensor1;
    private int rankOfTensor2;
    private int dimensionOfTensor2;
    private double[] elementsOfTensor1;
    private double[] elementsOfTensor2;

    public int getRankOfTensor1() {
        return rankOfTensor1;
    }

    public int getDimensionOfTensor1() {
        return dimensionOfTensor1;
    }

    public int getRankOfTensor2() {
        return rankOfTensor2;
    }

    public int getDimensionOfTensor2() {
        return dimensionOfTensor2;
    }

    public double[] getElementsOfTensor1() {
        return elementsOfTensor1;
    }

    public double[] getElementsOfTensor2() {
        return elementsOfTensor2;
    }
}