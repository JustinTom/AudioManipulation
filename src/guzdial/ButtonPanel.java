package guzdial;

import javax.swing.*;
import java.awt.event.*;

/**
 * Class that holds the buttons for the movie player
 * @author Barb Ericson
 */
public class ButtonPanel extends JPanel
{
  //////////////// fields ////////////////////////
  private JButton nextButton = new JButton("Next");
  private JButton playButton = new JButton("Play Movie");
  private JButton prevButton = new JButton("Prev");
  private MoviePlayer moviePlayer = null;
  
  ///////////////// Constructors /////////////////
  
  /**
   * Constructor that doesn't take any parameters
   */
  public ButtonPanel(MoviePlayer player) 
  {
    this.moviePlayer = player;
    
    // add the buttons to this panel
    this.add(prevButton);
    this.add(nextButton);
    this.add(playButton);
    
    // add the action listeners to the buttons
    nextButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        moviePlayer.showNext();
      }});
    prevButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e)  {
        moviePlayer.showPrevious();
      }});
    playButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        moviePlayer.playMovie();
      }});
  }
  
}