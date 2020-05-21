import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements Constants {
    JButton jb;
    MyPanel mp;

    public static void main(String[] args) {
        new MainFrame();
    }
    MainFrame(){
        jb = new JButton("play");
        mp = new MyPanel();

        LoginMusic t = new LoginMusic();
        t.start();
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Go to the new panel
                mp.setVisible(false);
                jb.setVisible(false);
                t.stop();
                Board board = new Board();
                add(board);
            }
        });
        this.setLayout(null);
        this.add(jb);
        this.add(mp);
        jb.setBounds(470, 440, 80, 60);
        mp.setBounds(0, 0, 1000, 600);
        //界面设置
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(GWIDTH,GHEIGHT);
        setTitle(TITLE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
//登入面板
class MyPanel extends JPanel{
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(new ImageIcon("bg.jpg").getImage(),0,0, null);
        g.drawImage(new ImageIcon("bg1.png").getImage(),220,100, null);
    }
}


