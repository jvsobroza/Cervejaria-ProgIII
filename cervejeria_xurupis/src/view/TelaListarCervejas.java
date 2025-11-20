package view;

import java.awt.Color;

import javax.swing.JPanel;

import model.Usuario;
import net.miginfocom.swing.MigLayout;

public class TelaListarCervejas extends JPanel {

	private static final long serialVersionUID = 1L;
	private Usuario user;
	/**
	 * Create the panel.
	 */
	public TelaListarCervejas(Usuario user) {
		initComponents();
		this.user = user;
	}
	private void initComponents() {
		setBackground(new Color(230, 205, 153));
		setBounds(100, 100, 1000, 600);
		setLayout(new MigLayout("", "[]", "[]"));
	}

}
