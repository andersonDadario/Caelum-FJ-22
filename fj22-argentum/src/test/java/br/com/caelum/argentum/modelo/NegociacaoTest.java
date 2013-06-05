package br.com.caelum.argentum.modelo;

import java.util.Calendar;
import java.util.GregorianCalendar;

//import junit.framework.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

public class NegociacaoTest {

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
