package tbotalla.controller;

import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;

public interface ButtonListener {
	final static Logger logger = Logger.getLogger(ButtonListener.class); // Log4j

	public void actionPerformed(ActionEvent e);
}
