package br.com.financeiro.web;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import br.com.financeiro.entities.Conta;
import br.com.financeiro.entities.Usuario;
import br.com.financeiro.services.ContaService;
import br.com.financeiro.services.UsuarioService;

@ManagedBean
@SessionScoped
public class ContextoBean implements Serializable {

	
	private static final long serialVersionUID = 4334696245398847974L;
	private int codigoContaAtiva = 0;

	public Usuario getUsuarioLogado() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		String login = external.getRemoteUser();
		if (login != null) {
			UsuarioService usuarioService = new UsuarioService();
			return usuarioService.buscarPorLogin(login);
		}
		return null;
	}

	public Conta getContaAtiva() {
		Conta contaAtiva = null;
		if (this.codigoContaAtiva == 0) {
			contaAtiva = this.getContaAtivaPadrao();
		} else {
			ContaService contaService = new ContaService();
			contaAtiva = contaService.carregar(this.codigoContaAtiva);
		}
		if (contaAtiva != null) {
			this.codigoContaAtiva = contaAtiva.getCodigo();
			return contaAtiva;
		}
		return null;
	}

	private Conta getContaAtivaPadrao() {
		ContaService contaService = new ContaService();
		Conta contaAtiva = null;
		Usuario usuario = this.getUsuarioLogado();
		contaAtiva = contaService.buscarFavorita(usuario);
		if (contaAtiva == null) {
			List<Conta> contas = contaService.listar(usuario);
			if (contas != null && contas.size() > 0) {
				contaAtiva = contas.get(0);
			}
		}
		return contaAtiva;
	}

	public void changeContaAtiva(ValueChangeEvent event) {
		this.codigoContaAtiva = (Integer) event.getNewValue();
	}
	
	
	
	
	
	
	
	/*<!--<h:selectOneMenu value="#{contextoBean.contaAtiva.codigo}"-->
	<!--valueChangeListener="#{contextoBean.changeContaAtiva}"-->
	<!--rendered="#{empty contextoBean and not empty contextoBean.contaAtiva}"-->
	<!--onchange="submit()">-->
	<!--<f:selectItems value="#{contaBean.lista}" var="conta"-->
		<!--itemValue="#{conta.codigo}" itemLabel="#{conta.descricao}" />-->
<!--</h:selectOneMenu> -->*/
	
}
