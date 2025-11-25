package view;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.Border;

import org.mindrot.jbcrypt.BCrypt;

import controller.UsuarioDAO;
import model.Usuario;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaEditarUsuario extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblNewLabel;
	private JPanel panel;
	private JLabel lblNewLabel_1;
	private JTextField txtNome;
	private JLabel lblNewLabel_2;
	private JTextField txtEmail;
	private JButton btAlterar;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JPasswordField senhaVelha;
	private JPasswordField senhaNova;
	private JLabel imgLogo;
	private Usuario user;
	private UsuarioDAO conUser;

	/**
	 * Create the panel.
	 * 
	 * @throws IOException
	 */
	public TelaEditarUsuario(Usuario user) throws IOException {
		this.user = user;
		initComponents();
		preencherCampos();
		conUser = new UsuarioDAO();
	}

	private void initComponents() throws IOException {
		setBackground(new Color(230, 205, 153));
		setBounds(100, 100, 1000, 600);
		setLayout(new MigLayout("", "[][][708.00][grow]", "[][][360.00][][]"));

		this.lblNewLabel = new JLabel("Editar usuário!");
		this.lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(this.lblNewLabel, "cell 1 1");

		this.panel = new JPanel();
		add(this.panel, "cell 2 2 1 2,grow");
		this.panel.setLayout(
				new MigLayout("", "[70.00px][326.00,grow][grow]", "[14px][][2.00][][-1.00][][][][][][][grow]"));

		this.lblNewLabel_1 = new JLabel("Nome:");
		this.lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.panel.add(this.lblNewLabel_1, "cell 0 1,alignx left");

		this.txtNome = new JTextField();
		this.panel.add(this.txtNome, "cell 1 1,growx");
		this.txtNome.setColumns(10);

		this.lblNewLabel_2 = new JLabel("Email:");
		this.lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.panel.add(this.lblNewLabel_2, "cell 0 3,alignx left");

		this.txtEmail = new JTextField();
		this.txtEmail.setColumns(10);
		this.panel.add(this.txtEmail, "cell 1 3,growx");

		this.lblNewLabel_3 = new JLabel("Senha:");
		this.lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.panel.add(this.lblNewLabel_3, "cell 0 5");

		this.senhaVelha = new JPasswordField();
		this.senhaVelha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				senhaVelha.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
			}
		});
		this.panel.add(this.senhaVelha, "cell 1 5,growx");

		this.lblNewLabel_4 = new JLabel("Senha nova:");
		this.lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.panel.add(this.lblNewLabel_4, "cell 0 6,alignx trailing");

		this.senhaNova = new JPasswordField();
		this.senhaNova.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (senhaVelha.getText().equals("")) {
					e.consume();
					senhaVelha.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
					senhaNova.setText("");
				}
			}
		});
		this.panel.add(this.senhaNova, "cell 1 6,growx");

		this.imgLogo = new JLabel("");
		BufferedImage img = ImageIO.read(TelaEditarUsuario.class.getResource("/resources/img/logos/logoNBG.png"));
		Image dimg = img.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
		this.imgLogo.setIcon(new ImageIcon(dimg));
		this.panel.add(this.imgLogo, "cell 1 7 2 4,alignx right");

		this.btAlterar = new JButton("Alterar");
		this.btAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!verificarCampos()) {
					if (txtEmail.getText().contains("@")) {
						if (!txtEmail.getText().equals(user.getEmail())) {
							if (conUser.verificarEmail(txtEmail.getText())) {
								JOptionPane.showMessageDialog(null, "Email já existente!", "Erro!",
										JOptionPane.ERROR_MESSAGE);
							} else {
								if (verificaSenha()) {
									if (conUser.verificarSenha(user, senhaVelha.getText().toString())) {
										try {
											alterarUsuarioSenha();
										} catch (IOException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									}
								} else {
									try {
										alterarUsuario();
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
							}
						} else {
							if (senhaVelha.getText().contains("")) {
								try {
									alterarUsuario();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							} else if (conUser.verificarSenha(user, senhaVelha.getText().toString())) {
								try {
									alterarUsuarioSenha();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Possui campos vazios!", "Erro!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		this.btAlterar.setFont(new Font("Tahoma", Font.PLAIN, 19));
		this.panel.add(this.btAlterar, "cell 0 11 3 1,growx,aligny bottom");
	}

	public boolean verificaSenha() {
		boolean verifica = senhaVelha.getText().equals("") ? false : true;
		return verifica;
	}

	public void preencherCampos() {
		txtNome.setText(user.getNome());
		txtEmail.setText(user.getEmail());
	}

	public boolean verificarCampos() {
		boolean verificador = txtNome.getText().equals("") || txtEmail.getText().equals("") ? true : false;
		return verificador;
	}

	public void alterarUsuario() throws IOException {
		Usuario user = new Usuario();
		user.setIdUsuario(this.user.getIdUsuario());
		user.setNome(txtNome.getText());
		user.setEmail(txtEmail.getText());
		user.setSenha(this.user.getSenha());
		if (conUser.alterarUsuario(user)) {
			JOptionPane.showMessageDialog(null, "Usuario alterado com sucesso!");
		} else {
			JOptionPane.showMessageDialog(null, "Erro!", "Erro!", JOptionPane.ERROR_MESSAGE);
		}
		Janela.us = user;
		Janela.frame.setContentPane(new TelaLogado(Janela.us));
		Janela.frame.setVisible(true);
	}

	public void alterarUsuarioSenha() throws IOException {
		Usuario user = new Usuario();
		user.setIdUsuario(this.user.getIdUsuario());
		user.setNome(txtNome.getText());
		user.setEmail(txtEmail.getText());
		user.setSenha(BCrypt.hashpw(senhaNova.getText(), BCrypt.gensalt()));
		if (conUser.alterarUsuario(user)) {
			JOptionPane.showMessageDialog(null, "Usuario alterado com sucesso!");
		} else {
			JOptionPane.showMessageDialog(null, "Erro!", "Erro!", JOptionPane.ERROR_MESSAGE);
		}
		Janela.us = user;
		Janela.frame.setContentPane(new TelaLogado(Janela.us));
		Janela.frame.setVisible(true);
	}

}
