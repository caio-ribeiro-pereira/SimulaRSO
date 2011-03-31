package com.appspot.simularso.model;

import java.io.Serializable;

public class ProcessoDTO implements Comparable<ProcessoDTO>, Cloneable, Serializable {

	private static final long serialVersionUID = -8266518988561566012L;

	private int id;
	private long x;
	private long y;
	private long w;
	private long h;
	private String cor;

	public ProcessoDTO(int id, long x, long y, long w, long h, String cor) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.cor = cor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getX() {
		return x;
	}

	public void setX(long x) {
		this.x = x;
	}

	public long getY() {
		return y;
	}

	public void setY(long y) {
		this.y = y;
	}

	public long getW() {
		return w;
	}

	public void setW(long w) {
		this.w = w;
	}

	public long getH() {
		return h;
	}

	public void setH(long h) {
		this.h = h;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProcessoDTO [id=");
		builder.append(id);
		builder.append(", x=");
		builder.append(x);
		builder.append(", y=");
		builder.append(y);
		builder.append(", w=");
		builder.append(w);
		builder.append(", h=");
		builder.append(h);
		builder.append(", cor=");
		builder.append(cor);
		builder.append("]\n");
		return builder.toString();
	}

	@Override
	public int compareTo(ProcessoDTO p) {
		if (this.id < p.getId())
			return -1;
		if (this.id > p.getId())
			return 1;
		return 0;
	}

}
