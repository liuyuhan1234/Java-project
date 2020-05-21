import java.io.BufferedInputStream;
import java.io.FileInputStream;
import javazoom.jl.player.Player;




public class LoginMusic extends Thread implements Runnable {
    Player player;

    @Override
    public void run() {
        try {
            Thread.sleep(10);
            BufferedInputStream buffer = new BufferedInputStream(new FileInputStream("bgm.mp3"));
            player = new Player(buffer);
            player.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}