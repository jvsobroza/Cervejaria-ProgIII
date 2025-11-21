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
				LinkedList<Degustacao> ls = conDegu.listarDegustacao(user);
				DefaultTableModel tabela = (DefaultTableModel) table.getModel();
				for (Degustacao degu : ls) {// "Degustador", "Cerveja", "Data", "Local", "Avalia\u00E7\u00E3o",
											// "Critica", "Sugestao", "Foto"
					Object[] linha = { user.getNome(), degu.getNome_cerveja(), degu.getData_degustacao(),
							degu.getLocal_degustacao(), degu.getAvaliacao(), degu.getCritica(), degu.getSugestao(),
							carregarImagem(degu.getFoto())};
					tabela.addRow(linha);
				}
			}
		});
		add(this.radioGeral, "cell 1 2");
		buttonGroup.add(this.radioTipo);
		add(this.radioTipo, "cell 1 2");

		this.radioNota = new JRadioButton("Nota");
		buttonGroup.add(this.radioNota);
		add(this.radioNota, "cell 1 2");

		this.radioData = new JRadioButton("Data");
		buttonGroup.add(this.radioData);
		add(this.radioData, "cell 1 2");

		this.comboTipo = new JComboBox();
		this.comboTipo.setVisible(false);
		this.comboTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		this.comboTipo.setModel(new DefaultComboBoxModel(new String[] { "(Escolha o tipo)", "American Lager",
				"American Light Lager", "Dry Stout", "Japanese Rice Lager", "Pale Lager", "Pilsner" }));
		add(this.comboTipo, "cell 1 3,growx");

		this.scrollPane = new JScrollPane();
		add(this.scrollPane, "cell 0 5 3 2,grow");

		this.table = new JTable();
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
	}

	private void initComponents() {
		setBackground(new Color(230, 205, 153));
		setBounds(100, 100, 1000, 600);
		setLayout(new MigLayout("", "[][grow][grow]", "[][][][][][][grow][]"));
	}
	private ImageIcon carregarImagem(String caminhoDoBanco) {
	    String caminhoNoDisco = "cervejaria_xurupis" + caminhoDoBanco;
	    
	    File arquivo = new File(caminhoNoDisco);
	    if (arquivo.exists()) {
	        try {
	            ImageIcon original = new ImageIcon(arquivo.getAbsolutePath());
	            Image imgRedimensionada = original.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
	            return new ImageIcon(imgRedimensionada);
	        } catch (Exception e) {
	            System.out.println("Erro " + e.getMessage());
	        }
	    }
	    return null;
	}
}
