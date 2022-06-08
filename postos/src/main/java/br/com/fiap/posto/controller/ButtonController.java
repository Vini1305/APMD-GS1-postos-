package br.com.fiap.posto.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.com.fiap.posto.Application;

public class ButtonController implements ActionListener {
	
	private Application view;
	
	public ButtonController(Application view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		view.carregarDados();
	}

}
