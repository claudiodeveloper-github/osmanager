
package com.osmanager.util;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import java.io.FileOutputStream;

public class PdfTeste {

    public static void main(String[] args) {

        try {

            Document document = new Document();

            PdfWriter.getInstance(
                    document,
                    new FileOutputStream("teste.pdf")
            );

            document.open();

            document.add(new Paragraph("PDF funcionando"));

            document.close();

            System.out.println("PDF criado!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}