package model;

import java.util.Date;

public class Usuario_Cerveja {
	private int idCervejaUsuario;
	private int idUsuario;
	private int idCerveja;
	private Date dataDegustacao;
	private String localDegustacao;
	private int avaliacao;
	private String critica;
	private String foto;
	private String sugestao;

	public String getSugestao() {
		return sugestao;
	}

	public void setSugestao(String sugestao) {
		this.sugestao = sugestao;
	}

	public Usuario_Cerveja() {
	}

	public Usuario_Cerveja(int idUsuario, int idCerveja, Date dataDegustacao, String localDegustacao, int avaliacao,
			String critica, String foto, String sugestao) {
		super();
		this.idUsuario = idUsuario;
		this.idCerveja = idCerveja;
		this.dataDegustacao = dataDegustacao;
		this.localDegustacao = localDegustacao;
		this.avaliacao = avaliacao;
		this.critica = critica;
		this.foto = foto;
		this.sugestao = sugestao;
	}

	public Usuario_Cerveja(int idCervejaUsuario, int idUsuario, int idCerveja, Date dataDegustacao,
			String localDegustacao, int avaliacao, String critica, String foto, String sugestao) {
		super();
		this.idCervejaUsuario = idCervejaUsuario;
		this.idUsuario = idUsuario;
		this.idCerveja = idCerveja;
		this.dataDegustacao = dataDegustacao;
		this.localDegustacao = localDegustacao;
		this.avaliacao = avaliacao;
		this.critica = critica;
		this.foto = foto;
		this.sugestao = sugestao;
	}

	public int getIdCervejaUsuario() {
		return idCervejaUsuario;
	}

	public void setIdCervejaUsuario(int idCervejaUsuario) {
		this.idCervejaUsuario = idCervejaUsuario;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdCerveja() {
		return idCerveja;
	}

	public void setIdCerveja(int idCerveja) {
		this.idCerveja = idCerveja;
	}

	public Date getDataDegustacao() {
		return dataDegustacao;
	}

	public void setDataDegustacao(Date date) {
		this.dataDegustacao = date;
	}

	public String getLocalDegustacao() {
		return localDegustacao;
	}

	public void setLocalDegustacao(String localDegustacao) {
		this.localDegustacao = localDegustacao;
	}

	public int getAvaliacao() {
		return this.avaliacao;
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

}
