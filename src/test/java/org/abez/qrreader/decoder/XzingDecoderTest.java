package org.abez.qrreader.decoder;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class XzingDecoderTest {
    private static final String TEST_RESOURCES_ROOT = "src\\test\\resources\\";

    @Test
    public void readText() throws FormatException, ChecksumException, NotFoundException {
        Decoder decoder = new XzingDecoder();
        String text = decoder.readText(TEST_RESOURCES_ROOT + "qr_code_test.png");

        Assert.assertEquals("Hello :)", text);
    }
}