package com.baggio.jdev_erp_backend.model.enums;

public enum StatusPedido {
    AGUARDANDO_PAGAMENTO("Aguardando Pagamento"),
    PAGAMENTO_APROVADO("Pagamento Aprovado"),
    PAGAMENTO_RECUSADO("Pagamento Recusado"),
    EM_SEPARACAO("Em Separação"),
    SEPARADO("Separado"),
    FATURADO("Faturado"),
    AGUARDANDO_ENVIO("Aguardando Envio"),
    ENVIADO("Enviado"),
    EM_TRANSPORTE("Em Transporte"),
    ENTREGUE("Entregue"),
    CANCELADO("Cancelado"),
    DEVOLVIDO("Devolvido"),
    REEMBOLSADO("Reembolsado");

    private final String descricao;

    StatusPedido(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}