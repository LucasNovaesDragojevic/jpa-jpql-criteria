package br.com.alura.jpa.testes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.jpa.dao.MovimentacaoDao;
import br.com.alura.jpa.modelo.Movimentacao;

public class TesteCriteria {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("alura");
		EntityManager em = emf.createEntityManager();
		
		MovimentacaoDao movimentacaoDao = new MovimentacaoDao(em);
		
		List<Movimentacao> movimentacoes = movimentacaoDao.getMovimentacaoFiltradasPorData(17, 8, 2020);
		
		for (Movimentacao movimentacao : movimentacoes) {
			System.out.println(movimentacao.toString());
		}
	}
}
