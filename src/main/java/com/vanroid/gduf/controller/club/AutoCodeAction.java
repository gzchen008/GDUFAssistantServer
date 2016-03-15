package com.vanroid.gduf.controller.club;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class AutoCodeAction extends ActionSupport {
	private HttpServletRequest request = ServletActionContext.getRequest();
	private HttpServletResponse response = ServletActionContext.getResponse();

	@Override
	public String execute() throws Exception {
		int width = 60;
		int height = 20;
		try {
			String s = RandomStringUtils.random(4, true, true);
			HttpSession session = request.getSession(true);
			session.setAttribute("autoCode", s);

			response.setContentType("image/jpeg");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "No-cache");
			response.setDateHeader("Expires", 0);
			ServletOutputStream out = response.getOutputStream();
			BufferedImage image = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics g = image.getGraphics();
			g.setColor(getRandColor(200, 250));
			g.fillRect(0, 0, width, height);
			Font mFont = new Font("Times New Roman", Font.BOLD, 18);
			g.setFont(mFont);
			g.setColor(getRandColor(160, 200));
			Random random = new Random();
			for (int i = 0; i < 155; i++) {
				int x2 = random.nextInt(width);
				int y2 = random.nextInt(height);
				int x3 = random.nextInt(12);
				int y3 = random.nextInt(12);
				g.drawLine(x2, y2, x2 + x3, y2 + y3);
			}
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));
			g.drawString(s, 2, 16);
			g.dispose();
			ImageIO.write((BufferedImage) image, "JPEG", out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("生成图片错误：" + e.toString());
		}
		return null;
	}

	private Color getRandColor(int fc, int bc) {
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
