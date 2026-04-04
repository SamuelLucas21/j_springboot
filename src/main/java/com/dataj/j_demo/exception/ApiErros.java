package com.dataj.j_demo.exception;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

public class ApiErros {
    
    @Getter
    private List<String> erros;

    public ApiErros(List<String> erroStrings){
        this.erros=erroStrings;
    }

    public ApiErros(String erro){
        this.erros = Arrays.asList(erro);
    }
}
