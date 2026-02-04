package com.aluracursos.screenmatchLiteralura.ScreenmatchLiteralura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public record DatosLibro(

        @JsonAlias("title") String titulo,

        @JsonAlias("authors") List<DatosPersona> autores,

        @JsonAlias("languages") List<String> lenguaje,

        @JsonAlias("download_count") Integer contadorDeDescargas

        ) {
}
