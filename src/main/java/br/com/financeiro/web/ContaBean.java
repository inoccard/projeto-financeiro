package br.com.financeiro.web;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import br.com.financeiro.entities.Conta;
import br.com.financeiro.services.ContaService;

@ManagedBean(name = "contaBean")
@RequestScoped
public class ContaBean {
	private Conta selecionada = new Conta();
	private List<Conta> lista = null;

	@ManagedProperty(value = "#{contextoBean}")
	private ContextoBean contextoBean;

	public String salvar() {
		this.selecionada.setUsuario(this.contextoBean.getUsuarioLogado()); 
		ContaService contaService = new ContaService();
		contaService.salvar(this.selecionada); 
		this.selecionada = new Conta(); 
		this.lista = null; 
		return null;
	}

	public String excluir() {
		ContaService contaService = new ContaService();
		contaService.excluir(this.selecionada);
		this.selecionada = new Conta();
		this.lista = null;
		return null;
	}

	public String tornarFavorita() {
		ContaService contaService = new ContaService();
		contaService.tornarFavorita(this.selecionada);
		this.selecionada = new Conta();
		return null;
	}
	
	public List<Conta> getLista() {
		if (this.lista == null) {
			ContaService contaService = new ContaService();
			this.lista = contaService.listar(this.contextoBean.getUsuarioLogado());
		}
		return this.lista;
	}
	
	public void setLista(List<Conta> lista) {
		this.lista = lista;
	}

	public Conta getSelecionada() {
		return selecionada;
	}

	public void setSelecionada(Conta selecionada) {
		this.selecionada = selecionada;
	}


	public ContextoBean getContextoBean() {
		return contextoBean;
	}

	public void setContextoBean(ContextoBean contextoBean) {
		this.contextoBean = contextoBean;
	}

}
