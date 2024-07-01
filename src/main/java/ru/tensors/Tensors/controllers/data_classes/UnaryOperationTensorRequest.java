package ru.tensors.Tensors.controllers.data_classes;

public class UnaryOperationTensorRequest {
    int rank;
    int dimension;
    double[] tensor_elements;

    public int getRank() {
        return rank;
    }

    public int getDimension() {
        return dimension;
    }

    public double[] getTensor_elements() {
        return tensor_elements;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public void setTensor_elements(double[] tensor_elements) {
        this.tensor_elements = tensor_elements;
    }
}
