package br.com.financeiro.util;

import br.com.financeiro.daos.CategoriaDAO;
import br.com.financeiro.daos.ContaDAO;
import br.com.financeiro.daos.LancamentoDAO;
import br.com.financeiro.daos.UsuarioDAO;

public class DAOFactory {

	public static UsuarioDAO criarUsuarioDAO() {

		return new UsuarioDAO(HibernateUtil.getSessionFactory().getCurrentSession());

	}

	public static ContaDAO criarContaDAO() {

		return new ContaDAO(HibernateUtil.getSessionFactory().getCurrentSession());

	}
	
	public static CategoriaDAO criarCategoriaDAO(){
		
		return new CategoriaDAO(HibernateUtil.getSessionFactory().getCurrentSession());
		
	}
	
	public static LancamentoDAO criarLancamentoDAO(){
		return new LancamentoDAO(HibernateUtil.getSessionFactory().getCurrentSession());
	}
}
