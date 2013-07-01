/*
 * Title: BackgroundPanel.java
 * Abstract: This class is for the CarRental project that allows
 * 			 JPanel's to have background images.
 * Name: Micah Iriye
 * ID: 4321
 * Date: May 9th, 2012
 */

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BackgroundPanel extends JPanel
{
	private BufferedImage img;
	  
	  public BackgroundPanel(String location) {
		    // load the background image
		    try {
		      img = ImageIO.read(new File(location));
		    } catch(IOException e) {
		    e.printStackTrace();
		}
	  } 
	 
	  @Override
	  protected void paintComponent(Graphics g) 
	  {
	    super.paintComponent(g);
	    // paint the background image and scale it to fill the entire space
	    g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	  }
}