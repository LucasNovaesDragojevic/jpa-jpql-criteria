package br.com.alura.jpa.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import br.com.alura.jpa.modelo.Movimentacao;

public class TestaCriteriaSoma {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("alura");
		EntityManager em = emf.createEntityManager();
		
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		
		CriteriaQuery<BigDecimal> criteriaQuery = criteriaBuilder.createQuery(BigDecimal.class);
		
		/**
		 * Objeto que conhece como converter todos os atributos do objeto em jqpl
		 */
		Root<Movimentacao> root = criteriaQuery.from(Movimentacao.class);
		
		/**
		 * Forma expressão para montar sql
		 * sum(m.valor)
		 */
		Expression<BigDecimal> expression = criteriaBuilder.sum(root.<BigDecimal>get("valor"));
		
		/**
		 * Até agora ficou
		 * select sum(m.valor) from Movimentacao m
		 */
		criteriaQuery.select(expression);
		
		TypedQuery<BigDecimal> typedQuery = em.createQuery(criteriaQuery);
		
		System.out.println(typedQuery.getSingleResult());
	}

}
