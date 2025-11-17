package view;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

public class TelaLogado extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblNewLabel;

	/**
	 * Create the panel.
	 */
	public TelaLogado() {
		initComponents();
	}
	private void initComponents() {
		setBackground(new Color(230, 205, 153));
		setBounds(100, 100, 1000, 600);
		setLayout(new MigLayout("", "[]", "[]"));
		
		this.lblNewLabel = new JLabel("TESTE");
		add(this.lblNewLabel, "cell 0 0");
	}

}
