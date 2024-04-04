package ru.tensors.Tensors.models;

import java.util.ArrayList;
import java.util.List;

public class Tensor {
    private final int rank;
    private final int dimension;
    private ArrayList tensorMatrix = new ArrayList<>();
    private int[] elements;

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

    public int[] getElementsArray() {
        return elements;
    }

    public void setElementsArray(int[] elements) {
        this.elements = elements;
    }

    public int getElement(int ... coordinates) {
        int rankForRecursion = rank;
        int index = 0;
        ArrayList pathOfTensorMatrix = tensorMatrix;
        while (index < rank - 1) {
            pathOfTensorMatrix = (ArrayList) pathOfTensorMatrix.get(coordinates[index]);
            index++;
        }
        return (int) pathOfTensorMatrix.get(index);
    }
}
