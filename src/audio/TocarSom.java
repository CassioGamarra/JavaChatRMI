package audio;


import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class TocarSom {
    
    String nome;
    Clip clip;
    public TocarSom(String caminho){
        this.nome = caminho;
    }
    
    public void tocarSom() {
        URL urlAudio = getClass().getResource((nome));
        DataLine.Info daInfo = new DataLine.Info(Clip.class, null);
        try{
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(urlAudio);
            DataLine.Info info = new DataLine.Info(Clip.class, audioInputStream.getFormat());
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(audioInputStream);
            clip.start();
        }
        catch (Exception ex) {
            System.out.println("Erro ao executar SOM!");
            ex.printStackTrace();
        }
        
    }
    public void pararSom(){
        clip.stop();
    }
}
