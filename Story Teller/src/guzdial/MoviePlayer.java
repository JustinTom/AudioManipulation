package guzdial;

import javax.swing.*;
import java.util.*;
import java.io.*;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.*;

/**
 * Class that can play movies from multiple frames
 * @author Barb Ericson
 */
public class MoviePlayer
{
  
  ///////////////// fields ///////////////////////////
 
  private JFrame frame = new JFrame("Movie Player");
  private JLabel frameLabel = new JLabel("No images yet!");
  private AnimationPanel animationPanel = null;
  
  //////////////////// constructors ////////////////////
  
  /**
   * Constructor that takes a list of pictures
   * @param pictureList the list of pictures to show
   */
  public MoviePlayer(List pictureList)
  {
    animationPanel = new AnimationPanel(pictureList);
    init();
  }
  
  /**
   * Constructor that takes a directory and shows a movie
   * from it
   * @param directory the directory with the frames
   */
  public MoviePlayer(String directory)
  {
    animationPanel = new AnimationPanel(directory);
    init();
  }
  
  /////////////////////// methods ////////////////////////////
  
  /**
   * Method to show the next image
   */
  public void showNext()
  {
   animationPanel.showNext();
   frameLabel.setText("Frame Number " + 
                      animationPanel.getCurrIndex());
   frame.repaint();
  }
  
  /**
   * Method to show the previous image
   */
  public void showPrevious()
  {
    animationPanel.showPrev();
    frameLabel.setText("Frame Number " + 
                       animationPanel.getCurrIndex());
    frame.repaint();
  }
  
  /**
   * Method to play the movie from the beginning
   */
  public void playMovie()
  {
    frameLabel.setText("Playing Movie");
    frame.repaint();
    animationPanel.showAll();
    frameLabel.setText("Frame Number " + 
                       animationPanel.getCurrIndex());
    frame.repaint();
  }  
  
   /**
   * Method to play the movie from the beginning
   * @param framesPerSecond the number of frames to show
   * per second
   */
  public void playMovie(int framesPerSecond)
  {
    animationPanel.setFramesPerSec(framesPerSecond);
    playMovie();
  }  
  
  /**
   * Method to add a picture to the movie
   * @param picture the picture to add
   */
  public void addPicture(Picture picture)
  {
    animationPanel.add(picture);
    showNext();
  }
  
  /**
   * Method to set up the gui
   */
  private void init()
  {
    Container container = frame.getContentPane();
    container.setLayout(new BorderLayout());
    JPanel buttonPanel = new JPanel();
    
    // add the animation panel
    container.add(animationPanel,BorderLayout.CENTER);
    
    // add the frame label to the north
    JPanel labelPanel = new JPanel();
    labelPanel.add(frameLabel);
    container.add(labelPanel,BorderLayout.NORTH);
    
    // add the button panel to the south
    container.add(new ButtonPanel(this),BorderLayout.SOUTH);
      
    // set the size of the frame
    frame.pack();
    
    // show the frame
    frame.setVisible(true);
  }
  
  /**
   * Method to set the visibility of the frame
   * @param flag the visibility of the frame
   */
  public void setVisible(boolean flag)
  {
    frame.setVisible(flag);
  }
  
  public static void main(String[] args)
  {
    MoviePlayer moviePlayer = 
      new MoviePlayer("c:/intro-prog-java/mediasources/fish/");
    moviePlayer.playMovie(16);
  }
  
}
  