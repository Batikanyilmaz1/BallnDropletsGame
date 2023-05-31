//Batıkan Yılmaz
//120200036

import java.awt.Color;
import java.awt.Graphics;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Game extends JPanel implements ActionListener, MouseListener {
	Timer timer,timer2, timer3;
	Ball ball;
	private ArrayList<Droplet> droplets = new ArrayList<Droplet>();
	private JMenuBar menuBar;
	private JLabel health, ballBox;
	private JMenu fileMenu, debugMenu;
	private JMenuItem start, pause, exit, changeSpeedBall, changeSpeedDroplets;
	private Random ran;
	private String str1, str2, str3;
	private boolean pauseOp;
	private int int1, int2, int3, yV=4, timeCount;
	private int random=0;
	private BufferedImage ballImage, dropletImage, background;
	final int width = 600;
	final int height = 500;

	Game(){
		//setting panel
		this.setSize(width, height);
		this.setLayout(null);
		this.setBackground(Color.GRAY);
		this.setLocation(0, 0);
		
		//Pause operation
		pauseOp=false;
		
		//images
		try {
			ballImage = ImageIO.read(getClass().getResourceAsStream("/ball.png"));
			dropletImage = ImageIO.read(getClass().getResourceAsStream("/droplet.png"));
			background = ImageIO.read(getClass().getResourceAsStream("/background.jpg"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//creating ball
		ball = new Ball(0, 20, 4, 2);

		//creating and starting timers
		timer = new Timer(30, this);
		timer.start();
		timer2 = new Timer(1000, this);
		timer2.start(); 
		timeCount=0;
		
		//creating health label
		health = new JLabel();
		health.setText("Health: " +ball.getDefHeal());
		health.setSize(100, 50);
		health.setLocation(10, 420);
		add(health);
			
		//creating ball hitbox label
		ballBox = new JLabel();
		ballBox.setSize(40,40);
		add(ballBox);
		ballBox.addMouseListener(this);
		
		//creating menu items and menubar
		menuBar = new JMenuBar();
		menuBar.setSize(600, 20);
		menuBar.setLocation(0, 0);
		fileMenu = new JMenu("File");
		debugMenu = new JMenu("Debug");
		start = new JMenuItem("Start");
		pause = new JMenuItem("Pause");
		exit = new JMenuItem("Exit");
		changeSpeedBall = new JMenuItem("Change Ball Speed");
		changeSpeedDroplets = new JMenuItem("Change Droplets Speed");
		
		//adding items into the menu
		menuBar.add(fileMenu);
		menuBar.add(debugMenu);
		fileMenu.add(start);
		fileMenu.add(pause);
		fileMenu.add(exit);
		debugMenu.add(changeSpeedBall);
		debugMenu.add(changeSpeedDroplets);
		
		//adding action listeners
		start.addActionListener(this);
		pause.addActionListener(this);
		exit.addActionListener(this);
		changeSpeedBall.addActionListener(this);
		changeSpeedDroplets.addActionListener(this);
		
		//adding menubar to panel
		add(menuBar); 
		
		setVisible(true);
	}
	
	//gameover method
	public void gameOver() {
		//if user's health smaller than 0, stop and reset the game and show score
		if(ball.getDefHeal()<=0) {
			timer.stop(); //stops timer
			timer2.stop();
			JOptionPane.showMessageDialog(this, "You Died! \nYou Survived "+ timeCount+" seconds");	 //shows score
			droplets.removeAll(droplets); //removes droplets
			//setting initial properties
			ball.setDefHeal(10);
			ball.setX(0);
			ball.setY(20);
			ball.setxVelocity(4);
			ball.setyVelocity(2);
			yV=4;
			timeCount=0;
			}
		//and then start the game again
		timer.start();
		timer2.start();
	}
		
	//animating ball hitbox with ball at the same time
	public void ballBoxAnimation() {
		ballBox.setLocation(ball.getX(),ball.getY()); 
	}
	
	//if mouse intersects with droplet remove the droplet
	public boolean checkHitBoxes() throws Exception {
		//if mouse is inside the panel
		if(getMousePosition().x < width && getMousePosition().y < height) {
		for(Droplet droplet : droplets) {
			//it creates rectangle for every droplet and create rectangle for mouse. if these two rectangles intersects with each-other remove the droplet
			if (new Rectangle(droplet.getX(), droplet.getY(), 20, 40).intersects(new Rectangle(getMousePosition().x, getMousePosition().y,10,10))){
				droplets.remove(droplet); //remove the droplet 
				ball.setDefHeal(ball.getDefHeal()+droplet.getClickHeal()); //decrease user's heal
				return true;
			}
		}
	}
		else { //else throw exception
			throw new Exception("Out of Mouse");
		}
		return false;
	}
	
	//if ball intersects with droplet remove the droplet
	public boolean checkCollision() {
		for(Droplet droplet : droplets) {
			//it creates rectangle for every droplet and create rectangle for ball. if these two rectangles intersects with each-other remove the droplet
			if (new Rectangle(droplet.getX(), droplet.getY(), 20, 40).intersects(new Rectangle(ball.getX(), ball.getY(),20,20))){
				droplets.remove(droplet); //remove the droplet
				ball.setDefHeal(ball.getDefHeal()-droplet.getDmgCollision()); //decrease user's heal
				return true;
			}
		}
		return false;
	}
	
	//paint method
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		//setting background
		g.drawImage(background, 0, 20, 600, 420, null);
		
		//setting ball
		g.drawImage(ballImage, ball.getX(), ball.getY(), 50, 50, null);
		
		//setting droplets
		for(Droplet droplet : droplets) {
			g.drawImage(dropletImage, droplet.getX(), droplet.getY(), 40, 50, null);
		}
	
		//setting health label
		health.setText("Health: " +ball.getDefHeal());
	}
	
	//creating droplets
	public void enemy(int yVelocity) {
		ran=new Random(); //creating random object
		random = ran.nextInt(width); //selects random number up to panel's width
		droplets.add(new Droplet(random, 20, yV)); //adds the droplet at this random point
		
		//if the droplet goes out of the panel, remove the droplet
		for(int i=0; i<droplets.size(); i++) {
			if(droplets.get(i).getY()>height) {
			droplets.remove(i); //remove the droplet
			ball.setDefHeal(ball.getDefHeal()-5); //decrease user's health
			}
		} 
	}
	
	//exit method
	public void exit() {
		System.exit(1);
	}
	
	//animating the ball and droplets
	public void myAnimation() {
		//if the x is bigger or smaller than panel width make the xVelocity opposite way
		if(ball.getX()>=width-20 || ball.getX()<0) { 
			ball.setxVelocity(ball.getxVelocity()*-1);
		} 
		ball.setX(ball.getX()+ball.getxVelocity()); //increase position of x by xVelocity
		
		//if the y is bigger or smaller than panel height make the yVelocity opposite way
		if(ball.getY()>=height-60 || ball.getY()<20) { 
			ball.setyVelocity(ball.getyVelocity()*-1);
		}
		ball.setY(ball.getY()+ball.getyVelocity()); 
		
		//animating droplets
		for(int i=0; i<droplets.size(); i++) {
			droplets.get(i).setY(droplets.get(i).getY()+droplets.get(i).getyVelocity()); //increase position of y by yVelocity of droplet
		}
	}
	
	//changing ball speed method
	public void changeBallSpeed() {
		//option pane to take input from the user
		str1=JOptionPane.showInputDialog(this, "Change the Ball Speed x: ");
		int1 = Integer.parseInt(str1); //converts the string to the integer
		ball.setxVelocity(int1); //set the xVelocity
		str2=JOptionPane.showInputDialog(this, "Change the Ball Speed Y: ");
		int2 = Integer.parseInt(str2); //converts the string to the integer
		ball.setyVelocity(int2); //set the yVelocity
	}
	
	//chaning droplets speed method
	public void changeDropletSpeed() {
		//option pane to take input from the user
		str3=JOptionPane.showInputDialog(this, "Change the Droplet Speed Y: ");
		int3 = Integer.parseInt(str3); //converts the string to the integer
		//setting the all the droplets speed inside the panel
		for(Droplet droplet : droplets)  {
			droplet.setyVelocity(int3);
		}
		yV=int3; //changing velocity variable with this input so new droplets will have a new speed
	}

	//actionListener methods
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource().equals(timer2)) {
			enemy(yV); //createing new droplets with yV velocity in every 1 second
			timeCount+=1; //add 1 to count in every 1 second
		}
		//calling methods to start game
		if(e.getSource().equals(timer)) {
		myAnimation();
		ballBoxAnimation();
		checkCollision();
		gameOver();
		try {
			checkHitBoxes();
		} catch (Exception e1) {
			e1.printStackTrace();
			} 
		}  
		
		//if clicked start button
		else if (e.getSource().equals(start)) {
			pauseOp = false; //game is not paused
			timer.start();	//start the timer
			timer2.start(); //start the timer2
		//if clicked pause button
		}else if (e.getSource().equals(pause)) {
			pauseOp = true; //game is paused
			timer.stop(); //pause the timer
			timer2.stop(); //pause the timer2
		//if clicked exit button
		}else if (e.getSource().equals(exit)) {
			exit(); //exit the game
		//if clicked change ball speed button	
		}else if(e.getSource().equals(changeSpeedBall)) {
			changeBallSpeed(); //call the changeBallSpeed method
		//if clicked change droplet speed button	
		}else if(e.getSource().equals(changeSpeedDroplets)) {
			changeDropletSpeed(); //call the changeDropletSpeed method
		}
		SwingUtilities.updateComponentTreeUI(this); //updates the components
		repaint(); //repaint method
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		//if clicked ball and game is not paused
		if(e.getSource().equals(ballBox)&& !pauseOp) {
			ball.setDefHeal(ball.getDefHeal()+ball.getClickHeal()); //increase user's health
		}
		repaint(); //repaint method
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}
}
