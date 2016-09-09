/**
 * 
 */
package fl.domus.app;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import fl.domus.common.Builder;



/**
 * @author fab
 *
 */
public class DomusApp {

	/**
	 * @param args
	 */

	private static Logger logger = Logger.getLogger(DomusApp.class);

	
	protected final static DocumentBuilderFactory xml_factory = DocumentBuilderFactory.newInstance();
	
	public static void ReadProp(String file)
	{
		
	}
	
	public static void ReadConf(String file)
	{
		logger.debug("Read conf file : " + file);
		
		try {

		    DocumentBuilder builder = xml_factory.newDocumentBuilder();
			Document document= builder.parse(new File(file));
			
			//Affiche la version de XML

			logger.debug(document.getXmlVersion());


			//Affiche l'encodage

			logger.debug(document.getXmlEncoding());  


			//Affiche s'il s'agit d'un document standalone      

			logger.debug(document.getXmlStandalone());
			
			final Element racine = document.getDocumentElement();
			
			logger.debug(racine.getNodeName());
			
			final NodeList racineNoeuds = racine.getChildNodes();
			
			final int nbRacineNoeuds = racineNoeuds.getLength();

			for (int i = 0; i<nbRacineNoeuds; i++) 
			{
			    if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) 
			    {
			    	final Node n = racineNoeuds.item(i);

			    	logger.debug(n.getNodeName());
			        
			        try 
			        {
						@SuppressWarnings("unused")
						Object o = Builder.Instantiate(n, "fl.domus.common." + n.getNodeName());
						
					} catch (Exception e) 
			        {
						
						e.printStackTrace();
					}			        

			    }               

			}
		}
		catch (Exception e) 
		{
		    e.printStackTrace();
		}		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String propFile = args[0];
		String configFile = args[1];
		
		ReadConf(configFile);
	}

}
