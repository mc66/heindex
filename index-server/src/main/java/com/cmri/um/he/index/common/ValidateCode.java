package com.cmri.um.he.index.common;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class ValidateCode {
    private static String s = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
    private static int width = 90;
    private static int height = 40;
    private static int len = 30;

    public static void getVerifyCode(HttpServletRequest request,
                                     HttpServletResponse response) {
        //设置响应头无缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        //创建一个图像缓冲区
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        //创建一个画笔
        Graphics g = image.getGraphics();
        Random random = new Random();
        //设置画笔随机颜色
        g.setColor(getRandColor(220, 250));
        //画矩形
        g.fillRect(0, 0, width, height);
        //设置字体
        g.setFont(new Font("Times New Roman", Font.PLAIN, 32));
        g.draw3DRect(0, 0, width -2, height - 2, true);
        //设置画线的随机颜色
        g.setColor(getRandColor(160, 200));
        //随机画30条线段
        for (int i = 0; i < len; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(12);
            int y1 = random.nextInt(12);
            g.drawLine(x, y, x + x1, y + y1);
        }
        //要显示的验证码
        String sRand = "";
        //验证码为4个随机字符
        for (int i = 0; i < 4; i++) {
            char rand = s.charAt(random.nextInt(s.length()));
            sRand += rand;
            //设置画随机字符的颜色
            g.setColor(new Color(20 + random.nextInt(80), random.nextInt(80),
                    random.nextInt(80)));
            //验证码的范围在s中
            g.drawString(String.valueOf(rand), 18 * i + 6, 28);
        }

        request.getSession().setAttribute("VerifyCode", sRand);
        g.dispose();

        ServletOutputStream output;
        try {
            //输出显示到浏览器中
            output = response.getOutputStream();
            ImageIO.write(image, "JPEG", output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //获取随机颜色rgb的值
    private static Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }



}
