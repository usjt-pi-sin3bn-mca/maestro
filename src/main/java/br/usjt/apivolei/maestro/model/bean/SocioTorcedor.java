package br.usjt.apivolei.maestro.model.bean;

import java.io.Serializable;
import java.util.Date;

public class SocioTorcedor implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String cpf;
	private Date dataNascimento;
	private String endereco;
	private String celular;
	private String genero; // M, F, O

	private String token;
	
	
	public SocioTorcedor() {
	}

	public SocioTorcedor(Long id, String cpf, Date dataNascimento, String endereco, String celular, String genero) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.endereco = endereco;
		this.celular = celular;
		this.genero = genero;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SocioTorcedor other = (SocioTorcedor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SocioTorcedor [id=" + id + ", cpf=" + cpf + ", dataNascimento=" + dataNascimento + ", endereco="
				+ endereco + ", celular=" + celular + ", genero=" + genero + "]";
	}

}