package com.infiniteskills.data;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.infiniteskills.data.entities.Transaction;

public class JpqlApplication {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		EntityManagerFactory factory = null;
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try{
			factory = Persistence.createEntityManagerFactory("infinite-finances");
			em = factory.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
// JPQL -> Parametros começam no indice 1, E NÃO NO ZERO ! ***********************************			
			TypedQuery<Transaction> query = em.createQuery(
					"from Transaction t"
					+ " where (t.amount between ?1 and ?2) and t.title like '%s'"
					+ " order by t.title", Transaction.class);
			
			// Se usar parametros com indices 0 e 1, o hibernate NAO FAZ A CONSULTA. 
			query.setParameter(1, new BigDecimal("25"));
			query.setParameter(2, new BigDecimal("59"));
			
			List<Transaction> transactions = query.getResultList();

			for (Transaction t : transactions) {
				System.out.println(t.getTitle());
			}

			tx.commit();
		}catch(Exception e){
			tx.rollback();
		}finally{
			em.close();
			factory.close();
		}
	}
}
