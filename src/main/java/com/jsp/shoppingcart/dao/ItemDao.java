package com.jsp.shoppingcart.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.shoppingcart.dto.Iteam;


@Repository
public class ItemDao {
	@Autowired
	EntityManagerFactory emf;
	public void saveItem(Iteam item) {
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		
		et.begin();
		em.persist(item);
		et.commit();
	}
	public void updatedateItem(Iteam i) {
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		
		et.begin();
		em.merge(i);
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
	public Iteam findItemsById(int id) {
		
		EntityManager em=emf.createEntityManager();
		Iteam m=em.find(Iteam.class, id);
		if(m !=null)
			return m;
		else
			return null;
	}

}
