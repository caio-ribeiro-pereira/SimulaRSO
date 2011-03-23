package appspot.simularso.model;

import java.io.Serializable;
import java.util.Arrays;

public class Pagina implements Cloneable, Serializable {

	private static final long serialVersionUID = -5672594285333581212L;

	private String[] palavras;
	private boolean pageFault;

	public Pagina() {
		this(0, false);
	}

	public Pagina(int bloco, boolean pageFault) {
		this.setPalavras(new String[bloco]);
		this.setPageFault(pageFault);
	}

	@Override
	public Pagina clone() {
		try {
			return (Pagina) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	@Override
	public String toString() {
		return "Pagina [palavras=" + Arrays.toString(palavras) + ", pageFault=" + pageFault + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (pageFault ? 1231 : 1237);
		result = prime * result + Arrays.hashCode(palavras);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Pagina))
			return false;
		Pagina other = (Pagina) obj;
		if (pageFault != other.pageFault)
			return false;
		if (!Arrays.equals(palavras, other.palavras))
			return false;
		return true;
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
