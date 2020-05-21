import java.awt.*;

public class Rock {
    private int x;
    private int y;
    private int w;
    private int h;
    private static RandomGenerator ballPostion = new RandomGenerator();
    private static RandomGenerator ballPostiony = new RandomGenerator();
    Image image = FrameUtil.getImage("rock.png");
    public void drawRock(Graphics g) {
        g.drawImage(image, x, y, null);
    }
    public Rock() {
        this.x = ballPostion.getRandom(50, Constants.GWIDTH - 40);
        this.y = ballPostiony.getRandom(50, Constants.GHEIGHT - 60);
        this.w = 63;
        this.h = 50;
    }
        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
        public int getW() {
            return w;
        }

        public void setW(int w) {
            this.w = w;
        }

        public int getH() {
            return h;
        }

        public void setH(int h) {
            this.h = h;
        }
    }