package br.com.caelum.argentum.modelo;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;
import static org.junit.Assert.*;

public class NegociacaoTest {
	@Test
	public void mesmoMilissegundoEhDoMesmoDia() {
		  Calendar agora = Calendar.getInstance();
		  Calendar mesmoMomento = (Calendar) agora.clone();

		  Negociacao negociacao = new Negociacao(40.0, 100, agora);
		  assertTrue(negociacao.isMesmoDia(mesmoMomento));
		}
	
	@Test
	public void comHorariosDiferentesEhNoMesmoDia() {
	  // usando GregorianCalendar(ano, mes, dia, hora, minuto)
	  Calendar manha = new GregorianCalendar(2011, 10, 20, 8, 30);
	  Calendar tarde = new GregorianCalendar(2011, 10, 20, 15, 30);

	  Negociacao negociacao = new Negociacao(40.0, 100, manha);
	  assertTrue(negociacao.isMesmoDia(tarde));
	}
	
	@Test
	public void mesmoDiaMasMesesDiferentesNaoSaoDoMesmoDia(){
		  // usando GregorianCalendar(ano, mes, dia, hora, minuto)
		  Calendar manha = new GregorianCalendar(2011, 10, 20, 8, 30);
		  Calendar tarde = new GregorianCalendar(2011, 11, 20, 15, 30);

		  Negociacao negociacao = new Negociacao(40.0, 100, manha);
		  assertFalse(negociacao.isMesmoDia(tarde));
	}
	
	@Test
	public void mesmoDiaEMesMasAnosDiferentesNaoSaoDoMesmoDia(){
		// usando GregorianCalendar(ano, mes, dia, hora, minuto)
		  Calendar manha = new GregorianCalendar(2011, 10, 20, 8, 30);
		  Calendar tarde = new GregorianCalendar(2012, 10, 20, 15, 30);

		  Negociacao negociacao = new Negociacao(40.0, 100, manha);
		  assertFalse(negociacao.isMesmoDia(tarde));
	}

	@Test
	public void dataDoNegocioEhImutavel() {
		Negociacao n = new Negociacao(
				40.5
				, 100
				, new GregorianCalendar(2013,6,15,0,0)
		);
		
		// Altera a data para o dia 20
		Calendar cal = n.getData();
		cal.set(Calendar.DAY_OF_MONTH, 20);
		
		// Data tem que continuar no dia 15
		assertEquals(n.getData().get(Calendar.DAY_OF_MONTH), 15);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void dataNaoPodeSerNula(){
		new Negociacao(100, 100, null);
	}

}
