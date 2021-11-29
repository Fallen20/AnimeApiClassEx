package com.project.domain.dto;


import java.util.List;

public class ListResult {
    //esto es json y debe ser public siempre
    public List<?> result;//result es el nombre que te sale
    //con ? es cualquier tipo de objeto, por eso esto esto es un retorno generico porque acepta todo

    public ListResult(List<?> result) {
        this.result = result;
    }
}
