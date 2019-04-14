package br.usjt.apivolei.maestro.model.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "tb_torcedores")
@JsonIgnoreProperties(value = { "senha" }, allowSetters = true)
public class Torcedor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String nome;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	@JsonProperty("senha")
	private String senha;
	// socio torcedor
	@Column(columnDefinition = "boolean default false")
	private boolean socio;
	private String cpf;
	@Column(name = "datanasc")
	private Date dataNascimento;
	private String endereco;
	private String celular;
	private String genero; // M, F, O

	@JsonIgnore
	@Transient
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public Torcedor(boolean socio, String cpf, Date dataNascimento, String endereco, String celular, String genero) {
		this.socio = socio;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.endereco = endereco;
		this.celular = celular;
		this.genero = genero;
	}

	public Torcedor() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isSocio() {
		return socio;
	}

	public void setSocio(boolean socio) {
		this.socio = socio;
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

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
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
		Torcedor other = (Torcedor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Torcedor [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", socio=" + socio
				+ ", cpf=" + cpf + ", dataNascimento=" + dataNascimento + ", endereco=" + endereco + ", celular="
				+ celular + ", genero=" + genero + "]";
	}

}