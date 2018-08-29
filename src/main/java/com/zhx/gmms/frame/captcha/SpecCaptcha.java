package com.zhx.gmms.frame.captcha;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

public class SpecCaptcha extends Captcha
{
    public SpecCaptcha()
    {
    }
    public SpecCaptcha(int width, int height)
    {
        this.width = width;
        this.height = height;
    }
    public SpecCaptcha(int width, int height, int len){
        this(width,height);
        this.len = len;
    }
    public SpecCaptcha(int width, int height, int len, Font font){
        this(width,height,len);
        this.font = font;
    }
    
    @Override
    public Captcha makeCode(){
    	setText(new String(alphas()));
    	return this;
    }
    
    /**
     * 生成验证码
     * @throws Exception 
     * @throws java.io.IOException IO异常
     */
    @Override
    public void out(OutputStream out) throws Exception{
    	String code = text();
    	if("".equals(code)||null==code){
    		throw new Exception("请先执行makeCode()方法。。。");
    	}
        graphicsImage(code.toCharArray(), out);
    }
 
    /**
     * 画随机码图
     * @param strs 文本
     * @param out 输出流
     */
    private boolean graphicsImage(char[] strs, OutputStream out){
        boolean ok = false;
        try
        {
            BufferedImage bi = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
            Graphics2D g = (Graphics2D)bi.getGraphics();
            AlphaComposite ac3;
            Color color ;
            int len = strs.length;
            g.setColor(Color.WHITE);
            g.fillRect(0,0,width,height);
            g.setStroke(new BasicStroke(2.5f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND)); //设置线宽
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            // 随机画干扰的蛋蛋
            for(int i=0;i<15;i++){
                color = color(150, 250);
                g.setColor(color);
                g.drawOval(Randoms.num(width), Randoms.num(height), 5+Randoms.num(10), 5+Randoms.num(10));// 画蛋蛋，有蛋的生活才精彩
                color = null;
            }
            g.setFont(font);
            int h  = height - ((height - font.getSize()) >>1),
                w = width/len,
                size = w-font.getSize()+1;
            /* 画字符串 */
            for(int i=0;i<len;i++)
            {
                ac3 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f);// 指定透明度
                g.setComposite(ac3);
                color = new Color(20 + Randoms.num(90), 20 + Randoms.num(180), 20 + Randoms.num(90));// 对每个字符都用随机颜色
                g.setColor(color);
                g.drawString(strs[i]+"",(width-(len-i)*w)+size, h-4);
                color = null;
                ac3 = null;
            }
            ImageIO.write(bi, "png", out);
            out.flush();
            ok = true;
        }catch (IOException e){
            ok = false;
        }finally
        {
            Streams.close(out);
        }
        return ok;
    }
}