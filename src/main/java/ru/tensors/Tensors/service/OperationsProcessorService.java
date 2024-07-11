package ru.tensors.Tensors.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tensors.Tensors.controllers.data_classes.BinOperationTensorsRequest;
import ru.tensors.Tensors.controllers.data_classes.ResultResponse;
import ru.tensors.Tensors.controllers.data_classes.UnaryOperationTensorRequest;
import ru.tensors.Tensors.models.Tensor;

import java.util.Objects;

@Service
public class OperationsProcessorService {
    @Autowired
    private OperationsWithTensors operations;

    public ResultResponse processUnaryOperation(UnaryOperationTensorRequest requestData) {
        Tensor tensor = convertArrayToTensor(
                requestData.getRank(),
                requestData.getDimension(),
                requestData.getTensorElements()
        );

        if (Objects.equals(requestData.getOperation(), "symmetrize")) {
            return symmetrize(tensor);
        } else {
            return asymmetrize(tensor);
        }
    }

    public ResultResponse processBinOperation(BinOperationTensorsRequest requestData) throws Exception {
        Tensor tensor1 = convertArrayToTensor(
                requestData.getRankOfTensor1(),
                requestData.getDimension(),
                requestData.getElementsOfTensor1());

        Tensor tensor2 = convertArrayToTensor(
                requestData.getRankOfTensor2(),
                requestData.getDimension(),
                requestData.getElementsOfTensor2());

        if (Objects.equals(requestData.getOperation(), "plus")) {
            return plus(tensor1, tensor2);
        } else if (Objects.equals(requestData.getOperation(), "minus")) {
            return minus(tensor1, tensor2);
        } else {
            return multiply(tensor1, tensor2);
        }
    }

    private Tensor convertArrayToTensor(int rank, int dimension, double[] elements) {
        Tensor tensor = new Tensor(rank, dimension);
        int[] coordinates = new int[rank];

        if (rank == 1) {

            for (int i = 0; i < elements.length; i++) {
                coordinates[0] = i;
                tensor.setElement(elements[i], coordinates);
            }

        } else if (rank == 2) {
            int stringIndex = 0;
            int columnIndex = 0;

            for (int i = 0; i < elements.length; i++) {
                coordinates[0] = stringIndex;
                coordinates[1] = columnIndex;
                tensor.setElement(elements[i], coordinates);
                if ((i + 1) % dimension == 0 && i != 0) {
                    stringIndex += 1;
                    columnIndex = 0;
                } else {
                    columnIndex += 1;
                }
            }
        } else if (rank == 3) {
            int superColumnIndex = 0;
            int stringIndex = 0;
            int columnIndex = 0;

            for (int i = 0; i < elements.length; i++) {
                coordinates[0] = superColumnIndex;
                coordinates[1] = stringIndex;
                coordinates[2] = columnIndex;
                tensor.setElement(elements[i], coordinates);

                if (i != 0 && (i + 1) % Math.pow(dimension, 2) == 0) {
                    stringIndex += 1;
                    columnIndex = 0;
                    superColumnIndex = 0;
                } else if (i != 0 && (i + 1) % dimension == 0) {
                    columnIndex = 0;
                    superColumnIndex += 1;
                } else {
                    columnIndex += 1;
                }
            }
        } else if (rank == 4) {
            int superStringIndex = 0;
            int superColumnIndex = 0;
            int stringIndex = 0;
            int columnIndex = 0;

            for (int i = 0; i < elements.length; i++) {
                coordinates[0] = superStringIndex;
                coordinates[1] = superColumnIndex;
                coordinates[2] = stringIndex;
                coordinates[3] = columnIndex;
                tensor.setElement(elements[i], coordinates);

                if (i != 0 && (i + 1) % Math.pow(dimension, 3) == 0) {
                    superStringIndex += 1;
                    superColumnIndex = 0;
                    stringIndex = 0;
                    columnIndex = 0;
                } else if (i != 0 && (i + 1) % Math.pow(dimension, 2) == 0) {
                    stringIndex += 1;
                    columnIndex = 0;
                    superColumnIndex = 0;
                } else if (i != 0 && (i + 1) % dimension == 0) {
                    superColumnIndex += 1;
                    columnIndex = 0;
                } else {
                    columnIndex += 1;
                }
            }
        } else if (rank == 5) {
            int giperColumnIndex = 0;
            int superStringIndex = 0;
            int superColumnIndex = 0;
            int stringIndex = 0;
            int columnIndex = 0;

            for (int i = 0; i < elements.length; i++) {
                coordinates[0] = giperColumnIndex;
                coordinates[1] = superStringIndex;
                coordinates[2] = superColumnIndex;
                coordinates[3] = stringIndex;
                coordinates[4] = columnIndex;
                tensor.setElement(elements[i], coordinates);

                if (i != 0 && (i + 1) % Math.pow(dimension, 4) == 0) {
                    superStringIndex += 1;
                    giperColumnIndex = 0;
                    superColumnIndex = 0;
                    stringIndex = 0;
                    columnIndex = 0;
                } else if (i != 0 && (i + 1) % Math.pow(dimension, 3) == 0) {
                    stringIndex += 1;
                    giperColumnIndex = 0;
                    superColumnIndex = 0;
                    columnIndex = 0;
                } else if (i != 0 && (i + 1) % Math.pow(dimension, 2) == 0) {
                    giperColumnIndex += 1;
                    superColumnIndex = 0;
                    columnIndex = 0;
                } else if (i != 0 && (i + 1) % dimension == 0) {
                    superColumnIndex += 1;
                    columnIndex = 0;
                } else {
                    columnIndex += 1;
                }
            }
        } else if (rank == 6) {
            int giperStringIndex = 0;
            int giperColumnIndex = 0;
            int superStringIndex = 0;
            int superColumnIndex = 0;
            int stringIndex = 0;
            int columnIndex = 0;

            for (int i = 0; i < elements.length; i++) {
                coordinates[0] = giperStringIndex;
                coordinates[1] = giperColumnIndex;
                coordinates[2] = superStringIndex;
                coordinates[3] = superColumnIndex;
                coordinates[4] = stringIndex;
                coordinates[5] = columnIndex;
                tensor.setElement(elements[i], coordinates);

                if (i != 0 && (i + 1) % Math.pow(dimension, 5) == 0) {
                    giperStringIndex += 1;
                    giperColumnIndex = 0;
                    superStringIndex = 0;
                    superColumnIndex = 0;
                    stringIndex = 0;
                    columnIndex = 0;
                } else if (i != 0 && (i + 1) % Math.pow(dimension, 4) == 0) {
                    superStringIndex += 1;
                    giperColumnIndex = 0;
                    superColumnIndex = 0;
                    stringIndex = 0;
                    columnIndex = 0;
                } else if (i != 0 && (i + 1) % Math.pow(dimension, 3) == 0) {
                    stringIndex += 1;
                    giperColumnIndex = 0;
                    superColumnIndex = 0;
                    columnIndex = 0;
                } else if (i != 0 && (i + 1) % Math.pow(dimension, 2) == 0) {
                    giperColumnIndex += 1;
                    superColumnIndex = 0;
                    columnIndex = 0;
                } else if (i != 0 && (i + 1) % dimension == 0) {
                    superColumnIndex += 1;
                    columnIndex = 0;
                } else {
                    columnIndex += 1;
                }
            }
        }

        return tensor;
    }

