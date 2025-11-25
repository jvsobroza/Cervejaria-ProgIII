package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import controller.UsuarioCervejaDAO;
import model.Degustacao;
import model.Usuario;
import net.miginfocom.swing.MigLayout;

public class TelaGaleria extends JPanel {

	private static final long serialVersionUID = 1L;
	private Usuario user;
	private JLabel lblNewLabel;
	private JScrollPane scrollPane;
	private UsuarioCervejaDAO conDegu;
	private JTable table;

	/**
	 * Create the panel.
	 * @throws IOException 
	 */
	public TelaGaleria(Usuario user) throws IOException {
		this.user = user;
		initComponents();
		this.conDegu = new UsuarioCervejaDAO();

		this.lblNewLabel = new JLabel("Rótulos:");
		this.lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(this.lblNewLabel, "cell 1 1");

		this.scrollPane = new JScrollPane();
		add(this.scrollPane, "cell 2 2,grow");
		
		this.table = new JTable();
		this.table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Foto", "Cerveja", "Data degusta\u00E7\u00E3o"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
			public Class<?> getColumnClass(int column) {
				if (column == 0) {
					return javax.swing.ImageIcon.class;
				}
				return Object.class;
			}
		});
		
		this.table.setRowHeight(90);
		this.table.getColumnModel().getColumn(0).setPreferredWidth(300);
		this.table.getColumnModel().getColumn(1).setPreferredWidth(180);
		this.table.getColumnModel().getColumn(2).setPreferredWidth(115);
		this.scrollPane.setViewportView(this.table);
		carregarDadosNaTabela();
	}

	private void initComponents() {
		setBackground(new Color(230, 205, 153));
		setBounds(100, 100, 1000, 600);
		setLayout(new MigLayout("", "[][52.00][749.00,grow][]", "[][][grow][]"));
	}

	public void carregarDadosNaTabela() {
		LinkedList<Degustacao> ls = conDegu.listarParaGaleria(this.user);
		DefaultTableModel model = (DefaultTableModel) this.table.getModel();
		model.setRowCount(0);
		for (Degustacao degu : ls) {
			ImageIcon icon = carregarImagem(degu.getFoto());
			model.addRow(new Object[] { icon, degu.getNome_cerveja(), degu.getData_degustacao() });
		}
	}
	private ImageIcon carregarImagem(String caminhoDoBanco) {
		if (caminhoDoBanco == null || caminhoDoBanco.isEmpty()) {
			return null;
		}
		try {
			String raizDoProjeto = System.getProperty("user.dir");
			File arquivo = new File(raizDoProjeto + caminhoDoBanco);
			if (arquivo.exists()) {
				ImageIcon original = new ImageIcon(arquivo.getAbsolutePath());
				Image imgRedimensionada = original.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
				return new ImageIcon(imgRedimensionada);
			}
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		return null;
	}
}