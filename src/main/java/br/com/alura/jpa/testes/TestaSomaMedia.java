package br.com.alura.jpa.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class TestaSomaMedia {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("alura");
		EntityManager em = emf.createEntityManager();
		
		TypedQuery<BigDecimal> querySum = em.createQuery("SELECT SUM(m.valor) FROM Movimentacao m", BigDecimal.class);
		BigDecimal somaMovimentacoes = querySum.getSingleResult();

		TypedQuery<Double> queryAvg = em.createQuery("SELECT AVG(m.valor) FROM Movimentacao m", Double.class);
		Double mediaMovimentacoes = queryAvg.getSingleResult();
		
		System.out.println(somaMovimentacoes);
		System.out.println(mediaMovimentacoes);
	}
}
