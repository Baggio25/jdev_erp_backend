package com.baggio.jdev_erp_backend.model.enums;

public enum TipoPessoa {
    JURIDICA("Jurídica"),
    FISICA("Fisíca");

    private final String descricao;

    private TipoPessoa(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
