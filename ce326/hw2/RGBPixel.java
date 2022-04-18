/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ce326.hw2;

public class RGBPixel {
    
    private int initValue;
    short initRed;
    short initGreen;
    short initBlue;
    
    public RGBPixel(){}
    
    public RGBPixel(short red, short green, short blue){
        initRed = red;
        initGreen = green;
        initBlue = blue;
    }
    
    public RGBPixel(RGBPixel pixel){
        //pixel = new RGBPixel(initRed, initGreen, initBlue);
        initRed = pixel.initRed;
        initGreen = pixel.initGreen;
        initBlue = pixel.initBlue;
    }
    
    public RGBPixel(YUVPixel pixel){
        int C, D, E;
        
        C = pixel.getY() - 16;
        D = pixel.getU() - 128;
        E = pixel.getV() - 128;

        initRed = (short) clip(( 298*C + 409*E + 128) >> 8);
        initGreen = (short) clip(( 298*C - 100*D - 208*E + 128) >> 8);
        initBlue = (short) clip(( 298*C + 516*D + 128) >> 8);
        
        //System.out.println(initRed);

    }
    
    public final int clip(int val){
        
        if(val < 0){
            val = 0;
        }
        
        if(val > 255){
            val = 255;
        }
        
        return val;
    }
    
    short getRed(){
        return initRed;
    }
    
    short getGreen(){
        return initGreen;
    }
    
    short getBlue(){
        return initBlue;
    }
    
    void setRed(short red){
        initRed = red;
    }
    
    void setGreen(short green){
        initGreen = green;
    }
    
    void setBlue(short blue){
        initBlue = blue;
    }
    
    int getRGB(){
        return initValue;
    }

    void setRGB(int value){
        initValue = value;
        
        int alpha = (value >> 24) & 0xff;
        //set red
        initRed = (short)((value >> 16) & 0xff);
        //set green
        initGreen = (short)((value >> 8) & 0xff);
        //set blue
        initBlue = (short)(value & 0xff);
    }
    
    final void setRGB(short red, short green, short blue){
        initRed = red;
        initGreen = green;
        initBlue = blue;
    }
    
    @Override
    public String toString(){
        System.out.print(initRed + " " + initGreen + " " + initBlue);
        return null;
    }
}
