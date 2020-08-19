package br.com.alura.jpa.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.jpa.dao.MovimentacaoDao;

public class TestaSomaMedia {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("alura");
		EntityManager em = emf.createEntityManager();
		
		BigDecimal somaMovimentacoes = new MovimentacaoDao(em).getSomaMovimentacoes();
		
		/*
		TypedQuery<Double> queryAvg = em.createQuery("SELECT AVG(m.valor) FROM Movimentacao m", Double.class);
		Double mediaMovimentacoes = queryAvg.getSingleResult();
		*/
		System.out.println(somaMovimentacoes);
		//System.out.println(mediaMovimentacoes);
	}
}
