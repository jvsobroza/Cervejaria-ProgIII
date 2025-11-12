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
	 */
	public TelaLogin() {

		initComponents();
	}
	private void initComponents() {
		setBackground(new Color(230, 205, 153));
		setBounds(100, 100, 800, 500);
		setLayout(new MigLayout("", "[grow][][grow]", "[][]"));
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("img/logoNBG.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(200, 200,Image.SCALE_SMOOTH);
		
		this.lblNewLabel_1 = new JLabel("");
		this.lblNewLabel_1.setIcon(new ImageIcon(dimg));
		add(this.lblNewLabel_1, "cell 2 0,alignx right,aligny top");
	}

}
