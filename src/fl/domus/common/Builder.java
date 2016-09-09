package fl.domus.common;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;

public class Builder 
{
	private static Logger logger = Logger.getLogger(Builder.class);

	@SuppressWarnings("unchecked")
	public
	static Object Instantiate(Node n, String className) 
	{
	    try
	    {
	      // Récupération de la classe 
	      @SuppressWarnings("rawtypes")
	      Class classe = Class.forName (className);
	      
	      // Récupération du constructeur prenant en paramètre un node xml
	      @SuppressWarnings("rawtypes")
	      java.lang.reflect.Constructor constructeur = 
	                     classe.getConstructor (new Class [] {Class.forName ("org.w3c.dom.Node")});
	      
	      return constructeur.newInstance (new Object [] {n});
	    }
	    catch (Exception e)
	    {
	      // La classe n'existe pas
	    	logger.fatal("La classe n'existe pas : " + className);
			e.printStackTrace();
	    }
	    
		return null;	
	}		

}
