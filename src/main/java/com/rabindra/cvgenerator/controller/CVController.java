package com.rabindra.cvgenerator.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.http.HttpHeaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.rabindra.cvgenerator.entity.CVGenerator;
import com.rabindra.cvgenerator.repo.CVRepository;
import com.rabindra.cvgenerator.service.CVService;
//import com.rabindra.dashboardapi.model.Product;

@RestController
public class CVController {
	
	@Autowired
	private CVRepository cvRespository ;
	
	private CVService cvService;
	
	@Autowired
	public CVController(CVService cvservice) {
		this.cvService=cvservice;
	}

	@PostMapping
	  public ResponseEntity<byte[]> generateCV(@RequestBody CVGenerator form) {
	    CVGenerator cv = parseFormData(form);
	    byte[] cvBytes = generateCVBytes(cv, form.getFileType());
	    return ResponseEntity.ok()
	        .contentType(getContentType(form.getFileType()))
	        .body(cvBytes);
	  }
	  
	  private CVGenerator parseFormData(CVGenerator form) {
		  CVGenerator cv = new CVGenerator();
		    cv.setName(form.getName());
		    cv.setAddress(form.getAddress());
		    cv.setContact(form.getContact());
		    cv.setEmail(form.getEmail());
		    cv.setObjectives(form.getObjectives());
		    cv.setSkills(form.getSkills());
		    cv.setSkills(form.getSkills());
		    
		    return cv;	  }
	  
	  private byte[] generateCVBytes(CVGenerator cv, String fileType) {
		    if (!fileType.equalsIgnoreCase("pdf")) {
		        throw new IllegalArgumentException("Only PDF format is supported");
		    }
		    ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    Document document = new Document();
		    try {
		        PdfWriter.getInstance(document, baos);
		        document.open();

		        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA, 16, BaseColor.BLACK);
		        Paragraph header = new Paragraph(cv.getName(), headerFont);
		        header.setAlignment(Element.ALIGN_CENTER);
		        document.add(header);

		        Font subHeaderFont = FontFactory.getFont(FontFactory.HELVETICA, 14, BaseColor.GRAY);
		        Paragraph contact = new Paragraph(cv.getContact(), subHeaderFont);
		        contact.setAlignment(Element.ALIGN_CENTER);
		        document.add(contact);

		        Paragraph email = new Paragraph(cv.getEmail(), subHeaderFont);
		        email.setAlignment(Element.ALIGN_CENTER);
		        document.add(email);

		        Paragraph objectives = new Paragraph("Objectives", subHeaderFont);
		        objectives.setSpacingBefore(20);
		        objectives.setSpacingAfter(10);
		        document.add(objectives);

		        Font bodyFont = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);
		        for (String objective : cv.getObjectives()) {
		            Paragraph obj = new Paragraph("- " + objective, bodyFont);
		            obj.setSpacingAfter(5);
		            document.add(obj);
		        }

		        Paragraph skills = new Paragraph("Skills", subHeaderFont);
		        skills.setSpacingBefore(20);
		        skills.setSpacingAfter(10);
		        document.add(skills);

		        for (String skill : cv.getSkills()) {
		            Paragraph skl = new Paragraph("- " + skill, bodyFont);
		            skl.setSpacingAfter(5);
		            document.add(skl);
		        }

		        Paragraph projects = new Paragraph("Projects", subHeaderFont);
		        projects.setSpacingBefore(20);
		        projects.setSpacingAfter(10);
		        document.add(projects);

		        for (String project : cv.getSkills()) {
		            Paragraph proj = new Paragraph("- " + project, bodyFont);
		            proj.setSpacingAfter(5);
		            document.add(proj);
		        }

		        document.close();
		    } catch (DocumentException e) {
		        e.printStackTrace();
		    }
		    return baos.toByteArray();
		}

	  
	  private MediaType getContentType(String fileType) {
		  if (fileType.equalsIgnoreCase("pdf")) {
		        return MediaType.APPLICATION_PDF;
		    } else if (fileType.equalsIgnoreCase("doc")) {
		        return MediaType.APPLICATION_OCTET_STREAM;
		    } else if (fileType.equalsIgnoreCase("docx")) {
		        return MediaType.APPLICATION_OCTET_STREAM;
		    } else {
		        throw new IllegalArgumentException("Unsupported file type: " + fileType);
		    }	 
		  }
}