    private double[] convertTensorToArray(Tensor tensor) {
        int countOfElements = (int) Math.pow(tensor.getDimension(), tensor.getRank());
        double[] array = new double[countOfElements];
        int rank = tensor.getRank();
        int dimension = tensor.getDimension();
        int[] coordinates = new int[tensor.getRank()];


        if (rank == 1) {

            for (int i = 0; i < array.length; i++) {
                coordinates[0] = i;
                array[i] = tensor.getElement(coordinates);
            }

        } else if (rank == 2) {
            int stringIndex = 0;
            int columnIndex = 0;

            for (int i = 0; i < array.length; i++) {
                coordinates[0] = stringIndex;
                coordinates[1] = columnIndex;
                array[i] = tensor.getElement(coordinates);

                if ((i + 1) % dimension == 0 && i != 0) {
                    stringIndex += 1;
                    columnIndex = 0;
                } else {
                    columnIndex += 1;
                }
            }
        } else if (rank == 3) {
            int superColumnIndex = 0;
            int stringIndex = 0;
            int columnIndex = 0;

            for (int i = 0; i < array.length; i++) {
                coordinates[0] = superColumnIndex;
                coordinates[1] = stringIndex;
                coordinates[2] = columnIndex;
                array[i] = tensor.getElement(coordinates);

                if (i != 0 && (i + 1) % Math.pow(dimension, 2) == 0) {
                    stringIndex += 1;
                    columnIndex = 0;
                    superColumnIndex = 0;
                } else if (i != 0 && (i + 1) % dimension == 0) {
                    columnIndex = 0;
                    superColumnIndex += 1;
                } else {
                    columnIndex += 1;
                }
            }
        } else if (rank == 4) {
            int superStringIndex = 0;
            int superColumnIndex = 0;
            int stringIndex = 0;
            int columnIndex = 0;

            for (int i = 0; i < array.length; i++) {
                coordinates[0] = superStringIndex;
                coordinates[1] = superColumnIndex;
                coordinates[2] = stringIndex;
                coordinates[3] = columnIndex;
                array[i] = tensor.getElement(coordinates);

                if (i != 0 && (i + 1) % Math.pow(dimension, 3) == 0) {
                    superStringIndex += 1;
                    superColumnIndex = 0;
                    stringIndex = 0;
                    columnIndex = 0;
                } else if (i != 0 && (i + 1) % Math.pow(dimension, 2) == 0) {
                    stringIndex += 1;
                    columnIndex = 0;
                    superColumnIndex = 0;
                } else if (i != 0 && (i + 1) % dimension == 0) {
                    superColumnIndex += 1;
                    columnIndex = 0;
                } else {
                    columnIndex += 1;
                }
            }
        } else if (rank == 5) {
            int giperColumnIndex = 0;
            int superStringIndex = 0;
            int superColumnIndex = 0;
            int stringIndex = 0;
            int columnIndex = 0;

            for (int i = 0; i < array.length; i++) {
                coordinates[0] = giperColumnIndex;
                coordinates[1] = superStringIndex;
                coordinates[2] = superColumnIndex;
                coordinates[3] = stringIndex;
                coordinates[4] = columnIndex;
                array[i] = tensor.getElement(coordinates);

                if (i != 0 && (i + 1) % Math.pow(dimension, 4) == 0) {
                    superStringIndex += 1;
                    giperColumnIndex = 0;
                    superColumnIndex = 0;
                    stringIndex = 0;
                    columnIndex = 0;
                } else if (i != 0 && (i + 1) % Math.pow(dimension, 3) == 0) {
                    stringIndex += 1;
                    giperColumnIndex = 0;
                    superColumnIndex = 0;
                    columnIndex = 0;
                } else if (i != 0 && (i + 1) % Math.pow(dimension, 2) == 0) {
                    giperColumnIndex += 1;
                    superColumnIndex = 0;
                    columnIndex = 0;
                } else if (i != 0 && (i + 1) % dimension == 0) {
                    superColumnIndex += 1;
                    columnIndex = 0;
                } else {
                    columnIndex += 1;
                }
            }
        } else if (rank == 6) {
            int giperStringIndex = 0;
            int giperColumnIndex = 0;
            int superStringIndex = 0;
            int superColumnIndex = 0;
            int stringIndex = 0;
            int columnIndex = 0;

            for (int i = 0; i < array.length; i++) {
                coordinates[0] = giperStringIndex;
                coordinates[1] = giperColumnIndex;
                coordinates[2] = superStringIndex;
                coordinates[3] = superColumnIndex;
                coordinates[4] = stringIndex;
                coordinates[5] = columnIndex;
                array[i] = tensor.getElement(coordinates);

                if (i != 0 && (i + 1) % Math.pow(dimension, 5) == 0) {
                    giperStringIndex += 1;
                    giperColumnIndex = 0;
                    superStringIndex = 0;
                    superColumnIndex = 0;
                    stringIndex = 0;
                    columnIndex = 0;
                } else if (i != 0 && (i + 1) % Math.pow(dimension, 4) == 0) {
                    superStringIndex += 1;
                    giperColumnIndex = 0;
                    superColumnIndex = 0;
                    stringIndex = 0;
                    columnIndex = 0;
                } else if (i != 0 && (i + 1) % Math.pow(dimension, 3) == 0) {
                    stringIndex += 1;
                    giperColumnIndex = 0;
                    superColumnIndex = 0;
                    columnIndex = 0;
                } else if (i != 0 && (i + 1) % Math.pow(dimension, 2) == 0) {
                    giperColumnIndex += 1;
                    superColumnIndex = 0;
                    columnIndex = 0;
                } else if (i != 0 && (i + 1) % dimension == 0) {
                    superColumnIndex += 1;
                    columnIndex = 0;
                } else {
                    columnIndex += 1;
                }
            }
        }

        return array;
    }

