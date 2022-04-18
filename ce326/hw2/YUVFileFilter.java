/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ce326.hw2;

import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.*;

public class YUVFileFilter extends javax.swing.filechooser.FileFilter {
  public boolean accept(File file) {
    if(file.isDirectory())
      return true;
    String name = file.getName();
    if(name.length()>4 && name.substring(name.length()-4, name.length()).toLowerCase().equals(".yuv"))
      return true;
    return false;
  }
  
  public String getDescription() { return "YUV File"; }
}
