package br.com.caelum.argentum.indicadores;

import br.com.caelum.argentum.modelo.SerieTemporal;

public class MediaMovelSimples implements Indicador {

	private final Indicador outroIndicador;

	public MediaMovelSimples(Indicador outroIndicador){
		this.outroIndicador = outroIndicador;
		
	}

	public double calcula(int posicao, SerieTemporal serie) {
	     double soma = 0.0;
	     for (int i = posicao - 2; i <= posicao; i++) {
	    	 soma += outroIndicador.calcula(i, serie);
	     }
	     return soma / 3;
	   }
   
   public String toString() {
	    return "MMS de Fechamento";
	  }

}
