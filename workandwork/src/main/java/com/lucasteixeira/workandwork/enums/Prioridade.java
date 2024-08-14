package com.lucasteixeira.workandwork.enums;

public enum Prioridade {
    BAIXA(3, "BAIXA"),
    MEDIA(4, "MEDIA"),
    ALTA(5,"ALTA");

    private Integer code;
    private String descricao;

    Prioridade(Integer code, String descricao) {
        this.code = code;
        this.descricao = descricao;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Prioridade toEnum(Integer code) {
        for (Prioridade x : Prioridade.values()) {
            if(code.equals(x.code)) {
                return x;
            }
        }
        throw new IllegalArgumentException("Prioridade inválida");
    }
}