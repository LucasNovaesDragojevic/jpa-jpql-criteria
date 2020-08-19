package br.com.alura.jpa.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.alura.jpa.modelo.MediaComData;

public class MovimentacaoDao {

	private EntityManager em;
	
	public MovimentacaoDao(EntityManager em) {
		this.em = em;
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
