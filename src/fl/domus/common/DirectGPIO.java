package fl.domus.common;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class DirectGPIO 
{
	protected static ArrayList<DirectGPIO> _list = new  ArrayList<DirectGPIO>();
	private static Logger logger = Logger.getLogger(DirectGPIO.class);

	public static DirectGPIO Builder()
	{
		return null;
	}
	
	public DirectGPIO(Node n)
	{
		logger.debug("Create DirectGPIO");
		
		_list.add(this);

		logger.debug("Parcours sous nodes");		

		final NodeList racineNoeuds = n.getChildNodes();
		
		final int nbRacineNoeuds = racineNoeuds.getLength();
		
		for (int i = 0; i<nbRacineNoeuds; i++) {

            if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) 
            {
                Node e = (Node) racineNoeuds.item(i);
                
                logger.debug("Sous node : " + e.getNodeName());
                
                Object o = Builder.Instantiate(n, "fl.domus.common." + e.getNodeName());
                
                _deviceList.add((GPIOControledDevice) o);                
            }
		}

	}
	
	protected ArrayList<GPIOControledDevice> _deviceList = new ArrayList<GPIOControledDevice>();	
}
