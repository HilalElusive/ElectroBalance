package com.aeria.electroBalance.entity;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "factures")
public class Facture {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "facture_generator")
    @SequenceGenerator(name="facture_generator", sequenceName = "facture_seq")
    @Column(name = "facture_id")
	private Long id;
	
	private Float montant;
	private Date date;
	private Time time;
	private Float poidsNet;
	private String factureURL;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "productId" ,referencedColumnName = "product_id")
	private Produit produit;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "user_Id" ,referencedColumnName = "user_id")
	private User user;
	
	public Facture() {
		
	}
	public Facture(Float montant, Date date, Time time, Float poidsNet, String factureURL) {
		super();
		this.montant = montant;
		this.date = date;
		this.time = time;
		this.poidsNet = poidsNet;
		this.factureURL = factureURL;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Produit getProduit() {
		return produit;
	}
	
	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Float getMontant() {
		return montant;
	}
	public void setMontant(Float montant) {
		this.montant = montant;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	public Float getPoidsNet() {
		return poidsNet;
	}
	public void setPoidsNet(Float poidsNet) {
		this.poidsNet = poidsNet;
	}
	public String getFactureURL() {
		return factureURL;
	}
	public void setFactureURL(String factureURL) {
		this.factureURL = factureURL;
	}
	@Override
	public String toString() {
		return "Facture [id=" + id + ", montant=" + montant + ", date=" + date + ", time=" + time + ", poidsNet="
				+ poidsNet + ", factureURL=" + factureURL + "]";
	}
	
	

}
