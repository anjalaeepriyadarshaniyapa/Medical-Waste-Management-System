package MedicalWasteManagementSystem.FileHandlers;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.scene.control.Alert;
import MedicalWasteManagementSystem.Database.DatabaseHandler;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by v.Kodithuwakku on 9/18/2021.
 * PDF Saver Class
 */
public class PDFSaver {
    //Generate Table from DB and Save PDF File
    public void SaveFile(String tableName, String subjectName , String filename,DatabaseHandler databaseHandler){
        Document document = new Document();
        ResultSet resultSet = databaseHandler.populateTableData(tableName);

        try {
            //Create Table to Save
            PdfWriter.getInstance(document,new FileOutputStream(filename));
            document.open();
            PdfPTable table = new PdfPTable(5);
            PdfPCell headingCell1 = new PdfPCell(new Phrase("ID"));
            table.addCell(headingCell1);
            PdfPCell headingCell2 = new PdfPCell(new Phrase("Type of Waste"));
            table.addCell(headingCell2);
            PdfPCell headingCell3 = new PdfPCell(new Phrase("Object Type"));
            table.addCell(headingCell3);
            PdfPCell headingCell4 = new PdfPCell(new Phrase("Type of Container"));
            table.addCell(headingCell4);
            PdfPCell headingCell5 = new PdfPCell(new Phrase("Waste Per Day"));
            table.addCell(headingCell5);
            table.setHeaderRows(1);
            Paragraph title = new Paragraph(subjectName);
            Paragraph gap = new Paragraph(" ");
            while(resultSet.next()){
                table.addCell(resultSet.getString("id"));
                table.addCell(resultSet.getString("type_of_waste"));
                table.addCell(resultSet.getString("object"));
                table.addCell(resultSet.getString("type_of_container"));
                table.addCell(resultSet.getString("wasteperday"));
            }
            //Add table to PDF for saving
            document.add(title);
            document.add(gap);
            document.add(table);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        document.addAuthor("Team NO 12");
        document.addCreator("Team NO 12");
        document.addTitle(subjectName);
        document.addSubject(subjectName);
        document.close();
        //Success Alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("File Saved Successfully.!");
        alert.setContentText("You can find the Generated PDF file in 'GeneratedPDF' folder in the project directory ");
        alert.showAndWait();
    }
}
