package com.lucasteixeira.workandwork.enums;

public enum Perfil {
    ADMIN(0, "ROLE_ADMIN"),
    CLIENTE(1, "ROLE_CLIENTE"),
    TECNICO(2,"ROLE_TECNICO");

    private Integer code;
    private String descricao;

    Perfil(Integer code, String descricao) {
        this.code = code;
        this.descricao = descricao;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Perfil toEnum(Integer code) {
        for (Perfil x : Perfil.values()) {
            if(code.equals(x.code)) {
                return x;
            }
        }
        throw new IllegalArgumentException("Perfil inválido");
    }
}