package br.com.financeiro.services;

import java.util.Date;
import java.util.List;

import br.com.financeiro.daos.LancamentoDAO;
import br.com.financeiro.entities.Conta;
import br.com.financeiro.entities.Lancamento;
import br.com.financeiro.util.DAOFactory;

public class LancamentoService {

	private LancamentoDAO lancamentoDAO;

	public LancamentoService() {
		this.lancamentoDAO = DAOFactory.criarLancamentoDAO();
	}

	public void salvar(Lancamento lancamento) {
		this.lancamentoDAO.salvar(lancamento);
	}

	public void excluir(Lancamento lancamento) {
		this.lancamentoDAO.excluir(lancamento);
	}

	public Lancamento carregar(Integer lancamento) {
		return this.lancamentoDAO.carregar(lancamento);
	}

	public float saldo(Conta conta, Date data) {
		float saldoInicial = conta.getSaldoInicial();
		float saldoNaData = this.lancamentoDAO.saldo(conta, data);
		return saldoInicial + saldoNaData;
	}

	public List<Lancamento> listar(Conta conta, Date dataInicio, Date dataFim) {
		return this.lancamentoDAO.listar(conta, dataInicio, dataFim);
	}
}
