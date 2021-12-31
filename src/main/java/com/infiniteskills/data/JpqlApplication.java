package com.infiniteskills.data;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.infiniteskills.data.entities.Account;
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
			
			//javax.persistence.Query
			Query query = em.createQuery("select distinct t.account.name, "
					+ "concat(concat(t.account.bank.name, ' '),t.account.bank.address.state)"
					+ " from Transaction t"
					+ " where t.amount > 500 and t.transactionType = 'Deposit'");
			
			List<Object[]> accounts = query.getResultList();
			
			for(Object[] a:accounts){
				System.out.println(a[0]);
				System.out.println(a[1]);
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
