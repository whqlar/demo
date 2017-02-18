package com.dianping.study.biz.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;

/**
 * 二维码生成工具类
 * Created by wu on 17/2/14.
 */
public class QRCodeUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(QRCodeUtil.class);

    private static final int DEFAULT_HEIGHT = 250;

    private static final int DEFAULT_WIDTH = 250;

    private static final MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

    public static String genQRCode(String content) {
        return genQRCode(content, DEFAULT_HEIGHT, DEFAULT_WIDTH);
    }

    public static String genQRCode(String content, int height, int width) {
        try {
            HashMap e = new HashMap();
            e.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            e.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            e.put(EncodeHintType.MARGIN, Integer.valueOf(1));
            BitMatrix matrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, height, width, e);
            BufferedImage paramRenderedImage = MatrixToImageWriter.toBufferedImage(matrix);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(paramRenderedImage, "jpg", outputStream);
            byte[] imageByteArray = outputStream.toByteArray();
            String image = Base64.encodeBase64String(imageByteArray);
            return image;
        } catch (Exception e) {
            LOGGER.error("genQRCode error", e);
            return null;
        }
    }
}
