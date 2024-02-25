package com.example.testeUnitario.repository;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.testeUnitario.entity.Pessoa;

@Repository
public class PessoaRepository {

	public List<Pessoa> findPessoa(String cpf) {
		List<Pessoa> pessoas = listarPessoas();
		return pessoas.stream().filter(Objects::nonNull) // Processa apenas objetos não nulos
				.filter(pessoa -> pessoa.getCpf().equals(cpf)).collect(Collectors.toList());
	}

	private static List<Pessoa> listarPessoas() {

		return Arrays.asList(
				new Pessoa("NovoNome1", "11111111111", "NovaProfissao1", 30, "NovaCidade1", "NovaRua1", 111),
				new Pessoa("NovoNome2", "22222222222", "NovaProfissao2", 25, "NovaCidade2", "NovaRua2", 222),
				new Pessoa("NovoNome3", "33333333333", "NovaProfissao3", 58, "NovaCidade3", "NovaRua3", 333),
				new Pessoa("NovoNome4", "44444444444", "NovaProfissao4", 42, "NovaCidade4", "NovaRua4", 4444),
				new Pessoa("NovoNome5", "55555555555", "NovaProfissao5", 33, "NovaCidade5", "NovaRua5", 5555),
				new Pessoa("NovoNome6", "66666666666", "NovaProfissao6", 58, "NovaCidade6", "NovaRua6", 666));
	}

}
