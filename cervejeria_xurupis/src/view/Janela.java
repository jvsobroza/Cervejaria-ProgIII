package view;

import java.awt.EventQueue;
import java.awt.Image;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.UsuarioDAO;
import model.Usuario;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Janela extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static Janela frame;
	private JMenuBar menuBar;
	private static JMenu menuCadastro;
	private JMenuItem subMenuCadastrarCerveja;
	private static JMenu menuListagem;
	private JMenuItem subMenuListagemListaGeral;
	private JMenuItem SubMenuListagemListarRotulos;
	private JMenuItem SubMenuListagemListarEstatistica;
	private ImageIcon iconeCadastro = carregarIcon("/resources/img/icones/cadastrar.png"); // https://www.flaticon.com/br/icones-gratis/tampa-de-garrafa
																							// Tampa de garrafa
																							// ícones criados por
																							// Freepik
	private ImageIcon iconeListar = carregarIcon("/resources/img/icones/lista.png"); // https://www.flaticon.com/br/icones-gratis/lista-de-afazeres
																						// Lista de afazeres ícones
																						// criados por bsd - Flaticon
	private ImageIcon iconeSair = carregarIcon("/resources/img/icones/sair.png"); // https://www.flaticon.com/br/icones-gratis/sair
																					// Sair ícones criados por Iconpro86
																					// - Flaticon
	private ImageIcon iconeCerveja = carregarIcon("/resources/img/icones/cerveja.png"); // https://www.flaticon.com/br/icones-gratis/cerveja
																						// Cerveja ícones criados por
																						// Good Ware - Flaticon
	private ImageIcon iconeListaEstatistica = carregarIcon("/resources/img/icones/estatistica.png"); // https://www.flaticon.com/br/icones-gratis/grafico-de-pizza
																										// Gráfico de
																										// pizza ícones
																										// criados por
																										// Andrean
																										// Prabowo -
																										// Flaticon
	private ImageIcon iconeListaRotulo = carregarIcon("/resources/img/icones/rotulo.png"); // https://www.flaticon.com/br/icones-gratis/rotulo
																							// rótulo ícones Rótulo
																							// ícones criados por Good
																							// Ware - Flaticon
	private ImageIcon iconeListaGeral = carregarIcon("/resources/img/icones/lista_geral.png"); // https://www.flaticon.com/br/icones-gratis/visao-geral
																								// Visão geral ícones
																								// criados por
																								// Vectorsclub
	private ImageIcon iconeDeslogar = carregarIcon("/resources/img/icones/logout.png"); // https://www.flaticon.com/br/icones-gratis/saida
																						// Saída ícones criados por
																						// riajulislam - Flaticon
	private ImageIcon iconePerfil = carregarIcon("/resources/img/icones/perfil.png"); // https://www.flaticon.com/br/icones-gratis/perfil-de-usuario
																						// Perfil de usuário
																						// ícones criados por Anggara
	private ImageIcon iconePerfilEditar = carregarIcon("/resources/img/icones/perfil_editar.png"); // https://www.flaticon.com/br/icones-gratis/ferramenta-de-edicao
																									// Ferramenta de
																									// edição
																									// ícones criados
																									// por Icongeek26
	private ImageIcon iconePerfilApagar = carregarIcon("/resources/img/icones/perfil_apagar.png"); // https://www.flaticon.com/br/icones-gratis/deixar-de-seguir
																									// Deixar de seguir
																									// ícones criados
																									// por riajulislam
	private static JMenu menuSair;
	private JMenuItem subMenuSairDeslog;
	private JMenu menuReturn;
	public static Usuario us;
	private static JMenu menuPerfil;
	private JMenuItem subMenuPerfilAlterar;
	private JMenuItem subMenuApagarPerfil;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Janela();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */
	public Janela() throws IOException {
		setBounds(100, 100, 1000, 600);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		this.menuBar = new JMenuBar();
		setJMenuBar(this.menuBar);

		this.menuCadastro = new JMenu("Cadastro");
		this.menuCadastro.setIcon(iconeCadastro);
		this.menuBar.add(this.menuCadastro);

		this.subMenuCadastrarCerveja = new JMenuItem("Cadastrar Degustação");
		this.subMenuCadastrarCerveja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					setContentPane(new TelaCadastro(us));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setVisible(true);
			}
		});
		this.subMenuCadastrarCerveja.setIcon(iconeCerveja);
		this.menuCadastro.add(this.subMenuCadastrarCerveja);

		this.menuListagem = new JMenu("Listagem");
		this.menuListagem.setIcon(iconeListar);
		this.menuBar.add(this.menuListagem);

		this.subMenuListagemListaGeral = new JMenuItem("Lista Geral");
		this.subMenuListagemListaGeral.setIcon(iconeListaGeral);
		this.subMenuListagemListaGeral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					setContentPane(new TelaListarCervejas(us));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setVisible(true);
			}
		});
		this.menuListagem.add(this.subMenuListagemListaGeral);

		this.SubMenuListagemListarEstatistica = new JMenuItem("Listar Estatísticas");
		this.SubMenuListagemListarEstatistica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					setContentPane(new TelaRankingCervejas(us));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setVisible(true);
			}
		});
		this.SubMenuListagemListarEstatistica.setIcon(iconeListaEstatistica);
		this.menuListagem.add(this.SubMenuListagemListarEstatistica);

		this.SubMenuListagemListarRotulos = new JMenuItem("Listar Rótulos");
		this.SubMenuListagemListarRotulos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					setContentPane(new TelaGaleria(us));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setVisible(true);
			}
		});
		this.SubMenuListagemListarRotulos.setIcon(iconeListaRotulo);
		this.menuListagem.add(this.SubMenuListagemListarRotulos);

		this.menuPerfil = new JMenu("Perfil");
		this.menuPerfil.setIcon(iconePerfil);
		this.menuPerfil.setEnabled(false);
		this.menuBar.add(this.menuPerfil);

		this.subMenuPerfilAlterar = new JMenuItem("Alterar perfil");
		this.subMenuPerfilAlterar.setIcon(iconePerfilEditar);
		this.subMenuPerfilAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					setContentPane(new TelaEditarUsuario(us));
					setVisible(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		this.menuPerfil.add(this.subMenuPerfilAlterar);

		this.subMenuApagarPerfil = new JMenuItem("Apagar perfil");
		this.subMenuApagarPerfil.setIcon(iconePerfilApagar);
		this.subMenuApagarPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] options = { "Sim", "Não" };
				int response = JOptionPane.showOptionDialog(null, "Apagar conta?", "Apagar conta",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, null);
				if (response == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(null, "Conta excluída com sucesso!", "Exclusão", JOptionPane.INFORMATION_MESSAGE, null);
					try {
						apagarUsuario();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					us = new Usuario();
					try {
						setContentPane(new TelaLogin());
						setVisible(true);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		this.menuPerfil.add(this.subMenuApagarPerfil);

		this.menuSair = new JMenu("Sair");
		this.menuSair.setIcon(iconeSair);
		this.menuBar.add(this.menuSair);

		this.subMenuSairDeslog = new JMenuItem("Deslogar");
		this.subMenuSairDeslog.setIcon(iconeDeslogar);
		this.subMenuSairDeslog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setMenusHabilitados(false);
				us = null;
				try {
					setContentPane(new TelaLogin());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setVisible(true);
			}
		});
		this.menuSair.add(this.subMenuSairDeslog);

		this.menuReturn = new JMenu("Retornar");
		this.menuReturn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (menuCadastro.isEnabled() == true) {
					try {
						setContentPane(new TelaLogado(us));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					setVisible(true);
				} else {
					try {
						setContentPane(new TelaLogin());
						setVisible(true);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		this.menuBar.add(this.menuReturn);

		setContentPane(new TelaLogin());
		setMenusHabilitados(false);

	}

	private ImageIcon carregarIcon(String caminho) {
		ImageIcon icone = new ImageIcon(getClass().getResource(caminho));
		Image img = icone.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		return new ImageIcon(img);
	}

	public void setMenusHabilitados(boolean ds) {
		this.menuCadastro.setEnabled(ds);
		this.menuListagem.setEnabled(ds);
		this.menuSair.setEnabled(ds);
		this.menuPerfil.setEnabled(ds);
	}

	public static void setUsuario(Usuario user) {
		us = user;
	}

	public static void ativarMenus() {
		menuCadastro.setEnabled(true);
		menuListagem.setEnabled(true);
		menuSair.setEnabled(true);
		menuPerfil.setEnabled(true);
	}
	
	public void apagarUsuario() throws IOException {
		UsuarioDAO conUser = new UsuarioDAO();
		conUser.apagarUsuario(us);
	}

}
