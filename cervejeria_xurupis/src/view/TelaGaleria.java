package view;

import java.awt.Color;

import javax.swing.JPanel;

import model.Usuario;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;

public class TelaGaleria extends JPanel {

	private static final long serialVersionUID = 1L;
	private Usuario user;
	private JLabel lblNewLabel;
	/**
	 * Create the panel.
	 */
	public TelaGaleria(Usuario user) {
		initComponents();
		this.user = user;
		
		this.lblNewLabel = new JLabel("Rótulos:");
		this.lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(this.lblNewLabel, "cell 1 1");
	}
	private void initComponents() {
		setBackground(new Color(230, 205, 153));
		setBounds(100, 100, 1000, 600);
		setLayout(new MigLayout("", "[][]", "[][]"));
	}

}
