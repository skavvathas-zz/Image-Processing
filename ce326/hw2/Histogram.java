/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ce326.hw2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;

/**
 *
 * @author spiros
 */
public class Histogram {
    int histArray[];
    YUVImage yuv;
    
    public Histogram(YUVImage img){       
        this.histArray = new int[236];
        int x, y;
        
        yuv = img;
        
        Arrays.fill(histArray, 0);
        
        for(y = 0; y < img.initHeight; y++){
            for(x = 0; x < img.initWidth; x++){
                histArray[img.initImage[x][y].getY()]++;
            }
        }
    }
    
    @Override
    public String toString(){
        //String histString = new String();
        StringBuilder contentBuilder = new StringBuilder();
        int x, y, u, t, h, th;
        String str = new String();
        
        
        for(x = 0; x < histArray.length; x++){
            
            contentBuilder.append("\n");
            str = String.format("%3d", x);
            contentBuilder.append(str);
            contentBuilder.append(".").append("(");
            str = String.format("%4d", histArray[x]);
            contentBuilder.append(str);
            contentBuilder.append(")").append("\t");
            
            u = histArray[x]%10; 
            t = (histArray[x]/10)%10; 
            h = (histArray[x]/100)%10; 
            th = histArray[x]/1000;
            
            for(y = 0; y < th; y++){
                contentBuilder.append("#");
            }
                
            for(y = 0; y < h; y++){
                contentBuilder.append("$");
            }
            
            for(y = 0; y < t; y++){
                contentBuilder.append("@");
            }
            
            for(y = 0; y < u; y++){
                contentBuilder.append("*");
            }
        }
        
        contentBuilder.append("\n");
        
        return contentBuilder.toString();
    }
    
    public void toFile(File file) throws IOException{
        String str = new String();
        str = toString();
    
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.append(str);
        }
    }
    
    public void equalize(){
        /*int eq[] = new int[236];
        int x, y;*/
        YUVImage yuvimg;
        
        yuvimg = new YUVImage(yuv);
        yuvimg.equalize();
        
    }
   
    public short getEqualizedLuminocity(int luminocity){
        return (short) (luminocity*235);
    }
    
}
