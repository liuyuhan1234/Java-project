

 Specification
 ------------------

#### Dependencies
>1.  java version"11.0.5"
>2.  jl1.0.1.jar

#### Directory
>├── .idea  
>├── out  
>├── src  
>│   ├── Board.java  
>│   ├── chiefduck.png  
>│   ├── Duck.java  
>│   ├── duck.png  
>│   ├── Constants.java  
>│   ├── MainFrame.java  
>│   ├── FrameUtil.java  
>│   ├── Lily.java  
>│   ├── lili.png  
>│   ├── LoginMusic.java  
>│   ├── RandomGeneator.java  
>│   ├── Rock.java  
>│   └── rock.png  
>├── bg.jpg  
>├── bg1.png  
>├── bgm.mp3  
>├── jl1.0.1.jar  
>└── whistle.mp3  

#### Model
There are many rocks and water lilies in a pond.And ducks born, live and die in the pool.The water lilies grow back randomly in the pond.
When a duck borns, it moves in the pond and when it meets a water lily, it eats it and when it eats, the duck grows.If a duck does not eat after a certain time, it will lose weight and if it continues to lose weight it will eventually die.When a duck gets bigger and  bigger it eventually becomes a head duck, in which case it changes color.A chief duck is the only one that whistles and the other ducks line up behind him in single file.

#### Classes, Interfaces, Packages
##### Classes
>+ Duck
><font color=#000000>Duck class defines duck's x-coordinate , y-coordinate , width , height , x-speed , y-speed</font>
>```java
>public class Duck implements GameConstants {       
>private int x;      
>private int y;       
>private int wsize;       
>private int hsize;       
>private int xSpeed;       
>private int ySpeed;
>...
>```
><font color=#000000>Defines ducks move and when they meet pond edge,they will change their direction</font>
>
>```java
>public void move(Duck duck){      
>x+=xSpeed;       
>y+=ySpeed;       
>if(x>=GWIDTH-duck.getWsize()) {             
>   xSpeed = -Math.abs(xSpeed);      
>}      
>else if(x<=0) {            
>   xSpeed = Math.abs(xSpeed);      
>}      
>else if(y>=GHEIGHT-duck.getHsize()) {            
>   ySpeed = -Math.abs(ySpeed);      
>}       
>else if(y<=0) {             
>  ySpeed = Math.abs(ySpeed);      
>}
>}
>```
>
><font color=#000000>Defines constructor</font>
>
>```java
>public Duck() {
>		this.x =   duckx.getRandom2(50,GWIDTH);
>		this.y = ducky.getRandom2(50,GHEIGHT);
>		this.xSpeed = speedRandom.getRandom(MIN_SPEED,MAX_SPEED);
>		this.ySpeed = speedRandom.getRandom(MIN_SPEED,MAX_SPEED);
>		this.wsize = 40;
>		this.hsize = 40;
>	}
>```
>+ Lily
>
> <font color=#000000>Defines related properties and constructor</font>
>
>```java
>public class Lily implements GameConstants  {
>   private int x;
>   private int y;
>   private int w;
>   private int h;
>   ...
>```
>
>```java
>public Lily() {
>  this.x = ballPostion.getRandom(50,GWIDTH-40);
>  this.y = ballPostiony.getRandom(50,GHEIGHT-60);
>  this.w = 30;
>  this.h= 23;
>}
>```
>+ Rock
>
><font color=#000000> Defines related properties and constructor</font>
>
> ```java
> public class Rock {
>     private int x;
>     private int y;
>     private int w;
>     private int h;
>     ...
> ```
>
> ```java
> public Rock() {
>     this.x = ballPostion.getRandom(50, GameConstants.GWIDTH - 40);
>     this.y = ballPostiony.getRandom(50, GameConstants.GHEIGHT - 60);
>     this.w = 63;
>     this.h = 50;
> }
> ```
##### Interface

><font color=#000000>Defines some constants that need to be used</font>
>
> ```java
> public interface Constants {
>    int GWIDTH = 1000;
>    int GHEIGHT = 600;
>    String TITLE = "DuckPondModel";
>    int DELAY = 10;
>    int MAX_SPEED = 1;
>    int MIN_SPEED = -1;
>    int INI_DUCKS = 2;
>    int MAX_DUCKS = 5;
>    int MAX_LILIES = 5;
>    int ROCK = 3;
> }
> ```
>
##### Packages
><font color=#000000>jli.0.1.jar provides audio file support</font>

#### Graphics

