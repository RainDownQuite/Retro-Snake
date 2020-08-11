package tcs;

import javax.swing.*;

import java.awt.*;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;


import java.util.Random;

public class Gamepanel extends JPanel implements KeyListener, ActionListener
{	
	private static final long serialVersionUID = 1L;
	int lenth; 
	int c=1;
	int[] X=new int[100]; 
	int[] Y=new int[100];  
	String fx_sur;  
	String fx;  
	boolean isStart=false; 
	int foodx;
	int foody;
	Random random=new Random();
	Timer timer =new Timer(160, this);
	boolean isFail=false;
	int score;
	
	public Gamepanel(){
		init();
		this.setFocusable(true);  
		this.addKeyListener(this);
		timer.start(); 
	}
	
	public void init(){
		timer.setDelay(300);
		lenth=3;
		X[0]=400;Y[0]=350;
		X[1]=400;Y[1]=375;   
		X[2]=400;Y[2]=400; 
		fx="U";
		fx_sur=fx;
		foodx= 25 + 25 * random.nextInt(34);
		foody= 75 + 25 * random.nextInt(24);
		score=0;
	}
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g); 
		this.setBackground(Color.white);
		if(fx.equals("R")){
			Data.you.paintIcon(this, g, X[0], Y[0]);
		}
		else if(fx.equals("L")){
			Data.zuo.paintIcon(this, g, X[0], Y[0]);
		}
		else if(fx.equals("U")){
			Data.shang.paintIcon(this, g, X[0], Y[0]);
		}
		else if(fx.equals("D")){
			Data.xia.paintIcon(this, g,X[0], Y[0]);
		}
		X[lenth]=X[lenth-1];
		Y[lenth]=Y[lenth-1];
		c++;
		for(int i=1;i<lenth-1;i++){
				if(X[i]==X[i+1]&&X[i]==X[i-1]){
					if(c % 2==1) {Data.NS1.paintIcon(this, g, X[i], Y[i]);}
					else {Data.NS2.paintIcon(this, g, X[i], Y[i]);}
				}
				if(Y[i]==Y[i+1]&&Y[i]==Y[i-1]){
					if(c % 2==1) {Data.EW1.paintIcon(this, g, X[i], Y[i]);}
					else {Data.EW2.paintIcon(this, g, X[i], Y[i]);}
				}
				if(2*X[i]>X[i+1]+X[i-1]&&2*Y[i]>Y[i-1]+Y[i+1]){
					Data.NW.paintIcon(this, g, X[i], Y[i]);
				}
				if(2*X[i]>X[i+1]+X[i-1]&&2*Y[i]<Y[i-1]+Y[i+1]){
					Data.SW.paintIcon(this, g, X[i], Y[i]);
				}
				if(2*X[i]<X[i+1]+X[i-1]&&2*Y[i]>Y[i-1]+Y[i+1]){
					Data.NE.paintIcon(this, g, X[i], Y[i]);
				}
				if(2*X[i]<X[i+1]+X[i-1]&&2*Y[i]<Y[i-1]+Y[i+1]){
					Data.SE.paintIcon(this, g, X[i], Y[i]);
				}
				if(X[lenth-1]==X[lenth-2]&&Y[lenth-2]>Y[lenth-1]){
					Data.WEIN.paintIcon(this, g, X[lenth-1], Y[lenth-1]);
				}
				if(X[lenth-1]==X[lenth-2]&&Y[lenth-2]<Y[lenth-1]){
					Data.WEIS.paintIcon(this, g, X[lenth-1], Y[lenth-1]);
				}
				if(X[lenth-1]>X[lenth-2]&&Y[lenth-2]==Y[lenth-1]){
					Data.WEIE.paintIcon(this, g, X[lenth-1], Y[lenth-1]);
				}
				if(X[lenth-1]<X[lenth-2]&&Y[lenth-2]==Y[lenth-1]){
					Data.WEIW.paintIcon(this, g, X[lenth-1], Y[lenth-1]);
				}
		}

		Data.shiwu.paintIcon(this,g,foodx,foody);
		if(isStart==false){
			g.setColor(Color.black); 
			g.setFont(new Font("华文行楷",Font.BOLD,40)); 
			g.drawString("暂停", 400, 300);
		}
		if(isFail){
			g.setColor(Color.red); 
			g.setFont(new Font("华文行楷",Font.BOLD,40));
			g.drawString("重新开始", 200, 300);
		}
	}

	@Override
	public void keyPressed(KeyEvent e){
		int keyCode=e.getKeyCode();
		if(keyCode==KeyEvent.VK_SPACE){ 
			if(isFail){
				isFail=false;
				init();  
			}
			else{
				isStart=!isStart;
			}
			repaint(); 
		}  
		if(keyCode==KeyEvent.VK_LEFT ){
			if(fx_sur.equals("R")){
				fx="R";
			}
			else{
				fx="L";
			}
		}
		else if(keyCode==KeyEvent.VK_RIGHT){
			if(fx_sur.equals("L")){
				fx="L";
			}
			else{
				fx="R";
			}
		}
		else if(keyCode==KeyEvent.VK_UP){
			if(fx_sur.equals("D")){
				fx="D";
			}
			else{
				fx="U";
			}
		}
		else if(keyCode==KeyEvent.VK_DOWN){
			if(fx_sur.equals("U")){
				fx="U";
			}
			else{
				fx="D";
			}
		}
		fx_sur=fx;
	}
	
	@Override
	
	public void actionPerformed(ActionEvent e){
		if(isStart && isFail==false){
			for(int i=lenth-1;i>0;i--){
				X[i]=X[i-1];
				Y[i]=Y[i-1];
			}
			if(fx.equals("R")){
				X[0]=X[0]+25;  
				if(X[0]>850) {
					X[0]=25;
				}
			}
			else if(fx.equals("L")){
				X[0]=X[0]-25; 
				if(X[0]<0) {
					X[0]=850;
				}
			}
			else if(fx.equals("U")){
				Y[0]=Y[0]-25;  
				if(Y[0]<25){
					Y[0]=650;
				}
			}
			else if(fx.equals("D")){
				Y[0]=Y[0]+25;  
				if(Y[0]>650){
					Y[0]=0;
				}
			}
			if(X[0]==foodx &&Y[0]==foody){
				lenth++;
				score+=1;
				if(score<18) {
					timer.setDelay(300-score*10);
				}
				else {
					timer.setDelay(138-score);
				}
				foody=25+25*random.nextInt(24);
				foodx=25+25*random.nextInt(34);
			}
			for(int i=1;i<lenth;i++){
				if(X[0]==X[i]&&Y[0]==Y[i]){
				isFail=true;
				}
			}
			repaint();
		}
		timer.start();
	}
	
	@Override
	public void keyTyped(KeyEvent e){}
	
	@Override
	public void keyReleased(KeyEvent e){}
}
