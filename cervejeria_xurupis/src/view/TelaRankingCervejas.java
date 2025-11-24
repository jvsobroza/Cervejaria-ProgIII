package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import controller.UsuarioCervejaDAO;
import model.Degustacao;
import model.Usuario;
import net.miginfocom.swing.MigLayout;

public class TelaRankingCervejas extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblTitulo;
	private JScrollPane scrollPane;
	private JTable table;
	private JLabel lblFiltro;
	private JRadioButton radioMedia;
	private JRadioButton radioRanking;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private Usuario user;
	private UsuarioCervejaDAO conDegu;

	/**
	 * Create the panel.
	 * 
	 * @throws IOException
	 */
	public TelaRankingCervejas(Usuario user) throws IOException {
		this.user = user;
		initComponents();
		conDegu = new UsuarioCervejaDAO();
	}

	private void initComponents() {
		setBounds(100, 100, 1000, 600);
		setBackground(new Color(230, 205, 153));

		setLayout(new MigLayout("", "[][78.00][grow][]", "[][][grow][]"));

		this.lblFiltro = new JLabel("Escolha o filtro:");
		this.lblFiltro.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		add(this.lblFiltro, "flowx,cell 2 0,alignx left");

		this.lblTitulo = new JLabel("Ranking!");
		this.lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(this.lblTitulo, "cell 1 1");

		this.scrollPane = new JScrollPane();
		add(this.scrollPane, "cell 2 2,grow");

		this.table = new JTable();
		this.table.setRowHeight(25);
		this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.table.getTableHeader().setReorderingAllowed(false);
		this.scrollPane.setViewportView(this.table);

		this.radioRanking = new JRadioButton("Ranking");
		buttonGroup.add(this.radioRanking);
		add(this.radioRanking, "cell 2 0");

		this.radioMedia = new JRadioButton("Média por Tipo");
		buttonGroup.add(this.radioMedia);
		add(this.radioMedia, "cell 2 0");
		radioRanking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregarRanking();
				lblTitulo.setText("Top 10 Cervejas Favoritas");
			}
		});
		radioMedia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregarMedia();
				lblTitulo.setText("Média de Notas por Estilo");
			}
		});
	}

	public void carregarRanking() {
		DefaultTableModel model = new DefaultTableModel(new Object[][] {},
				new String[] { "Posição", "Cerveja", "Tipo", "Nota (0-10)" }) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		LinkedList<Degustacao> ls = conDegu.getRanking(user);
		int posicao = 1;
		for (Degustacao degu : ls) {
			model.addRow(new Object[] { posicao + "º", degu.getNome_cerveja(), degu.getTipo(),
					(double) degu.getAvaliacao() });
			posicao++;
		}
		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(300);
	}

	private void carregarMedia() {
		DefaultTableModel model = new DefaultTableModel(new Object[][] {},
				new String[] { "Tipo de Cerveja", "Média das Notas" }) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		LinkedList<Degustacao> lista = conDegu.getMediaPorTipo(this.user);

		for (Degustacao degu : lista) {
			model.addRow(new Object[] { 
					degu.getTipo(), 
					degu.getMedia()
			});
		}

		table.setModel(model);
	}
}