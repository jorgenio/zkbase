package org.zkbase.webapp.controller;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Components;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zul.Include;
import org.zkoss.zul.Window;

public class MainWindow extends Window {

	private static final long serialVersionUID = -2091092165757421532L;
	
	public void includeContent(String file) {
		System.out.println("Loading file: " + file);
		Include content = (Include)this.getFellow("content");
		content.setSrc(file);			
	}

	public void about() {

		Window win = (Window)Executions.createComponents("about.zul", this, null);
		win.setClosable(true);
		try {
			win.doModal();
		} catch (SuspendNotAllowedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
