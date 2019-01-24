package br.com.financeiro.web;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.financeiro.entities.Usuario;
import br.com.financeiro.services.UsuarioService;

@ManagedBean(name = "usuarioBean")
@RequestScoped
public class UsuarioBean {

	private Usuario usuario = new Usuario();
	private String confirmarSenha;
	private List<Usuario> lista;
	private String destinoSalvar;
	

	public String novo() {
		this.destinoSalvar = "usuariosucesso";
		this.usuario = new Usuario();
		this.usuario.setAtivo(true);
		return "/publico/usuario";
	}
	
	public String editar() { 
		this.confirmarSenha = this.usuario.getSenha();
		return "/publico/usuario";
	}

	public String salvar() {
		FacesContext facesContext = FacesContext.getCurrentInstance();

		String senha = this.usuario.getSenha();
		if (!senha.equals(this.confirmarSenha)) {
			FacesMessage facesMessage = new FacesMessage("A senha não foi confirmada corretamente");
			facesContext.addMessage(null, facesMessage);
			return null;
		}

		UsuarioService usuarioService = new UsuarioService();
		usuarioService.salvar(this.usuario);

		return this.destinoSalvar;
	}
	
	public String excluir() { 
		UsuarioService usuarioService = new UsuarioService();
		usuarioService.excluir(this.usuario);
		this.lista = null; 
		return null;
	}
	
	public String atribuiPermissao(Usuario usuario, String permissao) {
		this.usuario = usuario;
		java.util.Set<String> permissoes = this.usuario.getPermissao();
		if (permissoes.contains(permissao)) {
			permissoes.remove(permissao);
		} else {
			permissoes.add(permissao);
		}
		return null;
	}
	
	public String ativar() { 
		if (this.usuario.isAtivo())
			this.usuario.setAtivo(false);
		else
			this.usuario.setAtivo(true);

		UsuarioService usuarioService = new UsuarioService();
		usuarioService.salvar(this.usuario);
		return null;
	}
	
	public List<Usuario> getLista() { 
		if (this.lista == null) {
			UsuarioService usuarioService = new UsuarioService();
			this.lista = usuarioService.listar();
		}
		return this.lista;
	}
	
	

	public String getDestinoSalvar() {
		return destinoSalvar;
	}

	public void setDestinoSalvar(String destinoSalvar) {
		this.destinoSalvar = destinoSalvar;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

	// dando um redirect na página
			/*
			 * UIViewRoot vr = facesContext.getViewRoot(); if (vr != null) { // Get
			 * the URL where to redirect the user String url =
			 * facesContext.getExternalContext().getRequestContextPath(); url = url
			 * + "/" + "publico/usuariosucesso.jsf"; Object obj =
			 * facesContext.getExternalContext().getResponse(); if (obj instanceof
			 * HttpServletResponse) { HttpServletResponse response =
			 * (HttpServletResponse) obj; try { // Redirect the user now.
			 * response.sendRedirect(response.encodeURL(url)); } catch (IOException
			 * e) { e.printStackTrace(); } } }
			 */



}