package com.aluracursos.screenmatchLiteralura.ScreenmatchLiteralura;

import com.aluracursos.screenmatchLiteralura.ScreenmatchLiteralura.principal.Principal;
import com.aluracursos.screenmatchLiteralura.ScreenmatchLiteralura.repositorio.LibroRepository;
import com.aluracursos.screenmatchLiteralura.ScreenmatchLiteralura.repositorio.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchLiteraluraApplication implements CommandLineRunner {

    @Autowired
    private LibroRepository repository;
    @Autowired
    private PersonaRepository personaRepository;

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchLiteraluraApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        Principal principal = new Principal(repository, personaRepository);
        principal.muestraElMenu();
    }
}
