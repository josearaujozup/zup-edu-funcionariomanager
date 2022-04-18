package br.com.zup.edu.funcionariosmanager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zup.edu.funcionariosmanager.model.Funcionario;
import br.com.zup.edu.funcionariosmanager.repository.FuncionarioRepository;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
	
	private final FuncionarioRepository repository;

	public FuncionarioController(FuncionarioRepository repository) {
		this.repository = repository;
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable("id") Long idFuncionario){
		
		Funcionario funcionario = repository.findById(idFuncionario).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Funcionario não encontrado"));
		
		funcionario.aumento();
		
		repository.save(funcionario);
		
		return ResponseEntity.noContent().build();
	}
	
}
