package com.jsp.shoppingcart.controller;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;

import com.jsp.shoppingcart.dao.Merchantdao;
import com.jsp.shoppingcart.dto.Merchent;

@Controller
public class MerchantController {
	@Autowired
	Merchantdao dao;
	
@RequestMapping("/addmarchant")
public ModelAndView addmerchant() {
	Merchent m=new Merchent();
	ModelAndView mav=new ModelAndView();
	mav.addObject("marchantobj",m);
	mav.setViewName("merchantform");
	return mav;
}
@RequestMapping("/savemerchant")//to get objects from and @RequestParam to get data form browser
public ModelAndView savemerchant(@ModelAttribute("marchantobj") Merchent mar) {
	dao.savemarchant(mar);
	ModelAndView mav=new ModelAndView();
	mav.addObject("msg","data added successfully");
	mav.setViewName("menucart");
	return mav;
}
	@RequestMapping("/loginvalidation")
	public ModelAndView login(ServletRequest req,HttpSession session) {
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		
		Merchent m=dao.login(email, password);
		ModelAndView mav=new ModelAndView();
		if(m!=null) {
			mav.addObject("msg","sucessfully logedin");
			session.setAttribute("merchantinfo", m);
			mav.setViewName("merchantoption");
			
			return mav;
		}else {
			mav.addObject("msg"," invalid credintials");
			mav.setViewName("merchantloginform");
			return mav;
		}
	
}

}
