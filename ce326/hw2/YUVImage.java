/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ce326.hw2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Files;
import java.util.Arrays;

public class YUVImage {
    
    int initWidth;
    int initHeight;
    YUVPixel [][]initImage; // = new YUVPixel [initWidth][initHeight];
    String fileToString = new String();
    
    public YUVImage(int width, int height){
        short Y = 16, U = 128, V = 128;
        
        for(int y = 0; y < initHeight; y++){
            for(int x = 0; x < initWidth; x++){
                initImage[x][y] = new YUVPixel(Y, U, V);
            }
        }
    }
    
    public YUVImage(YUVImage copyImg){
        
        initWidth = copyImg.initWidth;
        initHeight = copyImg.initHeight;
        
        initImage = new YUVPixel [initWidth][initHeight];
        
        for(int y = 0; y < initHeight; y++){
            for(int x = 0; x < initWidth; x++){
                initImage[x][y] = new YUVPixel(copyImg.initImage[x][y]); // stin ousia to setPixe
            }
        }
        
        fileToString = ImageToString();
    }
    
    public YUVImage(RGBImage RGBImg){
        int x, y;
        initWidth = RGBImg.initWidth;
        initHeight = RGBImg.initHeight;
    
        initImage = new YUVPixel [initWidth][initHeight];
        
        for(y = 0; y < initHeight; y++){
            for(x = 0; x < initWidth; x++){
                initImage[x][y] = new YUVPixel(RGBImg.initImage[x][y]);
            }
        }
        
        /*for(x = 0; x < initWidth; x++){
            System.out.println(initImage[x][0].getY());
        }*/
        
        fileToString = ImageToString();
    }
    
    public YUVImage(java.io.File file) throws UnsupportedFileFormatException{
        String type;
        short Y, U, V;
        int x, y;
        
        fileToString = readFileToString(file);
    
        try(Scanner sc = new Scanner(file)){
            type = sc.next();
            if(!"YUV3".equals(type)){
                throw new UnsupportedFileFormatException("The file is not YUV3 type");
            }
            
            initWidth = sc.nextInt();
            initHeight = sc.nextInt();
            
            initImage = new YUVPixel [initWidth][initHeight];
            
            for(y = 0; y < initHeight; y++){
                for(x = 0; x < initWidth; x++){

                    if(sc.hasNext()){
                        Y = sc.nextShort();
                        U = sc.nextShort();
                        V = sc.nextShort();
                
                        initImage[x][y] = new YUVPixel(Y, U, V); //.setY(Y);
                    }
                    else{
                        break;
                    }
                }
            }
           
        } catch(FileNotFoundException ex){
            System.out.println("Unable to open");
        }
    }
    
    public final String readFileToString(java.io.File file){
        
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))){
 
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) 
            {
                contentBuilder.append(sCurrentLine).append("\n");
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        
        return contentBuilder.toString();
    }
    
    final String ImageToString(){
        //String str = new String();
        StringBuilder contentBuilder = new StringBuilder();
        short Y, U, V;
        
        //initImage = new YUVPixel [initWidth][initHeight];
        
        contentBuilder.append("YUV3\n");
        contentBuilder.append(initWidth).append(" ").append(initHeight).append("\n");
        
        for(int y = 0; y < initHeight; y++){
            for(int x = 0; x < initWidth; x++){
                Y = initImage[x][y].getY();
                U = initImage[x][y].getU();
                V = initImage[x][y].getV();
                
                contentBuilder.append(Y).append(" ").append(U).append(" ").append(V).append("\n");
            }
        }
        
        return contentBuilder.toString();
    }
    
    @Override
    public String toString(){
        
        return fileToString;
    }
    
    public void toFile(java.io.File file){
        
        try(FileWriter writer = new FileWriter(file)) {
            writer.write(fileToString); 
        }
        catch(IOException e){
            // Handle the exception
        }
    }
    
    public void equalize(){
        int histArray[] = new int[236];
        double probability[] = new double[236];
        double cumulProb[] = new double[236];
        
        int x, y;
        
        Arrays.fill(histArray, 0);
        Arrays.fill(probability, 0);
        Arrays.fill(cumulProb, 0);
        
        for(y = 0; y < initHeight; y++){
            for(x = 0; x < initWidth; x++){
                histArray[initImage[x][y].getY()]++;
            }
        }
        
        for(x = 0; x < histArray.length; x++){
            probability[x] = ((double) histArray[x])/((double)(initHeight*initWidth));
        }
        
        for(x = 0; x < histArray.length; x++){
            if(x == 0){
                cumulProb[x] = probability[x];
            }
            else{
                cumulProb[x] = probability[x] + cumulProb[x-1];
            }
        }       
        
        int eq[] = new int[236];
               
        for(x = 0; x < 236; x++){
            eq[x] = (int) (cumulProb[x]*235);
        }
        
        for(y = 0; y < initHeight; y++){
            for(x = 0; x < initWidth; x++){
                int f = initImage[x][y].getY();
                initImage[x][y].setY((short) eq[f]);
            }
        }
    }
}