> <font color=#000000>Write a utility class to find the path of the image you want and use `ImageIO.read()`to read it. </font>
> <font color=#000000>Override `paintComponent()` and draw the background, duck, lily and stone on the panel.</font>
>
> ```java
> @Override
> public void paintComponent(Graphics g) {
> super.paintComponent(g);
> g.drawImage(new ImageIcon("bg.jpg").getImage(),0,0, null);
> drawDucks(g);
> drawLilies(g);
> drawRocks(g);
> }
> ```
>
> <font color=#000000>Draw a duck as an example</font>
>
> ![image1](https://github.com/lyufan/Java-project/blob/master/image/image1.png)
>
> ![image2](https://github.com/lyufan/Java-project/blob/master/image/image2.png)
>
> 
>
> ```java
> private void drawDucks(Graphics g) {
>    for(Duck duck : ducks) {
>       if (duck.getWsize() < 64 || duck.getHsize() < 64) {
>          duck.drawDuck(g);
>       }
>       if (duck.getWsize() >= 64 || duck.getHsize() >= 64) {
>          duck.drawDuck2(g);
>          duck_chief.set(ducks.indexOf(duck),1);
>       }
>       duck.move();
>       duck.changeDirection(duck);
>    }
> }
> ```
>
> ```java
> Image image = GameUtil.getImage("duck.png");
> Image image2 = GameUtil.getImage("chiefduck.png");
> 
> public void drawDuck(Graphics g) {
>    g.drawImage(image, x, y, wsize,hsize,null);
> }
> public void drawDuck2(Graphics g) {
>    g.drawImage(image2, x, y, wsize,hsize,null);
> }
> ```
>
> <font color=#000000>Draw the start interface background</font>
>
> ![image3](https://github.com/lyufan/Java-project/blob/master/image/image3.png)
>
> ```java
> class MyPanel extends JPanel{
>     public void paint(Graphics g){
>         super.paint(g);
>         g.drawImage(new ImageIcon("bg.jpg").getImage(),0,0, null);
>         g.drawImage(new ImageIcon("bg1.png").getImage(),220,100, null);
>     }
> }
> ```

#### Animation

><font color=#000000>Override `actionPerformed()` method.<font>
>
><font color=#000000>In  MainFrame.java ，set the button event listener,when play button is pressed,the listener responds to the event,go to the new panel.<font>
>
>```java
>jb = new JButton("play");
>...
>jb.addActionListener(new ActionListener() {
>@Override
>public void actionPerformed(ActionEvent e) {
>   //Go to the new panel
>   mp.setVisible(false);
>   jb.setVisible(false);
>   t.stop();
>   Board board = new Board();
>   add(board);
>}
>});
>```
>
><font color=#000000>Use `Timer `Class to trigger the `ActionEvent` event at a predetermined frequency to achieve dynamic effect. </font>
>
><font color=#000000>`delay`: Sets the time interval between the two events</font>
>
><font color=#000000>`actionListener`： the listener that triggers the  event of the Timer class</font>
>
>```java
>timer = new Timer(DELAY,this);
>timer.start();
>```
>
><font color=#000000>Override `actionPerformed()` method to repaint the panel.</font>
>
>```java
>@Override
>public void actionPerformed(ActionEvent e) {+
>for(int i=0;i<ducks.size();i++){
> if(duck_colision.get(i)==0)
>    Collision();
>}
>Eat();
>RockCollision();
>}
>```
>
><font color=#000000>duck move: speed is random</font>
>
>```java
>public void move(Duck duck) {
>x+=xSpeed;
>y+=ySpeed;
>...
>```
>
><font color=#000000>Ducks and lilies use threads to generate continuously , but ducks have the largest number, If the number of ducks is less than the maximum, a new one is born.</font>
>
>##### Eat lilis
>
><font color=#000000>Whether to eat lilies was determined by examining whether  ducks and lilies overlapped.Use `ArrayList` to remove the lily was ate and use an arraylist to mark it.</font> 
>
>```java
>private void Eat(){
>	for(int i = 0; i< ducks.size(); i++) {
> 		for(int j = 0; j< lilies.size(); j++) {
>    			if(checkEat(ducks.get(i), lilies.get(j))) {
>//				System.out.println(" "+ducks.get(i).getX()+ " "+ ducks.get(i).getY()+" "+lilies.get(j).getX()+ " "+ lilies.get(j).getY());
>     				lilies.remove(j);
>     				duck_lily.set(i,duck_lily.get(i)+1);
>//				duck_data[i].set(0,1);
>     				duck_sign.set(i,1);
>    			}
> 		}
>	}
>}
>private boolean checkEat(Duck firstDuck, Lily firstLily) {
>	int xDistance2 = firstDuck.getX() - firstLily.getX();
>	int yDistance2 = firstDuck.getY() - firstLily.getY();
>	boolean sign1=false;
>	if(firstDuck.getWsize()> firstLily.getW()&&firstDuck.getHsize()> firstLily.getH())
> 		sign1= (xDistance2 >= -firstDuck.getWsize() && xDistance2 <= firstLily.getW())
>    			&& (yDistance2 >= -firstLily.getH() && yDistance2 <= firstDuck.getHsize());
>	else if(firstDuck.getWsize()< firstLily.getW()&&firstDuck.getHsize()> firstLily.getH())
> 		sign1= (xDistance2 >= -firstDuck.getWsize()&& xDistance2 <= firstLily.getW())
>     				&& (yDistance2 >= -firstLily.getH() && yDistance2 <= firstDuck.getHsize());
>	else if(firstDuck.getWsize()< firstLily.getW()&&firstDuck.getHsize()< firstLily.getH())
> 		sign1= (xDistance2 >=-firstDuck.getWsize()  && xDistance2 <= firstLily.getW())
>     				&& (yDistance2 >= -firstLily.getH() && yDistance2 <= firstDuck.getHsize());
>	return sign1;
>}
>```
>
>##### Collision
>
><font color=#000000>Similar to ducks eating lilies, ducks change speed when they collide with each other or with rocks</font>
>
>##### Grow and lose weight
>
><font color=#000000>Judge by the mark,duck become bigger or smaller.Ducks will disappear when they become smaller and smaller, and become ducks when they become bigger and bigger. The size of ducks is used as a judgment condition</font>
>
>```java
>for(int i = 0;i<ducks.size();i++){
>                if(duck_sign.get(i)==1&&(ducks.get(i).getWsize()<=72||ducks.get(i).getHsize()<=72)){
>                        ducks.get(i).setWsize(ducks.get(i).getWsize()+8);
>                        ducks.get(i).setHsize(ducks.get(i).getHsize()+8);
>                        duck_sign.set(i, 0);
>                     }
>                if(ducks.get(i).getWsize()<=8||ducks.get(i).getHsize()<=8){
>                        ducks.remove(i);
>                        duck_lily.remove(i);
>                        duck_sign.remove(i);
>                        duck_chief.remove(i);
>                   duck_colision.remove(i);
>                   duck_member.remove(i);
>
>                     }
>                     }
>     ```
>     
>##### Chief duck
>     
>     When the duck becomes the chief duck, it is marked. Through the mark, the whistle thread is triggered, and all the other ducks will follow the chief duck, keeping the speed direction of each duck pointing to the position of the previous duck.
>
>```java
>if (duck.getWsize() >= 64 || duck.getHsize() >= 64) {
>    duck.drawDuck2(g);
>    duck_chief.set(ducks.indexOf(duck),1);
>    sign=true;
>//       duck_data[ducks.indexOf(duck)].set(1,1);
> }
>     ```
>     
>     ```java
>Thread t_4= new Thread(){
>     public void run() {
> while (!result) {
>    if (duck_chief.contains(1)) {
>       int a = duck_chief.indexOf(1);
>       for(int i = 0; i < duck_chief.size(); i++){
>             duck_member.set(i, i);
>               duck_colision.set(i, 1);
>            }
>            duck_member.set(0, a);
>            duck_member.set(a, 0);
>            for (int i = 1; i < duck_member.size(); i++) {
>               ducks.get(duck_member.get(i)).setxSpeed((ducks.get(duck_member.get(i-1)).getX() - ducks.get(duck_member.get(i)).getX())/ducks.get(a).getWsize());
>               ducks.get(duck_member.get(i)).setySpeed((ducks.get(duck_member.get(i-1)).getY() - ducks.get(duck_member.get(i)).getY())/ducks.get(a).getWsize());
>     
>            }
>         }
>      }
>     }
>};
>     t_4.start();
>     ```
>     
>   ![image4](https://github.com/lyufan/Java-project/blob/master/image/image4.png)

#### Thread

>+ `LoginMusic()` : Start UI background music
>+ `t_duck` : Born ducks
>+ `t_lily` :Born lilies
>+ `t_1` : Ducks grow and dead
>+ `t_2` : Ducks lose weight
>+ `t_3` : Chief ducks whistle
>+ `t_4 `: Chief queue
>
> 
>
