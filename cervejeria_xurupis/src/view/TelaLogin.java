package view;

import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.Border;

import org.mindrot.jbcrypt.BCrypt;

import controller.UsuarioDAO;
import model.Usuario;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaLogin extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JTextField txtEmail;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JTextField txtSenha;
	private JPanel panel_1;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private UsuarioDAO conUsuario;

	/**
	 * Create the panel.
	 * 
	 * @throws IOException
	 */
	public TelaLogin() throws IOException {
		initComponents();
		conUsuario = new UsuarioDAO();
		System.out.println("TESTE");
		// BCrypt.hashpw(senhaCriptografar, BCrypt.gensalt());
		// BCrypt.checkpw(senha, senhaCript);
	}

	private void initComponents() throws IOException {
		setBackground(new Color(230, 205, 153));
		setBounds(100, 100, 1000, 600);
		setLayout(new MigLayout("", "[][350.00][grow][204.00][][grow]", "[][][][][][][grow]"));
		BufferedImage img = null;

		img = ImageIO.read(TelaLogin.class.getResource("/resources/img/logos/logoNBG.png"));
		Image dimg = img.getScaledInstance(250, 250, Image.SCALE_SMOOTH);

		this.panel_1 = new JPanel();
		Border bordaSimples = BorderFactory.createLineBorder(Color.BLACK, 4);
		panel_1.setBorder(bordaSimples);
		add(this.panel_1, "flowx,cell 1 0,alignx leading,aligny bottom");
		this.panel_1.setLayout(new MigLayout("", "[][][][][][][]", "[][][]"));

		this.lblNewLabel_3 = new JLabel("Realizar login:");
		this.panel_1.add(this.lblNewLabel_3, "cell 0 0 2 1");
		this.lblNewLabel_3.setFont(new Font("Tahoma", Font.ITALIC, 16));

		this.lblNewLabel_4 = new JLabel("Email:");
		this.panel_1.add(this.lblNewLabel_4, "cell 0 1,alignx left");
		this.lblNewLabel_4.setFont(new Font("SansSerif", Font.PLAIN, 14));

		this.lblNewLabel_5 = new JLabel("Senha:");
		this.panel_1.add(this.lblNewLabel_5, "cell 0 2");
		this.lblNewLabel_5.setFont(new Font("SansSerif", Font.PLAIN, 14));

		this.txtSenha = new JPasswordField();
		this.panel_1.add(this.txtSenha, "cell 1 2 2 1");
		this.txtSenha.setColumns(10);

		this.txtEmail = new JTextField();
		this.panel_1.add(this.txtEmail, "cell 1 1 3 1,growx,aligny center");
		this.txtEmail.setText("Digite seu email");
		this.txtEmail.setForeground(Color.GRAY);
		this.txtEmail.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtEmail.getText().equals("Digite seu email")) {
					txtEmail.setText("");
					txtEmail.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if (txtEmail.getText().isEmpty()) {
					txtEmail.setText("Digite seu email");
					txtEmail.setForeground(Color.GRAY);
				}
			}
		});
		this.txtEmail.setColumns(10);

		this.lblNewLabel_2 = new JLabel("Seja bem vindo a melhor cervejaria da America Látina");
		this.lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(this.lblNewLabel_2, "cell 0 0 4 1,alignx left,aligny top");

		this.lblNewLabel_1 = new JLabel("");
		this.lblNewLabel_1.setIcon(new ImageIcon(dimg));
		add(this.lblNewLabel_1, "cell 5 0,alignx center,aligny top");

		this.btnNewButton_1 = new JButton("Realizar Login");
		this.btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				realizarLogin();
			}
		});
		this.btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(this.btnNewButton_1, "flowx,cell 1 1,alignx right");

		this.btnNewButton = new JButton("Realizar Cadastro");
		this.btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrarUsuario();
			}
		});
		this.btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(this.btnNewButton, "cell 1 1,alignx right");
	}

	public void cadastrarUsuario() {
		JTextField nome = new JTextField();
		JTextField email = new JTextField();
		JTextField senha = new JPasswordField();
		Object[] message = { "Nome:", nome, "Email:", email, "Senha:", senha };

		int option = JOptionPane.showConfirmDialog(null, message, "Cadastrar usuário", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			if (!nome.getText().toString().equals("") && !senha.getText().toString().equals("")
					&& !email.getText().toString().equals("")) {
				if (!email.getText().toString().contains("@")) {
					JOptionPane.showMessageDialog(null, "Erro, email inválido!", "Erro cadastro",
							JOptionPane.ERROR_MESSAGE);
				} else {
					if (conUsuario.verificarEmail(email.getText().toString())) {
						JOptionPane.showMessageDialog(null, "Erro, email já cadastrado!", "Erro cadastro",
								JOptionPane.ERROR_MESSAGE);
					} else {
						Usuario user = new Usuario(nome.getText().toString(), email.getText().toLowerCase().toString(),
								senha.getText().toString());
						if (conUsuario.inserirUsuario(user)) {
							JOptionPane.showMessageDialog(null, "Usuário cadastrado!");
						}
					}
				}
			}
		}
	}
	
	public void realizarLogin() {
		if (txtEmail.getText().toString().equals("") || txtSenha.getText().toString().equals("")) {
			JOptionPane.showMessageDialog(null, "Erro! Há campos vazios!", "Erro login",
					JOptionPane.ERROR_MESSAGE);
		} else if (!txtEmail.getText().toString().contains("@")) {
			JOptionPane.showMessageDialog(null, "Erro! Insira um email válido!", "Erro login",
					JOptionPane.ERROR_MESSAGE);
		} else if (conUsuario.verificarUsuario(txtEmail.getText().toString(), txtSenha.getText().toString())) {
			JOptionPane.showMessageDialog(null, "Login realizado!");
			Janela.ativarMenus();
			Janela.frame.setContentPane(new TelaLogado()); // 1. Define o novo conteúdo
		    Janela.frame.pack();                       // 2. FORÇA a janela a se redimensionar ao novo conteúdo
		    Janela.frame.setLocationRelativeTo(null);  // 3. Opcional, mas bom: Recentraliza a janela
		    Janela.frame.revalidate();                 // 4. Revalida (redundante com pack(), mas não custa)
		    Janela.frame.repaint();}
		else {
			JOptionPane.showMessageDialog(null, "Erro, usuário ou senha inválidos!", "Erro login",
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
