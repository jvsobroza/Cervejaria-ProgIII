package view;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

import model.Cerveja;
import model.Usuario;
import model.Usuario_Cerveja;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import action.TratadorErros;
import controller.CervejaDAO;
import controller.UsuarioCervejaDAO;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class TelaCadastro extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblNewLabel;
	private Usuario user;
	private JLabel lblNewLabel_1;
	private JComboBox comboCerveja;
	private JLabel lblNewLabel_2;
	private JTextField txtTipo;
	private JLabel lblNewLabel_3;
	private JTextField txtTeor;
	private JLabel lblNewLabel_4;
	private JTextField txtIbu;
	private JLabel lblNewLabel_5;
	private JTextField txtPais;
	private JLabel lblNewLabel_6;
	private JDateChooser txtData;
	private JLabel lblNewLabel_7;
	private JTextField txtLocal;
	private JLabel lblNewLabel_8;
	private JTextField txtAvaliacao;
	private JLabel lblNewLabel_9;
	private JTextArea txtComentario;
	private JLabel lblNewLabel_10;
	private JButton btEscolherRotulo;
	private JLabel labelNomeImg;
	private JLabel lblNewLabel_11;
	private JTextField txtSugestao;
	private JButton btCadastrarDegustacao;
	private CervejaDAO conCerveja;
	private LinkedList<Cerveja> listaCerveja;
	private File imagem = null;

	/**
	 * Create the panel.
	 * 
	 * @throws IOException
	 */
	public TelaCadastro(Usuario user) throws IOException {
		this.user = user;
		conCerveja = new CervejaDAO();
		initComponents();
		listaCerveja = conCerveja.selectCerveja();
		UsuarioCervejaDAO con = new UsuarioCervejaDAO();
		iniciarCombo();
	}

	private void initComponents() {
		setBounds(100, 100, 1000, 600);
		setBackground(new Color(230, 205, 153));
		setLayout(new MigLayout("", "[][][grow][grow]", "[][][][][][][][][][][63.00][][][]"));

		this.lblNewLabel = new JLabel("Cadastre sua desgustação!");
		this.lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(this.lblNewLabel, "cell 0 0");

		this.lblNewLabel_1 = new JLabel("Cerveja:");
		add(this.lblNewLabel_1, "cell 1 2,alignx right");

		this.comboCerveja = new JComboBox();
		this.comboCerveja.setEditable(true);
		this.comboCerveja.setMaximumRowCount(100);
		this.comboCerveja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboCerveja.getSelectedIndex() > 0) {
					preencherCerveja();
				} else {
					txtTeor.setText("");
					txtTipo.setText("");
					txtIbu.setText("");
					txtPais.setText("");
				}
			}
		});
		this.comboCerveja.setModel(new DefaultComboBoxModel(new String[] { "(Escolha a cerveja)" }));
		add(this.comboCerveja, "cell 2 2,growx");

		this.lblNewLabel_2 = new JLabel("Tipo:");
		add(this.lblNewLabel_2, "cell 1 3,alignx trailing");

		this.txtTipo = new JTextField();
		this.txtTipo.setEditable(false);
		add(this.txtTipo, "cell 2 3");
		this.txtTipo.setColumns(30);

		this.lblNewLabel_3 = new JLabel("Teor alcoólico:");
		add(this.lblNewLabel_3, "cell 1 4,alignx trailing");

		this.txtTeor = new JTextField();
		this.txtTeor.setEditable(false);
		add(this.txtTeor, "cell 2 4");
		this.txtTeor.setColumns(10);

		this.lblNewLabel_4 = new JLabel("IBU:");
		add(this.lblNewLabel_4, "cell 1 5,alignx trailing");

		this.txtIbu = new JTextField();
		this.txtIbu.setEditable(false);
		add(this.txtIbu, "cell 2 5");
		this.txtIbu.setColumns(10);

		this.lblNewLabel_5 = new JLabel("País de origem:");
		add(this.lblNewLabel_5, "cell 1 6,alignx trailing");

		this.txtPais = new JTextField();
		this.txtPais.setEditable(false);
		add(this.txtPais, "cell 2 6,growx,aligny center");
		this.txtPais.setColumns(10);

		this.lblNewLabel_6 = new JLabel("Data de degustação");
		add(this.lblNewLabel_6, "cell 1 7,alignx trailing");
		this.txtData = new JDateChooser();
		this.txtData.setDateFormatString("dd/MM/yyyy");
		this.txtData.setForeground(Color.gray);
		add(this.txtData, "cell 2 7,growx");

		this.lblNewLabel_7 = new JLabel("Local de degustação:");
		add(this.lblNewLabel_7, "cell 1 8,alignx trailing");

		this.txtLocal = new JTextField();
		add(this.txtLocal, "cell 2 8,growx");
		this.txtLocal.setColumns(10);

		this.lblNewLabel_8 = new JLabel("Avaliação (1-10):");
		add(this.lblNewLabel_8, "cell 1 9,alignx trailing");

		this.txtAvaliacao = new JTextField();
		this.txtAvaliacao.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyCode() != e.VK_BACK_SPACE) {
					if (String.valueOf(e.getKeyChar()).matches("[0-9]")) {
						if (txtAvaliacao.getText().length() > 1) {
							e.consume();
						}
						if (!txtAvaliacao.getText().trim().equals("")) {
							if (Integer.parseInt(txtAvaliacao.getText() + e.getKeyChar()) > 10) {
								e.consume();
							}
						}
					} else {
						e.consume();
					}
				}
			}
		});

		add(this.txtAvaliacao, "cell 2 9");
		this.txtAvaliacao.setColumns(2);

		this.lblNewLabel_9 = new JLabel("Comentários:");
		add(this.lblNewLabel_9, "cell 1 10,alignx right,aligny top");

		this.txtComentario = new JTextArea();
		add(this.txtComentario, "cell 2 10,grow");

		this.lblNewLabel_10 = new JLabel("Rótulo:");
		add(this.lblNewLabel_10, "cell 1 11,alignx right");

		this.btEscolherRotulo = new JButton("Escolher imagem");
		this.btEscolherRotulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagens", "jpg", "jpeg", "png", "gif");
				chooser.setFileFilter(filter);

				int result = chooser.showOpenDialog(null);

				if (result == JFileChooser.APPROVE_OPTION) {
					imagem = chooser.getSelectedFile();
					labelNomeImg.setText(imagem.getName());
				}
			}
		});
		add(this.btEscolherRotulo, "flowx,cell 2 11");

		this.labelNomeImg = new JLabel("");
		add(this.labelNomeImg, "cell 2 11,alignx left");

		this.lblNewLabel_11 = new JLabel("Sugestão:");
		add(this.lblNewLabel_11, "cell 1 12,alignx trailing");

		this.txtSugestao = new JTextField();
		add(this.txtSugestao, "cell 2 12,growx");
		this.txtSugestao.setColumns(10);
		this.btCadastrarDegustacao = new JButton("Cadastrar degustação");
		this.btCadastrarDegustacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(txtData.getDate());

				if (possuiCamposVazios()) {
					JOptionPane.showMessageDialog(null, "Possui campos vazios!", "Erro!", JOptionPane.ERROR_MESSAGE);
				} else {
					inserirDegustacao();
				}
			}
		});
		add(this.btCadastrarDegustacao, "cell 2 13,alignx right");
	}

	public void iniciarCombo() {
		for (Cerveja ceva : listaCerveja) {
			comboCerveja.addItem(ceva.getNome());
		}
	}

	public void preencherCerveja() {
		Cerveja ceva = listaCerveja.get(comboCerveja.getSelectedIndex() - 1);
		txtTipo.setText(ceva.getTipo());
		txtIbu.setText(ceva.getIbu() + "");
		txtTeor.setText(ceva.getTeorAlcolico() + "");
		txtPais.setText(ceva.getPais());
	}

	public boolean possuiCamposVazios() {
		this.txtTipo.getText().equals("");
		boolean verifica = (this.txtTipo.getText().equals("") || this.txtAvaliacao.getText().equals("")
				|| this.txtComentario.getText().equals("") || this.txtComentario.getText().equals("")
				|| this.txtData.getDateFormatString().equals("") || this.txtLocal.getText().equals("")
				|| this.txtSugestao.getText().equals("") || this.labelNomeImg.getText().equals("")) ? true : false;
		return verifica;
	}

	public void inserirDegustacao() {
		try {
			String imagemBd = "";
			if (this.imagem != null) {
			    String raizDoProjeto = System.getProperty("user.dir");
			    File pastaDestino = new File(raizDoProjeto + File.separator + "resources" + File.separator + "img");

			    if (!pastaDestino.exists()) {
			        pastaDestino.mkdirs();
			    }
			    File arquivoDestino = new File(pastaDestino, imagem.getName());
			    Files.copy(imagem.toPath(), arquivoDestino.toPath(), StandardCopyOption.REPLACE_EXISTING);
			    imagemBd = "/resources/img/" + imagem.getName();
			}
			Usuario_Cerveja degustacao = new Usuario_Cerveja();
			degustacao.setIdUsuario(user.getIdUsuario());
			Cerveja id = listaCerveja.get(comboCerveja.getSelectedIndex() - 1);
			degustacao.setIdCerveja(id.getIdCerveja());
			degustacao.setAvaliacao(Integer.parseInt(txtAvaliacao.getText()));
			degustacao.setCritica(txtComentario.getText().toString());
			degustacao.setDataDegustacao(txtData.getDate());
			degustacao.setLocalDegustacao(txtLocal.getText().toString());
			degustacao.setSugestao(txtSugestao.getText().toString());
			degustacao.setFoto(imagemBd);
			UsuarioCervejaDAO con = new UsuarioCervejaDAO();
			if (con.inserirDegustacao(degustacao)) {
				JOptionPane.showMessageDialog(null, "Cadastro e imagem salvos com sucesso!");
				reiniciarCampos();
			}

		} catch (IOException e) {
			TratadorErros.mensagemErro("Erro Imagem;Erro mover imagem: " + e.getMessage());
		} catch (Exception e) {
			TratadorErros.mensagemErro("Erro Cadastro;Erro cadastrar imagem: " + e.getMessage());
		}
	}
	
	public void reiniciarCampos() {
		comboCerveja.setSelectedIndex(0);
		txtData.setDateFormatString("");
		txtAvaliacao.setText("");
		txtComentario.setText("");
		txtSugestao.setText("");
		txtLocal.setText("");
		labelNomeImg.setText("");
	}

}
