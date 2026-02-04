package com.aluracursos.screenmatchLiteralura.ScreenmatchLiteralura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Libros")

public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String titulo;
    private String lenguaje;
    private Integer contadorDeDescargas;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "autor_id")
    private Persona autor;

    public Libro(){}

    public Libro(DatosLibro datosLibro){
        this.titulo = datosLibro.titulo();
        this.autor = new Persona(datosLibro.autores().get(0));
        this.lenguaje = datosLibro.lenguaje().get(0);
        this.contadorDeDescargas = datosLibro.contadorDeDescargas();
    }

    public Libro(Gutendex datos) {
    }

    @Override
    public String toString() {
        return
                "================\n" +
                        "    TITULO    \n" +
                        "================\n" + "Título del libro: " + titulo +
                        "\n================\n" +
                        "    AUTOR    \n" +
                        "================\n" + "Apellido y nombre del autor: " + autor +
                        "\n================\n" +
                        "    IDIOMA    \n" +
                        "================\n" + "Idioma: " + lenguaje +
                        "\n================\n" +
                        "   DESCARGAS    \n" +
                        "================\n" + "Total de descargas: " + contadorDeDescargas + "\n";

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public Integer getContadorDeDescargas() {
        return contadorDeDescargas;
    }

    public void setContadorDeDescargas(Integer contadorDeDescargas) {
        this.contadorDeDescargas = contadorDeDescargas;
    }

    public Persona getAutor() {
        return autor;
    }

    public void setAutor(Persona autor) {
        this.autor = autor;
    }
}
