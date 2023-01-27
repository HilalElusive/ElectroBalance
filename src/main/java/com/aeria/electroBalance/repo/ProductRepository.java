package com.aeria.electroBalance.repo;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aeria.electroBalance.entity.Produit;
import com.aeria.electroBalance.entity.TypeProduct;

@Repository
public interface ProductRepository extends JpaRepository<Produit, Long> {
	List<Produit> findByType(TypeProduct type);
	Produit findById(long id);
}
