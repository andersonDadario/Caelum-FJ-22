package br.com.caelum.argentum.ws;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import br.com.caelum.argentum.modelo.Negociacao;
import br.com.caelum.argentum.reader.LeitorXML;

public class ClienteWebService {
	private static final String URL_WEBSERVICE  =
		    "http://argentum-ws.cloudfoundry.com/negociacoes";
	
	public List<Negociacao> getNegociacoes() {

	    URL url;
	    
		try {
			url = new URL(URL_WEBSERVICE);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		    InputStream content = connection.getInputStream();
		    connection.disconnect();
		    return new LeitorXML().carrega(content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 

	  }
}
