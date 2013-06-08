package br.com.caelum.argentum.indicadores;

import br.com.caelum.argentum.modelo.Candle;
import br.com.caelum.argentum.modelo.SerieTemporal;

public class MediaMovelPonderada implements Indicador {
	
	private final Indicador outroIndicador;

	public MediaMovelPonderada(Indicador outroIndicador){
		this.outroIndicador = outroIndicador;
		
	}
     
   public double calcula(int posicao, SerieTemporal serie) {
     double soma = 0.0;
     int peso = 1;
   
     for (int i = posicao - 2; i <= posicao; i++) {
       //Candle c = serie.getCandle(i);
       //soma += c.getFechamento() * peso;
       soma += outroIndicador.calcula(i, serie) * peso;;
       peso++;
     }
     return soma / 6;
   }
   
   public String toString() {
	    return "MMP-" + this.outroIndicador.toString();
	  }
 }