package br.usjt.apivolei.maestro.model.bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tb_experiencia")
public class Experiencia implements  Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@Column
	@NotNull
	private LocalDate data;

	@Column
	@NotNull
	private Integer custo;

	@Column
    @NotBlank
    private String descricao, local, nome;

	@Column
	@NotNull
    private int qtdDisponivel;

	@Column
	private boolean ativo;

	@ManyToMany
	@JoinTable(name = "tb_torcedor_experiencia", joinColumns = @JoinColumn(name = "id_torcedor"), inverseJoinColumns = @JoinColumn(name = "id_experiencia"))
	private List<Torcedor> torcedor;
	
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}

	public Integer getCusto() {
		return custo;
	}
	public void setCusto(Integer custo) {
		this.custo = custo;
	}

	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQtdDisponivel() {
		return qtdDisponivel;
	}
	public void setQtdDisponivel(int qtdDisponivel) {
		this.qtdDisponivel = qtdDisponivel;
	}

	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public List<Torcedor> getTorcedorList() {
		return torcedor;
	}
	public Torcedor getTorcedor(Torcedor torcedor) {
		for(Torcedor t : this.torcedor) {
			if(t.equals(torcedor)) {
				return t;	
			}
		}
		
		return null;
	}
	public void addTorcedor(Torcedor torcedor) {
		this.torcedor.add(torcedor);
	}
	public boolean removeTorcedor(Torcedor torcedorRemover) {
		return this.torcedor.removeIf(torcedor -> torcedor.equals(torcedorRemover));
			
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
		Experiencia other = (Experiencia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Experiencia [id=" + id + ", data=" + data + ", custo=" + custo + ", descricao=" + descricao + ", local="
				+ local + ", nome=" + nome + ", qtdDisponivel=" + qtdDisponivel + "]";
	}
    
	
    
    
	
}
