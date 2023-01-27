package com.aeria.electroBalance.entity;

public class urlObj {
	
	private String url;
	private float poidNet;
	private long idProd;
	private long idFact;

	public urlObj(String url) {
		super();
		this.url = url;
	}
	public urlObj() {}
	
	public String getUrl() {
		return url;
	}
	public float getPoidNet() {
		return poidNet;
	}
	public long getIdProd() {
		return idProd;
	}
	public long getIdFact() {
		return idFact;
	}
	public urlObj(String url, float poidNet, long idProd,long idFact) {
		super();
		this.url = url;
		this.poidNet = poidNet;
		this.idProd = idProd;
		this.idFact = idFact;
	}
	@Override
	public String toString() {
		return "urlObj [poidNet=" + poidNet + ", idProd=" + idProd + ", idFact=" + idFact + "]";
	}
	
	
	

	
}
