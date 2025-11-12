package model;


public class Usuario_Cerveja {
	private int idCervejaUsuario;
	private int idUsuario;
	private int idCerveja;
	private String dataDegustacao;
	private String localDegustacao;
	private int Avaliacao;
	private String critica;
	private String foto;
	
	public Usuario_Cerveja() {
		super();
	}
	public Usuario_Cerveja(int idUsuario, int idCerveja, String dataDegustacao, String localDegustacao, int avaliacao,
			String critica, String foto) {
		super();
		this.idUsuario = idUsuario;
		this.idCerveja = idCerveja;
		this.dataDegustacao = dataDegustacao;
		this.localDegustacao = localDegustacao;
		Avaliacao = avaliacao;
		this.critica = critica;
		this.foto = foto;
	}
	public Usuario_Cerveja(int idCervejaUsuario, int idUsuario, int idCerveja, String dataDegustacao,
			String localDegustacao, int avaliacao, String critica, String foto) {
		super();
		this.idCervejaUsuario = idCervejaUsuario;
		this.idUsuario = idUsuario;
		this.idCerveja = idCerveja;
		this.dataDegustacao = dataDegustacao;
		this.localDegustacao = localDegustacao;
		Avaliacao = avaliacao;
		this.critica = critica;
		this.foto = foto;
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
	public String getDataDegustacao() {
		return dataDegustacao;
	}
	public void setDataDegustacao(String dataDegustacao) {
		this.dataDegustacao = dataDegustacao;
	}
	public String getLocalDegustacao() {
		return localDegustacao;
	}
	public void setLocalDegustacao(String localDegustacao) {
		this.localDegustacao = localDegustacao;
	}
	public int getAvaliacao() {
		return Avaliacao;
	}
	public void setAvaliacao(int avaliacao) {
		Avaliacao = avaliacao;
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
