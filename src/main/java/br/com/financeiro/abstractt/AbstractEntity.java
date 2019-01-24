package br.com.financeiro.abstractt;

public abstract class AbstractEntity {

	public abstract Integer getCodigo();

	public abstract void setCodigo(Integer codigo);

	public boolean temIdValido() {
		return getCodigo() != null && getCodigo() != 0;
	}
}
