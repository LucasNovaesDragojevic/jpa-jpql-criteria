package br.com.alura.jpa.testes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.jpa.modelo.Categoria;
import br.com.alura.jpa.modelo.Cliente;
import br.com.alura.jpa.modelo.Conta;
import br.com.alura.jpa.modelo.Movimentacao;
import br.com.alura.jpa.modelo.TipoMovimentacao;

public class PopulaCategoriaContaClienteMovimentacao 
{
	public static void main(String[] args) 
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("alura");
		EntityManager em = emf.createEntityManager();
		
		Categoria categoria1 = new Categoria("Conta");
		Categoria categoria2 = new Categoria("Eletrônicos");
		Categoria categoria3 = new Categoria("Viagem");
		Categoria categoria4 = new Categoria("Mercado");
		
		Conta conta1 = new Conta();
		Conta conta2 = new Conta();
		
		conta1.setAgencia(0123);
		conta1.setNumero(4569);
		conta1.setSaldo(1000.00);
		conta1.setTitular("Rube");		
		
		conta2.setAgencia(0321);
		conta2.setNumero(7845);
		conta2.setSaldo(10000.00);
		conta2.setTitular("Luci");
		
		Cliente cliente1 = new Cliente();
		Cliente cliente2 = new Cliente();
		
		cliente1.setConta(conta1);
		cliente1.setEndereco("Rua dos pavões, 351");
		cliente1.setNome("Ruberval");
		cliente1.setProfissao("Engenheiro");
		
		cliente2.setConta(conta2);
		cliente2.setEndereco("Rua das calopsitas");
		cliente2.setNome("Lucinda");
		cliente2.setProfissao("Pilota");
		
		Movimentacao movimentacao1 = new Movimentacao();
		Movimentacao movimentacao2 = new Movimentacao();
		
		movimentacao1.setCategorias(Arrays.asList(categoria1, categoria3));
		movimentacao1.setConta(conta1);
		movimentacao1.setData(LocalDateTime.now());
		movimentacao1.setDescricao("Despesas com viagem pra Bahia");
		movimentacao1.setTipoMovimentacao(TipoMovimentacao.SAIDA);
		movimentacao1.setValor(new BigDecimal(100));
		
		movimentacao2.setCategorias(Arrays.asList(categoria4));
		movimentacao2.setConta(conta1);
		movimentacao2.setData(LocalDateTime.now());
		movimentacao2.setDescricao("Reembolso da compra para amigo");
		movimentacao2.setTipoMovimentacao(TipoMovimentacao.ENTRADA);
		movimentacao2.setValor(new BigDecimal(200));
		
		em.getTransaction().begin();
		
		em.persist(categoria1);
		em.persist(categoria2);
		em.persist(categoria3);
		em.persist(categoria4);
		
		em.persist(conta1);
		em.persist(conta2);
		
		em.persist(cliente1);
		em.persist(cliente2);
		
		em.persist(movimentacao1);
		em.persist(movimentacao2);
		
		em.getTransaction().commit();
		em.close();
	}
}
