package com.rabindra.cvgenerator.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rabindra.cvgenerator.entity.CVGenerator;
//import com.rabindra.cvgenerator.repo.CVRepository;

@Service
public class CVService {
	
	public File generateCV(CVGenerator cvForm, String fileType) throws IOException {
	    XWPFDocument document = new XWPFDocument();
	    XWPFParagraph paragraph = document.createParagraph();
	    XWPFRun run = paragraph.createRun();
	    
	    paragraph.setAlignment(ParagraphAlignment.CENTER);
	    run.setText("Curriculum Vitae");
	    run.setBold(true);
	    run.setFontSize(24);
	    
	    paragraph = document.createParagraph();
	    paragraph.setSpacingAfter(200);
	    run = paragraph.createRun();
	    run.setText("Name: " + cvForm.getName());
	    
	    paragraph = document.createParagraph();
	    run = paragraph.createRun();
	    run.setText("Address: " + cvForm.getAddress());
	    
	    paragraph = document.createParagraph();
	    run = paragraph.createRun();
	    run.setText("Contact: " + cvForm.getContact());
	    
	    paragraph = document.createParagraph();
	    run = paragraph.createRun();
	    run.setText("Experience: " + cvForm.getExperience());
	    
	    paragraph = document.createParagraph();
	    run = paragraph.createRun();
	    run.setText("Objectives: " + cvForm.getObjectives());
	    
	    paragraph = document.createParagraph();
	    run = paragraph.createRun();
	    run.setText("Skills: " + cvForm.getSkills());
	    
	    File file = new File("cv.docx");
	    FileOutputStream fos = new FileOutputStream(file);
	    document.write(fos);
	    fos.close();
	    
	    return file;
	  }
}

