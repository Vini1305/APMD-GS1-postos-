package br.com.fiap.posto.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.fiap.posto.model.Posto;

public class PostoDao {

	// criar um gerente de entidades
		EntityManagerFactory factory = 
				Persistence.createEntityManagerFactory("posto");
		EntityManager manager = factory.createEntityManager();
		
		public void cadastrar(Posto posto) {
			// iniciar transação
			manager.getTransaction().begin();
			
			// salvar o produto
			manager.persist(posto);
			
			// terminar transação
			manager.getTransaction().commit();
			manager.close();
		}
		
		public List<Posto> listarTodos() {
			
				return manager
					.createQuery("SELECT p FROM Posto p", Posto.class)
					.getResultList();

		}
		
		public List<Posto> buscarPorNome(String nome){
			
				TypedQuery<Posto> query = manager
						.createQuery("SELECT p FROM Posto p WHERE nome=:nome", Posto.class);
				
				query.setParameter("nome", nome);
				
				return query.getResultList();
			
		}
		
		public void apagar(Long id) {
			Posto posto = manager.find(Posto.class, id);
			
			manager.getTransaction().begin();
			manager.remove(posto);
			manager.getTransaction().commit();

	}
	
}
