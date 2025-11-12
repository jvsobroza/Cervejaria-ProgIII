package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Janela extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static Janela frame;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmNewMenuItem;
	private JMenu mnNewMenu_1;
	private JMenuItem mntmNewMenuItem_1;
	private JMenuItem mntmNewMenuItem_2;
	private JMenuItem mntmNewMenuItem_3;

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
	 */
	public Janela() {
		setBounds(100, 100, 800, 500);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		this.menuBar = new JMenuBar();
		setJMenuBar(this.menuBar);
		
		this.mnNewMenu = new JMenu("Cadastro");
		this.menuBar.add(this.mnNewMenu);
		
		this.mntmNewMenuItem = new JMenuItem("Cadastrar Degustação");
		this.mnNewMenu.add(this.mntmNewMenuItem);
		
		this.mnNewMenu_1 = new JMenu("Listagem");
		this.menuBar.add(this.mnNewMenu_1);
		
		this.mntmNewMenuItem_1 = new JMenuItem("Lista Geral");
		this.mnNewMenu_1.add(this.mntmNewMenuItem_1);
		
		this.mntmNewMenuItem_3 = new JMenuItem("Listar Estatísticas");
		this.mnNewMenu_1.add(this.mntmNewMenuItem_3);
		
		this.mntmNewMenuItem_2 = new JMenuItem("Listar Rótulos");
		this.mnNewMenu_1.add(this.mntmNewMenuItem_2);

		setContentPane(new TelaLogin());
		
	}

}
