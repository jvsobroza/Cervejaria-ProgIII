package model;

public class Cerveja {
	private int idCerveja;
	private String nome;
	private String tipo;
	private double teorAlcolico;
	private int ibu;
	private String pais;
	
	public Cerveja() {
		super();
	}
	public Cerveja(String nome, String tipo, double teorAlcolico, int ibu, String pais) {
		super();
		this.nome = nome;
		this.tipo = tipo;
		this.teorAlcolico = teorAlcolico;
		this.ibu = ibu;
		this.pais = pais;
	}
	public Cerveja(int idCerveja, String nome, String tipo, double teorAlcolico, int ibu, String pais) {
		super();
		this.idCerveja = idCerveja;
		this.nome = nome;
		this.tipo = tipo;
		this.teorAlcolico = teorAlcolico;
		this.ibu = ibu;
		this.pais = pais;
	}
	public int getIdCerveja() {
		return idCerveja;
	}
	public void setIdCerveja(int idCerveja) {
		this.idCerveja = idCerveja;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public double getTeorAlcolico() {
		return teorAlcolico;
	}
	public void setTeorAlcolico(double teorAlcolico) {
		this.teorAlcolico = teorAlcolico;
	}
	public int getIbu() {
		return ibu;
	}
	public void setIbu(int ibu) {
		this.ibu = ibu;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	
	
}
