package org.zkbase.webapp.controller;

import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Textbox;

public class MainComposer extends GenericForwardComposer {
	
	public String getAbout() {		
		return "ZKBASE Version 1.0";
	}
	
	public String getTest() {
		return "test";
	}
}
