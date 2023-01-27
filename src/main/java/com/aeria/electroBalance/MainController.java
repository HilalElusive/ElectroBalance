package com.aeria.electroBalance;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.sql.Date;
import java.sql.Time;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aeria.electroBalance.entity.Facture;
import com.aeria.electroBalance.entity.Produit;
import com.aeria.electroBalance.entity.TypeProduct;
import com.aeria.electroBalance.entity.User;
import com.aeria.electroBalance.entity.urlObj;
import com.aeria.electroBalance.repo.FactureRepository;
import com.aeria.electroBalance.repo.ProductRepository;
import com.aeria.electroBalance.repo.UserRepository;

@Controller
public class MainController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private FactureRepository factureRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	private urlObj currentObj = new urlObj();

	@GetMapping("")
    public String viewHomePage() {
        return "index";
    }
	
	@GetMapping("/fragments/selectPopularFacture")
    public String viewFacturePopularPage(Model model) {
		List<Produit> produits = new ArrayList<Produit>();
		
		BigInteger b1;
		try {
			b1 = (BigInteger) factureRepository.findLastFacture().get(0)[0];
		} catch (NullPointerException e) {
			b1 = BigInteger.ZERO;
		}
		Facture f = factureRepository.getById(b1.longValue());
		System.out.println("next val: "+(f.getId()+1));
		
		int count = 0;
		int size = factureRepository.findPopularProductId().size();
		System.out.println("size = " + size);
		BigInteger b;
		while(count < size && count < 10) {
			b = (BigInteger) factureRepository.findPopularProductId().get(count)[0];
			produits.add(productRepository.findById(b.longValue()));
			count++;
			
		}
    	model.addAttribute("produits", produits);
    	model.addAttribute("factId", f.getId()+1);
        return "/fragments/selectFactureProduct";
    }
	
	@GetMapping("/fragments/remplacePopularFruit")
    public String viewPopularFruitPage(Model model) {
		List<Produit> produits = new ArrayList<Produit>();
		Produit p;
		BigInteger b1;
		try {
			b1 = (BigInteger) factureRepository.findLastFacture().get(0)[0];
		} catch (NullPointerException e) {
			b1 = BigInteger.ZERO;
		}
		Facture f = factureRepository.getById(b1.longValue());
		
		int count = 0;
		int size = factureRepository.findPopularProductId().size();
		BigInteger b;
		while(count < size && count < 10) {
			b = (BigInteger) factureRepository.findPopularProductId().get(count)[0];
			p = productRepository.findById(b.longValue());
			if(p.getType() == TypeProduct.FRUIT) {
				produits.add(p);
			}
			count++;
			
		}
    	model.addAttribute("produits", produits);
    	model.addAttribute("factId", f.getId()+1);
        return "/fragments/selectFactureProduct";
    }
	
	@GetMapping("/fragments/remplacePopularLegume")
    public String viewPopularLegumePage(Model model) {
		List<Produit> produits = new ArrayList<Produit>();
		Produit p;
		BigInteger b1;
		try {
			b1 = (BigInteger) factureRepository.findLastFacture().get(0)[0];
		} catch (NullPointerException e) {
			b1 = BigInteger.ZERO;
		}
		Facture f = factureRepository.getById(b1.longValue());
		
		int count = 0;
		int size = factureRepository.findPopularProductId().size();
		BigInteger b;
		while(count < size && count < 10) {
			b = (BigInteger) factureRepository.findPopularProductId().get(count)[0];
			p = productRepository.findById(b.longValue());
			if(p.getType() == TypeProduct.LEGUME) {
				produits.add(p);
			}
			count++;
			
		}
    	model.addAttribute("produits", produits);
    	model.addAttribute("factId", f.getId()+1);
        return "/fragments/selectFactureProduct";
    }
	
	@GetMapping("/fragments/remplacePopularVrac")
    public String viewPopularVracPage(Model model) {
		List<Produit> produits = new ArrayList<Produit>();
		Produit p;
		BigInteger b1;
		try {
			b1 = (BigInteger) factureRepository.findLastFacture().get(0)[0];
		} catch (NullPointerException e) {
			b1 = BigInteger.ZERO;
		}
		Facture f = factureRepository.getById(b1.longValue());
		
		int count = 0;
		int size = factureRepository.findPopularProductId().size();
		BigInteger b;
		while(count < size && count < 10) {
			b = (BigInteger) factureRepository.findPopularProductId().get(count)[0];
			p = productRepository.findById(b.longValue());
			if(p.getType() == TypeProduct.VRAC) {
				produits.add(p);
			}
			count++;
			
		}
    	model.addAttribute("produits", produits);
    	model.addAttribute("factId", f.getId()+1);
        return "/fragments/selectFactureProduct";
    }
	
	@GetMapping("/fragments/remplaceAll")
    public String viewAllPage(Model model) {
		List<Produit> produits = productRepository.findAll();
		
		BigInteger b1;
		try {
			b1 = (BigInteger) factureRepository.findLastFacture().get(0)[0];
		} catch (NullPointerException e) {
			b1 = BigInteger.ZERO;
		}
		Facture f = factureRepository.getById(b1.longValue());
		System.out.println("next val: "+(f.getId()+1));
		
    	model.addAttribute("produits", produits);
    	model.addAttribute("factId", f.getId()+1);
        return "/fragments/selectFactureProduct";
    }
	
	@GetMapping("/fragments/remplaceAllFruit")
    public String viewAllFruitPage(Model model) {
		List<Produit> produits = productRepository.findByType(TypeProduct.FRUIT);
		BigInteger b1;
		try {
			b1 = (BigInteger) factureRepository.findLastFacture().get(0)[0];
		} catch (NullPointerException e) {
			b1 = BigInteger.ZERO;
		}
		Facture f = factureRepository.getById(b1.longValue());
		
    	model.addAttribute("produits", produits);
    	model.addAttribute("factId", f.getId()+1);
        return "/fragments/selectFactureProduct";
    }
	
	@GetMapping("/fragments/remplaceAllLegume")
    public String viewAllLegumePage(Model model) {
		List<Produit> produits = productRepository.findByType(TypeProduct.LEGUME);
		BigInteger b1;
		try {
			b1 = (BigInteger) factureRepository.findLastFacture().get(0)[0];
		} catch (NullPointerException e) {
			b1 = BigInteger.ZERO;
		}
		Facture f = factureRepository.getById(b1.longValue());
		
    	model.addAttribute("produits", produits);
    	model.addAttribute("factId", f.getId()+1);
        return "/fragments/selectFactureProduct";
    }
	
	@GetMapping("/fragments/remplaceAllVrac")
    public String viewAllVracPage(Model model) {
		List<Produit> produits = productRepository.findByType(TypeProduct.VRAC);
		BigInteger b1;
		try {
			b1 = (BigInteger) factureRepository.findLastFacture().get(0)[0];
		} catch (NullPointerException e) {
			b1 = BigInteger.ZERO;
		}
		Facture f = factureRepository.getById(b1.longValue());
		
    	model.addAttribute("produits", produits);
    	model.addAttribute("factId", f.getId()+1);
        return "/fragments/selectFactureProduct";
    }
	
	@GetMapping("/fragments/selectFruit")
    public String viewFruitPage(Model model) {
		List<Produit> produits = productRepository.findByType(TypeProduct.FRUIT);
    	model.addAttribute("produits", produits);
        return "/fragments/selectProduct";
    }
	
	@GetMapping("/fragments/selectLegume")
    public String viewLegumePage(Model model) {
		List<Produit> produits = productRepository.findByType(TypeProduct.LEGUME);
    	model.addAttribute("produits", produits);
        return "/fragments/selectProduct";
    }
	@GetMapping("/fragments/selectVrac")
    public String viewVracPage(Model model) {
		List<Produit> produits = productRepository.findByType(TypeProduct.VRAC);
    	model.addAttribute("produits", produits);
        return "/fragments/selectProduct";
    }
     
    @GetMapping("/admin/login")
    public String viewAdminLoginPage() {
        return "admin/admin_login";
    }
    @GetMapping("/user/login")
    public String viewUserLoginPage() {
        return "user/user_login";
    }
    @GetMapping("/facturation")
    public String InvoicePage(Model model, HttpSession session) {
    	Produit p = productRepository.findById(currentObj.getIdProd());
    	DecimalFormat df = new DecimalFormat("#.##");
    	model.addAttribute("poids", currentObj.getPoidNet());
    	model.addAttribute("prixKg", p.getPrix_unit_kg());
    	model.addAttribute("total", df.format(currentObj.getPoidNet()*p.getPrix_unit_kg()));
    	model.addAttribute("base64", (String)session.getAttribute("base64"));
    	//System.out.println("base69: "+session.getAttribute("base64"));
        return "user/FactureTransaction";
    }
    
    @PostMapping("/export")
    @ResponseBody
    public void exportToPDF(@RequestBody urlObj url, HttpSession session) {
    	String username = (String) session.getAttribute("name");
    	
    	Facture facture = new Facture();
    	factureRepository.save(facture);
    	
    	this.currentObj = url;
    	String base64Image = url.getUrl().split(",")[1];
    	byte[] imageBytes = DatatypeConverter.parseBase64Binary(base64Image);
    	
    	String path = "C:\\Users\\EmH\\resources\\qrCode\\qr_"+String.valueOf(facture.getId())+".png";
        File file = new File(path);
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            outputStream.write(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(currentObj);
        Produit p = productRepository.findById(currentObj.getIdProd());
        User u =  userRepository.findByUserName(username);
        
        long now = System.currentTimeMillis();
        facture.setDate(new Date(now));
        facture.setTime(new Time(now));
        facture.setMontant((float) (Math.round(currentObj.getPoidNet()*p.getPrix_unit_kg()* 100.0) / 100.0));
        facture.setPoidsNet(currentObj.getPoidNet());
        facture.setProduit(p);
        facture.setUser(u);
        facture .setFactureURL("resources/facture/facture"+facture.getId()+".pdf");
        factureRepository.save(facture);
        
        if(session.getAttribute("base64") != null) {
        	session.removeAttribute("base64");
        } 
    }
    

	@GetMapping("/export/pdf/{id}")
	@ResponseBody
    public urlObj exportSpecificPDF(@PathVariable(name="id")Long id, HttpSession session) throws IOException {
		String base = "C:/Users/EmH/resources/";
		String path = base+"facture/facture"+ id +".pdf" ;
		
		File file = new File(path);
        byte [] bytes = Files.readAllBytes(file.toPath());
        String b64 = Base64.getEncoder().encodeToString(bytes);
        
        urlObj pdfObj = new urlObj(b64, 0, 0, 0);
        
        return pdfObj;
	}

}
