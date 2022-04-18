# Image-Processing

The program processes different types of images (.ppm, .yuv).

## main()
We have 2 files with main(), ImageProcessing.java and ImageProcessing2.java.  
ImageProcessing.java has graphic environment to help the user view the operations which takes part at the images.  
ImageProcessing2.java hasn't graphic environment, but have the same Functionality with ImageProcessing.java.

## RGB images
RGBPixel.java and RGBImage.java implement functions for RGB images.   
Also RGBImage.java contains the implementation of interface Image.java and have 4 methods:  
public void grayscale()  
public void doublesize()  
public void halfsize()  
public void rotateClockwise()  


## PPM images
PPMImage.java and PPMImageStacker.java (for stacking of .ppm images).

## YUV images
YUVPixel.java and YUVImage.java
