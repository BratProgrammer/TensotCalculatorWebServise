package ru.tensors.Tensors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.tensors.Tensors.models.Tensor;
import ru.tensors.Tensors.service.OperationsWithTensors;

@SpringBootApplication
public class TensorsApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(TensorsApplication.class, args);
	}

}
