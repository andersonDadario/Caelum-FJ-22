package br.com.caelum.argentum.modelo;

import java.util.Calendar;
import java.util.List;

public final class Negociacao {
	private final double preco;
	private final int quantidade;
	private final Calendar data;
	
	/* CTRL + 3
	 * Digitar "Construtor"
	 * Selecionar "Generate Contructor Using Fields"
	 * Selecionar "Ommit call to default constructor super()"
	 */
	public Negociacao(double preco, int quantidade, Calendar data) {
		if(data == null){
			throw new IllegalArgumentException();
		}
		
		this.preco = preco;
		this.quantidade = quantidade;
		this.data = data;
	}

	/*
	 * CTRL + 3
	 * Digitar "Generater Get"
	 * Selecionar "Generate Getters and Setters"
	 * Selecione "Select Getters"
	 */
	public double getPreco() {
		return preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Calendar getData() {
		//return data;
		return (Calendar) data.clone();
	}
	
	public double getVolume() {
		  return preco * quantidade;
	}
	
	public boolean isMesmoDia(Calendar outraData){
		return this.data.get(Calendar.DATE) == outraData.get(Calendar.DATE) &&
	       this.data.get(Calendar.MONTH) == outraData.get(Calendar.MONTH) &&
	       this.data.get(Calendar.YEAR) == outraData.get(Calendar.YEAR);
	}
}
