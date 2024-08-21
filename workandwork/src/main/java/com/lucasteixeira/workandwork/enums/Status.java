package com.lucasteixeira.workandwork.enums;

public enum Status {
    ABERTO(0, "ABERTO"),
    ANDAMENTO(1, "ANDAMENTO"),
    ENCERRADO(2,"ENCERRADO");

    private Integer code;
    private String descricao;

    Status(Integer code, String descricao) {
        this.code = code;
        this.descricao = descricao;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Status toEnum(Integer code) {
        for (Status x : Status.values()) {
            if(code.equals(x.code)) {
                return x;
            }
        }
        throw new IllegalArgumentException("Status inválido");
    }
}