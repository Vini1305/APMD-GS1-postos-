package br.com.fiap.posto.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import br.com.fiap.posto.Application;
import br.com.fiap.posto.dao.PostoDao;

public class TableController implements MouseListener {
	
	private PostoDao dao = new PostoDao();
	private Application view;

	public TableController(Application view) {
		this.view = view;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			int resposta = JOptionPane.showConfirmDialog(null,
					"Apagar posto?");
			if (resposta == JOptionPane.YES_OPTION) {
				JTable table = (JTable) e.getSource();
				String id = (String) table.getValueAt(table.getSelectedRow(), 0);
				dao.apagar(Long.valueOf(id));
				view.carregarDados();
				JOptionPane.showMessageDialog(null, "Posto retirado do registro!");
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
	

}
