package ru.tensors.Tensors.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationsProcessorService {
    @Autowired
    private OperationsWithTensors operations;

}
