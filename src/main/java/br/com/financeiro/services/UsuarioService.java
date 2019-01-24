package br.com.financeiro.services;

import java.util.List;

import br.com.financeiro.daos.UsuarioDAO;
import br.com.financeiro.entities.Usuario;
import br.com.financeiro.util.DAOFactory;

public class UsuarioService {
	private UsuarioDAO usuarioDAO;

	public UsuarioService() { 
		this.usuarioDAO = DAOFactory.criarUsuarioDAO();
	}

	public Usuario carregar(Integer codigo) {
		return this.usuarioDAO.carregar(codigo);
	}

	public Usuario buscarPorLogin(String login) {
		return this.usuarioDAO.buscarPorLogin(login);
	}

	public void salvar(Usuario usuario) {
		if (usuario.getCodigo() == null || usuario.getCodigo() == 0) {
			usuario.getPermissao().add("ROLE_USUARIO");
			this.usuarioDAO.salvar(usuario);
			CategoriaService categoriaService = new CategoriaService();
			categoriaService.salvaEstruturaPadrao(usuario);
		} else {
			this.usuarioDAO.atualizar(usuario);
		}
	}

	public void excluir(Usuario usuario) {
		
		CategoriaService categoriaService = new CategoriaService();
		categoriaService.excluir(usuario);
		
		this.usuarioDAO.excluir(usuario);
	}

	public List<Usuario> listar() {
		return this.usuarioDAO.listar();
	}
}
