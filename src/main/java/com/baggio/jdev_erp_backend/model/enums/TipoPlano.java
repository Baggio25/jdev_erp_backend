package com.baggio.jdev_erp_backend.model.enums;

public enum TipoPlano {
	
	FREE("Plano Gratuito"),
	PRO("Plano Profissional"),
	ENTERPRISE("Plano Corporativo");

	private final String descricao;
	
	private TipoPlano(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
