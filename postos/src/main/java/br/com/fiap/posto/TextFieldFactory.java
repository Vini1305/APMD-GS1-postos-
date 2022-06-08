package br.com.fiap.posto;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class TextFieldFactory extends JTextField {
	
	private static final long serialVersionUID = 1L;

	
	public TextFieldFactory() {
		init();
	}
	
	private void init() {
		this.setBackground(Color.DARK_GRAY);
		this.setBorder(new LineBorder(new Color(100,110,110)));
		this.setForeground(new Color(150,150,150));
		this.setFont(new Font("Century Gothic", Font.PLAIN, 12));
	}
	

}
