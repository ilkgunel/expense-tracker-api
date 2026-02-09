package com.ilkaygunel.service;

import com.ilkaygunel.dto.ExpenseOutputDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PDFService {

    private final TemplateEngine templateEngine;

    public byte[] generateExpenseReport(List<ExpenseOutputDto> expenses) {

        Context context = new Context();
        context.setVariable("expenses", expenses);

        String htmlContent = templateEngine.process("expense-report", context);
        try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(outputStream);
            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error Generating PDF", e);
        }

    }

}
