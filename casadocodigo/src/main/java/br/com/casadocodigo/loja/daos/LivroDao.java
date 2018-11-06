package br.com.casadocodigo.loja.daos;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import br.com.casadocodigo.loja.models.Livro;

// precisamos usar o ejb stateful uma vez que marcamos o PersistenceContext como extended
// o stateful vai manter o livroDao vivo durante todo o seu request, mas como o Bean
// está sendo controlado pelo model do cdi ele só vai manter o livroDao vivo quando
// seu request for acionado e depois vai matar o livroDao
@Stateful
public class LivroDao {

	// Mantém o manager sempre ativo com o extended
	// mantendo o Bean como Model e o Dao com o Stateful, vai evitar que conexão fique para sempre aberta
    @PersistenceContext(type=PersistenceContextType.EXTENDED)
    private EntityManager manager;
    
    public void salvar(Livro livro) {
        manager.persist(livro);
    }

    // vamos precisar que este Join, 
    //faça a junção entre Livro e Autores, 
    //ou seja, para cada livro ele deve carregar os autores, vamos usar então o join fetch
  //  Como podemos ter mais de um autor para cada livro temos que fazer um distinct 
 //   para distinguir o livro independente da quantidade de autores
  //  , ou seja, só trará um único resultado de livro independente da quantidade de autores
	public List<Livro> listar() {
        String jpql = "select distinct(l) from Livro l"
                + " join fetch l.autores";        

            return manager.createQuery(jpql, Livro.class).getResultList();
	}

	public List<Livro> ultimosLancamentos() {
	    String jpql = "select l from Livro l order by l.id desc";
	    return manager.createQuery(jpql, Livro.class)
	            .setMaxResults(2)
	            .getResultList();
	}
	
    public List<Livro> demaisLivros() {
        String jpql = "select l from Livro l order by l.id desc";
        return manager.createQuery(jpql, Livro.class)
                .getResultList();
    }

    // uma maneira de evitar o lazy exception, é com o join fetch, e ele faz um único select
    public Livro buscarPorId(Integer id) {
    	
        return manager.find(Livro.class, id);

//        String jpql = "select l from Livro l join fetch l.autores "
//                + "where l.id = :id";
//        return manager.createQuery(jpql, Livro.class)
//                .setParameter("id", id)
//                .getSingleResult();
    }
    
}