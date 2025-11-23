package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
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
	private JTable table;

	/**
	 * Create the panel.
	 */
	public TelaGaleria(Usuario user) {
		this.user = user; // Salva o usuário antes de carregar os componentes
		initComponents();
		
		this.lblNewLabel = new JLabel("Rótulos:");
		this.lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(this.lblNewLabel, "cell 1 1");
		
		this.scrollPane = new JScrollPane();
		add(this.scrollPane, "cell 2 2,grow");
		
		this.table = new JTable();
		
		// CONFIGURAÇÃO VISUAL IMPORTANTE:
		this.table.setRowHeight(100); // Aumenta a altura da linha para caber a foto
		this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		this.table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Foto", "Cerveja", "Data de degusta\u00E7\u00E3o"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
			
			// A MÁGICA ACONTECE AQUI:
			// Dizemos para a tabela que a Coluna 0 é uma IMAGEM (ImageIcon)
			@Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) {
                    return ImageIcon.class;
                }
                return Object.class;
            }
		});
		
		this.table.getColumnModel().getColumn(0).setPreferredWidth(100); // Ajustei um pouco para ficar quadrado
		this.table.getColumnModel().getColumn(1).setPreferredWidth(250);
		this.table.getColumnModel().getColumn(2).setPreferredWidth(150);
		this.scrollPane.setViewportView(this.table);
		
		// Chama o carregamento dos dados ao iniciar o painel
		carregarDadosNaTabela();
	}

	private void initComponents() {
		setBackground(new Color(230, 205, 153));
		setBounds(100, 100, 1000, 600);
		setLayout(new MigLayout("", "[][52.00][749.00,grow][]", "[][][grow][]"));
	}
	
	// --- MÉTODOS DE LÓGICA (ADICIONADOS) ---

	private void carregarDadosNaTabela() {
        try {
            UsuarioCervejaDAO dao = new UsuarioCervejaDAO();
            LinkedList<Degustacao> lista = dao.listarParaGaleria(this.user);
            
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // Limpa tabela

            for (Degustacao degu : lista) {
                // 1. Carrega e redimensiona a imagem para caber na linha (90px)
                ImageIcon icon = carregarImagem(degu.getFoto());
                
                // 2. Formata a data
                String dataFormatada = formatarData(degu.getData_degustacao());

                // 3. Adiciona a linha
                model.addRow(new Object[]{
                    icon, 
                    degu.getNome_cerveja(), 
                    dataFormatada
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar galeria: " + e.getMessage());
        }
    }

    private String formatarData(java.util.Date data) {
        if (data == null) return "";
        return new java.text.SimpleDateFormat("dd/MM/yyyy").format(data);
    }

    private ImageIcon carregarImagem(String caminhoDoBanco) {
        if (caminhoDoBanco == null || caminhoDoBanco.isEmpty()) return null;
        try {
            String raizDoProjeto = System.getProperty("user.dir");
            
            if (!caminhoDoBanco.startsWith("/") && !caminhoDoBanco.startsWith("\\")) {
                caminhoDoBanco = File.separator + caminhoDoBanco;
            }
            
            File arquivo = new File(raizDoProjeto + caminhoDoBanco);
            
            if (arquivo.exists()) {
                ImageIcon original = new ImageIcon(arquivo.getAbsolutePath());
                // Redimensiona para 90x90 para caber na linha de altura 100
                Image img = original.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
                return new ImageIcon(img);
            }
        } catch (Exception e) { 
        	// Ignora erro de imagem para não travar a tela
        }
        return null;
    }
}