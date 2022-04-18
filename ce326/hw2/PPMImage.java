/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ce326.hw2;
import java.io.BufferedReader;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PPMImage extends RGBImage{
    PPMImage PPMIimg;
    String type;
     
    public PPMImage(java.io.File file) throws UnsupportedFileFormatException{
        short red, green, blue;
        int x = 0, y = 0;
        
        fileToString = FileToString(file);
        
        
        
        try(Scanner sc = new Scanner(file)){
            type = sc.next();
            if(!"P3".equals(type)){
                throw new UnsupportedFileFormatException("The file is not PPM type");
            }
            
            initWidth = sc.nextInt();
            initHeight = sc.nextInt();
            initColordepth = sc.nextInt();
            
            
            initImage = new RGBPixel [initWidth][initHeight];

            for(y = 0; y < initHeight; y++){
                for(x = 0; x < initWidth; x++){
                    RGBPixel newPixel = new RGBPixel();
                    setPixel(y, x, newPixel);
                }
            }
            
            
            for(y = 0; y < initHeight; y++){
                for(x = 0; x < initWidth; x++){

                    if(sc.hasNext()){
                        red = sc.nextShort();
                        green = sc.nextShort();
                        blue = sc.nextShort();

                        initImage[x][y].setRed(red);
                        initImage[x][y].setGreen(green);
                        initImage[x][y].setBlue(blue);
                    }
                    else{
                        break;
                    }
                }
            }


        } catch(FileNotFoundException ex){
            System.out.println("Unable to open");
        } catch(ArrayIndexOutOfBoundsException ex){
            System.out.println("x = "+ x +"\ny = "+ y +"\ninitHeight = "+initHeight+"\ninitWidth = "+initWidth);
        }

    }
    
    public PPMImage(RGBImage img){
        
        initWidth = img.initWidth;
        initHeight = img.initHeight;
        initColordepth = img.initColordepth;
       
        initImage = new RGBPixel [initWidth][initHeight];
        
        for(int y = 0; y < initHeight; y++){
            for(int x = 0; x < initWidth; x++){
                initImage[x][y] = new RGBPixel(img.initImage[x][y]); // stin ousia to setPixe
            }
        }
        
        fileToString = ImageToString();
    }
    
    public PPMImage(YUVImage img){
        
        initWidth = img.initWidth;
        initHeight = img.initHeight;
        initColordepth = 255;
       
        initImage = new RGBPixel [initWidth][initHeight];
        
        for(int y = 0; y < initHeight; y++){
            for(int x = 0; x < initWidth; x++){
                initImage[x][y] = new RGBPixel(img.initImage[x][y]);
            }
        }
        
        fileToString = ImageToString();
    }
    
    String FileToString(java.io.File file){
        
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
    
    
    
    @Override
    public String toString(){
        return fileToString;
    }
    
    public void toFile(java.io.File file){
        
        if(file.exists()){
           
            try (PrintWriter writer = new PrintWriter(file)) {
                writer.print("");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PPMImage.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
        
        try(FileWriter writer = new FileWriter(file)) {
           writer.write(fileToString); 
        }
        catch(IOException e){
            // Handle the exception
        }
    }
}
