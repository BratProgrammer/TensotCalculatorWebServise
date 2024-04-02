package ru.tensors.Tensors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tensors.Tensors.models.Tensor;

@SpringBootApplication
public class TensorsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TensorsApplication.class, args);
		Tensor tensor = new Tensor(3, 3);

		System.out.println(tensor);

	}

}
