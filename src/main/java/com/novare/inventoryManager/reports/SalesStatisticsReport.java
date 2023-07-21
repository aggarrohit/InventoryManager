package com.novare.inventoryManager.reports;

import com.novare.inventoryManager.data.order.SalesStatistics;
import com.novare.inventoryManager.utils.ConsoleMessage;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class SalesStatisticsReport {
    private SalesStatisticsReport() {}

    public static void generate(List<SalesStatistics> salesStatistics) {
        String filePath = "data/reports/report.pdf";
        Document document = new Document(PageSize.A4);
        List<String> columns = List.of("N", "Item name", "Quantity Sold", "Measurement", "Average Price", "Threshold Quantity");

        try{
            Path path = Paths.get(filePath);
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            }

            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            // Report title
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph title = new Paragraph("Sales Report", titleFont);

            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingBefore(10);
            title.setSpacingAfter(10);

            document.add(title);

            // Table
            PdfPTable table = new PdfPTable(columns.size());
            table.setWidthPercentage(100);
            table.setWidths(new int[]{1, 4, 3, 3, 3, 3});

            for(String columnName : columns) {
                Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
                PdfPCell cell = new PdfPCell(new Phrase(columnName, boldFont));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
            }

            for(int i = 0; i < salesStatistics.size(); i++) {
                SalesStatistics productStatistics = salesStatistics.get(i);

                table.addCell(Integer.toString(i+1));
                table.addCell(productStatistics.product().product_name());
                table.addCell(String.valueOf(productStatistics.totalQuantitySold()));
                table.addCell(productStatistics.product().measurement().getMeasurement());
                table.addCell(String.valueOf(productStatistics.averagePrice()));
                table.addCell(String.valueOf(productStatistics.product().threshold_qty()));

            }

            document.add(table);
            document.close();

            ConsoleMessage.showSuccessMessage("PDF report generated successfully!");
        }
        catch(DocumentException e){
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            ConsoleMessage.showErrorMessage("File is not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
