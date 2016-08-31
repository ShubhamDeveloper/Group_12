import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.citi.portfoliomanager.ejb.PortfolioManagerBeanRemote;
import com.citi.portfoliomanager.jpa.ClientPortfolio;
import com.citi.portfoliomanager.jpa.MarketData;
import com.citi.portfoliomanager.jpa.Stock;
//import com.citi.portfoliomanager.jpa.Dummy;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
	public static void main(String[] args) throws NamingException {
		// TODO Auto-generated method stub
		Properties prop = new Properties();
		prop.put(Context.INITIAL_CONTEXT_FACTORY, org.jboss.naming.remote.client.InitialContextFactory.class.getName()); 
		prop.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		prop.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		prop.put("jboss.naming.client.ejb.context", true);
		
		// Create the JNDI InitialContext.
		Context context = new InitialContext(prop);
		
		PortfolioManagerBeanRemote bean = (PortfolioManagerBeanRemote) context.lookup("PortfolioManager/PortfolioManagerEJB/PortfolioManagerBean!com.citi.portfoliomanager.ejb.PortfolioManagerBeanRemote");
	//	bean.putString();
	//	List<Dummy> dummies = bean.sayHello();
//		for(Dummy dummy: dummies){
//			System.out.println(dummy.getDummyField());
//		}
//		try {
//			Dummy dummy = putHello();
//			bean.putString(dummy);
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		MarketData mkd = bean.getCurrentMarketData("AAPL");
		System.out.println(bean.getQuantityForAStock());
		
	}
//	public static Dummy putHello() throws JsonParseException, JsonMappingException, IOException{
//		ObjectMapper mapper = new ObjectMapper();
//		Dummy dummy = mapper.readValue(new File("C:\\Users\\Grad21\\Downloads\\portfolio.json"), Dummy.class);
//		return dummy;
//	}
	public Main() {
		super();
	}

}