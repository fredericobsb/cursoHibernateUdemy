package com.infiniteskills.data;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.infiniteskills.data.entities.Transaction;

public class HibernateApplication {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		SessionFactory factory = null;
		Session session = null;
		org.hibernate.Transaction tx = null;

		int pageNumber = 3;
		int pageSize = 4;

		try {
			factory = HibernateUtil.getSessionFactory();
			session = factory.openSession();
			tx = session.beginTransaction();

			Criteria criteria = session.createCriteria(Transaction.class)
					.addOrder(Order.asc("title"));
			criteria.setFirstResult((pageNumber - 1) * pageSize);
			criteria.setMaxResults(pageSize);

			List<Transaction> transactions = criteria.list();

			for (Transaction t : transactions) {
				System.out.println(t.getTitle());
			}

			tx.commit();


		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
			factory.close();
		}
	}
}
