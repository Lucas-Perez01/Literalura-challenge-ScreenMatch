package com.aluracursos.screenmatchLiteralura.ScreenmatchLiteralura.services;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
