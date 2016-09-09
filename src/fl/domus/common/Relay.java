package fl.domus.common;

import java.util.ArrayList;
import org.apache.log4j.Logger;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Relay extends GPIOControledDevice 
{	
	public Relay(Node n)
	{
		super(n);
		// extraire les valeurs du node		   

		_logger.debug("Creation  Relay ");
		_logger.debug("Node " + n.getNodeName());
/*		
		Element e = n.getAttribute("name"); 

	    System.out.println("sexe : " + n.getAttribute("sexe"));
		*/
	}


	public Relay(Pin gpionum, String name) 
	{
		super(gpionum, name);
		_logger.debug("Creation  Relay (" + name + ") " + _gpionum );
	}

	//-------------- Methodes standards ------------------

	@Override
	public void Activate(boolean state) 
	{
		_logger.info("Activation (" + _name + ") " + _gpionum + " : " + state);

		if(state)
		{
			_pin.high();				
		}
		else
		{
			_pin.low();				
		}				
	}

	@Override
	public boolean IsActivated() 
	{ 
		PinState etat = _pin.getState();
		return (PinState.HIGH == etat) ? true : false;
	}

	//-------------- variables ---------------------------

	private static Logger 			_logger = Logger.getLogger(Relay.class);
}
