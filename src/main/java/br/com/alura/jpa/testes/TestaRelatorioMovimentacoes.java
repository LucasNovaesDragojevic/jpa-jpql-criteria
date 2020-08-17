package br.com.alura.jpa.testes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.alura.jpa.modelo.Conta;

public class TestaRelatorioMovimentacoes {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("alura");
		EntityManager em = emf.createEntityManager();
		
		TypedQuery<Conta> query = em.createQuery("select distinct c from Conta c left join fetch c.movimentacoes", Conta.class);
		List<Conta> contas = query.getResultList();
		
		for (Conta conta : contas) {
			System.out.println("titular " + conta.getTitular());
			System.out.println("agencia " + conta.getAgencia());
			System.out.println("numero " + conta.getNumero());
			System.out.println("movimentacao " + conta.getMovimentacoes());
		}
	}

}
