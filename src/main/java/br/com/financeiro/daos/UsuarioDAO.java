package br.com.financeiro.daos;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.financeiro.abstractt.AbstractDAO;
import br.com.financeiro.entities.Usuario;

public class UsuarioDAO extends AbstractDAO<Usuario> {

	public UsuarioDAO(Session session) {

		super(session, Usuario.class);

	}

	public Usuario buscarPorLogin(String login) {
		String hql = "select u from Usuario u where u.login = :login";
		Query consulta = this.session.createQuery(hql);
		consulta.setString("login", login);
		return (Usuario) consulta.uniqueResult();
	}

	@Override
	public void atualizar(Usuario usuario) {

		if (usuario.getPermissao() == null || usuario.getPermissao().size() == 0) {
			Usuario usuarioPermissao = this.carregar(usuario.getCodigo());
			usuario.setPermissao(usuarioPermissao.getPermissao());
			//metodo evict é chamado para retornar do contexto de persistencia o objeto usuarioPermissao
			//que foi utilizado apenas para guardar as permissoes do usuario carregado
			this.session.evict(usuarioPermissao);
		}
		
		/* Depois testar desta forma
		if (usuario.getPermissao() == null || usuario.getPermissao().size() == 0) {
			usuario.setPermissao(this.carregar(usuario.getCodigo()).getPermissao());
		}*/

		super.atualizar(usuario);
	}
	
	

}
