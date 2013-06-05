package br.com.caelum.argentum.modelo;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

//import junit.framework.Assert;

import org.junit.Test;
import static org.junit.Assert.*;


public class CandlestickFactoryTest {
	  /*
	   * Para rodar os testes há 2 opções:
	   * 1) CTRL + F11: roda o que estiver aberto
	   * 2) ALT + SHIFT + X (solte) T: roda testes do JUnit
	   */

	  @Test
	  public void sequenciaSimplesDeNegociacoes() {
	    Calendar hoje = Calendar.getInstance();

	    Negociacao negociacao1 = new Negociacao(40.5, 100, hoje);
	    Negociacao negociacao2 = new Negociacao(45.0, 100, hoje);
	    Negociacao negociacao3 = new Negociacao(39.8, 100, hoje);
	    Negociacao negociacao4 = new Negociacao(42.3, 100, hoje);

	    List<Negociacao> negociacoes = Arrays.asList(
	    		negociacao1
	    		, negociacao2
	    		, negociacao3
	    		, negociacao4
	    );

	    CandlestickFactory fabrica = new CandlestickFactory();
	    Candlestick candle = fabrica.constroiCandleParaData(hoje, negociacoes);
	    
	    assertEquals(40.5, candle.getAbertura(), 0.00001);
	    assertEquals(42.3, candle.getFechamento(), 0.00001);
	    assertEquals(39.8, candle.getMinimo(), 0.00001);
	    assertEquals(45.0, candle.getMaximo(), 0.00001);
	    assertEquals(16760.0, candle.getVolume(), 0.00001);
	  }
	  
	  @Test
	  public void semNegociacoesGeraCandleComZeros(){
		  Calendar hoje = Calendar.getInstance();
		  List <Negociacao> ns = Arrays.asList();
		  
		  CandlestickFactory candlestick = new CandlestickFactory();
		  Candlestick candle = candlestick.constroiCandleParaData(hoje, ns);
		  assertEquals(0.0, candle.getVolume(), 0.00001);
	  }
	}