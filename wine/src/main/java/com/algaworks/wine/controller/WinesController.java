package com.algaworks.wine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.algaworks.wine.repository.Wines;

@Controller
@RequestMapping("/wines")
public class WinesController {

	@Autowired
	private Wines wines;
	
	@RequestMapping
	public ModelAndView pesquisa() {
		ModelAndView mv = new ModelAndView("/wine/WinesListing");
		mv.addObject("wines", wines.findAll());
		return mv;
	}
	
	@RequestMapping("/novo")
	public String novo() {
		return "/produto/CadastroProduto";
	}
}
