package fl.domus.common;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;


public abstract class GPIOControledDevice 
{

	public GPIOControledDevice(Pin gpionum, String name)
	{
		_logger.debug("Creation  GPIOControledDevice (" + name + ") " + _gpionum );

		_name = name;
		SetPin(gpionum);		
	}
	
	public GPIOControledDevice(Node n)
	{
		_logger.debug("Creation  GPIOControledDevice par node");
		_list.add(this);
	}

	public GPIOControledDevice GetByName(String name) 
	{
		// TODO Auto-generated method stub
		
		for(GPIOControledDevice r : _list)
		{
			if(r.GetName().equals(name))
			{
				return r;
			}
		}
		
		return null;
	}

	//-------------- Methodes abstraites ------------------
	public abstract void Activate(boolean state);
	public abstract boolean IsActivated(); 

	//-------------- Methodes standards ------------------
	public void SetPin(Pin p) 
	{ 
		_logger.debug("SetPin (" + _name + ") " + p );
		
		_gpionum = p; 
		_pin = _gpio.provisionDigitalOutputPin(_gpionum,				
				 _name + "_pin_" + _gpionum                   // PIN FRIENDLY NAME (optional)
				 );				
	}
	
	public Pin GetPin() { return _gpionum; }
	public void SetName(String name) { _name = name; }
	public String GetName() { return _name; }
	
	
	//-------------- variables ---------------------------
	
//	GpioController    		_gpio = GpioFactory.getInstance();
	
	GpioController    		_gpio = null;

	protected Pin					_gpionum = null;
	protected GpioPinDigitalOutput 	_pin;

	protected String				_name = "undef";	

	private static Logger 			_logger = Logger.getLogger(GPIOControledDevice.class);
	
	protected static ArrayList<GPIOControledDevice> _list = new  ArrayList<GPIOControledDevice>();



}
