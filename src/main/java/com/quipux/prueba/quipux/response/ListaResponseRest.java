package com.quipux.prueba.quipux.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListaResponseRest extends ResponseRest {
	
	private ListaResponse listaResponse = new ListaResponse();

	public ListaResponse getListaResponse() {
		return listaResponse;
	}

	public void setListaResponse(ListaResponse listaResponse) {
		this.listaResponse = listaResponse;
	}

}
