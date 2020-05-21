import javazoom.jl.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Board extends JPanel implements Constants, ActionListener{
private Timer timer ;
private	Boolean result = false;
private	Boolean sign = false;
private	ArrayList<Duck> ducks = new ArrayList<Duck>();
private	ArrayList<Lily> lilies = new ArrayList<Lily>();
private	ArrayList<Rock> rocks = new ArrayList<Rock>();
private	ArrayList<Integer> duck_lily = new ArrayList<Integer>();//Mark how many lilies the duck ate
private	ArrayList<Integer> duck_sign = new ArrayList<Integer>();//mark a duck eats a lily, eats a lily, goes from a 3 to a 1 to a 0
private	ArrayList<Integer> duck_chief = new ArrayList<Integer>();//Mark who is a duck, 1 is, 0 is not
private	ArrayList<Integer> duck_colision = new ArrayList<Integer>();//标记谁碰撞，被吸引的是1不碰撞，0碰撞
private	ArrayList<Integer> duck_distance = new ArrayList<Integer>();//标记与头鸭的距离
private	ArrayList<Integer> duck_member = new ArrayList<Integer>();
//private	ArrayList[] duck_data = new ArrayList[MAX_DUCKS];
private void intDucks() {
	for( int i=0;i <INI_DUCKS;i++ ) { //给数组增加10个Int元素
		ducks.add(new Duck());
//		duck_data[i]=new ArrayList<Integer>();
////		duck_data[i].add(i+1);
//		duck_data[i].add(3);
//		duck_data[i].add(0);
//		duck_data[i].add(0);
		duck_lily.add(0);
		duck_sign.add(3);
		duck_chief.add(0);
		duck_member.add(0);
		duck_colision.add(0);
		for(int j = 0; j< rocks.size(); j++){
			if(checkRock(ducks.get(i),rocks.get(j))) {
				i=i-1;
				ducks.remove(i);
				duck_lily.remove(i);
				duck_sign.remove(i);
				duck_chief.remove(i);
				duck_member.remove(i);
				duck_colision.remove(i);
			}
		}

	}}


private void intLilies() {
/*	for( int i=0;i <2;i++ ) { //给数组增加10个Int元素
		lilies.add(new Lily());

	}*/
/*	for( int i=0;i <MAX_LILIS;i++ ) //给数组增加10个Int元素
		lilies.add(i,new Lily());*/
}
	private void intRocks() {
	for( int i=0;i <ROCK;i++ ) { //给数组增加10个Int元素
		rocks.add(new Rock());

	}
/*	for( int i=0;i <MAX_LILIS;i++ ) //给数组增加10个Int元素
		lilies.add(i,new Lily());*/
	}

private void Collision() {
	for(int i = 0; i< ducks.size()-1; i++) {
		for(int j = i + 1; j< ducks.size(); j++) {
			if(checkCollision(ducks.get(i), ducks.get(j))) {
				if(ducks.get(i).getxSpeed()*ducks.get(j).getxSpeed()>0&&ducks.get(i).getySpeed()*ducks.get(j).getySpeed()>0){
					ducks.get(i).setxSpeed(2*ducks.get(j).getxSpeed());
					ducks.get(i).setySpeed(2*ducks.get(j).getySpeed());
				}
				else
					{int a=ducks.get(i).getxSpeed();
					int b=ducks.get(i).getySpeed();
					ducks.get(i).setxSpeed(ducks.get(j).getxSpeed());
					ducks.get(i).setySpeed(ducks.get(j).getySpeed());
					ducks.get(j).setxSpeed(a);
					ducks.get(j).setySpeed(b);}
			}
		}
	}
}
private boolean checkCollision(Duck firstDuck, Duck secondDuck) {
	int xDistance = firstDuck.getX() - secondDuck.getX();
	int yDistance = firstDuck.getY() - secondDuck.getY();
	boolean sign2=false;
	if(firstDuck.getWsize()>=secondDuck.getWsize()&&firstDuck.getHsize()>=secondDuck.getHsize())
		sign2= (xDistance >= -firstDuck.getWsize() && xDistance <= secondDuck.getWsize())
				&& (yDistance >= -firstDuck.getHsize() && yDistance <= secondDuck.getHsize());
	else if(firstDuck.getWsize()<secondDuck.getWsize()&&firstDuck.getHsize()<secondDuck.getHsize())
		sign2= (xDistance >= -firstDuck.getWsize() && xDistance <= secondDuck.getWsize())
				&& (yDistance >= -firstDuck.getHsize() && yDistance <= secondDuck.getHsize());
	return sign2;
}
private void Eat(){
	for(int i = 0; i< ducks.size(); i++) {
		for(int j = 0; j< lilies.size(); j++) {
			if(checkEat(ducks.get(i), lilies.get(j))) {
//				System.out.println(" "+ducks.get(i).getX()+ " "+ ducks.get(i).getY()+" "+lilies.get(j).getX()+ " "+ lilies.get(j).getY());
				lilies.remove(j);
				duck_lily.set(i,duck_lily.get(i)+1);
//				duck_data[i].set(0,1);
				duck_sign.set(i,1);
			}
		}
	}
}

private boolean checkEat(Duck firstDuck, Lily firstLily) {
	int xDistance2 = firstDuck.getX() - firstLily.getX();
	int yDistance2 = firstDuck.getY() - firstLily.getY();
	boolean sign1=false;
	if(firstDuck.getWsize()> firstLily.getW()&&firstDuck.getHsize()> firstLily.getH())
		sign1= (xDistance2 >= -firstDuck.getWsize() && xDistance2 <= firstLily.getW())
			&& (yDistance2 >= -firstLily.getH() && yDistance2 <= firstDuck.getHsize());
	else if(firstDuck.getWsize()< firstLily.getW()&&firstDuck.getHsize()> firstLily.getH())
		sign1= (xDistance2 >= -firstDuck.getWsize()&& xDistance2 <= firstLily.getW())
				&& (yDistance2 >= -firstLily.getH() && yDistance2 <= firstDuck.getHsize());
	else if(firstDuck.getWsize()< firstLily.getW()&&firstDuck.getHsize()< firstLily.getH())
		sign1= (xDistance2 >=-firstDuck.getWsize()  && xDistance2 <= firstLily.getW())
				&& (yDistance2 >= -firstLily.getH() && yDistance2 <= firstDuck.getHsize());
	return sign1;
}
	private void RockCollision(){
		for(int i = 0; i< ducks.size(); i++) {
			for(int j = 0; j< rocks.size(); j++){
				if(checkRock(ducks.get(i),rocks.get(j))) {
//				System.out.println(" "+ducks.get(i).getX()+ " "+ ducks.get(i).getY()+" "+lilies.get(j).getX()+ " "+ lilies.get(j).getY());
					ducks.get(i).setxSpeed(-ducks.get(i).getxSpeed());
					ducks.get(i).setySpeed(-ducks.get(i).getySpeed());
				}
			}
		}
	}

	private boolean checkRock(Duck firstDuck, Rock firstRock) {
		int xDistance3 = firstDuck.getX() - firstRock.getX();
		int yDistance3 = firstDuck.getY() - firstRock.getY();
		boolean sign3=false;
		if(firstDuck.getWsize()>firstRock.getW()&&firstDuck.getHsize()>firstRock.getH())
			sign3= (xDistance3 >= -firstDuck.getWsize() && xDistance3 <= firstRock.getW())
					&& (yDistance3 >= -firstRock.getH() && yDistance3 <= firstDuck.getHsize());
		else if(firstDuck.getWsize()<firstRock.getW()&&firstDuck.getHsize()>firstRock.getH())
			sign3= (xDistance3 >= -firstDuck.getWsize()&& xDistance3 <= firstRock.getW())
					&& (yDistance3 >= -firstRock.getH() && yDistance3 <= firstDuck.getHsize());
		else if(firstDuck.getWsize()<firstRock.getW()&&firstDuck.getHsize()<firstRock.getH())
			sign3= (xDistance3 >=-firstDuck.getWsize()  && xDistance3 <= firstRock.getW())
					&& (yDistance3 >= -firstRock.getH() && yDistance3 <= firstDuck.getHsize());
		return sign3;
	}
/*	private int distance(Duck firstduck,Duck secondduck){
	int duckdistance=(firstduck.getX()+firstduck.getWsize()/2-secondduck.getX()-secondduck.getWsize()/2)^2+(firstduck.getY()+firstduck.getHsize()/2-secondduck.getY()-secondduck.getHsize()/2)^2;
	return duckdistance;
}*/

	public Board() {
		intDucks();
		intLilies();
		intRocks();
		Thread t_duck= new Thread(){
			public void run() {
				while (!result) {
					try {
						Thread.sleep(2000);

//						System.out.println(ducks.size());
//						for (Duck duck : ducks) {
//							System.out.println(" " + duck.getWsize() + " " + duck.getHsize());
//						}
						if(ducks.size()<MAX_DUCKS){
							ducks.add(new Duck());
							duck_lily.add(0);
							duck_sign.add(0);
							duck_chief.add(0);
							duck_colision.add(0);
							duck_member.add(0);
							for(int j = 0; j< rocks.size(); j++){
								if(checkRock(ducks.get(ducks.size()),rocks.get(j))) {
									ducks.remove(ducks.size());
									duck_lily.remove(ducks.size());
									duck_sign.remove(ducks.size());
									duck_chief.remove(ducks.size());
									duck_member.remove(ducks.size());
									duck_colision.remove(ducks.size());
								}
							}
//							Thread.sleep(2000);

							}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		};
		t_duck.start();
		Thread t_lily= new Thread(){
			public void run(){
				while(!result) {
					try {

						for (int i=0;i< MAX_LILIES;i++) {
							lilies.add(new Lily());
							Thread.sleep(2000);
//							System.out.println(lilies.size());
//						for(Lily lili : lilies) {
//							System.out.println(" "+lili.getX()+ " "+ lili.getY());
//						}

						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		};
		t_lily.start();
		Thread t_1= new Thread(){
			public void run(){
				while(!result) {
					try {
						 //设置暂停的时间 2 秒
						for(int i = 0;i<ducks.size();i++){
							if(duck_sign.get(i)==1&&(ducks.get(i).getWsize()<=72||ducks.get(i).getHsize()<=72)){
								ducks.get(i).setWsize(ducks.get(i).getWsize()+8);
								ducks.get(i).setHsize(ducks.get(i).getHsize()+8);
								duck_sign.set(i, 0);
//								duck_data[i].set(0,0);
							}
							if(ducks.get(i).getWsize()<=8||ducks.get(i).getHsize()<=8){
								ducks.remove(i);
								duck_lily.remove(i);
//								duck_data[i].remove(0);
//								duck_data[i].remove(1);
//								duck_data[i].remove(2);
								duck_sign.remove(i);
								duck_chief.remove(i);
								duck_colision.remove(i);
								duck_member.remove(i);

							}
							}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		};
		t_1.start();
		Thread t_2= new Thread(){
			public void run(){
				while(!result) {
					try {
						Thread.sleep(2500); //设置暂停的时间 2 秒
						for(int i = 0;i<ducks.size();i++){
							if((duck_sign.get(i)==0||duck_sign.get(i)==3)&&!sign){
									ducks.get(i).setWsize(ducks.get(i).getWsize()-2);
									ducks.get(i).setHsize(ducks.get(i).getHsize()-2);
//									System.out.println(" " + ducks.get(i).getWsize() + " " + ducks.get(i).getHsize());
							}
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		t_2.start();
		Thread t_3= new Thread(){
			public void run() {
				while (!result) {
					if (duck_chief.contains(1)) {
						Player player;
							try {
								Thread.sleep(10);
								BufferedInputStream buffer = new BufferedInputStream(new FileInputStream("whistle.mp3"));
								player = new Player(buffer);
								player.play();
							} catch (Exception e) {
								e.printStackTrace();
							}

					}
				}
			}
		};
		t_3.start();
		Thread t_4= new Thread(){
			public void run() {
				while (!result) {
					if (duck_chief.contains(1)) {
						int a = duck_chief.indexOf(1);
						for(int i = 0; i < duck_chief.size(); i++){
							duck_member.set(i, i);
							duck_colision.set(i, 1);
						}
//						duck_distance.set(0, a);
						duck_member.set(0, a);
						duck_member.set(a, 0);
						/*for (int i = 1; i < duck_member.size(); i++) {
							int x1=ducks.get(duck_member.get(i)).getX();
							int x2=ducks.get(duck_member.get(0)).getX();
							int y1=ducks.get(duck_member.get(i)).getY();
							int y2=ducks.get(duck_member.get(0)).getY();
							if(Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2))<500){duck_distance.add(i,duck_member.get(i));}
						}*/
						for (int i = 1; i < duck_member.size(); i++) {
							ducks.get(duck_member.get(i)).setxSpeed((ducks.get(duck_member.get(i-1)).getX() - ducks.get(duck_member.get(i)).getX())/ducks.get(a).getWsize());
							ducks.get(duck_member.get(i)).setySpeed((ducks.get(duck_member.get(i-1)).getY() - ducks.get(duck_member.get(i)).getY())/ducks.get(a).getWsize());

						}
//						for (int i = 1; i < duck_member.size(); i++) {
//							ducks.get(duck_member.get(i)).setxSpeed((ducks.get(duck_member.get(i-1)).getX() - ducks.get(duck_member.get(i)).getX())/ducks.get(a).getW());
//							ducks.get(duck_member.get(i)).setySpeed((ducks.get(duck_member.get(i-1)).getY() - ducks.get(duck_member.get(i)).getY())/ducks.get(a).getW());
//
//						}
						}
					}
				}
		};
		t_4.start();
	setSize(GWIDTH,GHEIGHT);
	timer = new Timer(DELAY,this);
	timer.start();
	
}


@Override
public void actionPerformed(ActionEvent e) {
	repaint();
	for(int i=0;i<ducks.size();i++){
		if(duck_colision.get(i)==0)
			Collision();
	}
	Eat();
	RockCollision();

}

private void drawDucks(Graphics g) {
	for(Duck duck : ducks) {
		if (duck.getWsize() < 64 || duck.getHsize() < 64) {
			duck.drawDuck(g);
		}
		if (duck.getWsize() >= 64 || duck.getHsize() >= 64) {
			duck.drawDuck2(g);
			duck_chief.set(ducks.indexOf(duck),1);
			sign=true;
//			duck_data[ducks.indexOf(duck)].set(1,1);
		}
		//duck.move();
		duck.move(duck);
	}
}

private void drawLilies(Graphics g) {
		for(Lily lily : lilies) {
			lily.drawLily(g);

		}
	}
private void drawRocks(Graphics g) {
	for(Rock rock : rocks) {
		rock.drawRock(g);

	}
}

@Override
public void paintComponent(Graphics g) {
	super.paintComponent(g);
	g.drawImage(new ImageIcon("bg.jpg").getImage(),0,0, null);
	drawDucks(g);
	drawLilies(g);
	drawRocks(g);
}

}






