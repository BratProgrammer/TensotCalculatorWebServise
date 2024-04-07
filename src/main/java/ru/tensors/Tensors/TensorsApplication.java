package ru.tensors.Tensors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tensors.Tensors.configuration.Configuration;
import ru.tensors.Tensors.models.Tensor;

@SpringBootApplication
public class TensorsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TensorsApplication.class, args);

		ApplicationContext context = new AnnotationConfigApplicationContext(Configuration.class);

		OperationsWithTensors operations = new OperationsWithTensors();

		Tensor tensor = (Tensor) context.getBean("Tensor1");

		tensor.init(2, 2);
		tensor.setElement(1, 0, 0);
		tensor.setElement(2, 0, 1);
		tensor.setElement(3, 1, 0);
		tensor.setElement(4, 1, 1);

		tensor = operations.symmetrize(tensor);

		System.out.println(tensor.getElement(0, 0) + " " + tensor.getElement(0, 1));
		System.out.println(tensor.getElement(1, 0) + " " + tensor.getElement(1, 1));

	}

}
