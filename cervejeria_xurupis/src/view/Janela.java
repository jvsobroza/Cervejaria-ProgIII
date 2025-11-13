package view;

import java.awt.EventQueue;
import java.awt.Image;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
	private JMenu menuCadastro;
	private JMenuItem subMenuCadastrarCerveja;
	private JMenu menuListagem;
	private JMenuItem subMenuListagemListaGeral;
	private JMenuItem SubMenuListagemListarRotulos;
	private JMenuItem SubMenuListagemListarEstatistica;
	private ImageIcon iconeCadastro = carregarIcon("/resources/img/icones/perfil.png"); // https://www.flaticon.com/br/icones-gratis/individual ícones criados por Good Ware - Flaticon
	private ImageIcon iconeListar = carregarIcon("/resources/img/icones/lista.png"); // https://www.flaticon.com/br/icones-gratis/lista-de-afazeres  Lista de afazeres ícones criados por bsd - Flaticon
	private ImageIcon iconeSair = carregarIcon("/resources/img/icones/sair.png"); // https://www.flaticon.com/br/icones-gratis/sair Sair ícones criados por Iconpro86 - Flaticon
	private ImageIcon iconeCerveja = carregarIcon("/resources/img/icones/cerveja.png"); // https://www.flaticon.com/br/icones-gratis/cerveja Cerveja ícones criados por Good Ware - Flaticon
	private ImageIcon iconeListaEstatistica = carregarIcon("/resources/img/icones/estatistica.png"); // https://www.flaticon.com/br/icones-gratis/grafico-de-pizza Gráfico de pizza ícones criados por Andrean Prabowo - Flaticon
	private ImageIcon iconeListaRotulo = carregarIcon("/resources/img/icones/rotulo.png"); // https://www.flaticon.com/br/icones-gratis/rotulo rótulo ícones">Rótulo ícones criados por Good Ware - Flaticon
	private JMenu menuSair;
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
		this.subMenuCadastrarCerveja.setIcon(iconeCerveja);
		this.menuCadastro.add(this.subMenuCadastrarCerveja);
		
		this.menuListagem = new JMenu("Listagem");
		this.menuListagem.setIcon(iconeListar);
		this.menuBar.add(this.menuListagem);
		
		this.subMenuListagemListaGeral = new JMenuItem("Lista Geral");
		this.menuListagem.add(this.subMenuListagemListaGeral);
		
		this.SubMenuListagemListarEstatistica = new JMenuItem("Listar Estatísticas");
		this.SubMenuListagemListarEstatistica.setIcon(iconeListaEstatistica);
		this.menuListagem.add(this.SubMenuListagemListarEstatistica);
		
		this.SubMenuListagemListarRotulos = new JMenuItem("Listar Rótulos");
		this.SubMenuListagemListarRotulos.setIcon(iconeListaRotulo);
		this.menuListagem.add(this.SubMenuListagemListarRotulos);
		
		this.menuSair = new JMenu("Sair");
		this.menuSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Saindo...");
				System.exit(0);
			}
		});
		this.menuSair.setIcon(iconeSair);
		this.menuBar.add(this.menuSair);

		setContentPane(new TelaLogin());
		
	}
	
	private ImageIcon carregarIcon(String caminho) {
		ImageIcon icone = new ImageIcon(getClass().getResource(caminho));
		Image img = icone.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		return new ImageIcon(img);
	}

}
