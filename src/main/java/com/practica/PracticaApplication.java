package com.practica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class PracticaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticaApplication.class, args);
	}

}
