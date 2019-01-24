package br.com.financeiro.daos;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.financeiro.abstractt.AbstractDAO;
import br.com.financeiro.entities.Conta;
import br.com.financeiro.entities.Lancamento;

public class LancamentoDAO extends AbstractDAO<Lancamento> {

	public LancamentoDAO(Session session) {
		super(session, Lancamento.class);
	}

	private Session session;

	@Override
	public void salvar(Lancamento lancamento) {
		this.session.saveOrUpdate(lancamento);
	}
	
	@SuppressWarnings("unchecked")
	public List<Lancamento> listar(Conta conta, Date dataInicio, Date dataFim) {
		Criteria criteria = this.session.createCriteria(Lancamento.class);

		if (dataInicio != null && dataFim != null) {
			criteria.add(Restrictions.between("data", dataInicio, dataFim));
		} else if (dataInicio != null) {
			criteria.add(Restrictions.ge("data", dataInicio));
		} else if (dataFim != null) {
			criteria.add(Restrictions.le("data", dataFim));
		}

		criteria.add(Restrictions.eq("conta", conta));
		criteria.addOrder(Order.asc("data"));
		return criteria.list();
	}

	public float saldo(Conta conta, Date data) {
		StringBuffer sql = new StringBuffer();
		sql.append("select sum(l.valor * c.fator)");
		sql.append(" from LANCAMENTO l,");
		sql.append(" CATEGORIA c");
		sql.append(" where l.categoria = c.codigo");
		sql.append(" and l.codigo = :codigo");
		sql.append(" and l.data <= :data");
		SQLQuery query = this.session.createSQLQuery(sql.toString());
		query.setParameter("codigo", conta.getCodigo());
		query.setParameter("data", data);
		BigDecimal saldo = (BigDecimal) query.uniqueResult();
		if (saldo != null) {
			return saldo.floatValue();
		}
		return 0f;
	}
}
