package com.jsp.shoppingcart.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.shoppingcart.dto.Cart;
@Repository
public class Cartdao {
	@Autowired
EntityManagerFactory emf;
	public void saveCart(Cart c) {
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		et.begin();
		em.persist(c);
		et.commit();
	}
	public void deleteCartByid(int id) {
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		Cart c=em.find(Cart.class, id);
		et.begin();
		em.remove(c);
		et.commit();
	}
	public void updatedCartBYid(Cart c) {
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		//Cart c=em.find(Cart.class, id);
		et.begin();
		em.merge(c);
		et.commit();
		
	}
	public Cart  findCartById(int id) {
		EntityManager em=emf.createEntityManager();
			
			Cart c=em.find(Cart.class,id);
			if(c!=null) {
				return c;
			}else {
				return null;
				
			}
			
			
		
		}
	public Cart removeAllItemsIntheCart(int id) {
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		Cart c=em.find(Cart.class, id);
		c.setIteams(null);
		c.setTotalprice(0);
		et.begin();
		em.persist(c);
		et.commit();
		return c;
	}
	public void removeItemFormCartById(int cart_id,int item_id) {
		
	}
	}
