package data;


import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

public class ReadingPDF {

public static void main(String[] args) {


try {
PDDocument document = PDDocument.load(new File("C:\\Users\\91784\\Downloads\\Documents\\RELIANCE.NS.pdf"));

document.getClass();

            if (!document.isEncrypted()) {

                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);

                PDFTextStripper tStripper = new PDFTextStripper();

                String pdfFileInText = tStripper.getText(document);
               
             
                String lines[] = pdfFileInText.split("\\r?\\n");
               
                BigDecimal totVol = BigDecimal.valueOf(0);
                BigDecimal closeVol = BigDecimal.valueOf(0);
               
                int days = 0;
               
               
                for (int  i=1;i <lines.length;i++) {
               
                String[] stringArray = lines[i].split(" ");
               
                String close = stringArray[4];
                String vol = stringArray[6];
               
                if(vol == null || vol.equals("null")) continue;
               
                BigDecimal convertedVol = new BigDecimal(vol);
                totVol = totVol.add(convertedVol);
               
                BigDecimal convertedClose = new BigDecimal(close);
                BigDecimal multiplied = convertedClose.multiply(convertedVol);
               
                closeVol = closeVol.add(multiplied);
                days++;
               
                }
               
                BigDecimal convertedDays  = new BigDecimal(days);
               
                BigDecimal totalCloseVol = convertedDays.multiply(closeVol);
               
                BigDecimal VWMA = totalCloseVol.divide(totVol,2,RoundingMode.HALF_UP);
               
                System.out.println("Volume Weighted Moving Average is " +VWMA);
            }
}catch(Exception e) {
e.printStackTrace();
}
}

}


