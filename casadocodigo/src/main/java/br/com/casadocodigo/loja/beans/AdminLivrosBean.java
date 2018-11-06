package br.com.casadocodigo.loja.beans;


import java.io.IOException;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import javax.transaction.Transactional;

import br.com.casadocodigo.loja.daos.AutorDao;
import br.com.casadocodigo.loja.daos.LivroDao;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.models.Autor;
import br.com.casadocodigo.loja.models.Livro;

@RequestScoped
@Named
public class AdminLivrosBean {

    private Livro livro = new Livro();      
	
    @Inject
    private FacesContext context;

	@Inject
    private LivroDao dao;
	
	private Part capaLivro;

    

	@Transactional
    public String salvar() throws IOException { 
    
    	dao.salvar(livro);
    	
    	 FileSaver fileSaver = new FileSaver(); // Nossa nova classe
         livro.setCapaPath(fileSaver.write(capaLivro, "livros")); // Já chamamos o método write e já retornamos o path direto para o Livro
    	
        // chamada do livroDao.salvar acima
        context.getExternalContext().getFlash().setKeepMessages(true);
        context.addMessage(null, new FacesMessage("Livro Cadastrado com sucesso"));
        
        return "/livros/lista?faces-redirect=true";

        
//        // limpar o formulario após o cadastro
//        this.livro = new Livro();
//        this.autoresId = new ArrayList<>();
    }
    
    @Inject
    private AutorDao autorDao;

    public List<Autor> getAutores() {
        return autorDao.listar();
    }
    
    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }
    
    
    public Part getCapaLivro() {
		return capaLivro;
	}

	public void setCapaLivro(Part capaLivro) {
		this.capaLivro = capaLivro;
	}


}