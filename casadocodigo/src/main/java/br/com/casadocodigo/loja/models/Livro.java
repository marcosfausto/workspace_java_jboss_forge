package br.com.casadocodigo.loja.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Livro {
	
	
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @NotBlank // Valida já vazio e espeços em branco
    private String titulo;

    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	@Lob
    @Length(min=10) // Número mínimo de caracteres que o campo pode ter
    @NotBlank
    private String descricao;

    @DecimalMin("20") // Valor decimal mínimo
    private BigDecimal preco;

    @Min(50) // Valor inteiro mínimo
    private int numeroPaginas;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataPublicacao;
    
    private String capaPath;


	public String getCapaPath() {
		return capaPath;
	}
	public void setCapaPath(String capaPath) {
		this.capaPath = capaPath;
	}
	public Calendar getDataPublicacao() {
		return dataPublicacao;
	}
	public void setDataPublicacao(Calendar dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	@ManyToMany
    @Size(min=1) // número mínimo de elementos na lista
    @NotNull // A lista não pode ser nula
    private List<Autor> autores = new ArrayList<>();

	
    public List<Autor> getAutores() {
		return autores;
	}
	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getNumeroPaginas() {
		return numeroPaginas;
	}
	public void setNumeroPaginas(int numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
	public Calendar getDataLancamento() {
		return dataPublicacao;
	}
	public void setDataLancamento(Calendar dataLancamento) {
		this.dataPublicacao = dataLancamento;
	}
	
    @Override
    public String toString() {
        return "Livro [id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", preco=" + preco
                + ", numeroPaginas=" + numeroPaginas + ", autores=" + autores + "]";
    }


}
