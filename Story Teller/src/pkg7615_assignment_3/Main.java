/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg7615_assignment_3;

import guzdial.FileChooser;
import guzdial.Picture;
import guzdial.Sound;
import guzdial.SoundSample;
import java.awt.Component;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
        
/**
 *
 * @author thilinaratnayake
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Sound grab the sound file
            
            //Process User Function
            System.out.println("What would you like to do? Enter \n [1] to Normalize a clip\n"
                    + " [2] to Apply Clipping (Amplitude) \n [3] to Decrease Volume \n [4] to play a story");
            Scanner scanner = new Scanner(System.in);

            String command = scanner.nextLine();
            switch (command) {
                case "1":
                    
                    System.out.println("User wants to Normalize a clip");
                    System.out.println("Please choose which sound file you would like to manipulate");
                    File soundFile = chooseFile();
                    normalizeSample(soundFile,scanner);
                    break;
                case "2":
                    System.out.println("User wants to Apply clipping (Amplitude)");
                    System.out.println("Please choose which sound file you would like to manipulate");
                    File soundFile1 = chooseFile();
                    System.out.println("Please enter what amplitude you would like to begin clipping");
                    int clippingVal = Integer.parseInt(scanner.nextLine());
                    clipAmplitude(soundFile1,clippingVal);
                    break;
                case "3":
                    System.out.println("User wants to Decrease Volumes");
                    System.out.println("Please choose which sound file you would like to manipulate");
                    File soundFile2 = chooseFile();
                    System.out.println("Please enter by how much you would like to decrease the volume (amplitude)");
                    int decreaseVal = Integer.parseInt(scanner.nextLine());
                    decreaseVolume(soundFile2, decreaseVal, scanner);
                    break;
                case "4": 
                    System.out.println("User wants to play a story");
                    tellAStory();

            } 
           
    }
    
    public static File chooseFile(){
        JFileChooser chooser = new JFileChooser();
        Component frame = null;

         // Show the dialog; wait until dialog is closed
        chooser.showOpenDialog(frame);
        File file = chooser.getSelectedFile();
        return file;
    }
    
    
    public static void tellAStory(){
        
      
        File[] backingFiles = new File[7];
        
        backingFiles[0] = new File("src/pkg7615_assignment_3/BTrack1.wav");
        backingFiles[1]= new File("src/pkg7615_assignment_3/BTrack2.wav");
        backingFiles[2] = new File("src/pkg7615_assignment_3/BTrack3.wav");
        backingFiles[3] = new File("src/pkg7615_assignment_3/BTrack4.wav");
        backingFiles[4] = new File("src/pkg7615_assignment_3/BTrack5.wav");
        backingFiles[5] = new File("src/pkg7615_assignment_3/BTrack6.wav");
        backingFiles[6] = new File("src/pkg7615_assignment_3/BTrack7.wav");
        
        ArrayList<Sound> backingTracks = new ArrayList<>();
            
        
        for (File file : backingFiles) {
            String filePath = file.getAbsolutePath();
            Sound sound = new Sound(filePath);
         
            backingTracks.add(sound);
        }
        
        
        
        //Pick the files
        System.out.println("Choose sounds");
        ArrayList<Sound> sounds = Main.chooseFiles();
        
        //Play in sequence
       
        backingTracks.get(0).blockingPlay();
        sounds.get(0).blockingPlay();
        backingTracks.get(1).blockingPlay();
        sounds.get(1).blockingPlay();
        backingTracks.get(2).blockingPlay();
        sounds.get(2).blockingPlay();
        backingTracks.get(3).blockingPlay();
        sounds.get(3).blockingPlay();
        backingTracks.get(4).blockingPlay();
        sounds.get(4).blockingPlay();
        backingTracks.get(5).blockingPlay();
        sounds.get(5).blockingPlay();
        sounds.get(6).blockingPlay();
        backingTracks.get(6).blockingPlay();
   
    
        
        
    }
    
    public static ArrayList<Sound> chooseFiles(){
        System.out.println("Choose FIles()");
        ArrayList<Sound> sounds = new ArrayList<>();
        //pick images
        
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(true);
        Component frame;
        frame = null;

         // Show the dialog; wait until dialog is closed
        chooser.showOpenDialog(null);

        // Retrieve the selected files.
        File[] files = chooser.getSelectedFiles();
        for (File file : files) {
            String filePath = file.getAbsolutePath();
            Sound sound = new Sound(filePath);
            sounds.add(sound);
        }
        
        return sounds;
    }
    
    //Takes in two values, file and what level to clip at.
    public static void clipAmplitude(File soundFile, int value){
        Sound oldSound = new Sound(soundFile.getAbsolutePath());
        Sound newSound = new Sound(soundFile.getAbsolutePath());
        //Get all the values of the (new) selected sound clip.
        SoundSample[] sampleArray = newSound.getSamples();
        int maxAmplitude = value;
        int minAmplitude = (value * -1);
        
        for (SoundSample sample : sampleArray) {
            //Get the amplitude of the sound value and assign it the amplitude variable.
            int amplitude = sample.getValue();
            //If the amplitude of the current sound sample is greater than the max
            //assigned amplitude value, set it to the max.
            if (amplitude > 0) {
                if (amplitude > maxAmplitude) {
                    sample.setValue(maxAmplitude);
                }
            }
            //If the negative amplitude if less than the min amplitude, set it to the
            //minAmplitude value.
            else if (amplitude < 0) {
                if (amplitude < minAmplitude)
                    sample.setValue(-minAmplitude);
            }
        }
        
        //Allow user to compare the old and new sound
        System.out.println("Volume has been decreased");
        while (true) {
            System.out.println("Hit [1] to listen to the old sample \n"
                    + "Hit [2] to listen to the new sample \n"
                    + "Hit [3] to save the files to directory \n"
                    + "Hit [4] to exit");
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            switch (command) {
                case "1":
                    System.out.println("Playing old sample");
                    oldSound.play();
                    break;
                case "2":
                    System.out.println("Playing new sample");
                    newSound.play();
                    break;
                case "3":
                    System.out.println("Saving files..");
                    oldSound.write(soundFile.getParent()+"/DV_oldSound.wav");
                    newSound.write(soundFile.getParent()+"/DV_newSound.wav");
                    System.out.println("Done saving!");
                    break;
                default:
                    System.out.println("Exiting current operation");
                    break;

            }

        }
    }
    public static void normalizeSample(File soundFile, Scanner scanner){
        Sound oldSound = new Sound(soundFile.getAbsolutePath());
        Sound newSound = new Sound(soundFile.getAbsolutePath());
        
        //Iterate through the sample and find the max.
        SoundSample[] sampleArray = newSound.getSamples();
        //Value to hold the max positives and negatives
        int maxPos = 0;
        int maxNeg = 0;
        for (SoundSample sample : sampleArray) {
            int value = sample.getValue();
            //If positive, find check if it's higher than the existing max positive value
            if(value > 0){
               //if it is, assign it the new max
                if(maxPos < value){
                    maxPos = value;
                }
            }
            //if negative. No just else because we don't wanna touch the 0's
            else if (value < 0){
                if (maxNeg > value){
                    maxNeg = value;
                }
            }
        }
        System.out.println("The new maxes are, positive: " + maxPos + "negative: " + maxNeg);
        
        //Begin normalizing each sample in the file.
        for(SoundSample sample: sampleArray){
            int value = sample.getValue();
            //If positive, multiply peak to get to max
            if(value > 0){
                float multiplier = maxPos / value;
                //Round to get the closest integer because operation results in float
                int newValue = Math.round(value * multiplier);
                sample.setValue(newValue);
            }
            else if (value < 0){
                float multiplier = maxNeg / value;
                //Round to get the closest integer because operation results in float
                int newValue = Math.round(value * multiplier);
                sample.setValue(value);
                
            }
        }
        
        //Allow user to compare the old and new sound
        System.out.println("Clip has been normalized");
        while (true) {
            System.out.println("Hit [1] to listen to the old sample \n"
                    + "Hit [2] to listen to the new sample \n"
                    + "Hit [3] to save the files to directory \n"
                    + "Hit [4] to exit");
            String command = scanner.nextLine();
            switch (command) {
                case "1":
                    System.out.println("Playing old sample");
                    oldSound.play();
                    break;
                case "2":
                    System.out.println("Playing new sample");
                    newSound.play();
                    break;
                case "3":
                    System.out.println("Saving files..");
                    oldSound.write(soundFile.getParent() + "/NM_oldSound.wav");
                    newSound.write(soundFile.getParent() + "/NM_newSound.wav");
                    System.out.println("Done saving!");
                    break;
                default:
                    System.out.println("Exiting current operation");
                    break;

            }

        }
        
    }
    
    public static void decreaseVolume(File soundFile, int decreaseAmount, Scanner scanner){
        //Create sound objects with the file path
        Sound oldSound = new Sound(soundFile.getAbsolutePath());
        //Create two copies, one so that we can listen to the difference later.
        Sound newSound = new Sound(soundFile.getAbsolutePath());
        
        //Iterate through the sample and grab all the values.
        SoundSample[] sampleArray = newSound.getSamples();
        for (SoundSample sample : sampleArray) {
            int oldValue = sample.getValue();
            int newValue = oldValue / decreaseAmount;
            sample.setValue(newValue);
        }

        //Allow user to compare the old and new sound
        System.out.println("Volume has been decreased");
        while (true) {
            System.out.println("Hit [1] to listen to the old sample \n"
                    + "Hit [2] to listen to the new sample \n"
                    + "Hit [3] to save the files to directory \n"
                    + "Hit [4] to exit");
            String command = scanner.nextLine();
            switch (command) {
                case "1":
                    System.out.println("Playing old sample");
                    oldSound.play();
                    break;
                case "2":
                    System.out.println("Playing new sample");
                    newSound.play();
                    break;
                case "3":
                    System.out.println("Saving files..");
                    oldSound.write(soundFile.getParent()+"/DV_oldSound.wav");
                    newSound.write(soundFile.getParent()+"/DV_newSound.wav");
                    System.out.println("Done saving!");
                    break;
                default:
                    System.out.println("Exiting current operation");
                    break;

            }

        }
    }
}
