package com.aluracursos.screenmatchLiteralura.ScreenmatchLiteralura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public record Gutendex(

        @JsonAlias("count") Integer cantidad,

        @JsonAlias("next") String siguiente,

        @JsonAlias("previous") String anterior,

        @JsonAlias("results") List<DatosLibro> resultados

) {
}
