import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class Music {
    private String filePath;
    public Music(String filePath){
        this.filePath = filePath;
    }

    public void playBackground(){

        File musicPath = new File(filePath);
        try{


            if(musicPath.exists()){
                AudioInputStream audio = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audio);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);

            }else{
                System.out.println("No File");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void playBallCollision(){

        File musicPath = new File(filePath);
        try{


            if(musicPath.exists()){
                AudioInputStream audio = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audio);
                clip.start();


            }else{
                System.out.println("No File");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void playScore(String filePath){

        try{
            File musicPath = new File(filePath);

            if(musicPath.exists()){
                AudioInputStream audio = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audio);
                clip.start();


            }else{
                System.out.println("No File");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
