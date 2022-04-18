/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ce326.hw2;

public class YUVPixel {
    
    private short initY; 
    private short initU;
    private short initV;
    
    
    public YUVPixel(short Y, short U, short V){
        initY = Y;
        initU = U;
        initV = V;
    }
    
    public YUVPixel(YUVPixel pixel){
        
        initY = pixel.initY;
        initU = pixel.initU;
        initV = pixel.initV;
        
    }
    
    public YUVPixel(RGBPixel pixel){
        
        initY = (short) ((( (short) 66*pixel.initRed + (short) 129*pixel.initGreen +  (short) 25*pixel.initBlue + (short)128) >> 8) + (short)16);
        initU = (short) ((( -(short)38*pixel.initRed - (short)74*pixel.initGreen + (short)112*pixel.initBlue + (short)128) >> 8) + (short)128);
        initV = (short) ((( (short)112*pixel.initRed - (short)94*pixel.initGreen - (short)18*pixel.initBlue + (short)128) >> 8) + (short)128);

    }
    
    short getY(){
        return initY;
    }
    
    short getU(){
        return initU;
    }
    
    short getV(){
        return initV;
    }
    
    void setY(short Y){
        initY = Y;
    }
    
    void setU(short U){
        initU = U;
    }
    
    void setV(short V){
        initV = V;
    }

}
