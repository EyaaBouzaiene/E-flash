/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import tn.esprit.utils.MyDB;

/**
 *
 * @author Khadija
 */
public class PDF {
       
      Connection connexion;
            
              PreparedStatement stm;

          
    

    public void add(String file,String N1,String N2,String N3,String N4,String N5,String N6,String N7) throws FileNotFoundException, SQLException, DocumentException{
        
                
        
                Document my_pdf_report = new Document(PageSize.A5.rotate());
                PdfWriter.getInstance(my_pdf_report, new FileOutputStream(file));
                my_pdf_report.open();            
                //we have four columns in our table
                PdfPTable my_report_table = new PdfPTable(2);
                //create a cell object
                PdfPCell table_cell;
     
                                table_cell=new PdfPCell(new Phrase("nomPartenaireS"));
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase(N1));
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase("nomS"));
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase(N2));
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase("refS"));
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase(N3));
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase("categorieS"));
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase(N4));
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase("qteS"));
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase(N5));
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase("dateS"));
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase(N6));
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase("qualiteS"));
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase(N7));
                                my_report_table.addCell(table_cell);
                                my_pdf_report.add(my_report_table);                       
                my_pdf_report.close();
                                
    }
        
      
            
       
            
        }


    
    


