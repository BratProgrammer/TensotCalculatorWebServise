package ru.tensors.Tensors.configuration;

import org.springframework.context.annotation.Bean;
import ru.tensors.Tensors.models.Tensor;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean("Tensor1")
    public Tensor tensor1() {
        return new Tensor();
    }

    @Bean("Tensor2")
    public Tensor tensor2() {
        return new Tensor();
    }

}
