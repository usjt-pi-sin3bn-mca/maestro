package br.usjt.apivolei.maestro.model.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_convenio")
public class Convenio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String nome;
	@NotBlank
	private String cpf;
	@NotBlank
	@Column(name = "nomeresp")
	private String nomeReponsavel;
	@NotBlank
	private String fone; 
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String endereco;
	
	public Convenio(@NotBlank String nome, @NotBlank String cpf, @NotBlank String nomeReponsavel,
			@NotBlank String fone, @NotBlank @Email String email, @NotBlank String endereco) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.nomeReponsavel = nomeReponsavel;
		this.fone = fone;
		this.email = email;
		this.endereco = endereco;
	}
	public Convenio() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNomeReponsavel() {
		return nomeReponsavel;
	}

	public void setNomeReponsavel(String nomeReponsavel) {
		this.nomeReponsavel = nomeReponsavel;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((fone == null) ? 0 : fone.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((nomeReponsavel == null) ? 0 : nomeReponsavel.hashCode());
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
		Convenio other = (Convenio) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (fone == null) {
			if (other.fone != null)
				return false;
		} else if (!fone.equals(other.fone))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (nomeReponsavel == null) {
			if (other.nomeReponsavel != null)
				return false;
		} else if (!nomeReponsavel.equals(other.nomeReponsavel))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Convenio [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", nomeReponsavel=" + nomeReponsavel
				+ ", fone=" + fone + ", email=" + email + ", endereco=" + endereco + "]";
	}
	
	 

	
}
