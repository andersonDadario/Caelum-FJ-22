package br.com.caelum.argentum.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CandlestickFactory {
	public List<Candle> constroiCandles(List<Negociacao> todosOsNegocios){
		Calendar dataDoDia = todosOsNegocios.get(0).getData();
		List<Negociacao> doDia = new ArrayList<Negociacao>();
		List<Candle> candles = new ArrayList<Candle>();
		Candle c = null;
		
		for(Negociacao n : todosOsNegocios){
			if (n.getData().before(dataDoDia)) {
			    throw new IllegalStateException("negociações em ordem errada");
			}
			// se não for mesmo dia, fecha candle e reinicia variáveis
						
			if(c == null){
				c = constroiCandleParaData(dataDoDia, doDia);
			}
			
			if(!n.isMesmoDia(dataDoDia)){
			    Candle candleDoDia = constroiCandleParaData(dataDoDia, doDia);
			    candles.add(candleDoDia);
				dataDoDia = n.getData();
				doDia.clear();
			}
			
			doDia.add(n);
		}
		
		return candles;
	}
	
	public Candle constroiCandleParaData(Calendar data, List<Negociacao> negocios) {

		double maximo = Double.MIN_VALUE;
		double minimo = Double.MAX_VALUE;
		double volume = 0;

		for (Negociacao negocio : negocios) {
			volume += negocio.getVolume();

			double preco = negocio.getPreco();
			if (preco > maximo) {
				maximo = preco;
			} 
			if (preco < minimo) {
				minimo = preco;
			}
		}

		double abertura = negocios.isEmpty() ? 0 : negocios.get(0).getPreco();
		double fechamento = negocios.isEmpty() ? 0 : negocios.get(negocios.size() - 1).getPreco();

		return new Candle(abertura, fechamento, minimo, maximo, volume, data);
	}

	private List<Candle> criaEGuardaCandle(List<Negociacao> todasNegociacoes) {
		List<Candle> candles = new ArrayList<Candle>();

		List<Negociacao> negociacoesDoDia = new ArrayList<Negociacao>();
		Calendar dataAtual = todasNegociacoes.get(0).getData();

		for (Negociacao negociacao : todasNegociacoes) {
			// se não for mesmo dia, fecha candle e reinicia variáveis
			if (!negociacao.isMesmoDia(dataAtual)) {
				criaEGuardaCandle(candles, negociacoesDoDia, dataAtual);
				negociacoesDoDia = new ArrayList<Negociacao>();
				dataAtual = negociacao.getData();
			}
			negociacoesDoDia.add(negociacao);
		}
		criaEGuardaCandle(candles, negociacoesDoDia, dataAtual);

		return candles;
	}

	private void criaEGuardaCandle(List<Candle> candles,
			List<Negociacao> negociacoesDoDia, Calendar dataAtual) {
		Candle candleDoDia = constroiCandleParaData(dataAtual, 
												  negociacoesDoDia);
		candles.add(candleDoDia);
	}
}
