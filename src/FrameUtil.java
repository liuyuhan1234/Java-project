import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class FrameUtil {

    private FrameUtil() {
    }

    public static BufferedImage getImage(String path) {
        URL u = FrameUtil.class.getClassLoader().getResource(path);
        BufferedImage image = null;
        try {
            image = ImageIO.read(u);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }


}
