package com.osmanager.service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.osmanager.entity.OrdemServico;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class PdfService {

    public void gerarPdfOs(OrdemServico os, HttpServletResponse response) throws IOException {

        Document document = new Document(PageSize.A4);


        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();


        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
        Font fontSubtitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
        Font fontNormal = FontFactory.getFont(FontFactory.HELVETICA, 12);


        document.add(new Paragraph("Ordem de Servico #" + os.getId(), fontTitle));
        document.add(new Paragraph("--------------------------------------------------"));
        document.add(new Paragraph(" "));


        document.add(new Paragraph("Dados do Servico", fontSubtitle));
        document.add(new Paragraph("Cliente: " + (os.getCliente() != null ? os.getCliente().getNome() : "Não informado"), fontNormal));
        document.add(new Paragraph("Equipamento: " + (os.getEquipamento() != null ? os.getEquipamento().getNome() + " (" + os.getEquipamento().getModelo() + ")" : "Não informado"), fontNormal));
        document.add(new Paragraph("Status: " + (os.getStatus() != null ? os.getStatus() : "Pendente"), fontNormal));
        document.add(new Paragraph("Valor: R$ " + (os.getValor() != null ? String.format("%.2f", os.getValor()) : "0.00"), fontNormal));

        document.add(new Paragraph(" "));
        document.add(new Paragraph("Detalhes Tecnicos", fontSubtitle));


        String observacoes = (os.getObservacoes() != null && !os.getObservacoes().isEmpty()) ? os.getObservacoes() : "Nenhuma observacao relatada.";
        String laudo = (os.getLaudoTecnico() != null && !os.getLaudoTecnico().isEmpty()) ? os.getLaudoTecnico() : "Aguardando diagnostico.";

        document.add(new Paragraph("Observacoes: " + observacoes, fontNormal));
        document.add(new Paragraph("Laudo Tecnico: " + laudo, fontNormal));

        document.add(new Paragraph(" "));
        document.add(new Paragraph("Data de emissao: " + java.time.LocalDate.now(), fontNormal));

        document.close();
    }
}