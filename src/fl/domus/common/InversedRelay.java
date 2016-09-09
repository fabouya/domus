package fl.domus.common;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;

public class InversedRelay extends Relay 
{
	
	public InversedRelay(Node n)
	{
		super(n);
	}
	
	public InversedRelay(Pin gpionum, String name) {
		super(gpionum, name);
		_logger.debug("Creation  InversedRelay (" + name + ") " + _gpionum );
	}

	@Override
	public void Activate(boolean state) 
	{
		_logger.info("Activation Inverse (" + _name + ") " + _gpionum + " : " + state);
		
		super.Activate(! state);
	}
	
	@Override
	public boolean IsActivated() 
	{ 
		PinState etat = _pin.getState();
		return (PinState.HIGH == etat) ? false : true;
	}

	//-------------- variables ---------------------------

	private static Logger 			_logger = Logger.getLogger(InversedRelay.class);

}
