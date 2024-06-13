package com.jsp.shoppingcart.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.shoppingcart.dao.Cartdao;
import com.jsp.shoppingcart.dao.Customerdao;
import com.jsp.shoppingcart.dto.Cart;
import com.jsp.shoppingcart.dto.Customer;
import com.jsp.shoppingcart.dto.Iteam;

@Controller
public class CartController {
	@Autowired
	Cartdao cdao;
	@Autowired
	Customerdao custdao;

	@RequestMapping("/fetchitemsfromcart")
	public ModelAndView fetchitemsfromcart(HttpSession session) {
		
		Customer c = (Customer) session.getAttribute("customerinfo");
		
		Customer customer = custdao.findCustomerById(c.getId());
		
		Cart cart = customer.getCart();
		
		List<Iteam> items = cart.getIteams();
		
		ModelAndView m = new ModelAndView();
		
		m.addObject("itemlist", items);
		
		m.addObject("totalprice", cart.getTotalprice());
		
		m.setViewName("displaycartitemstocustomer");
		
		
		return m;
	}
}
