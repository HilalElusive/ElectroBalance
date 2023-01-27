package com.aeria.electroBalance.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import com.aeria.electroBalance.entity.Facture;
import com.aeria.electroBalance.entity.User;

public interface FactureRepository extends JpaRepository<Facture, Long> {
	List<Facture> findByUser(User user);

	@Query(value = "select product_id from ("
			+ "SELECT product_id,count(facture_id) as nbr "
			+ "FROM electrobalance.factures "
			+ "group by product_id "
			+ "order by nbr desc) as id "
			+ "WHERE product_id is not null;", nativeQuery = true)
	List<Object[]> findPopularProductId();
	
	@Query(value = "SELECT max(facture_id) FROM factures;", nativeQuery = true)
	List<Object[]> findLastFacture();
}
