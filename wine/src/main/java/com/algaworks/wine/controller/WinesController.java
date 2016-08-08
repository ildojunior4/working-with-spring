package com.algaworks.wine.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.wine.model.Wine;
import com.algaworks.wine.model.WineType;
import com.algaworks.wine.repository.Wines;
import com.algaworks.wine.service.CreateWineService;

@Controller
@RequestMapping("/wines")
public class WinesController {

	@Autowired
	private Wines wines;
	
	@Autowired
	private CreateWineService createWineService;
	
	@RequestMapping
	public ModelAndView pesquisa() {
		ModelAndView mv = new ModelAndView("/wine/WinesListing");
		mv.addObject("wines", wines.findAll());
		return mv;
	}
	
	@RequestMapping("/new")
	public ModelAndView newWine(Wine wine) {
		ModelAndView mv = new ModelAndView("/wine/ProductCreate");
		mv.addObject("types", WineType.values());
		return mv;
	}
	
	@RequestMapping(value="/new", method= RequestMethod.POST)
	public ModelAndView save(@Valid Wine wine, BindingResult result, RedirectAttributes attributes ){
		if(result.hasErrors()){
			return newWine(wine);
		}
		
		createWineService.save(wine);
		attributes.addFlashAttribute("message","Wine salved!");
		return new ModelAndView("redirect:/wines/new");
	}
	
	@RequestMapping("/{code}")
	public ModelAndView visualize(@PathVariable("code") Wine wine){
		ModelAndView mv = new ModelAndView("/wine/WineView");
		mv.addObject("wine",wine);
		return mv;
	}
	
}