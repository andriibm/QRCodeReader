package org.abez.qrreader.app;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import org.abez.qrreader.decoder.Decoder;
import org.abez.qrreader.decoder.XzingDecoder;
import org.abez.qrreader.properties.PropertiesFile;
import org.abez.qrreader.properties.PropertiesSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class QRCReader {
    public static void main(String[] args) throws IOException {
        PropertiesSource props = new PropertiesFile();
        boolean readeBarcode = Boolean.parseBoolean(props.getProperty("barcode"));

        System.out.println("Specify path to a QR-Code image file: ");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filePath = reader.readLine();

        Decoder decoder = new XzingDecoder();
        decoder.setReadBarcode(readeBarcode);

        decoder.setReadBarcode(readeBarcode);

        try {
            String text = decoder.readText(filePath);
            System.out.println("Decoded text is: " + text);
        } catch (FormatException | ChecksumException | NotFoundException e) {
            System.out.println("Unable to decode:");
            e.printStackTrace();
        }
    }
}
