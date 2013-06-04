package br.com.caelum.argentum.modelo;

import java.util.Calendar;
import java.util.List;

public class CandlestickFactory {
	public Candlestick constroiCandleParaData(Calendar data, 
            List<Negociacao> ns) {
		double abertura = ns.get(0).getPreco();
		double fechamento = ns.get(ns.size()-1).getPreco();
		double minimo = abertura;
		double maximo = ns.get(0).getPreco();
		double volume = 0;
		
		for(Negociacao n : ns){
			if(n.getPreco() < minimo){
				minimo = n.getPreco();
			} else if(n.getPreco() > maximo){
				maximo = n.getPreco();
			}
			
			volume += n.getPreco();
		}
		
		return new Candlestick(
				abertura
				, fechamento
				, minimo
				, maximo
				, volume
				, data
		);

	}
}
