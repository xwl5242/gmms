package com.zhx.gmms.frame.captcha;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.OutputStream;

public class GifCaptcha extends Captcha
{
    public GifCaptcha()
    {
    }
 
    public GifCaptcha(int width,int height){
        this.width = width;
        this.height = height;
    }
 
    public GifCaptcha(int width,int height,int len){
        this(width,height);
        this.len = len;
    }
 
    public GifCaptcha(int width,int height,int len,Font font)
    {
        this(width,height,len);
        this.font = font;
    }
 
    @Override
    public Captcha makeCode(){
    	setText(new String(alphas()));
    	return this;
    }
    
    @Override
    public void out(OutputStream os) throws Exception{
    	String code = text();
    	if("".equals(code)||null==code){
    		throw new Exception("请先执行makeCode()方法。。。");
    	}
        try
        {
            GifEncoder gifEncoder = new GifEncoder();   // gif编码类，这个利用了洋人写的编码类，所有类都在附件中
            //生成字符
            gifEncoder.start(os);
            gifEncoder.setQuality(180);
            gifEncoder.setDelay(100);
            gifEncoder.setRepeat(0);
            BufferedImage frame;
            Color fontcolor[]=new Color[len];
            for(int i=0;i<len;i++)
            {
                fontcolor[i]=new Color(20 + Randoms.num(90), 20 + Randoms.num(180), 20 + Randoms.num(90));
            }
            for(int i=0;i<len;i++)
            {
                frame=graphicsImage(fontcolor, text().toCharArray(), i);
                gifEncoder.addFrame(frame);
                frame.flush();
            }
            gifEncoder.finish();
        }finally
        {
            Streams.close(os);
        }
 
    }
 
    /**
     * 画随机码图
     * @param fontcolor 随机字体颜色
     * @param strs 字符数组
     * @param flag 透明度使用
     * @return BufferedImage
     */
    private BufferedImage graphicsImage(Color[] fontcolor,char[] strs,int flag)
    {
        BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
        //或得图形上下文
        //Graphics2D g2d=image.createGraphics();
        Graphics2D g2d = (Graphics2D)image.getGraphics();
        //利用指定颜色填充背景
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);
        g2d.setStroke(new BasicStroke(2.5f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND)); //设置线宽
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        AlphaComposite ac3;
        int h  = height - ((height - font.getSize()) >>1) ;
        int w = width/len;
        g2d.setFont(font);
        for(int i=0;i<len;i++)
        {
            ac3 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, getAlpha(flag, i));
            g2d.setComposite(ac3);
            g2d.setColor(fontcolor[i]);
            g2d.drawOval(Randoms.num(width), Randoms.num(height), 5+Randoms.num(10), 5+Randoms.num(10));
            g2d.drawString(strs[i]+"", (width-(len-i)*w)+(w-font.getSize())+1, h-4);
        }
        g2d.dispose();
        return image;
    }
 
    /**
     * 获取透明度,从0到1,自动计算步长
     * @return float 透明度
     */
    private float getAlpha(int i,int j)
    {
        int num = i+j;
        float r = (float)1/len,s = (len+1) * r;
        return num > len ? (num *r - s) : num * r;
    }
 
}