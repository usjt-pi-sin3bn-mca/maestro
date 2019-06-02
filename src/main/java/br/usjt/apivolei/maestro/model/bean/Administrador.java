package br.usjt.apivolei.maestro.model.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Calendar;

@Entity
@Table(name = "tb_administrador")
@JsonIgnoreProperties(value = { "senha" }, allowSetters = true)
public class Administrador implements Serializable {
    
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    
    @NotBlank
    private String senha;

    @NotBlank
    @Column(unique = true)
    @Email
    private String email;

	@Column(columnDefinition = "boolean default true", name = "ativo")
	private boolean contaAtiva;
	// dados de um socio torcedor
	@Column(columnDefinition = "boolean default false")
	private boolean socio;
	@Column(name = "cpf", unique = true)
	private String cpf;
	@Column(name = "datanasc")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Calendar dataNascimento;
	private String endereco;
	private String celular;
	private String genero; // M, F, O
	private Integer pontos;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@Column(name = "dataUltimaPontuacao")
	private Calendar dataUltimaPontuacao;

    public boolean isContaAtiva() {
		return contaAtiva;
	}

	public void setContaAtiva(boolean contaAtiva) {
		this.contaAtiva = contaAtiva;
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

	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
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

	public Integer getPontos() {
		return pontos;
	}

	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}

	public Calendar getDataUltimaPontuacao() {
		return dataUltimaPontuacao;
	}

	public void setDataUltimaPontuacao(Calendar dataUltimaPontuacao) {
		this.dataUltimaPontuacao = dataUltimaPontuacao;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getNome() {
		return nome;
	}
    
    public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Administrador [id=" + id + ", nome=" + nome + ", senha=" + senha + ", email=" + email + ", contaAtiva="
				+ contaAtiva + ", socio=" + socio + ", cpf=" + cpf + ", dataNascimento=" + dataNascimento
				+ ", endereco=" + endereco + ", celular=" + celular + ", genero=" + genero + ", pontos=" + pontos
				+ ", dataUltimaPontuacao=" + dataUltimaPontuacao + "]";
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
		Administrador other = (Administrador) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
    
}