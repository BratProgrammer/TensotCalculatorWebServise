package ru.tensors.Tensors.service;

import org.springframework.stereotype.Repository;
import ru.tensors.Tensors.models.Tensor;

@Repository
public class OperationsWithTensors {

    int factorial(int number) {
        int result = 1;
        for (int i = 2; i <= number; i++) {
            result *= i;
        }
        return result;
    }

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

    private double getSymmetrizedElement(int[] indexes, Tensor tensor, int index) {
        double element = 0;
        if (index == indexes.length - 1) {
            element = tensor.getElement(indexes);
            return element;
        }

        for (int i = index; i < indexes.length; i++) {
            swap(indexes, i, index);
            element += getSymmetrizedElement(indexes, tensor, index + 1);
            swap(indexes, i, index); // backtrack
        }

        if (index == 0) {
            element = element / factorial(tensor.getRank());
        }
        return element;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /*
    private double getSymmetrizedElement(int[] indexes, Tensor tensor) {
        double element = 0;
        if (rank == 1) {
            for (int i = 0; i < dimension; i++) {
                indexes[rank - 1] = i;
                element += tensor1.getElement(indexes);
            }
            return element;
        } else {
            for (int i = 0; i < dimension; i++) {
                indexes[rank - 1] = i;
                element += getSymmetrizedElement(indexes, rank - 1, dimension);
            }
        }
        if (rank == indexes.length) {
            element = element / factorial(rank);
        }
        return element;
    }
     */

    public Tensor symmetrize(Tensor tensor) {
        int rank = tensor.getRank();
        int dimension = tensor.getDimension();

        var resultTensor = new Tensor();
        resultTensor.init(rank, dimension);
        double element = 0;
        int[] coordinates = new int[]{};
        switch (rank) {
            case 1:
                return tensor;
            case 2:
                for (int stringIndex = 0; stringIndex < dimension; stringIndex++) {
                    for (int columnIndex = 0; columnIndex < dimension; columnIndex++) {
                        coordinates = new int[]{stringIndex, columnIndex};
                        element += tensor.getElement(coordinates);
                        coordinates = new int[]{columnIndex, stringIndex};
                        element += tensor.getElement(coordinates);
                    }
                }
                element = element / factorial(rank);
                resultTensor.setElement(element, coordinates);
                break;
            case 3:
                for (int superColumnIndex = 0; superColumnIndex < dimension; superColumnIndex++) {
                    for (int stringIndex = 0; stringIndex < dimension; stringIndex++) {
                        for (int columnIndex = 0; columnIndex < dimension; columnIndex++) {
                            coordinates = new int[]{superColumnIndex, stringIndex, columnIndex};
                            element = getSymmetrizedElement(coordinates, tensor, 0);
                        }
                    }
                }
                element = element / factorial(rank);
                resultTensor.setElement(element, coordinates);
                break;
            case 4:
                for (int superStringIndex = 0; superStringIndex < dimension; superStringIndex++) {
                    for (int superColumnIndex = 0; superColumnIndex < dimension; superColumnIndex++) {
                        for (int stringIndex = 0; stringIndex < dimension; stringIndex++) {
                            for (int columnIndex = 0; columnIndex < dimension; columnIndex++) {
                                coordinates = new int[]{stringIndex, superColumnIndex, columnIndex, superStringIndex};
                                element = getSymmetrizedElement(coordinates, tensor, 0);
                            }
                        }
                    }
                }
                element = element / factorial(rank);
                resultTensor.setElement(element, coordinates);
                break;
            case 5:
                for (int giperColumnIndex = 0; giperColumnIndex < dimension; giperColumnIndex++) {
                    for (int superStringIndex = 0; superStringIndex < dimension; superStringIndex++) {
                        for (int superColumnIndex = 0; superColumnIndex < dimension; superColumnIndex++) {
                            for (int stringIndex = 0; stringIndex < dimension; stringIndex++) {
                                for (int columnIndex = 0; columnIndex < dimension; columnIndex++) {
                                    coordinates = new int[]{stringIndex, giperColumnIndex, superColumnIndex, superStringIndex, columnIndex};
                                    element = getSymmetrizedElement(coordinates, tensor, 0);
                                }
                            }
                        }
                    }
                }
                element = element / factorial(rank);
                resultTensor.setElement(element, coordinates);
                break;
            case 6:
                for (int giperStringIndex = 0; giperStringIndex < dimension; giperStringIndex++) {
                    for (int giperColumnIndex = 0; giperColumnIndex < dimension; giperColumnIndex++) {
                        for (int superStringIndex = 0; superStringIndex < dimension; superStringIndex++) {
                            for (int superColumnIndex = 0; superColumnIndex < dimension; superColumnIndex++) {
                                for (int stringIndex = 0; stringIndex < dimension; stringIndex++) {
                                    for (int columnIndex = 0; columnIndex < dimension; columnIndex++) {
                                        coordinates = new int[]{giperStringIndex, giperColumnIndex, superColumnIndex, superStringIndex, columnIndex, stringIndex};
                                        element = getSymmetrizedElement(coordinates, tensor, 0);
                                        resultTensor.setElement(element, coordinates);
                                    }
                                }
                            }
                        }
                    }
                }
                break;
        }
        return resultTensor;
    }

}
