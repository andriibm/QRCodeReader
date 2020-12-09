package org.abez.qrreader.decoder;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;

public interface Decoder {
    String readText(String filePath) throws FormatException, ChecksumException, NotFoundException;
    void setReadBarcode(boolean isQRCodeOnly);
}
