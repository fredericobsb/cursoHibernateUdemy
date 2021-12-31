package com.infiniteskills.data;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.infiniteskills.data.entities.Transaction;

public class HqlApplication {

	public static void main(String[] args) {
		
		SessionFactory factory = null;
		Session session = null;
		org.hibernate.Transaction tx = null;
		
		try{
			factory = HibernateUtil.getSessionFactory();
			session = factory.openSession();
			tx = session.beginTransaction();
			
			Query query = session.createQuery("select t from Transaction t "
					+ "where t.amount > :amount and t.transactionType = 'Withdrawl'");
			
			query.setParameter("amount", new BigDecimal("10"));
			List<Transaction> transactions = query.list();
			tx.commit();
			
			tx = session.beginTransaction();
			Query query2 = session.createQuery("select t from Transaction t "
					+ "where t.amount > ? and t.transactionType = ?");
			query2.setParameter(0, new BigDecimal("20.00"));
			query2.setParameter(1, "Withdrawl");
			List<Transaction> transactions2 = query2.list();
			tx.commit();
		}catch(Exception e){
			tx.rollback();
		}finally{
			session.close();
			factory.close();
		}
	}
}

