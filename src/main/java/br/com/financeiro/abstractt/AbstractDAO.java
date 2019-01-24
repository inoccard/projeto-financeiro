package br.com.financeiro.abstractt;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import br.com.financeiro.entities.Categoria;

public class AbstractDAO<T extends AbstractEntity> {

	protected Session session;

	private Class<T> classe;

	public AbstractDAO(Session session, Class<T> classe) {

		this.session = session;
		this.classe = classe;
	}
	
	public void salvar(T entidade) {
		this.session.save(entidade);
	}

	public void atualizar(T entidade) {
		this.session.update(entidade);
	}

	public void excluir(T entidade) {
		this.session.delete(entidade);
	}

	public T carregar(Integer codigo) {

		return (T) this.session.get(classe, codigo);

	}

	public List<T> listarTodos() {

		Query query = (Query) session.createQuery("select e from " + classe.getSimpleName() + " e");

		return query.getResultList();

	}

	public List listar() {

		return (List) this.session.createCriteria(classe).list();

	}
	
	
	
}
