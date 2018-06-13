package br.edu.unichristus.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.edu.unichristus.autores.Autor;
import br.edu.unichristus.autores.AutorService;

@Controller
@RequestMapping("/autor")
public class AutorController {

	@Autowired
	private AutorService servicoAutores;

	@RequestMapping("/buscar")
	public String buscarTodos(Model modelo) {
		List<Autor> autores = this.servicoAutores.buscarTodos();
		modelo.addAttribute("autores", autores);
		modelo.addAttribute("autor", new Autor());
		return "autores";
	}
	
	@RequestMapping(value = "/{autorID}")
	public String buscar(Model modelo, @PathVariable Long autorID) {
		modelo.addAttribute("autor", this.servicoAutores.buscarPeloID(autorID));
		return "autor";
	}
	
	@RequestMapping(value = "/adicionar", method = RequestMethod.POST)
	public String salvarAutor(Model modelo, @ModelAttribute Autor autor) {
		this.servicoAutores.salvar(autor);
		modelo.addAttribute("msgsucesso", "Autor adicionado com sucesso!");
		modelo.addAttribute("sucesso", true);
		modelo.addAttribute("autor", new Autor());
		List<Autor> autores = this.servicoAutores.buscarTodos();
		modelo.addAttribute("autores", autores);
		return "autores";
	}
	
	@RequestMapping(value = "/alterar/{autorID}", method = RequestMethod.POST)
	public String alterarAutor(Model modelo, @ModelAttribute Autor autor, @PathVariable Long autorID) {
		// Busca o autor no repositório pelo ID
		Autor autorAntigo = this.servicoAutores.buscarPeloID(autorID);
		// Altera os campos do autor com os novos valores
		autorAntigo.setNome(autor.getNome());
		autorAntigo.setPais(autor.getPais());
		// Salva o autor alterado
		this.servicoAutores.salvar(autorAntigo);
		modelo.addAttribute("msgsucessoAlteracao", "Autor alterado com sucesso!");
		modelo.addAttribute("sucessoAlteracao", true);
		modelo.addAttribute("autor", new Autor());
		List<Autor> autores = this.servicoAutores.buscarTodos();
		modelo.addAttribute("autores", autores);
		return "autores";
	}
	
	@RequestMapping(value = "/excluir/{autorID}", method = RequestMethod.POST)
	public String excluirAutor(Model modelo, @ModelAttribute Autor autor, @PathVariable Long autorID) {
		// Busca o autor no repositório pelo ID
		Autor autorExclusao = this.servicoAutores.buscarPeloID(autorID);
		// Exclui o autor
		this.servicoAutores.remover(autorExclusao);
		modelo.addAttribute("msgsucessoExckysai", "Autor excluído com sucesso!");
		modelo.addAttribute("sucessoExclusao", true);
		modelo.addAttribute("autor", new Autor());
		List<Autor> autores = this.servicoAutores.buscarTodos();
		modelo.addAttribute("autores", autores);
		return "autores";
	}
	
	
	

}
