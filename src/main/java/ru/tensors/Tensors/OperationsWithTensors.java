package ru.tensors.Tensors;

import ru.tensors.Tensors.models.Tensor;


public class OperationsWithTensors {

    public Tensor sum(Tensor tensor1, Tensor tensor2) {
        Tensor result = new Tensor();
        if (tensor1.getRank() == tensor2.getRank() && tensor1.getDimension() == tensor2.getDimension()) {

        }


        return result;
    }

    public Tensor symmetrize(Tensor tensor) {
        int rank = tensor.getRank();
        int dimension = tensor.getDimension();

        Tensor resultTensor = new Tensor();
        resultTensor.init(rank, dimension);

        switch (rank) {

            case 1:
                return tensor;
            case 2:
                for (int stringIndex = 0; stringIndex < dimension; stringIndex++) {
                    for (int columnIndex = 0; columnIndex < dimension; columnIndex++) {
                        int element = tensor.getElement(stringIndex, columnIndex)
                                + tensor.getElement(columnIndex, stringIndex);
                        resultTensor.setElement(element, stringIndex, columnIndex);
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
