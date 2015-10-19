package guzdial;

import java.util.*;
import java.text.*;
import java.io.*;

/**
 * Class to save frames in a movie to a directory.  This class
 * tracks the directory, base file name, current frame number, 
 * and whether this sequence is being shown.
 * 
 * Copyright Georgia Institute of Technology 2004
 * @author Barbara Ericson
 */
public class FrameSequence 
{
  //////////////////// Fields ///////////////////////////////////
  
  /** stores the directory to write the frames to */
  private String directory;
  
  /** stores the base file name for each frame file */
  private String baseName = "frame";
  
  /** stores the current frame number */
  private int frameNumber = 1;
  
  /** true if this sequence is being shown */
  private boolean shown = false;
  
  /** the picture frame used to show this sequence */
  private PictureFrame pictureFrame = null;
  
  /** List of all the pictures so far */
  private List pictureList = new ArrayList();
  
  /** Use this to format the number for the frame */
  private NumberFormat numberFormat = NumberFormat.getIntegerInstance();
  
  //////////////////// Constructors /////////////////////////////
  
  /**
   * Constructor that takes a directory name
   * @param directory the directory to save the frames to
   */
  public FrameSequence(String directory)
  {
    this.directory = directory;
    initFormatter();
    validateDirectory();
  }
  
  /**
   * Constructor that takes a directory name and a base file name
   * @param directory the directory to save the frames to
   * @param baseName the base file name to use for the frames
   */
  public FrameSequence(String directory, String baseName)
  {
    // use the other constructor to set the directory
    this(directory);
    
    // set the base file name
    this.baseName = baseName;
  }
    
  
  ///////////////////// Methods ////////////////////////////////
  
  /**
   * Method to get the directory to write the frames to
   * @return the directory to write the frames to
   */
  public String getDirectory() { return directory; }
  
  /**
   * Method to set the directory to write the frames to
   * @param dir the directory to use
   */
  public void setDirectory(String dir) { directory = dir; }
  
  /**
   * Method to get the base name 
   * @return the base file name for the movie frames
   */
  public String getBaseName() { return baseName; }
  
  /**
   * Method to set the base name 
   * @param name the new base name to use
   */
  public void setBaseName(String name) { baseName = name; }
  
  /**
   * Method to get the frame number
   * @return the next frame number for the next picture
   * added
   */
  public int getFrameNumber() { return frameNumber; }
  
  /**
   * Method to check if the frame sequence is being shown
   * @return true if shown and false otherwise
   */
  public boolean isShown() { return shown; }
  
  /**
   * Method to set the shown flag
   * @param value the value to use
   */
  public void setShown(boolean value) { shown = value; }
  
  /**
   * Method to get the number of frames in this sequence
   * @return the number of frames
   */
  public int getNumFrames() { return pictureList.size(); }
  
  /**
   * Method to set the picture frame to use to shown this
   * @param frame the picture frame to use
   */
  public void setPictureFrame(PictureFrame frame) { pictureFrame = frame; }
  
  /**
   * Method to get the picture frame to use to show this sequence
   * @return the picture frame used to show this (may be null)
   */
  public PictureFrame getPictureFrame() { return pictureFrame; }
  
  /**
   * Method to initialize the number formatter to show 4 digits
   * with no commas
   */
  private void initFormatter()
  {
    numberFormat.setMinimumIntegerDigits(4);
    numberFormat.setGroupingUsed(false);
  }
  
  /**
   * Method to validate the directory (make
   * sure it ends with a separator character
   */
  private void validateDirectory()
  {
    char end = directory.charAt(directory.length() - 1);
    if (end != '/' || end != '\\') 
      directory = directory + '/';
    File directoryFile = new File(directory);
    if (!directoryFile.exists())
      directoryFile.mkdirs();
  }
  
  /**
   * Method to add a picture to the frame sequence
   * @param picture the picture to add
   */
  public void addFrame(Picture picture)
  {
    
    // add this picture to the list 
    pictureList.add(picture);
    
    // write out this frame
    picture.write(directory + baseName + 
                  numberFormat.format(frameNumber) + ".jpg");
    
    // if this sequence is being shown update the frame
    if (shown)
    {
      if (pictureFrame != null)
        pictureFrame.setPicture(picture);
      else
        pictureFrame = new PictureFrame(picture);
    }
    
    // increment the frame number
    frameNumber++;
  }
  
  /**
   * Method to show the frame sequence
   */
  public void show()
  {
    if (shown != true)
    {
      // set it to true
      shown = true;
    
      // if there is a picture show the last one
      if (pictureList.size() > 0)
        pictureFrame = new PictureFrame((Picture) pictureList.get(pictureList.size() - 1));
      else
        System.out.println("There are no frames to show yet.  When you add a frame it will be shown");
    }  
  }
  
  /**
   * Method to replay the frames (pictures) added so far
   * @param sleepTime the amount to sleep in milliseconds
   * between frames
   */
  public void replay(int sleepTime)
  {
    Picture picture = null;
    Iterator iterator = pictureList.iterator();
    while (iterator.hasNext())
    {
      picture = (Picture) iterator.next();
      pictureFrame.setPicture(picture);
      try { 
        Thread.sleep(sleepTime);
      } catch (Exception ex) {
      }
    }
  } 
  
} // end of class