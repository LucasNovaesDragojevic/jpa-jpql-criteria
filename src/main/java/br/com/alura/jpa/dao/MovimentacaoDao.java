package br.com.alura.jpa.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.alura.jpa.modelo.MediaComData;
import br.com.alura.jpa.modelo.Movimentacao;

public class MovimentacaoDao {

	private EntityManager em;
	
	public MovimentacaoDao(EntityManager em) {
		this.em = em;
	}
	
	public List<Movimentacao> getMovimentacaoFiltradasPorData(Integer dia, Integer mes, Integer ano) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Movimentacao> criteriaQuery = criteriaBuilder.createQuery(Movimentacao.class);
		Root<Movimentacao> root = criteriaQuery.from(Movimentacao.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if (dia != null) {
			Predicate predicate = criteriaBuilder.equal(criteriaBuilder.function("day", Integer.class, root.get("data")), dia);
			predicates.add(predicate);
		}
		
		if (mes != null) {
			Predicate predicate = criteriaBuilder.equal(criteriaBuilder.function("month", Integer.class, root.get("data")), mes);
			predicates.add(predicate);
		}
		
		if (ano != null) {
			Predicate predicate = criteriaBuilder.equal(criteriaBuilder.function("year", Integer.class, root.get("data")), ano);
			predicates.add(predicate);
		}
		
		criteriaQuery.where((Predicate[]) predicates.toArray(new Predicate[0]));
		
		TypedQuery<Movimentacao> typedQuery = em.createQuery(criteriaQuery);
		
		return typedQuery.getResultList();
	}

	public List<MediaComData> getMediaDiariaMovimentacoes() {
		TypedQuery<MediaComData> query = em.createNamedQuery("mediaDiariaMovimentacoes", MediaComData.class);
		return query.getResultList();
	}
	
	public BigDecimal getSomaMovimentacoes() {
		TypedQuery<BigDecimal> querySum = em.createQuery("SELECT SUM(m.valor) FROM Movimentacao m", BigDecimal.class);
		return querySum.getSingleResult();
	}
}
