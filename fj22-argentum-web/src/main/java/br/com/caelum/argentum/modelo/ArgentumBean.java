package br.com.caelum.argentum.modelo;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.chart.ChartModel;

import br.com.caelum.argentum.grafico.GeradorModeloGrafico;
import br.com.caelum.argentum.indicadores.IndicadorFechamento;
import br.com.caelum.argentum.indicadores.MediaMovelSimples;
import br.com.caelum.argentum.ws.ClienteWebService;

@ManagedBean
@SessionScoped
public class ArgentumBean {

  private List<Negociacao> negociacoes;
  private ChartModel modeloGrafico;
  
  @PostConstruct
  void preparaDados() {
    ClienteWebService cliente = new ClienteWebService();
    this.negociacoes = cliente.getNegociacoes();

    List<Candle> candles = new CandlestickFactory().constroiCandles(negociacoes);
    SerieTemporal serie = new SerieTemporal(candles);

    GeradorModeloGrafico gerador = 
            new GeradorModeloGrafico(serie, 2, serie.getTotal() - 1);

    gerador.plotaMediaMovelSimples(new MediaMovelSimples(new IndicadorFechamento()));

    this.modeloGrafico = gerador.getModeloGrafico();
  }

  public ChartModel getModeloGrafico() {
    return this.modeloGrafico;
  }

  public List<Negociacao> getNegociacoes() {
    return this.negociacoes;
  }

}
