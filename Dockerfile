FROM openjdk:23-ea-21-slim-bullseye
WORKDIR /tensor_calculator
COPY TensorCalculator.jar /tensor_calculator/TensorCalculator.jar
ENTRYPOINT ["java", "-jar", "TensorCalculator.jar"]