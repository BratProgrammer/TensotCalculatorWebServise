package ru.tensors.Tensors.models;

import java.util.ArrayList;
import java.util.List;

public class Tensor {
    private final int rank;
    private final int dimension;

    ArrayList<List> tensorMatrix = new ArrayList<>();

    private org.tensorflow.Tensor tensor;
    private int[] elements;

    public Tensor(int rank, int dimension) {
        this.rank = rank;
        this.dimension = dimension;
        createTensorMatrix(rank);
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

    public int[] getElementsArray() {
        return elements;
    }

    public void setElementsArray(int[] elements) {
        this.elements = elements;
    }

    public int getElement(int ... coordinates) {

        //TODO
        return 0;
    }
}
