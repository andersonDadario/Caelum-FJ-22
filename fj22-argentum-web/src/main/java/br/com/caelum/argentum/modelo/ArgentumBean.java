package br.com.caelum.argentum.modelo;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.chart.ChartModel;

import br.com.caelum.argentum.grafico.GeradorModeloGrafico;
import br.com.caelum.argentum.indicadores.MontadorIndicador;
import br.com.caelum.argentum.ws.ClienteWebService;

@ManagedBean
@SessionScoped
public class ArgentumBean {

  private List<Negociacao> negociacoes;
  private ChartModel modeloGrafico;
  private String titulo;
  private String nomeIndicador; //getter e setter
  private String nomeMedia; //getter e setter
  
//@PostConstruct
  public void preparaDados() {
	//System.out.println("Indicador: " + nomeIndicador + ", " + nomeMedia);
	  
    ClienteWebService cliente = new ClienteWebService();
    this.negociacoes = cliente.getNegociacoes();

    List<Candle> candles = new CandlestickFactory().constroiCandles(negociacoes);
    SerieTemporal serie = new SerieTemporal(candles);


    GeradorModeloGrafico gerador = 
    	      new GeradorModeloGrafico(serie, 2, serie.getTotal() -1 );
    	  
    MontadorIndicador indicadorFactory = 
    	      new MontadorIndicador(getNomeIndicador(), getNomeMedia());
    	  
    	  gerador.plotaIndicador(indicadorFactory.getIndicador());
    	  
    	  this.modeloGrafico = gerador.getModeloGrafico();

    
  }

  public ChartModel getModeloGrafico() {
    return this.modeloGrafico;
  }

  public List<Negociacao> getNegociacoes() {
    return this.negociacoes;
  }

	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getNomeIndicador() {
		return nomeIndicador;
	}

	public void setNomeIndicador(String nomeIndicador) {
		this.nomeIndicador = nomeIndicador;
	}

	public String getNomeMedia() {
		return nomeMedia;
	}

	public void setNomeMedia(String nomeMedia) {
		this.nomeMedia = nomeMedia;
	}

}
