package com.github.hotire.spring.hexagonal.architecture

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringHexagonalArchitectureApplication

fun main(args: Array<String>) {
    runApplication<SpringHexagonalArchitectureApplication>(*args)
}
