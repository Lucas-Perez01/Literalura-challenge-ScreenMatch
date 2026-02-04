package com.aluracursos.screenmatchLiteralura.ScreenmatchLiteralura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Autores")

public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Integer fechaDeNacimiento;
    private Integer fechaDeFallecimiento;
    @Column(unique = true)
    private String nombre;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros;

    public Persona(){}

    public Persona(DatosPersona datosPersona){

        this.fechaDeNacimiento = datosPersona.fechaDeNacimiento();
        this.fechaDeFallecimiento = datosPersona.fechaDeFallecimiento();
        this.nombre = datosPersona.nombre();
    }

    @Override
    public String toString() {
        return nombre + " (" + fechaDeNacimiento + " - " + fechaDeFallecimiento + ")";
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Integer getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Integer fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public Integer getFechaDeFallecimiento() {
        return fechaDeFallecimiento;
    }

    public void setFechaDeFallecimiento(Integer fechaDeFallecimiento) {
        this.fechaDeFallecimiento = fechaDeFallecimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
}
