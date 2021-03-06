package br.com.caelum.argentum.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CandlestickFactory {
	public List<Candlestick> constroiCandles(List<Negociacao> todosOsNegocios){
		Calendar dataDoDia = todosOsNegocios.get(0).getData();
		List<Negociacao> doDia = new ArrayList<Negociacao>();
		List<Candlestick> candles = new ArrayList<Candlestick>();
		Candlestick c = null;
		
		for(Negociacao n : todosOsNegocios){
			if (n.getData().before(dataDoDia)) {
			    throw new IllegalStateException("negociações em ordem errada");
			}
			// se não for mesmo dia, fecha candle e reinicia variáveis
						
			if(c == null){
				c = constroiCandleParaData(dataDoDia, doDia);
			}
			
			if(!n.isMesmoDia(dataDoDia)){
			    Candlestick candleDoDia = constroiCandleParaData(dataDoDia, doDia);
			    candles.add(candleDoDia);
				dataDoDia = n.getData();
				doDia.clear();
			}
			
			doDia.add(n);
		}
		
		// adiciona último candle
		  Candlestick candleDoDia = constroiCandleParaData(dataDoDia, doDia);
		  candles.add(candleDoDia);

		
		return candles;
	}
	
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
			
			volume += n.getVolume();
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
