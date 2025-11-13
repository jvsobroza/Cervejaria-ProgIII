package view;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;

public class TelaLogin extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;

	/**
	 * Create the panel.
	 * @throws IOException 
	 */
	public TelaLogin() throws IOException {

		initComponents();
	}
	private void initComponents() throws IOException {
		setBackground(new Color(230, 205, 153));
		setBounds(100, 100, 1000, 600);
		setLayout(new MigLayout("", "[grow][][][]", "[][]"));
		BufferedImage img = null;

		img = ImageIO.read(TelaLogin.class.getResource("/resources/img/logos/logoNBG.png")); 
		Image dimg = img.getScaledInstance(250, 250,Image.SCALE_SMOOTH);
		
		this.lblNewLabel_1 = new JLabel("");
		this.lblNewLabel_1.setIcon(new ImageIcon(dimg));
		add(this.lblNewLabel_1, "cell 3 0,alignx right,aligny top");
	}

}
