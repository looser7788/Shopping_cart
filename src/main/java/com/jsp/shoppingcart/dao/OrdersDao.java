package com.jsp.shoppingcart.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.shoppingcart.dto.Iteam;
import com.jsp.shoppingcart.dto.Orders;
@Repository
public class OrdersDao {
	@Autowired
	EntityManagerFactory emf;
	public void saveOrder(Orders order) {
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		
		et.begin();
		em.persist(order);
		et.commit();
	}
	public void updatedateOrders(Orders o) {
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		
		et.begin();
		em.merge(o);
		et.commit();
	}
	public void deleteItemById(int id) {
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		
		Iteam m=em.find(Iteam.class, id);
		et.begin();
		em.remove(m);
		et.commit();
	}
	public Iteam findOrderById(int id) {
		
		EntityManager em=emf.createEntityManager();
		Iteam m=em.find(Iteam.class, id);
		if(m !=null)
			return m;
		else
			return null;
	}
}
