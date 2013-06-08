package br.com.caelum.argentum.indicadores;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class MontadorIndicador {

	 private static final String PACKAGE = "br.com.caelum.argentum.indicadores.";
	  private String nomeIndicador;
	  private String nomeMedia;

	  public MontadorIndicador(String nomeIndicador, String nomeMedia) {
	    this.nomeIndicador = nomeIndicador;
	    this.nomeMedia = nomeMedia;
	  }



	  public Indicador getIndicador() {

		  try {
		    Indicador indicador = (Indicador) Class.forName(PACKAGE + this.nomeIndicador)
		          .newInstance();
		    
		    if(this.nomeMedia != null && !this.nomeMedia.isEmpty()) {
		      Constructor<?> constructor = Class.forName(PACKAGE + this.nomeMedia)
		          .getConstructor(Indicador.class);

		      indicador = (Indicador) constructor.newInstance(indicador);
		    }
		    
		    return indicador;
		  
		  } catch (InstantiationException e) {
		    throw new RuntimeException(e);
		  } catch (IllegalAccessException e) {
		    throw new RuntimeException(e);
		  } catch (ClassNotFoundException e) {
		    throw new RuntimeException(e);
		  }  catch (NoSuchMethodException e) {
		    throw new RuntimeException(e);
		  } catch (InvocationTargetException e) {
		    throw new RuntimeException(e);
		  }
		}
}