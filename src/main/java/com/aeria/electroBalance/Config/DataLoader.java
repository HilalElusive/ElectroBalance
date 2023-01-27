package com.aeria.electroBalance.Config;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.aeria.electroBalance.entity.Facture;
import com.aeria.electroBalance.entity.Produit;
import com.aeria.electroBalance.entity.TypeProduct;
import com.aeria.electroBalance.entity.User;
import com.aeria.electroBalance.repo.FactureRepository;
import com.aeria.electroBalance.repo.ProductRepository;
import com.aeria.electroBalance.repo.UserRepository;

@Configuration
public class DataLoader {
	
	private UserRepository repo;
	
	private ProductRepository repop;
	
	private FactureRepository repof;
	
	private BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
	
    public DataLoader(UserRepository repo, ProductRepository repop, FactureRepository repof) {
        this.repo = repo;
        this.repop = repop;
        this.repof = repof;
    }
 
    /*@Bean
    public CommandLineRunner initializeDatabase() {
        return args -> {
            //User user1 = new User("admin4", bcrypt.encode("pass"), true, "ADMIN");
            long now = System.currentTimeMillis();
            Facture fact1 = new Facture(9.99f,new Date(now), new Time(now),2.4f,"just trash");
            Facture fact2 = new Facture(8.39f,new Date(now), new Time(now),2.4f,"just trash 2");
            Facture fact3 = new Facture(0.28f,new Date(now), new Time(now),2.4f,"just trash 3");
            Facture fact4 = new Facture(1.23f,new Date(now), new Time(now),2.4f,"just trash 4");
            repof.saveAll(List.of(fact1,fact2,fact3,fact4));
            
            User exist = repo.findByUserName("user");
            User user3 = repo.findByUserName("user3");
            User user1 = repo.findByUserName("user1");
            Produit p = repop.findById(42);
            Produit p1 = repop.findById(41);
            
            fact1.setProduit(p);
            fact1.setUser(user1);
            
            fact2.setProduit(p1);
            fact2.setUser(user1);
            
            fact3.setProduit(p);
            fact3.setUser(exist);
            
            fact4.setProduit(p1);
            fact4.setUser(user3);
            
            
            repof.saveAll(List.of(fact1,fact2,fact3,fact4));
            System.out.println("Database initialized");
        };
    }*/
}
