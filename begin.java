package tcs;
import javax.swing.*;
public class begin {
	public static void main(String[] args) {
		JFrame frame=new JFrame();
		frame.setTitle("贪吃蛇");
		frame.setBounds(10, 10, 900, 720);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new Gamepanel());
		frame.setVisible(true); 
	}
}
