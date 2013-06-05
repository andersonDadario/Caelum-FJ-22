package br.com.caelum.argentum.reader;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;

import br.com.caelum.argentum.modelo.Negociacao;

public class LeitorXMLTest {

	@Test
	public void carregaXmlComUmaNegociacaoEmListaUnitaria() {
		String xmlDeTeste = "<list>" +
		          "  <negociacao>" +
		          "    <preco>43.5</preco>" +
		          "    <quantidade>1000</quantidade>" +
		          "    <data>" +
		          "      <time>1322233344455</time>" +
		          "    </data>" +
		          "  </negociacao>" +
		          "</list>";
	  
	  LeitorXML leitor = new LeitorXML();

	  InputStream xml = new ByteArrayInputStream(xmlDeTeste.getBytes());

	  List<Negociacao> negociacoes = leitor.carrega(xml);
	  
	  
	  assertEquals((Integer)1, (Integer)negociacoes.size());
	  assertEquals(negociacoes.get(0).getPreco(), 43.5, 00001);
	  assertEquals(negociacoes.get(0).getQuantidade(), 1000);
	}

}
