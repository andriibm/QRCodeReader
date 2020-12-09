package org.abez.qrreader.decoder;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.List;

public class XzingDecoder implements Decoder {
    private final Reader reader = new MultiFormatReader();
    private boolean readBarcode = false;

    @Override
    public String readText(String filePath) throws FormatException, ChecksumException, NotFoundException {
        BinaryBitmap bitmap = getBitmapFromFile(filePath);
        Hashtable<DecodeHintType, Object> options = null;

        if (!readBarcode) {
            options = new Hashtable<>();
            options.put(DecodeHintType.POSSIBLE_FORMATS, List.of(BarcodeFormat.QR_CODE));
        }

        Result result = reader.decode(bitmap, options);
        return result.getText();
    }

    @Override
    public void setReadBarcode(boolean readBarcode) {
        this.readBarcode = readBarcode;
    }

    private BinaryBitmap getBitmapFromFile(String filePath) {
        BinaryBitmap bitmap = null;

        try (InputStream fileInputStream = new FileInputStream(filePath)) {
            BufferedImage bufferedImage = ImageIO.read(fileInputStream);
            LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
            bitmap = new BinaryBitmap(new HybridBinarizer(source));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }
}
