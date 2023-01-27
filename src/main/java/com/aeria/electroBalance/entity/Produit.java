package com.aeria.electroBalance.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "produits")
public class Produit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_generator")
    @SequenceGenerator(name="product_generator", sequenceName = "product_seq")
	@Column(name = "product_id")
	private Long id;
	
	@Column(name = "product_name")
	private String nom;
	private String description;
	private float prix_unit_kg;
	private String img_url;
	
	@Enumerated(EnumType.STRING)
	private TypeProduct type;
	
	@OneToMany(mappedBy = "produit", cascade = CascadeType.ALL)
    List<Facture> factures = new ArrayList<>();
	
	public Produit(String nom, String description, float prix_unit_kg, String img_url, TypeProduct type) {
		
		this.nom = nom;
		this.description = description;
		this.prix_unit_kg = prix_unit_kg;
		this.img_url = img_url;
		this.type = type;
	}
	
	public List<Facture> getFactures() {
		return factures;
	}
	
	public void setFactures(List<Facture> factures) {
		this.factures = factures;
	}

	public Produit() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrix_unit_kg() {
		return prix_unit_kg;
	}

	public void setPrix_unit_kg(float prix_unit_kg) {
		this.prix_unit_kg = prix_unit_kg;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	public TypeProduct getType() {
		return type;
	}

	public void setType(TypeProduct type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Produit [id=" + id + ", nom=" + nom + ", description=" + description + ", prix_unit_kg=" + prix_unit_kg
				+ ", img_url=" + img_url + ", type=" + type + "]";
	}
	
	
}