    public ResultResponse plus(Tensor tensor1, Tensor tensor2) throws Exception {
        double[] resultTensorElements = convertTensorToArray(operations.sum(tensor1, tensor2));
        return new ResultResponse(tensor1.getRank(), tensor1.getDimension(), resultTensorElements);
    }

    public ResultResponse minus(Tensor tensor1, Tensor tensor2) throws Exception {
        double[] resultTensorElements = convertTensorToArray(operations.subtraction(tensor1, tensor2));
        return new ResultResponse(tensor1.getRank(), tensor1.getDimension(), resultTensorElements);
    }

    public ResultResponse multiply(Tensor tensor1, Tensor tensor2) throws Exception {
        if (tensor1.getRank() + tensor2.getRank() <= 6) {
            Tensor resultTensor = operations.multiply(tensor1, tensor2);
            double[] resultTensorElements = convertTensorToArray(resultTensor);
            return new ResultResponse(resultTensor.getRank(), resultTensor.getDimension(), resultTensorElements);
        } else {
            throw new Exception("Result tensor rank cannot be more than 6\n");
        }
    }

    public ResultResponse symmetrize(Tensor tensor) {
        Tensor symTensor = operations.symmetrize(tensor);
        double[] symTensorElements = convertTensorToArray(symTensor);
        return new ResultResponse(symTensor.getRank(), symTensor.getDimension(), symTensorElements);
    }

    public ResultResponse asymmetrize(Tensor tensor) {
        Tensor symTensor = operations.asymmetrize(tensor);
        double[] symTensorElements = convertTensorToArray(symTensor);
        return new ResultResponse(symTensor.getRank(), symTensor.getDimension(), symTensorElements);
    }
}