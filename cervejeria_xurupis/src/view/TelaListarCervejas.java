package view;

import java.awt.Color;

import javax.swing.JPanel;

import model.Degustacao;
import model.Usuario;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.UsuarioCervejaDAO;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaListarCervejas extends JPanel {

	private static final long serialVersionUID = 1L;
	private Usuario user;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JRadioButton radioTipo;
	private JRadioButton radioNota;
	private JRadioButton radioData;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JComboBox comboTipo;
	private JScrollPane scrollPane;
	private JTable table;
	private JRadioButton radioGeral;
	private UsuarioCervejaDAO conDegu;
	private DefaultTableModel tabela;

	/**
	 * Create the panel.
	 * 
	 * @throws IOException
	 */
	public TelaListarCervejas(Usuario user) throws IOException {
		initComponents();
		this.user = user;
		conDegu = new UsuarioCervejaDAO();
		this.lblNewLabel = new JLabel("Listagem das degustações!");
		this.lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(this.lblNewLabel, "cell 0 0");
		this.radioTipo = new JRadioButton("Tipo");
		this.radioTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabela.setNumRows(0);
				comboTipo.setVisible(true);
			}
		});

		this.lblNewLabel_1 = new JLabel("Filtros:");
		this.lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 14));
		add(this.lblNewLabel_1, "flowx,cell 1 2,alignx center");

		this.radioGeral = new JRadioButton("Geral");
		buttonGroup.add(this.radioGeral);
		this.radioGeral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboTipo.setVisible(false);
				LinkedList<Degustacao> ls = conDegu.listarDegustacao(user);
				tabela.setNumRows(0);
				for (Degustacao degu : ls) {// "Degustador", "Cerveja", "Data", "Local", "Avalia\u00E7\u00E3o",
											// "Critica", "Sugestao", "Foto"
					Object[] linha = { user.getNome(), degu.getNome_cerveja(), degu.getData_degustacao(),
							degu.getLocal_degustacao(), degu.getAvaliacao(), degu.getCritica(), degu.getSugestao(),
							carregarImagem(degu.getFoto()) };
					tabela.addRow(linha);
				}
			}
		});
		add(this.radioGeral, "cell 1 2");
		buttonGroup.add(this.radioTipo);
		add(this.radioTipo, "cell 1 2");

		this.radioNota = new JRadioButton("Nota");
		this.radioNota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabela.setNumRows(0);
				comboTipo.setVisible(false);
				preencherComboNota();
			}
		});
		buttonGroup.add(this.radioNota);
		add(this.radioNota, "cell 1 2");

		this.radioData = new JRadioButton("Data");
		this.radioData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboTipo.setVisible(false);
				tabela.setNumRows(0);
				preencherComboData();
			}
		});
		buttonGroup.add(this.radioData);
		add(this.radioData, "cell 1 2");

		this.comboTipo = new JComboBox();
		this.comboTipo.setVisible(false);
		this.comboTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboTipo.getSelectedIndex() > 0) {
					tabela.setNumRows(0);
					preencherComboTipo();
				}
			}
		});
		this.comboTipo.setModel(new DefaultComboBoxModel(
				new String[] { "(Escolha o tipo)", "Pilsen", "Lager", "IPA (India Pale Ale)", "Weiss (Trigo)",
						"Stout / Escura", "Witbier", "Red Ale", "Sour / Fruit Beer", "Sem Álcool", "Outros" }));
		add(this.comboTipo, "cell 1 3,growx");

		this.scrollPane = new JScrollPane();
		add(this.scrollPane, "cell 0 5 3 2,grow");

		this.table = new JTable();
		this.table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
		            int linha = table.getSelectedRow();
		            if (linha != -1) {
		                Object degustador = table.getValueAt(linha, 0);
		                Object cerveja = table.getValueAt(linha, 1);
		                Object data = table.getValueAt(linha, 2);
		                Object local = table.getValueAt(linha, 3);
		                Object avaliacao = table.getValueAt(linha, 4);
		                Object critica = table.getValueAt(linha, 5);
		                Object sugestao = table.getValueAt(linha, 6);

		                ImageIcon icone = (ImageIcon) table.getValueAt(linha, 7);
		                String mensagem = "Degustador: " + degustador + "\n" +
		                        "Cerveja: " + cerveja + "\n" +
		                        "Data: " + data + "\n" +
		                        "Local: " + local + "\n" +
		                        "Nota: " + avaliacao + "/10\n\n" +
		                        "Crítica: " + critica + "\n" +
		                        "Sugestão: " + sugestao;
		                JOptionPane.showMessageDialog(null, mensagem, "Detalhes da Degustação", JOptionPane.INFORMATION_MESSAGE, icone);
		            }
		        }
			}
		});
		this.table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Degustador", "Cerveja", "Data",
				"Local", "Avalia\u00E7\u00E3o", "Critica", "Sugestao", "Foto" }) {
			@Override
			public Class<?> getColumnClass(int column) {
				if (column == 7) {
					return javax.swing.ImageIcon.class;
				}
				return Object.class;
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		this.table.setRowHeight(60);
		this.table.getColumnModel().getColumn(0).setPreferredWidth(115);
		this.table.getColumnModel().getColumn(3).setPreferredWidth(141);
		this.table.getColumnModel().getColumn(4).setPreferredWidth(60);
		this.table.getColumnModel().getColumn(5).setPreferredWidth(183);
		this.scrollPane.setViewportView(this.table);
		tabela = (DefaultTableModel) table.getModel();
	}

	public void preencherComboData() {
		LinkedList<Degustacao> ls = conDegu.listarDegustacaoData(user);
		for (Degustacao degu : ls) {
			Object[] linha = { user.getNome(), degu.getNome_cerveja(), degu.getData_degustacao(),
					degu.getLocal_degustacao(), degu.getAvaliacao(), degu.getCritica(), degu.getSugestao(),
					carregarImagem(degu.getFoto()) };
			tabela.addRow(linha);
		}
	}

	public void preencherComboNota() {
		LinkedList<Degustacao> ls = conDegu.listarDegustacaoNota(user);
		for (Degustacao degu : ls) {
			Object[] linha = { user.getNome(), degu.getNome_cerveja(), degu.getData_degustacao(),
					degu.getLocal_degustacao(), degu.getAvaliacao(), degu.getCritica(), degu.getSugestao(),
					carregarImagem(degu.getFoto()) };
			tabela.addRow(linha);
		}
	}

	private void initComponents() {
		setBackground(new Color(230, 205, 153));
		setBounds(100, 100, 1000, 600);
		setLayout(new MigLayout("", "[][grow][grow]", "[][][][][][][grow][]"));
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

	public void preencherComboTipo() {
		/*
		 * Pilsen 
		 * Lager 
		 * IPA (India Pale Ale) 
		 * Weiss (Trigo) 
		 * Stout / Escura 
		 * Witbier 
		 * Red Ale 
		 * Sour / Fruit Beer 
		 * Sem Álcool 
		 * Outros
		 */
		int ind = comboTipo.getSelectedIndex() - 1;
		String tipo = "";
		switch (ind) {
		case 0:
			tipo = "Pilsen"; break;
		case 1:
			tipo="Lager";break;
		case 2:
			tipo="IPA (India Pale Ale)";break;
		case 3:
			tipo="Weiss (Trigo)";break;
		case 4:
			tipo="Stout / Escura";break;
		case 5:
			tipo="Witbier";break;
		case 6:
			tipo="Red Ale";break;
		case 7:
			tipo="Sour / Fruit Beer";break;
		case 8:
			tipo="Sem Álcool";break;
		case 9:
			tipo="Outros";break;
		}
		LinkedList<Degustacao> ls = conDegu.listarDegustacaoTipo(user, tipo);
		for (Degustacao degu : ls) {
			Object[] linha = { user.getNome(), degu.getNome_cerveja(), degu.getData_degustacao(),
					degu.getLocal_degustacao(), degu.getAvaliacao(), degu.getCritica(), degu.getSugestao(),
					carregarImagem(degu.getFoto()) };
			tabela.addRow(linha);
		}
	}
}
