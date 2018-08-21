package com.cmri.um.he.index.common;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @author lch
 *         Created on 2018/08/15 15:03
 */
public class VerifyCodeUtils {
    private static StringBuffer codesave = null;

    public static BufferedImage paintImage(int width, int height, int len) {
        BufferedImage img = new BufferedImage(width, height, 1);

        Graphics graphics = img.getGraphics();

        graphics.setColor(Color.BLACK);

        graphics.fillRect(0, 0, width, height);

        graphics.setColor(Color.WHITE);
        graphics.fillRect(1, 1, width - 2, height - 2);

        String code = "23456789abcdefghijkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ";
        Random rd = new Random();
        codesave = new StringBuffer();

        graphics.setFont(new Font("楷体", 1, height - 4));
        for (int i = 0; i < len; i++) {
            graphics.setColor(new Color(rd.nextInt(256), rd.nextInt(256), rd.nextInt(256)));

            int index = rd.nextInt(code.length());
            codesave.append(code.substring(index, index + 1));

            graphics.drawString(code.substring(index, index + 1), width / 8 * (i + 1), height - 4);
        }

        return img;
    }

    public static String getCode() {
        if (codesave == null) {
            return "";
        }
        return codesave.toString();
    }

    public static void generateCode(HttpServletRequest request, HttpServletResponse response, int width, int len)
            throws ServletException, IOException {
        BufferedImage img = paintImage(width, 40, len);
        String code = VerifyCodeUtils.getCode();

        HttpSession session = request.getSession();
        session.removeAttribute("code");
        session.setAttribute("code", code.toLowerCase());
        System.out.println(session.getAttribute("code"));

        ImageIO.write(img, "jpeg", response.getOutputStream());
    }
}

