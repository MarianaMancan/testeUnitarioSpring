package com.example.testeUnitario.service;

import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.testeUnitario.entity.Pessoa;
import com.example.testeUnitario.exception.BusinessException;
import com.example.testeUnitario.repository.PessoaRepository;
@ExtendWith(MockitoExtension.class)
public class PessoaServiceTeste {
	
	@InjectMocks
	PessoaService service;
	
	@Mock
	PessoaRepository repository;
	
	Pessoa pessoa;
	
	//dados que podem ser utilizados em todos os testes
	@BeforeEach
	public void setUp() {
	    pessoa = new Pessoa("NovoNome4", "44444444444", "NovaProfissao4", 42, "NovaCidade4", "NovaRua4", 4444);
	}
	  
	  @Test
	  void deveBuscarPessoasPorCPFSucesso() {
		  
		  when(repository.findPessoa(pessoa.getCpf())).thenReturn(Collections.singletonList(pessoa));		  
		  List<Pessoa> pessoas = service.findByCpf(pessoa.getCpf());
		  
		  assertEquals(Collections.singletonList(pessoa),pessoas);
		  //verifica se é chamado
		  verify(repository).findPessoa(pessoa.getCpf());
		  // verifica se não é chamado mais de uma vez
		  verifyNoMoreInteractions(repository);
	  }
	  
	  @Test
	  void naoDeveChamarRepositoryCasoCPFNulo() {
	      BusinessException e = assertThrows(BusinessException.class, () -> {
	          service.findByCpf(null);
	      });

	      assertThat(e.getMessage(), is("Erro ao buscar pessoas por cpf = null"));
	      assertNull(e.getCause());
	      verifyNoInteractions(repository);
	  }

	  void deveAcionarExceptionQuandoRepositoryFalhar() {
		    when(repository.findPessoa(pessoa.getCpf())).thenThrow(new RuntimeException("Falha ao buscar pessoas por cpf!"));

		    final BusinessException e = assertThrows(BusinessException.class, () -> {
		        service.findByCpf(pessoa.getCpf());
		    });

		    assertThat(e.getMessage(), is(format("Erro ao buscar pessoas por cpf = %s", pessoa.getCpf())));
		    assertNotNull(e.getCause()); // Verifica se a causa não é nula
		    assertTrue(e.getCause() instanceof Exception);  // Verifica se a causa é uma instância de Exception
		    assertThat(e.getCause().getMessage(), is("Falha ao buscar pessoas por cpf!"));
		    verify(repository).findPessoa(pessoa.getCpf());
		    verifyNoMoreInteractions(repository);
		}
}
