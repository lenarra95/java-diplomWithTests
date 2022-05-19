package ru.netology.graphics.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class TextGraphicsConverterNew implements TextGraphicsConverter {

    private int width;
    private int maxWidth;
    private int height;
    private int maxHeight;
    private double maxRatio;

    @Override
    public String convert (String url) throws IOException, BadImageSizeException {
        TextColorSchemaNew schema = new TextColorSchemaNew();
        schema.fillColorMap();
        BufferedImage img = ImageIO.read(new URL(url));
        setWidth(img.getWidth());
        setHeight(img.getHeight());
        setMaxRatio(1400d / 900d);
        checkMaxRatio();
        setMaxWidth(300);
        setMaxHeight(200);
        compressImg();
        Image scaledImage = img.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);
        BufferedImage bwImg = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D graphics = bwImg.createGraphics();
        graphics.drawImage(scaledImage, 0, 0, null);
        ImageIO.write(bwImg, "png", new File("out.png"));
        WritableRaster bwRaster = bwImg.getRaster();
        StringBuilder string = new StringBuilder();
        for (int h = 0; h < bwRaster.getHeight(); h++) {
            for (int w = 0; w < bwRaster.getWidth(); w++) {
                int color = bwRaster.getPixel(w, h, new int[3])[0];
                char c = schema.convert(color);
                string.append(c);
                string.append(c);
            }
            string.append("\n");
        }
        return string.toString();
    }

    @Override
    public void setMaxWidth (int maxWidth) {
        this.maxWidth = maxWidth;
    }

    public int getMaxWidth () {
        return maxWidth;
    }

    @Override
    public void setMaxHeight (int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public int getMaxHeight () {
        return maxHeight;
    }

    @Override
    public void setMaxRatio (double maxRatio) {
        this.maxRatio = maxRatio;
    }

    public double getMaxRatio () {
        return maxRatio;
    }

    @Override
    public void setTextColorSchema (TextColorSchema schema) {
    }

    public void setWidth (int width) {
        this.width = width;
    }

    public int getWidth () {
        return width;
    }

    public void setHeight (int height) {
        this.height = height;
    }

    public int getHeight () {
        return height;
    }

    private boolean needCompress (int width, int height, int maxWidth, int maxHeight) {
        return width > maxWidth || height > maxHeight;
    }

    public void checkMaxRatio() throws BadImageSizeException {
        double ratio = (double) getWidth() / getHeight();
        if (ratio > getMaxRatio()) {
            throw new BadImageSizeException(ratio, getMaxRatio());
        }
    }

    public void compressImg() {
        if (needCompress(getWidth(), getHeight(), getMaxWidth(), getMaxHeight())) {
            double maxSideValue = Integer.max(getWidth(), getHeight());
            double maxSideNewValue = Integer.max(getMaxWidth(), getMaxHeight());
            double compressValue = maxSideValue / maxSideNewValue;
            setHeight((int) (getHeight() / compressValue));
            setWidth((int) (getWidth() / compressValue));
        }
    }
}

