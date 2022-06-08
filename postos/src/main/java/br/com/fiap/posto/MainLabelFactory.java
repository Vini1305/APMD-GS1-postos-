package br.com.fiap.posto;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class MainLabelFactory extends JLabel {
	
	private static final long serialVersionUID = 1L;
	private int fontSize = 15;
	
	public MainLabelFactory(String texto) {
		super(texto);
		init();
	}
	
	private void init() {
		this.setHorizontalAlignment(JLabel.LEFT);
		this.setForeground(new Color(250,63,147));
		this.setFont(new Font("Century Gothic", Font.ITALIC, fontSize));
	}

}
