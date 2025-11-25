package view;

import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.UsuarioCervejaDAO;
import model.Usuario;
import net.miginfocom.swing.MigLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TelaLogado extends JPanel {

	private static final long serialVersionUID = 1L;
	private Usuario user;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel labelQuantiDegu;
	private UsuarioCervejaDAO conDegu;

	/**
	 * Create the panel.
	 * @throws IOException 
	 */
	public TelaLogado(Usuario user) throws IOException {
		this.user = user;
		conDegu = new UsuarioCervejaDAO();
		initComponents();
		labelQuantiDegu.setText("Quantidade de degustações esse mes: " + conDegu.getContagemMes(user));
	}
	private void initComponents() throws IOException {
		setBackground(new Color(230, 205, 153));
		setBounds(100, 100, 1000, 600);
		setLayout(new MigLayout("", "[grow][grow]", "[][][][grow]"));
		
		this.lblNewLabel = new JLabel("Seja bem vindo novamente");
		this.lblNewLabel.setText("Seja bem vindo novamente " + user.getNome() + "!");
		this.lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(this.lblNewLabel, "cell 0 0");
		this.lblNewLabel_2 = new JLabel("");
		BufferedImage img = ImageIO.read(TelaLogin.class.getResource("/resources/img/logos/logoNBG.png"));
		Image dimg = img.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
		this.lblNewLabel_2.setIcon(new ImageIcon(dimg));
		add(this.lblNewLabel_2, "cell 1 0");
		
		this.lblNewLabel_1 = new JLabel("Aproveite o menu para realizar as ações na melhor cervejaria da America Látina");
		this.lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(this.lblNewLabel_1, "cell 0 1");
		
		this.labelQuantiDegu = new JLabel("Quantidade de degustações esse mes:");
		this.labelQuantiDegu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		add(this.labelQuantiDegu, "cell 0 2");
	}
	

}
