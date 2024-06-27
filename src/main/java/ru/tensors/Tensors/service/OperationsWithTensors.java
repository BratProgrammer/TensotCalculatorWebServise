package ru.tensors.Tensors.service;

import ru.tensors.Tensors.models.Tensor;

public class OperationsWithTensors {

    private void incrementCoordinates(int[] coordinates, int dimension) {
        boolean increment = true;
        for (int i = 0; i < coordinates.length; i++) {
            if (increment) {
                coordinates[i] += 1;
            }
            increment = false;
            if (coordinates[i] >= dimension) {
                coordinates[i] = 0;
                increment = true;
            }
        }
    }

    public Tensor sum(Tensor tensor1, Tensor tensor2) throws Exception {
        var result = new Tensor();
        if (tensor1.getRank() == tensor2.getRank() && tensor1.getDimension() == tensor2.getDimension()) {
            result.init(tensor1.getRank(), tensor1.getDimension());

            int[] coordinates = new int[tensor1.getRank()];
            int countOfElements = (int) Math.pow(result.getDimension(), result.getRank());

            for (int i = 0; i < countOfElements; i++) {
                result.setElement(tensor1.getElement(coordinates) + tensor2.getElement(coordinates), coordinates);
                incrementCoordinates(coordinates, result.getDimension());
            }

        } else {
            throw new Exception("Tensors have different formats\n" +
                    "Tensor_1: rank = " + tensor1.getRank() + ", dimention = " + tensor1.getDimension() + "\n" +
                    "Tensor_2: rank = " + tensor2.getRank() + ", dimention = " + tensor2.getDimension() + "\n"
            );
        }
        return result;
    }

    public Tensor subtraction(Tensor tensor1, Tensor tensor2) throws Exception {
        var result = new Tensor();
        if (tensor1.getRank() == tensor2.getRank() && tensor1.getDimension() == tensor2.getDimension()) {
            result.init(tensor1.getRank(), tensor1.getDimension());

            int[] coordinates = new int[tensor1.getRank()];
            int countOfElements = result.getDimension() ^ result.getRank();

            for (int i = 0; i < countOfElements; i++) {
                result.setElement(tensor1.getElement(coordinates) - tensor2.getElement(coordinates), coordinates);
                incrementCoordinates(coordinates, result.getDimension());
            }

        } else {
            throw new Exception("Tensors have different formats\n" +
                    "Tensor_1: rank = " + tensor1.getRank() + ", dimention = " + tensor1.getDimension() + "\n" +
                    "Tensor_2: rank = " + tensor2.getRank() + ", dimention = " + tensor2.getDimension() + "\n"
            );
        }
        return result;
    }

    public Tensor symmetrize(Tensor tensor) {
        int rank = tensor.getRank();
        int dimension = tensor.getDimension();

        var resultTensor = new Tensor();
        resultTensor.init(rank, dimension);

        switch (rank) {

            case 1:
                return tensor;
            case 2:
                for (int stringIndex = 0; stringIndex < dimension; stringIndex++) {
                    for (int columnIndex = 0; columnIndex < dimension; columnIndex++) {
                        int[] coordinates = {stringIndex, columnIndex};
                        double element = (0.5 * (tensor.getElement(coordinates)
                                                        + tensor.getElement(coordinates)));
                        resultTensor.setElement(element, coordinates);
                    }
                }
                break;
            case 3:
                //TODO
                break;
            case 4:
                //TODO
                break;
            case 5:
                //TODO
                break;
            case 6:
                //TODO
                break;

        }

        return resultTensor;

    }

}
