package view;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TelaLogado extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public TelaLogado() {
		setBackground(new Color(230, 205, 153));
		setBounds(100, 100, 1000, 600);
		add(new JLabel("=== SE VOCÊ VÊ ISSO, A TROCA FUNCIONOU! ==="));
	}

}
