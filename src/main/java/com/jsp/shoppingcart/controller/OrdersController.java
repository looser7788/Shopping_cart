package com.jsp.shoppingcart.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.shoppingcart.dao.Cartdao;
import com.jsp.shoppingcart.dao.Customerdao;
import com.jsp.shoppingcart.dao.OrdersDao;
import com.jsp.shoppingcart.dao.Productdao;
import com.jsp.shoppingcart.dto.Cart;
import com.jsp.shoppingcart.dto.Customer;
import com.jsp.shoppingcart.dto.Iteam;
import com.jsp.shoppingcart.dto.Orders;
import com.jsp.shoppingcart.dto.Product;

@Controller
public class OrdersController {
	@Autowired
OrdersDao dao;
	@Autowired
Customerdao cdao;
	@Autowired
Productdao pdao;
	@Autowired
	Cartdao cartdao;
	@RequestMapping("/addorder")

	public ModelAndView addorder() {
		Orders o=new Orders();
		ModelAndView m=new ModelAndView();
		m.addObject("orderobj",o);
		m. setViewName("ordersform");
		return m;
	}
	@RequestMapping("/saveorder")
	public ModelAndView saveorder(@ModelAttribute("orderobj") Orders o,HttpSession session) {
		Customer c=(Customer)session.getAttribute("customerinfo");
		Customer customer=cdao.findCustomerById(c.getId());
		Cart cart=customer.getCart();
		List<Iteam> items=cart.getIteams();
		//o.setTotalprice(cart.getTotalprice());
		List<Iteam> itemlist=new ArrayList<Iteam>();
		List<Iteam> itemwithgeneratedQuality=new ArrayList<Iteam>();
		for(Iteam i:items) {
			Product p=pdao.findproductById(i.getP_id());
			if(i.getQuantity()<p.getStock()) {
				itemlist.add(i);
				p.setStock(p.getStock()-i.getQuantity());
				
				pdao.updatedataproduct(p);
				
				
			}
			else {
				itemwithgeneratedQuality.add(i);
				
			}
			
		}
		o.setIteams(itemlist);
		double totalpriceofOrder=0;
		for(Iteam i:itemlist) {
			totalpriceofOrder=totalpriceofOrder+i.getPrice();
		}
		o.setTotalprice(totalpriceofOrder);
		cart.setIteams(itemwithgeneratedQuality);
		double totalprice=0;
		for(Iteam i:itemwithgeneratedQuality) {
			totalprice=totalprice+i.getPrice();
		}
		cart.setTotalprice(totalprice);
		List<Orders> orders=customer.getOrders();
		if(orders.size()>0) {
			orders.add(o);
			customer.setOrders(orders);}
		else {
			List<Orders> orders1=new ArrayList<Orders>();
			orders1.add(o);
			customer.setOrders(orders1);
			
		}
		customer.setCart(cart);
		cartdao.updatedCartBYid(cart);
		dao.saveOrder(o);
		cdao.updatecustomer(customer);
		
		ModelAndView m=new ModelAndView();
		m.addObject("msg","order placed successfully");
		m.addObject("orderdetyails",o);
		m.setViewName("customerBill");
		return m;
	}
}
