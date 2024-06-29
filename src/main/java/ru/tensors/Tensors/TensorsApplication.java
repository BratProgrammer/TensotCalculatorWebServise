package ru.tensors.Tensors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.tensors.Tensors.configuration.Configuration;
import ru.tensors.Tensors.models.Tensor;
import ru.tensors.Tensors.service.OperationsWithTensors;

@SpringBootApplication
public class TensorsApplication {

	static ApplicationContext context;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(TensorsApplication.class, args);

		context = new AnnotationConfigApplicationContext(Configuration.class);

		var tensor1 = (Tensor) context.getBean("Tensor1");
		var tensor2 = (Tensor) context.getBean("Tensor2");

		var operations = new OperationsWithTensors();

		tensor1.init(3, 2);
		tensor1.setElement(1, new int[]{0, 0, 0});
		tensor1.setElement(2, new int[]{0, 0, 1});
		tensor1.setElement(3, new int[]{0, 1, 0});
		tensor1.setElement(4, new int[]{0, 1, 1});
		tensor1.setElement(5, new int[]{1, 0, 0});
		tensor1.setElement(6, new int[]{1, 0, 1});
		tensor1.setElement(7, new int[]{1, 1, 0});
		tensor1.setElement(8, new int[]{1, 1, 1});
		tensor2.init(3, 2);
		tensor2.setElement(1, new int[]{0, 0, 0});
		tensor2.setElement(2, new int[]{0, 0, 1});
		tensor2.setElement(3, new int[]{0, 1, 0});
		tensor2.setElement(4, new int[]{0, 1, 1});
		tensor2.setElement(5, new int[]{1, 0, 0});
		tensor2.setElement(6, new int[]{1, 0, 1});
		tensor2.setElement(7, new int[]{1, 1, 0});
		tensor2.setElement(8, new int[]{1, 1, 1});

	}

}
