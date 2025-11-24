package model;

import java.sql.Date;

public class Degustacao {
	private int id_usuario;
	private int id_cerveja;
	private int id_usuario_cerveja;
	private String nome_usuario;
	private String nome_cerveja;
	private Date data_degustacao;
	private String local_degustacao;
	private int avaliacao;
	private String critica;
	private String foto;
	private String sugestao;
	private String tipo;
	private double media;

	public double getMedia() {
		return media;
	}

	public void setMedia(double media) {
		this.media = media;
	}

	public Degustacao() {
	}

	public Degustacao(int id_usuario, int id_cerveja, int id_usuario_cerveja, String nome_usuario, String nome_cerveja,
			Date data_degustacao, String local_degustacao, int avaliacao, String critica, String foto,
			String sugestao) {
		super();
		this.id_usuario = id_usuario;
		this.id_cerveja = id_cerveja;
		this.id_usuario_cerveja = id_usuario_cerveja;
		this.nome_usuario = nome_usuario;
		this.nome_cerveja = nome_cerveja;
		this.data_degustacao = data_degustacao;
		this.local_degustacao = local_degustacao;
		this.avaliacao = avaliacao;
		this.critica = critica;
		this.foto = foto;
		this.sugestao = sugestao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public int getId_cerveja() {
		return id_cerveja;
	}

	public void setId_cerveja(int id_cerveja) {
		this.id_cerveja = id_cerveja;
	}

	public int getId_usuario_cerveja() {
		return id_usuario_cerveja;
	}

	public void setId_usuario_cerveja(int id_usuario_cerveja) {
		this.id_usuario_cerveja = id_usuario_cerveja;
	}

	public String getNome_usuario() {
		return nome_usuario;
	}

	public void setNome_usuario(String nome_usuario) {
		this.nome_usuario = nome_usuario;
	}

	public String getNome_cerveja() {
		return nome_cerveja;
	}

	public void setNome_cerveja(String nome_cerveja) {
		this.nome_cerveja = nome_cerveja;
	}

	public Date getData_degustacao() {
		return data_degustacao;
	}

	public void setData_degustacao(Date data_degustacao) {
		this.data_degustacao = data_degustacao;
	}

	public String getLocal_degustacao() {
		return local_degustacao;
	}

	public void setLocal_degustacao(String local_degustacao) {
		this.local_degustacao = local_degustacao;
	}

	public int getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(int avaliacao) {
		this.avaliacao = avaliacao;
	}

	public String getCritica() {
		return critica;
	}

	public void setCritica(String critica) {
		this.critica = critica;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getSugestao() {
		return sugestao;
	}

	public void setSugestao(String sugestao) {
		this.sugestao = sugestao;
	}

	@Override
	public String toString() {
		return "Degustacao [id_usuario_cerveja=" + id_usuario_cerveja + ", nome_cerveja=" + nome_cerveja
				+ ", data_degustacao=" + data_degustacao + ", local_degustacao=" + local_degustacao + ", avaliacao="
				+ avaliacao + ", critica=" + critica + ", foto=" + foto + ", sugestao=" + sugestao + "]";
	}

}
