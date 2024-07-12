package ru.tensors.Tensors.service;

import org.springframework.stereotype.Component;
import ru.tensors.Tensors.models.Tensor;

import java.util.Arrays;

@Component
public class OperationsWithTensors implements IOperationsWithTensors {

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

        if (tensor1.getRank() == tensor2.getRank() && tensor1.getDimension() == tensor2.getDimension()) {
            var result = new Tensor(tensor1.getRank(), tensor1.getDimension());

            int[] coordinates = new int[tensor1.getRank()];
            int countOfElements = (int) Math.pow(result.getDimension(), result.getRank());

            for (int i = 0; i < countOfElements; i++) {
                result.setElement(tensor1.getElement(coordinates) + tensor2.getElement(coordinates), coordinates);
                incrementCoordinates(coordinates, result.getDimension());
            }
            return result;
        } else {
            throw new Exception("Tensors have different formats\n" +
                    "Tensor_1: rank = " + tensor1.getRank() + ", dimention = " + tensor1.getDimension() + "\n" +
                    "Tensor_2: rank = " + tensor2.getRank() + ", dimention = " + tensor2.getDimension() + "\n"
            );
        }

    }

    public Tensor subtraction(Tensor tensor1, Tensor tensor2) throws Exception {

        if (tensor1.getRank() == tensor2.getRank() && tensor1.getDimension() == tensor2.getDimension()) {
            var result = new Tensor(tensor1.getRank(), tensor1.getDimension());

            int[] coordinates = new int[tensor1.getRank()];
            int countOfElements = (int) Math.pow(result.getDimension(), result.getRank());

            for (int i = 0; i < countOfElements; i++) {
                result.setElement(tensor1.getElement(coordinates) - tensor2.getElement(coordinates), coordinates);
                incrementCoordinates(coordinates, result.getDimension());
            }
            return result;
        } else {
            throw new Exception("Tensors have different formats\n" +
                    "Tensor_1: rank = " + tensor1.getRank() + ", dimention = " + tensor1.getDimension() + "\n" +
                    "Tensor_2: rank = " + tensor2.getRank() + ", dimention = " + tensor2.getDimension() + "\n"
            );
        }

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

    private int getPermutationParity(int[] permutation) {
        int parity = 0;
        for (int i = 0; i < permutation.length - 1; i++) {
            for (int j = i + 1; j < permutation.length; j++) {
                if (permutation[i] > permutation[j]) {
                    parity += 1;
                }
            }
        }
        return parity;
    }

    private double getAsymmetrizedElement(int[] indexes, Tensor tensor, int index, int[] permutation) {
        double element = 0;
        if (index == indexes.length - 1) {
            element = Math.pow(-1, getPermutationParity(permutation)) * tensor.getElement(indexes);
            return element;
        }

        for (int i = index; i < indexes.length; i++) {
            swap(indexes, i, index);
            swap(permutation, i, index);
            element += getAsymmetrizedElement(indexes, tensor, index + 1, permutation);
            swap(indexes, i, index); // backtrack
            swap(permutation, i, index); // backtrack
        }

        if (index == 0) {
            element = element / factorial(tensor.getRank());
        }
        return element;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public Tensor symmetrize(Tensor tensor) {
        int rank = tensor.getRank();
        int dimension = tensor.getDimension();

        var resultTensor = new Tensor(rank, dimension);
        double element;
        int[] coordinates;
        switch (rank) {
            case 1:
                return tensor;
            case 2:
                for (int stringIndex = 0; stringIndex < dimension; stringIndex++) {
                    for (int columnIndex = 0; columnIndex < dimension; columnIndex++) {
                        coordinates = new int[]{stringIndex, columnIndex};
                        element = getSymmetrizedElement(coordinates, tensor, 0);
                        resultTensor.setElement(element, coordinates);
                    }
                }

                break;
            case 3:
                for (int superColumnIndex = 0; superColumnIndex < dimension; superColumnIndex++) {
                    for (int stringIndex = 0; stringIndex < dimension; stringIndex++) {
                        for (int columnIndex = 0; columnIndex < dimension; columnIndex++) {
                            coordinates = new int[]{superColumnIndex, stringIndex, columnIndex};
                            element = getSymmetrizedElement(coordinates, tensor, 0);
                            resultTensor.setElement(element, coordinates);
                        }
                    }
                }
                break;
            case 4:
                for (int superStringIndex = 0; superStringIndex < dimension; superStringIndex++) {
                    for (int superColumnIndex = 0; superColumnIndex < dimension; superColumnIndex++) {
                        for (int stringIndex = 0; stringIndex < dimension; stringIndex++) {
                            for (int columnIndex = 0; columnIndex < dimension; columnIndex++) {
                                coordinates = new int[]{stringIndex, superColumnIndex, columnIndex, superStringIndex};
                                element = getSymmetrizedElement(coordinates, tensor, 0);
                                resultTensor.setElement(element, coordinates);
                            }
                        }
                    }
                }
                break;
            case 5:
                for (int giperColumnIndex = 0; giperColumnIndex < dimension; giperColumnIndex++) {
                    for (int superStringIndex = 0; superStringIndex < dimension; superStringIndex++) {
                        for (int superColumnIndex = 0; superColumnIndex < dimension; superColumnIndex++) {
                            for (int stringIndex = 0; stringIndex < dimension; stringIndex++) {
                                for (int columnIndex = 0; columnIndex < dimension; columnIndex++) {
                                    coordinates = new int[]{
                                            stringIndex,
                                            giperColumnIndex,
                                            superColumnIndex,
                                            superStringIndex,
                                            columnIndex};
                                    element = getSymmetrizedElement(coordinates, tensor, 0);
                                    resultTensor.setElement(element, coordinates);
                                }
                            }
                        }
                    }
                }
                break;
            case 6:
                for (int giperStringIndex = 0; giperStringIndex < dimension; giperStringIndex++) {
                    for (int giperColumnIndex = 0; giperColumnIndex < dimension; giperColumnIndex++) {
                        for (int superStringIndex = 0; superStringIndex < dimension; superStringIndex++) {
                            for (int superColumnIndex = 0; superColumnIndex < dimension; superColumnIndex++) {
                                for (int stringIndex = 0; stringIndex < dimension; stringIndex++) {
                                    for (int columnIndex = 0; columnIndex < dimension; columnIndex++) {
                                        coordinates = new int[]{
                                                giperStringIndex,
                                                giperColumnIndex,
                                                superColumnIndex,
                                                superStringIndex,
                                                columnIndex,
                                                stringIndex};
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

    public Tensor asymmetrize(Tensor tensor) {
        int rank = tensor.getRank();
        int dimension = tensor.getDimension();

        var resultTensor = new Tensor(rank, dimension);
        double element;
        int[] coordinates;
        switch (rank) {
            case 1:
                return tensor;
            case 2:
                for (int stringIndex = 0; stringIndex < dimension; stringIndex++) {
                    for (int columnIndex = 0; columnIndex < dimension; columnIndex++) {
                        coordinates = new int[]{stringIndex, columnIndex};
                        element = getAsymmetrizedElement(coordinates, tensor, 0, new int[]{1, 2});
                        resultTensor.setElement(element, coordinates);
                    }
                }

                break;
            case 3:
                for (int superColumnIndex = 0; superColumnIndex < dimension; superColumnIndex++) {
                    for (int stringIndex = 0; stringIndex < dimension; stringIndex++) {
                        for (int columnIndex = 0; columnIndex < dimension; columnIndex++) {
                            coordinates = new int[]{superColumnIndex, stringIndex, columnIndex};
                            element = getAsymmetrizedElement(coordinates, tensor, 0, new int[]{1, 2, 3});
                            resultTensor.setElement(element, coordinates);
                        }
                    }
                }
                break;
            case 4:
                for (int superStringIndex = 0; superStringIndex < dimension; superStringIndex++) {
                    for (int superColumnIndex = 0; superColumnIndex < dimension; superColumnIndex++) {
                        for (int stringIndex = 0; stringIndex < dimension; stringIndex++) {
                            for (int columnIndex = 0; columnIndex < dimension; columnIndex++) {
                                coordinates = new int[]{stringIndex, superColumnIndex, columnIndex, superStringIndex};
                                element = getAsymmetrizedElement(coordinates, tensor, 0, new int[]{1, 2, 3, 4});
                                resultTensor.setElement(element, coordinates);
                            }
                        }
                    }
                }
                break;
            case 5:
                for (int giperColumnIndex = 0; giperColumnIndex < dimension; giperColumnIndex++) {
                    for (int superStringIndex = 0; superStringIndex < dimension; superStringIndex++) {
                        for (int superColumnIndex = 0; superColumnIndex < dimension; superColumnIndex++) {
                            for (int stringIndex = 0; stringIndex < dimension; stringIndex++) {
                                for (int columnIndex = 0; columnIndex < dimension; columnIndex++) {
                                    coordinates = new int[]{
                                            stringIndex,
                                            giperColumnIndex,
                                            superColumnIndex,
                                            superStringIndex,
                                            columnIndex};
                                    element = getAsymmetrizedElement(coordinates, tensor, 0, new int[]{1, 2, 3, 4, 5});
                                    resultTensor.setElement(element, coordinates);
                                }
                            }
                        }
                    }
                }
                break;
            case 6:
                for (int giperStringIndex = 0; giperStringIndex < dimension; giperStringIndex++) {
                    for (int giperColumnIndex = 0; giperColumnIndex < dimension; giperColumnIndex++) {
                        for (int superStringIndex = 0; superStringIndex < dimension; superStringIndex++) {
                            for (int superColumnIndex = 0; superColumnIndex < dimension; superColumnIndex++) {
                                for (int stringIndex = 0; stringIndex < dimension; stringIndex++) {
                                    for (int columnIndex = 0; columnIndex < dimension; columnIndex++) {
                                        coordinates = new int[]{
                                                giperStringIndex,
                                                giperColumnIndex,
                                                superColumnIndex,
                                                superStringIndex,
                                                columnIndex,
                                                stringIndex};
                                        element = getAsymmetrizedElement(coordinates, tensor, 0, new int[]{1, 2, 3, 4, 5, 6});
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

    private int[] concatWithArrayCopy(int[] array1, int[] array2) {
        int[] result = Arrays.copyOf(array1, array1.length + array2.length);
        System.arraycopy(array2, 0, result, array1.length, array2.length);
        return result;
    }

    public Tensor multiply(Tensor tensor1, Tensor tensor2) {
        int rankOfResultTensor = tensor1.getRank() + tensor2.getRank();
        int[] coordinatesInResultTensor;
        int[] coordinatesInTensor1 = new int[tensor1.getRank()];
        int[] coordinatesInTensor2;

        int countOfElementsInTensor1 = (int) Math.pow(tensor1.getDimension(), tensor1.getRank());
        int countOfElementsInTensor2 = (int) Math.pow(tensor2.getDimension(), tensor2.getRank());


        Tensor resultTensor = new Tensor(rankOfResultTensor, tensor1.getDimension());

        for (int i = 0; i < countOfElementsInTensor1; i++) {
            coordinatesInTensor2 = new int[tensor2.getRank()];
            for (int j = 0; j < countOfElementsInTensor2; j++) {
                double element = tensor1.getElement(coordinatesInTensor1) * tensor2.getElement(coordinatesInTensor2);
                coordinatesInResultTensor = concatWithArrayCopy(coordinatesInTensor1, coordinatesInTensor2);
                resultTensor.setElement(element, coordinatesInResultTensor);
                incrementCoordinates(coordinatesInTensor2, tensor2.getDimension());
            }
            incrementCoordinates(coordinatesInTensor1, tensor1.getDimension());
        }

        return resultTensor;
    }

}
