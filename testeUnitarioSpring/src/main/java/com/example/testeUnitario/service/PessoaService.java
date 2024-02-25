package com.example.testeUnitario.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.example.testeUnitario.entity.Pessoa;
import com.example.testeUnitario.exception.BusinessException;
import com.example.testeUnitario.repository.PessoaRepository;

import java.util.List;

import static java.lang.String.format;
import static org.springframework.util.Assert.notNull;

@Service
@Slf4j
public class PessoaService {

	private final PessoaRepository repository;

	public PessoaService(PessoaRepository repository) {
		this.repository = repository;
	}

	public List<Pessoa> findByCpf(String cpf) {

		try {
			notNull(cpf, "CPF É OBRIGATÓRIO");

			return repository.findPessoa(cpf);
			
		} catch (final Exception e) {

			throw new BusinessException(format("Erro ao buscar pessoas por cpf = %s", cpf), e);
		}

	}
}
