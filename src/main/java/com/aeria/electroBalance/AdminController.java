package com.aeria.electroBalance;

import java.awt.Color;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.aeria.electroBalance.entity.Facture;
import com.aeria.electroBalance.entity.Produit;
import com.aeria.electroBalance.entity.User;
import com.aeria.electroBalance.repo.FactureRepository;
import com.aeria.electroBalance.repo.ProductRepository;
import com.aeria.electroBalance.repo.UserRepository;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private FactureRepository factureRepository;
	
	private BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
	
	@GetMapping("/viewUser")
    public String viewAdminFactPage(Model model) {
		List<User> users = userRepository.findByRole("USER");
		model.addAttribute("users", users);
        return "admin/viewUser";
    }
    
    @GetMapping("/transaction")
    public String viewAdminTransactionPage(Model model) {
    	List<Facture> factures = factureRepository.findAll();
    	//System.out.println("transaction: "+factures.get(factures.size()-1));
    	model.addAttribute("factures", factures);
        return "admin/admin_transaction";
    }
    
    @GetMapping("/product")
    public String viewAdminProductPage(Model model) {
    	List<Produit> produits = productRepository.findAll();
    	model.addAttribute("produits", produits);
        return "admin/admin_product";
    }
    
    @GetMapping("/product/{id}")
    public String viewAdminProductPage(@PathVariable(name="id")Long id, Model model) {
    	Optional<Produit> produit = productRepository.findById(id);
    	model.addAttribute("produit", produit.get());
    	return "admin/updateProduit";
    }
    
    @GetMapping("/AddProduct")
    public String viewAdminAjoutProductPage(Model model) {
    	Produit produit = new Produit();
    	model.addAttribute("produit",produit);
        return "admin/addProduit";
    }
    
    @GetMapping("/createUser")
    public String viewAddUserAdminPage(Model model) {
    	User user = new User();
    	model.addAttribute("user",user);
        return "admin/createUser";
    }
    
    @PostMapping("/product_img/{id}")
    public String updateAdminProductPage(@PathVariable(name="id")Long id, @RequestParam("fileToUpload") MultipartFile file) {
		Path fileNameAndPath =Paths.get(uploadDirectory,file.getOriginalFilename());
		try {
			Files.write(fileNameAndPath, file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		Optional<Produit> product = productRepository.findById(id);
		product.get().setImg_url("/img/product/"+file.getOriginalFilename());
    	productRepository.save(product.get());
        return "redirect:/admin/product";
    }
    
    @PostMapping("/product_info/{id}")
    public String updateAdminProductInfo(@PathVariable(name="id")Long id, @ModelAttribute("product") Produit product) {
    	Optional<Produit> p = productRepository.findById(id);
    	p.get().setNom(product.getNom());
    	p.get().setPrix_unit_kg(product.getPrix_unit_kg());
    	p.get().setDescription(product.getDescription());
    	p.get().setType(product.getType());
    	productRepository.save(p.get());
        return "redirect:/admin/product";
    }
    
    @GetMapping("/user/{id}")
    public String updateUserInfo(@PathVariable(name="id")Long id, Model model) {
    	Optional<User> u = userRepository.findById(id);
    	model.addAttribute("user", u.get());
    	/*u.get().setUserName(user.getUserName());
    	u.get().setActive(true);
    	u.get().setPassword(bcrypt.encode(user.getPassword()));
    	u.get().setRole(user.getRole());
    	userRepository.save(u.get());*/
        return "/admin/updateUser";
    }
    @PostMapping("/user/{id}")
    public String updateUserInfoaction(@PathVariable(name="id")Long id, @ModelAttribute("user") User user) {
    	Optional<User> u = userRepository.findById(id);
    	u.get().setUserName(user.getUserName());
    	u.get().setActive(true);
    	u.get().setPassword(bcrypt.encode(user.getPassword()));
    	u.get().setRole(user.getRole());
    	userRepository.save(u.get());
        return "redirect:/admin/viewUser";
    }
    
    @GetMapping("/product/delete/{id}")
    public String deleteAdminProductPage(@PathVariable(name="id")Long id) {
    	Optional<Produit> p = productRepository.findById(id);
    	productRepository.delete(p.get());
        return "/admin/product";
    }
    
    @GetMapping("/user/delete/{id}")
    public String deleteUserAdminPage(@PathVariable(name="id")Long id) {
    	Optional<User> u = userRepository.findById(id);
    	userRepository.delete(u.get());
        return "/admin/viewUser";
    }
    
    @GetMapping("/user_status/{id}")
    public String updateUserStatusPage(@PathVariable(name="id")Long id) {
    	Optional<User> u = userRepository.findById(id);
    	u.get().setActive(!u.get().getActive());
    	userRepository.save(u.get());
        return "/admin/viewUser";
    }
    
    public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/img/product";
    @PostMapping("/AddProduct")
    public String postAdminAjoutProductPage(@ModelAttribute("produit") Produit produit, @RequestParam("fileToUpload") MultipartFile file) {
		Path fileNameAndPath =Paths.get(uploadDirectory,file.getOriginalFilename());
		try {
			Files.write(fileNameAndPath, file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
		produit.setImg_url("/img/product/"+file.getOriginalFilename());
    	System.out.println(produit);
    	productRepository.save(produit);
        return "redirect:/admin/product";
    }
    
    @PostMapping("/AddUser")
    public String addUserPage(@ModelAttribute("user") User user) {
    	
		user.setActive(true);
		user.setPassword(bcrypt.encode(user.getPassword()));
    	System.out.println(user);
    	userRepository.save(user);
        return "redirect:/admin/viewUser";
    }
    
    @GetMapping("/home")
    public String viewAdminHomePage(Model model, Principal principal) {
        return "admin/admin_home";
    }
    

}
