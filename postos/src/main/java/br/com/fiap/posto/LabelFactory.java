package br.com.fiap.posto;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class LabelFactory extends JLabel {
	
	private static final long serialVersionUID = 1L;
	private int fontSize = 12;
	
	public LabelFactory(String texto) {
		super(texto);
		init();
	}
	
	private void init() {
		this.setHorizontalAlignment(JLabel.LEFT);
		this.setForeground(new Color(34,186,255));
		this.setFont(new Font("Century Gothic", Font.ITALIC, fontSize));
	}

}
