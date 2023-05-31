import javax.swing.JFrame;

//Batıkan Yılmaz
//120200036


public class MyFrame extends JFrame {
	
	Game game=new Game();

	MyFrame(){
				
		//setting frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 500);
		this.setLayout(null);
		this.add(game);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		
	}
}

	


