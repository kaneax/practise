package com.example.demo.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.commons.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Hashtable;
import java.util.Random;

/**
 * @Author: xiaozhu13539
 * @Date: 2021/2/20
 */
public class QRCodeUtils {

    private static final String CHARSET = "utf-8";

    private static final String FORMAT_NAME = "JPG";

    //二维码尺寸
    private static final int QRCODE_SIZE = 188;
    //LOGO宽度
    private static final int WIDTH = 10;
    //LOGO高度
    private static final int HEIGHT = 5;


    /**
     * 创建图片
     * @param content url 地址
     * @return
     * @throws Exception
     */
    private static BufferedImage createImage(String content,String imgPath)throws Exception{
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
        if (imgPath == null || "".equals(imgPath)) {
            return image;
        }
        // 插入二维码
        QRCodeUtils.insertQrCode(imgPath,image);

        return image;
    }

    /**
     * 生成二维码图片
     * @param content
     * @param imgPath
     * @param needCompress
     * @return
     */
    private static BufferedImage createImage(String content,String imgPath,boolean needCompress)throws Exception{
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
        if (imgPath == null || "".equals(imgPath)) {
            return image;
        }
        // 插入图片
        QRCodeUtils.insertImage(image, imgPath, needCompress);
        return image;
    }




    /**
     * 图片地址内嵌二维码
     * @param imgPath 图片地址
     * @param source  二维码
     */
    private static void insertQrCode(String imgPath,BufferedImage source)throws Exception{
        File file = new File(imgPath);
        if (!file.exists()) {
            System.err.println(""+imgPath+"   该文件不存在！");
            return;
        }
        BufferedImage src = ImageIO.read(new File(imgPath));
        int width = src.getWidth(null);
        int height = src.getHeight(null);

        // 插入LOGO
        Graphics2D graph = src.createGraphics();
        int x = (width - QRCODE_SIZE) / 2;
        int y = (height - QRCODE_SIZE) / 2;

        //调整x,y

        graph.drawImage(source, 413, 991, source.getWidth(), source.getHeight(), null);
        //Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        graph.setStroke(new BasicStroke(0.1f));
        //graph.draw(shape);
        //结束绘画
        graph.dispose();

        mkdirs("/Users/zhuqingbiao/Desktop");
        String file1 = new Random().nextInt(99999999)+".png";
        ImageIO.write(src, "png", new File("/Users/zhuqingbiao/Desktop"+"/"+file1));
    }

    private static String insertQrCode1(String imgPath,BufferedImage source)throws Exception{
        try {
            File file = new File(imgPath);
            if (!file.exists()) {
                System.err.println(""+imgPath+"   该文件不存在！");
                return "";
            }
            BufferedImage src = ImageIO.read(new File(imgPath));
            int width = src.getWidth(null);
            int height = src.getHeight(null);

            // 插入LOGO
            Graphics2D graph = src.createGraphics();
            //int x = (width - QRCODE_SIZE) / 2;
            //int y = (height - QRCODE_SIZE) / 2;

            //调整x,y

            graph.drawImage(source, 267, 960, source.getWidth(), source.getHeight(), null);
            //Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
            graph.setStroke(new BasicStroke(0.1f));
            //graph.draw(shape);
            //结束绘画
            graph.dispose();

            //为了保证大图背景不变色，formatName必须为"png"
            //字节数组流
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            //将图片流转换为字节数组流
            ImageOutputStream imOut = ImageIO.createImageOutputStream(bs);
            //将合成好的背景图输入到字节数组流中
            ImageIO.write(src, "png", imOut);
            //将字节数组流转换为二进制流
            InputStream in = new ByteArrayInputStream(bs.toByteArray());
            byte[] bytes = new byte[in.available()];
            //读取数组流中的数据
            in.read(bytes);
            //转换为base64数据类型
            String base64String = Base64.encodeBase64String(bytes);
            System.out.println(("data:image/png;base64,").concat(base64String));

            //in.close();
            return base64String;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //if (in !)
        }

        return null;
    }

