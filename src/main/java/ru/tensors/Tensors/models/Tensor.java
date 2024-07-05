package ru.tensors.Tensors.models;

import java.util.ArrayList;
import java.util.List;

public class Tensor {
    private int rank;
    private int dimension;
    private ArrayList tensorMatrix = new ArrayList<>();

    public Tensor(int rank, int dimension) {
        this.rank = rank;
        this.dimension = dimension;
        this.tensorMatrix = createTensorMatrix(rank);
    }

    private ArrayList createTensorMatrix(int rankForRecursion) {
        if (rankForRecursion == 1) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int i = 0; i < dimension; i++) {
                row.add(0);
            }
            return row;
        } else {
            ArrayList<List> pathOfMatrix = new ArrayList<>();
            for (int i = 0; i < dimension; i++) {
                pathOfMatrix.add(createTensorMatrix(rankForRecursion - 1));
            }
            return pathOfMatrix;
        }
    }

    public int getDimension() {
        return dimension;
    }

    public int getRank() {
        return rank;
    }

    public double getElement(int[] coordinates) {
        int coordinateIndex = 0;
        ArrayList pathOfTensorMatrix = tensorMatrix;
        while (coordinateIndex < rank - 1) {
            pathOfTensorMatrix = (ArrayList) pathOfTensorMatrix.get(coordinates[coordinateIndex]);
            coordinateIndex++;
        }
        return (double) pathOfTensorMatrix.get(coordinates[coordinateIndex]);
    }


    public void setElement(double value, int[] coordinates) {
        int coordinateIndex = 0;
        ArrayList pathOfTensorMatrix = tensorMatrix;
        while (coordinateIndex < rank - 1) {
            pathOfTensorMatrix = (ArrayList) pathOfTensorMatrix.get(coordinates[coordinateIndex]);
            coordinateIndex++;
        }
        pathOfTensorMatrix.set(coordinates[coordinateIndex], value);
    }
}
