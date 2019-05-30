package br.usjt.apivolei.maestro.model.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
	@NotNull
	@Column(name = "pontuacaoQRCode")
	private Integer pontuacaoQRCode;
	
	public Convenio(@NotBlank String nome, @NotBlank String cpf, @NotBlank String nomeReponsavel,
			@NotBlank String fone, @NotBlank @Email String email, @NotBlank String endereco, @NotNull Integer pontuacaoQRCode) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.nomeReponsavel = nomeReponsavel;
		this.fone = fone;
		this.email = email;
		this.endereco = endereco;
		this.pontuacaoQRCode = pontuacaoQRCode;
	}
	
	public Convenio() {}

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
	
	public Integer getPontuacaoQRCode() {
		return pontuacaoQRCode;
	}
	
	public void setPontuacaoQRCode(Integer pontuacaoQRCode) {
		this.pontuacaoQRCode = pontuacaoQRCode;
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
		Convenio other = (Convenio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}