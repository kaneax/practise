package com.example.demo.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.core.io.ClassPathResource;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Hashtable;

/**
 * @Author: xiaozhu13539
 * @Date: 2021/3/3
 */
public class ImageCombine {

    private static final String CHARSET = "utf-8";

    private static final String FORMAT_NAME = "JPG";

    //二维码尺寸
    private static final int QRCODE_SIZE = 300;
    //LOGO宽度
    private static final int WIDTH = 60;
    //LOGO高度
    private static final int HEIGHT = 60;

    public static void main(String[] args) {
        ///Users/zhuqingbiao/Desktop/测试图片1.png
        String text = "www.baidu.com";  //这里设置自定义网站url
        String destPath = "/Users/zhuqingbiao/Desktop/测试图片1.png";
        String logoPath = "/Users/zhuqingbiao/Desktop";
        long starttime = System.currentTimeMillis();
        System.out.println("开始合成：" + starttime);
        try {
            //背景图
            //BufferedImage big = ImageIO.read(new File(backPicPath));
            BufferedImage big = ImageIO.read(new File(destPath));
            //二维码的图片
            //url扫二维码显示的内容
            BufferedImage small = execute(text);
            Graphics2D g = big.createGraphics();  //合成的图片

            //二维码或小图在大图的左上角坐标
            int x = big.getWidth() - small.getWidth()-45; //加是向右，减是向左
            int y = (big.getHeight() - small.getHeight()-50); //加是向下，减是向上
            //将二维码花在背景图上
            g.drawImage(small, x, y, small.getWidth(), small.getHeight(), null);
            //结束绘画
            g.dispose();
            //为了保证大图背景不变色，formatName必须为"png"
            ImageIO.write(big, "png", new FileOutputStream(logoPath+"111.jpg"));
            System.out.println("结束合成：" + (System.currentTimeMillis() - starttime) / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage execute(String content)throws Exception{
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
                BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE, hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000
                        : 0xFFFFFFFF);
            }
        }

        return image;
    }

    public static String getResult(){
        return null;
    }
}