    /**
     *
     * @param source 二维码图片
     * @param imgPath LOGO图片
     * @param needCompress 是否压缩
     */
    private static void insertImage(BufferedImage source,String imgPath,boolean needCompress)throws Exception{
        File file = new File(imgPath);
        if (!file.exists()) {
            System.err.println(""+imgPath+"   该文件不存在！");
            return;
        }
        Image src = ImageIO.read(new File(imgPath));
        int width = src.getWidth(null);
        int height = src.getHeight(null);
        if (needCompress) { // 压缩LOGO
            if (width > WIDTH) {
                width = WIDTH;
            }
            if (height > HEIGHT) {
                height = HEIGHT;
            }
            Image image = src.getScaledInstance(width, height,
                    Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图
            g.dispose();
            src = image;
        }
        // 插入LOGO
        Graphics2D graph = source.createGraphics();
        int x = (QRCODE_SIZE - width) / 2;
        int y = (QRCODE_SIZE - height) / 2;
        graph.drawImage(src, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
    }

    /**
     * 生成二维码(内嵌LOGO)
     *
     * @param content
     *            内容
     * @param imgPath
     *            LOGO地址
     * @param destPath
     *            存放目录
     * @param needCompress
     *            是否压缩LOGO
     * @throws Exception
     */
    public static String encode(String content, String imgPath, String destPath,
                                boolean needCompress) throws Exception {
        BufferedImage image = QRCodeUtils.createImage(content, imgPath,
                needCompress);
        mkdirs(destPath);
        String file = new Random().nextInt(99999999)+".jpg";
        ImageIO.write(image, FORMAT_NAME, new File(destPath+"/"+file));
        return file;
    }

    /**
     * 图片中插入二维码
     * @param content
     * @param imgPath
     * @param destPath
     * @return
     * @throws Exception
     */
    public static String encodeImage(String content, String imgPath, String destPath) throws Exception {
        BufferedImage image = QRCodeUtils.createImage(content, imgPath);

        return "";
    }

    /**
     * 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
     * @date 2013-12-11 上午10:16:36
     * @param destPath 存放目录
     */
    public static void mkdirs(String destPath) {
        File file =new File(destPath);
        //当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
    }

    /**
     * 生成二维码(内嵌LOGO)
     *
     * @param content
     *            内容
     * @param imgPath
     *            LOGO地址
     * @param destPath
     *            存储地址
     * @throws Exception
     */
    public static void encode(String content, String imgPath, String destPath)
            throws Exception {
        QRCodeUtils.encode(content, imgPath, destPath, false);
    }


    /**
     * 生成二维码
     *
     * @param content
     *            内容
     * @param destPath
     *            存储地址
     * @param needCompress
     *            是否压缩LOGO
     * @throws Exception
     */
    public static void encode(String content, String destPath,
                              boolean needCompress) throws Exception {
        QRCodeUtils.encode(content, null, destPath, needCompress);
    }

    /**
     * 生成二维码
     *
     * @param content
     *            内容
     * @param destPath
     *            存储地址
     * @throws Exception
     */
    public static void encode(String content, String destPath) throws Exception {
        QRCodeUtils.encode(content, null, destPath, false);
    }

    /**
     * 生成二维码(内嵌LOGO)
     *
     * @param content
     *            内容
     * @param imgPath
     *            LOGO地址
     * @param output
     *            输出流
     * @param needCompress
     *            是否压缩LOGO
     * @throws Exception
     */
    public static void encode(String content, String imgPath,
                              OutputStream output, boolean needCompress) throws Exception {
        BufferedImage image = QRCodeUtils.createImage(content, imgPath,
                needCompress);
        ImageIO.write(image, FORMAT_NAME, output);
    }

    /**
     * 生成二维码
     *
     * @param content
     *            内容
     * @param output
     *            输出流
     * @throws Exception
     */
    public static void encode(String content, OutputStream output)
            throws Exception {
        QRCodeUtils.encode(content, null, output, false);
    }


    /**
     * 解析二维码
     *
     * @param file
     *            二维码图片
     * @return
     * @throws Exception
     */
    public static String decode(File file) throws Exception {
        BufferedImage image;
        image = ImageIO.read(file);
        if (image == null) {
            return null;
        }
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(
                image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result;
        Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();
        hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
        result = new MultiFormatReader().decode(bitmap, hints);
        String resultStr = result.getText();
        return resultStr;
    }



    /**
     * 解析二维码
     *
     * @param path
     *            二维码图片地址
     * @return
     * @throws Exception
     */
    public static String decode(String path) throws Exception {
        return QRCodeUtils.decode(new File(path));
    }


    /**
     * 获取文件流
     * @param strUrl
     * @return
     */
    public static InputStream getInputStreamByUrl(String strUrl){
        HttpURLConnection conn = null;
        try {
            URL url = new URL(strUrl);
            conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(20 * 1000);
            //final ByteArrayOutputStream output = new ByteArrayOutputStream();
            //IOUtils.copy(conn.getInputStream(),output);
            return  conn.getInputStream();
        } catch (Exception e) {
            //logger.error(e+"");
        }finally {
            try{
                if (conn != null) {
                    conn.disconnect();
                }
            }catch (Exception e){
                //logger.error(e+"");
            }
        }
        return null;
    }


    public static void main(String[] args) throws Exception {

        long startTime = System.currentTimeMillis();
        String text = "www.baidu.com";  //这里设置自定义网站url
        String destPath = "/Users/zhuqingbiao/Downloads/docsmall/poster1.png";
        String logoPath = "/Users/zhuqingbiao/Desktop";
        //System.out.println(QRCodeUtils.encode(text, logoPath, destPath, true));


        System.out.println(QRCodeUtils.encodeImage(text,destPath,logoPath));

        long endTime = System.currentTimeMillis();

        System.out.println(endTime - startTime);

        /*InputStream inputStream = getInputStreamByUrl("https://locallife-cdn.hellobike.com/media/20210304/38a47cf0e898ae666f05c3ee24afbe8c.jpg");


        System.out.println(inputStream.available());*/


    }

}
