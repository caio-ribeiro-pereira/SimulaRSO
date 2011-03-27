package appspot.simularso.model;

import java.io.Serializable;
import java.util.Arrays;

public class Paginacao implements Cloneable, Serializable {

	private static final long serialVersionUID = 8783591747285682271L;

	private long id;
	private String[] palavras;
	private boolean pageFault;

	public Paginacao() {
		this(1, 0, false);
	}

	public Paginacao(long id, int bloco, boolean pageFault) {
		this.setId(id);
		this.setPalavras(new String[bloco]);
		this.setPageFault(pageFault);
	}

	@Override
	public Paginacao clone() {
		try {
			return (Paginacao) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Paginacao))
			return false;
		Paginacao other = (Paginacao) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Paginacao [id=" + id + ", palavras=" + Arrays.toString(palavras) + ", pageFault=" + pageFault + "]";
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setPalavras(String[] palavras) {
		this.palavras = palavras;
	}

	public String[] getPalavras() {
		return palavras;
	}

	public void setPageFault(boolean pageFault) {
		this.pageFault = pageFault;
	}

	public boolean isPageFault() {
		return pageFault;
	}

}
