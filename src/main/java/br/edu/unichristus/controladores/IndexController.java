package br.edu.unichristus.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/")
	String index() {
		return "index";
	}
	
	@RequestMapping("/sobre")
	String sobre() {
		return "sobre";
	}
	
	@RequestMapping("/listarEditoras")
	String listarEditoras() {
		return "editoras";
	}
	
	@RequestMapping("/listarLivros")
	String listarLivros() {
		return "livros";
	}
	
	
}
