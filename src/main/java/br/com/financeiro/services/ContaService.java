package br.com.financeiro.services;

import java.util.Date;
import java.util.List;

import br.com.financeiro.daos.ContaDAO;
import br.com.financeiro.entities.Conta;
import br.com.financeiro.entities.Usuario;
import br.com.financeiro.util.DAOFactory;

public class ContaService {

	private ContaDAO contaDAO;

	public ContaService() {
		this.contaDAO = DAOFactory.criarContaDAO(); 
	}

	public List<Conta> listar(Usuario usuario) {
		return this.contaDAO.listar(usuario);
	}

	public Conta carregar(Integer codigo) {
		return this.contaDAO.carregar(codigo);
	}

	public void salvar(Conta conta) {
		conta.setDataCadastro(new Date());
		this.contaDAO.salvar(conta);
	}

	public void excluir(Conta conta) {
		this.contaDAO.excluir(conta);
	}

	public void tornarFavorita(Conta contaFavorita) {
		Conta conta = this.contaDAO.buscarFavorita(contaFavorita.getUsuario());
		if (conta != null) {
			conta.setFavorita(false);
			this.contaDAO.salvar(conta);
		}
		contaFavorita.setFavorita(true);
		this.contaDAO.salvar(contaFavorita);
	}

	public Conta buscarFavorita(Usuario usuario){
		return this.contaDAO.buscarFavorita(usuario);
	}
}
