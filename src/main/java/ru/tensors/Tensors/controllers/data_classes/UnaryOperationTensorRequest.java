package ru.tensors.Tensors.controllers.data_classes;

public class UnaryOperationTensorRequest {
    private int rank;
    private int dimension;
    private double[] tensorElements;

    public int getRank() {
        return rank;
    }

    public int getDimension() {
        return dimension;
    }

    public double[] getTensorElements() {
        return tensorElements;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public void setTensorElements(double[] tensorElements) {
        this.tensorElements = tensorElements;
    }
}
