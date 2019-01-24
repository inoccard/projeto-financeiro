package br.com.financeiro.daos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.financeiro.abstractt.AbstractDAO;
import br.com.financeiro.entities.Categoria;
import br.com.financeiro.entities.Usuario;

public class CategoriaDAO extends AbstractDAO<Categoria> {

	public CategoriaDAO(Session session) {

		super(session, Categoria.class);

	}

	public Categoria salvar1(Categoria categoria) {

		Categoria merged = (Categoria) this.session.merge(categoria);
		this.session.flush();
		this.session.clear();
		return merged;

	}

	@Override
	public void excluir(Categoria categoria) {
		categoria = (Categoria) this.carregar(categoria.getCodigo());
		this.session.delete(categoria);
		this.session.flush();
		this.session.clear();
	}

	@SuppressWarnings("unchecked")
	public List<Categoria> listar(Usuario usuario) {

		String hql = "select c from Categoria c where c.pai is null and c.usuario = :usuario";
		Query query = this.session.createQuery(hql);
		query.setInteger("usuario", usuario.getCodigo());

		List<Categoria> lista = query.list();

		return lista;
	}

}
