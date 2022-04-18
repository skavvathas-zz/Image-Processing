/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ce326.hw2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

public class PPMImageStacker {
    List<PPMImage> ImageList;
    short sizeOfList = 0;
    PPMImage stack;
    
    public PPMImageStacker(java.io.File dir) throws FileNotFoundException, UnsupportedFileFormatException{
        this.ImageList = new LinkedList<>();
        
        if(!dir.exists()){
            throw new FileNotFoundException("[ERROR] Directory " + dir + " does not exist!");
        }

        if(!dir.isDirectory()){
            throw new FileNotFoundException("[ERROR] " + dir + "s not a directory!");          
        }
        
        File filesList[] = dir.listFiles();
        
        for(File file: filesList){
            if(!".directory".equals(file.getName())){
                sizeOfList++;
                PPMImage image = new PPMImage(file);
                ImageList.add(image);
            }          
        }
        
    }
    
    public void stack(){
        short red = 0, green = 0, blue = 0;
        short newRed = 0, newGreen = 0, newBlue = 0;
        
        //GBImage rgbImgGBImage rgbImg
        stack = new PPMImage(ImageList.get(0));
        for(int y = 0; y < ImageList.get(0).initHeight; y++){
            for(int x = 0; x < ImageList.get(0).initWidth; x++){
                stack.initImage[x][y].setRGB(red, green, blue);
            }
        }
        
        for(int y = 0; y < ImageList.get(0).initHeight; y++){
            for(int x = 0; x < ImageList.get(0).initWidth; x++){
                for(PPMImage img: ImageList){
                    red += img.initImage[x][y].getRed();
                    green += img.initImage[x][y].getGreen();
                    blue += img.initImage[x][y].getBlue();
                }
                
                newRed = (short) (((short)red) / ((short)sizeOfList));
                newGreen = (short) (((short)green) / ((short)sizeOfList));
                newBlue = (short) (((short)blue) / ((short) sizeOfList));
                
                stack.initImage[x][y].setRed(newRed);
                stack.initImage[x][y].setGreen(newGreen);
                stack.initImage[x][y].setBlue(newBlue);
                
                red = 0;
                green = 0;
                blue = 0;
            }
        }
    }
    
    public PPMImage getStackedImage(){

        stack.fileToString = stack.ImageToString();
        
        return stack;
    }
    
}
