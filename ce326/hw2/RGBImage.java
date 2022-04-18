/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ce326.hw2;

public class RGBImage implements Image{
    
    int initWidth;
    int initHeight;
    int initColordepth;
    
    RGBPixel [][]initImage;
    
    String fileToString = new String();
    
    /**
     *
     */
    public final static int MAX_COLORDEPTH = 255;
    
    public RGBImage(){}
    
    public RGBImage(int width, int height, int colordepth){
        initWidth = width;
        initHeight = height;
        initColordepth = colordepth;
        
        initImage = new RGBPixel [initWidth][initHeight];
        
        for(int y = 0; y < initHeight; y++){
            for(int x = 0; x < initWidth; x++){
                RGBPixel newPixel = new RGBPixel();
                setPixel(y, x, newPixel);
            }
        }
    }
    
    public RGBImage(RGBImage copyImg){
        
        initWidth = copyImg.initWidth;
        initHeight = copyImg.initHeight;
        initColordepth = copyImg.initColordepth;
        
        initImage = new RGBPixel [initWidth][initHeight];
        
        for(int y = 0; y < initHeight; y++){
            for(int x = 0; x < initWidth; x++){
                initImage[x][y] = new RGBPixel(copyImg.initImage[x][y]); // stin ousia to setPixe
            }
        }
        
        fileToString = ImageToString();
    }
    
    public RGBImage(YUVImage YUVImg){
        YUVPixel yuv;
        initWidth = YUVImg.initWidth;
        initHeight = YUVImg.initHeight;
        
        initImage = new RGBPixel [initWidth][initHeight];
        
        for(int y = 0; y < initHeight; y++){
            for(int x = 0; x < initWidth; x++){
                yuv = YUVImg.initImage[x][y];
                initImage[x][y] = new RGBPixel(yuv);
            }
        }
        
        fileToString = ImageToString();
    }
    
    int getWidth(){
        return initWidth;
    }
    
    int getHeight(){
        return initHeight;
    }
    
    int getColorDepth(){
        return initColordepth;
    }
    
    @SuppressWarnings("empty-statement")
    RGBPixel getPixel(int row, int col){
        int x, y;
        
        for(y = 0; y < row; y++);
        for(x = 0; x < col; x++);
        
        return initImage[x][y];
    }
    
    @SuppressWarnings("empty-statement")
    void setPixel(int row, int col, RGBPixel pixel){
        int x, y;
        
        for(y = 0; y < row; y++);
        for(x = 0; x < col; x++);
        
        initImage[x][y] = pixel;
    }
    
    public void grayscale(){
        short red, green, blue, gray;
        RGBPixel pixel;
        
        for(int y = 0; y < initHeight; y++){
            for(int x = 0; x < initWidth; x++){
                pixel = getPixel(y, x);

                red = pixel.getRed();

                green = pixel.getGreen();

                blue = pixel.getBlue();

                gray = (short) ((red*0.3) + (green*0.59) + (blue*0.11));

                initImage[x][y].setRGB(gray, gray, gray);
            }
        }
        
        fileToString = ImageToString();
    }
    
    public void doublesize(){
        short red, green, blue;
        //RGBPixel pixel;
        RGBPixel [][]img = new RGBPixel[2*initWidth][2*initHeight];
        int x, y;
        
        for(y = 0; y < initHeight; y++){
            for(x = 0; x < initWidth; x++){
                
                red = initImage[x][y].getRed();
                green = initImage[x][y].getGreen();
                blue = initImage[x][y].getBlue();
                
                img[2*x][2*y] = new RGBPixel(red, green, blue); 
                img[2*x][2*y+1] = new RGBPixel(red, green, blue);
                img[2*x+1][2*y] = new RGBPixel(red, green, blue);
                img[2*x+1][2*y+1] = new RGBPixel(red, green, blue);
            
            }
        }
        
        int rH = initHeight;
        int rW = initWidth;
        
        initHeight = 2*rH;
        initWidth = 2*rW;
        
        initImage = new RGBPixel [initWidth][initHeight];
        
        for(y = 0; y < initHeight; y++){
            for(x = 0; x < initWidth; x++){
                initImage[x][y] = img[x][y];
            }
        }
        
        fileToString = ImageToString();
    }
    
    public void halfsize(){
        short red, green, blue;
        RGBPixel [][]img = new RGBPixel[initWidth/2][initHeight/2];
        int x, y;
        
        for(y = 0; y < initHeight/2; y++){
            for(x = 0; x < initWidth/2; x++){
                
                short red1 = initImage[2*x][2*y].getRed();
                short green1 = initImage[2*x][2*y].getGreen();
                short blue1 = initImage[2*x][2*y].getBlue();
                              
                short red2 = initImage[2*x][2*y+1].getRed();
                short green2 = initImage[2*x][2*y+1].getGreen();
                short blue2 = initImage[2*x][2*y+1].getBlue();
                
                short red3 = initImage[2*x+1][2*y].getRed();
                short green3 = initImage[2*x+1][2*y].getGreen();
                short blue3 = initImage[2*x+1][2*y].getBlue();
                
                short red4 = initImage[2*x+1][2*y+1].getRed();
                short green4 = initImage[2*x+1][2*y+1].getGreen();
                short blue4 = initImage[2*x+1][2*y+1].getBlue();
                
                short nums = 4;
                
                red = (short) ((red1+red2+red3+red4)/nums);
                green = (short) ((green1+green2+green3+green4)/nums);
                blue = (short) ((blue1+blue2+blue3+blue4)/nums);
                
                img[x][y] = new RGBPixel(red, green, blue);
            }
        }
        
        int rH = initHeight;
        int rW = initWidth;
        
        initHeight = rH/2;
        initWidth = rW/2;
        
        initImage = new RGBPixel [initWidth][initHeight];
        
        for(y = 0; y < initHeight; y++){
            for(x = 0; x < initWidth; x++){
                initImage[x][y] = img[x][y];
            }
        }
        
        fileToString = ImageToString();
        
    }
    
    @Override
    public void rotateClockwise(){
        short red, green, blue;
        int x, y, rH, rW, j, i;
        //RGBPixel pixel;
        RGBPixel [][]img = new RGBPixel[initHeight][initWidth]; // rotate img height->width, width->height
        
        for(y = 0; y < initHeight; y++){
            for(x = 0; x < initWidth; x++){
                
                red = initImage[x][y].getRed();
                green = initImage[x][y].getGreen();
                blue = initImage[x][y].getBlue();
                
                img[initHeight-y-1][x] = new RGBPixel(red, green, blue);
            }
        }
        
        rH = initHeight;
        rW = initWidth;
        
        initHeight = rW;
        initWidth = rH;
        
        initImage = new RGBPixel [initWidth][initHeight];
        
        for(y = 0; y < initHeight; y++){
            for(x = 0; x < initWidth; x++){
                initImage[x][y] = img[x][y];
            }
        }
        
        fileToString = ImageToString();
    }

    final String ImageToString(){
        //String str = new String();
        StringBuilder contentBuilder = new StringBuilder();
        short red, green, blue;
        
        contentBuilder.append("P3\n");
        contentBuilder.append(initWidth).append(" ").append(initHeight).append(" \n").append(initColordepth).append("\n");
        
        for(int y = 0; y < initHeight; y++){
            for(int x = 0; x < initWidth; x++){
                red = initImage[x][y].getRed();
                green = initImage[x][y].getGreen();
                blue = initImage[x][y].getBlue();
                
                contentBuilder.append(red).append(" ").append(green).append(" ").append(blue).append("\n");
            }
        }
        
        return contentBuilder.toString();
    }
    
}
