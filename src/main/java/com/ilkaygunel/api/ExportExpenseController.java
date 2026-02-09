package com.ilkaygunel.api;

import com.ilkaygunel.dto.ExpenseOutputDto;
import com.ilkaygunel.service.ExpenseService;
import com.ilkaygunel.service.PDFService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/export")
@RequiredArgsConstructor
@Tag(name = "Export Management", description = "Services for exporting expenses in desired format.")
public class ExportExpenseController {

    private final ExpenseService expenseService;
    private final PDFService pdfService;

    @GetMapping("/expense/pdf")
    public ResponseEntity<byte[]> exportExpensesInPDFFormat(Principal principal) {
        String userEmail = principal.getName();
        List<ExpenseOutputDto> expenseList = expenseService.getExpensesOfCurrentUser(userEmail);
        byte[] exportedPDFAsByteArray = pdfService.generateExpenseReport(expenseList);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=expenses.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(exportedPDFAsByteArray);
    }
}
