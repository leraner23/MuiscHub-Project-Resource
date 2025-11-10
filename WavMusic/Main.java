import javax.sound.sampled.LineUnavailableException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.sound.sampled.*;
import javax.swing.plaf.synth.SynthOptionPaneUI;


public class Main {
    public static void main(String[] args) throws  IOException, LineUnavailableException, UnsupportedAudioFileException {
        Scanner scanner = new Scanner(System.in);
    File file = new File("I try to paint anime eye Anime eye drawing Billie Eilish - ocean eyes trend oceaneyes shorts.wav");
AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
Clip clip = AudioSystem.getClip();
clip.open(audioStream);

String response = "";

while(!response.equals("Q")){
    System.out.println("P = PlAY, S = STOP, R = RESET, Q = QUIT");
    System.out.println("ENTER YOUR CHOICE");
    response = scanner.next();
    response = response.toUpperCase();

    switch (response){
        case("P"): clip.start();
        break;

        case ("S"): clip.stop();
        break;

        case ("R"): clip.setMicrosecondPosition(0);
            break;

        case ("Q"): clip.close();
            break;

                    default:
            System.out.println("not a valid response");

    }

}

    }
}