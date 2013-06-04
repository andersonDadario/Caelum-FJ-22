package br.com.caelum.argentum.modelo;

import java.util.Calendar;
import java.util.List;

public class CandlestickFactory {
	public Candlestick constroiCandleParaData(Calendar data, 
            List<Negociacao> ns) {
		double abertura = ns.isEmpty()? 0 : ns.get(0).getPreco();
		double fechamento = ns.isEmpty()? 0 : ns.get(ns.size()-1).getPreco();
		double minimo = Double.MAX_VALUE;
		double maximo = Double.MIN_VALUE;
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
