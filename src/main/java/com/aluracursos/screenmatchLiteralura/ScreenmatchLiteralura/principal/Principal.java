package com.aluracursos.screenmatchLiteralura.ScreenmatchLiteralura.principal;

import com.aluracursos.screenmatchLiteralura.ScreenmatchLiteralura.model.*;
import com.aluracursos.screenmatchLiteralura.ScreenmatchLiteralura.repositorio.LibroRepository;
import com.aluracursos.screenmatchLiteralura.ScreenmatchLiteralura.repositorio.PersonaRepository;
import com.aluracursos.screenmatchLiteralura.ScreenmatchLiteralura.services.ConsumirAPI;
import com.aluracursos.screenmatchLiteralura.ScreenmatchLiteralura.services.ConvertirLosDatos;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumirAPI consumirAPI = new ConsumirAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvertirLosDatos conversor = new ConvertirLosDatos();
    private LibroRepository repositorio;
    private PersonaRepository personaRepositorio;

    public Principal(LibroRepository repository, PersonaRepository personaRepository) {
        this.repositorio = repository;
        this.personaRepositorio = personaRepository;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar Libro por título 
                    2 - Lista de todos los libros
                    3 - Lista todos los autores
                    4 - Lista los autores vivos en determinado año
                    5 - Estadísticas por idioma
                                   
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    listaDeLibros();
                    break;
                case 3:
                    listaDeAutores();
                    break;
                case 4:
                    ListarAutoresVivos();
                    break;
                case 5:
                    mostrarEstadisticasPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private Gutendex getDatosLibro() {
        System.out.println("Escribe el nombre del libro que deseas buscar");
        var nombreLibro = teclado.nextLine();
        var json = consumirAPI.obtenerDatos(URL_BASE + "?search=" + nombreLibro.replace(" ", "+"));
        System.out.println(json);
        Gutendex gutendex = conversor.obtenerDatos(json , Gutendex.class);
        return gutendex;
    }

    private void listaDeLibros() {
        List<Libro> libros = repositorio.findAll();
        libros.stream()
                .forEach(System.out::println);
    }

    public void buscarLibroPorTitulo() {
        Gutendex datos = getDatosLibro();
        Optional<DatosLibro> libroEncontrado = datos.resultados().stream()
                .findFirst();
        if (libroEncontrado.isPresent()) {
            DatosLibro datosLibro = libroEncontrado.get();

            Optional<Libro> libroExistente = repositorio.findByTituloContainsIgnoreCase(datosLibro.titulo());
            if (libroExistente.isPresent()) {
                System.out.println("El libro ya se encuentra registrado.");
            } else {
                DatosPersona datosAutor = datosLibro.autores().get(0);
                Optional<Persona> autorExistente = personaRepositorio.findByNombre(datosAutor.nombre());
                Persona autor;

                if (autorExistente.isPresent()) {
                    autor = autorExistente.get();
                } else {
                    autor = new Persona(datosAutor);
                }
                Libro libro = new Libro(datosLibro);
                libro.setAutor(autor);
                repositorio.save(libro);
                System.out.println("----- LIBRO REGISTRADO -----");
                System.out.println(libro);
            }
        } else {
            System.out.println("Libro no encontrado");
        }
    }

    public void listaDeAutores() {
        List<Persona> autores = personaRepositorio.findAll();
        autores.stream()
                .forEach(System.out::println);
    }

    public void ListarAutoresVivos() {
        System.out.println("Escribe el año para listar los autores vivos en esa fecha:");

        Integer anio = teclado.nextInt();
        teclado.nextLine();
        List<Persona> autoresVivos = personaRepositorio
                .findByFechaDeNacimientoLessThanEqualAndFechaDeFallecimientoGreaterThanEqual(anio, anio);
        autoresVivos.addAll(
                personaRepositorio.findByFechaDeNacimientoLessThanEqualAndFechaDeFallecimientoIsNull(anio)
        );
        autoresVivos.forEach(System.out::println);
    }

    public void mostrarEstadisticasPorIdioma() {
        System.out.println("Ingrese el primer idioma a consultar (ej: 'en', 'fr'):");
        String idioma1 = teclado.nextLine().trim();

        System.out.println("Ingrese el segundo idioma a consultar (ej: 'en', 'fr'):");
        String idioma2 = teclado.nextLine().trim();

        Long cantidad1 = repositorio.countByLenguaje(idioma1);
        Long cantidad2 = repositorio.countByLenguaje(idioma2);

        System.out.println("----- ESTADÍSTICAS DE LIBROS POR IDIOMA -----");
        System.out.println("Idioma: " + idioma1 + " -> " + cantidad1 + " libro(s)");
        System.out.println("Idioma: " + idioma2 + " -> " + cantidad2 + " libro(s)");
    }
}
