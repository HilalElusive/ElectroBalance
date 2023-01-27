package com.aeria.electroBalance;

import java.awt.Color;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aeria.electroBalance.entity.Facture;
import com.aeria.electroBalance.entity.Produit;
import com.aeria.electroBalance.entity.User;
import com.aeria.electroBalance.entity.urlObj;
import com.aeria.electroBalance.repo.FactureRepository;
import com.aeria.electroBalance.repo.ProductRepository;
import com.aeria.electroBalance.repo.UserRepository;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.LineSeparator;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private FactureRepository factureRepository;
	
	private BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
	
	@GetMapping("/NvFacture")
    public String viewNvFacturePage(HttpSession session, Principal principal) {
		session.setAttribute("name", principal.getName());
		//BigInteger b = (BigInteger) factureRepository.findPopularProductId().get(1)[0];
		//System.out.println(productRepository.findById(b.longValue()));
        return "user/NvFacture";
    }
	@GetMapping("/transaction")
    public String viewUserTransactionPage(Model model, Principal principal) {
		User user = userRepository.findByUserName(principal.getName());
		System.out.println(user);
    	List<Facture> factures = factureRepository.findByUser(user);
    	model.addAttribute("factures", factures);
        return "user/user_transaction";
    }
	@GetMapping("/product")
    public String viewUserProductPage(Model model) {
    	List<Produit> produits = productRepository.findAll();
    	model.addAttribute("produits", produits);
        return "user/user_product";
    }
	
	public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/img";
	@GetMapping("/export/pdf")
    public void exportToPDF(HttpServletResponse response, HttpSession session) throws DocumentException, IOException {
		BigInteger b;
		b = (BigInteger) factureRepository.findLastFacture().get(0)[0];
		String base = "C:/Users/EmH/resources/";
		Facture f = factureRepository.getById(b.longValue());
		
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=tmp" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        
        Document document = new Document(new Rectangle(198.425f,141.732f));
        String path = base+"facture/facture"+ f.getId() +".pdf" ;
        PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream(path));
         
        document.open();
        Image logo = Image.getInstance(uploadDirectory + "/stage_logo.jpg");
        logo.scalePercent(6.0f);
        logo.setAbsolutePosition(7.0f, 115.0f);
        
        LineSeparator ln = new LineSeparator(0.5f, 100.0f, Color.BLACK, 0, -13.0f);
        
        Font font = new Font(Font.HELVETICA, Font.DEFAULTSIZE, Font.BOLDITALIC);
        Font font2 = new Font(Font.STRIKETHRU, 8, Font.BOLD);
        Font font3 = new Font(Font.COURIER, 7, Font.NORMAL);
        Font font4 = new Font(Font.STRIKETHRU, 9, Font.BOLD);
        Font font5 = new Font(Font.STRIKETHRU, 14, Font.BOLD);
        Font font3_mini = new Font(Font.COURIER, 6, Font.NORMAL);
        
        PdfContentByte canvas = writer.getDirectContent();
        ColumnText ct = new ColumnText(canvas);
        ct.setSimpleColumn(185.f, 125.0f, 10.0f, 8.0f);
        ct.addElement(new Paragraph("\t\t\t"+f.getProduit().getNom(), font));
        ct.addElement(ln);
        ct.go();
        
        ColumnText ct2 = new ColumnText(canvas);
        ct2.setSimpleColumn(150.0f, 97.0f, 10.0f, 16.0f);
        ct2.addElement(new Paragraph("\t\t\t\t"+f.getId()+"\t\t"+"Emballe le :"+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+f.getDate().toString(), font2));
        ct2.go();
        
        ColumnText ct3 = new ColumnText(canvas);
        ct3.setSimpleColumn(185.f, 70.0f, 80.0f, 30.0f);
        ct3.addElement(new Paragraph("\t\t\t"+"dhs/Kg"+"\t\t\t\t\t\t\t"+"Poids net", font3));
        ct3.go();
        
        ColumnText ct4 = new ColumnText(canvas);
        ct4.setSimpleColumn(185.f, 62.0f, 80.0f, 30.0f);
        ct4.addElement(new Paragraph("\t\t\t\t\t\t"+f.getProduit().getPrix_unit_kg()+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+f.getPoidsNet(), font4));
        ct4.go();
        
        ColumnText ct5 = new ColumnText(canvas);
        ct5.setSimpleColumn(185.f, 45.0f, 150.0f, 30.0f);
        ct5.addElement(new Paragraph("P R I X", font3_mini));
        ct5.go();
        
        ColumnText ct6 = new ColumnText(canvas);
        ct6.setSimpleColumn(180.0f, 42.0f, 130.0f, 20.0f);
        ct6.addElement(new Paragraph(f.getMontant()+"d", font5));
        ct6.go();
        
        System.out.println("default font size: "+Font.DEFAULTSIZE);
        
        Image i = Image.getInstance(base+"qrCode/qr_"+f.getId() +".png");
        i.scalePercent(35.0f);
        i.setAbsolutePosition(5.5f, 5.5f);
        System.out.println("widht: "+i.getWidth()+ " height: "+i.getHeight()+" allo: "+document.getPageSize().getWidth()/2);
        document.add(logo);
        document.add(i);
         
        
        document.close();
        
        File file = new File(path);
        byte [] bytes = Files.readAllBytes(file.toPath());

        String b64 = Base64.getEncoder().encodeToString(bytes);
        System.out.println("fact id: "+f.getId());
        
        session.setAttribute("base64", b64);
        
    }
	
}
