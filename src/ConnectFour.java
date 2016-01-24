import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JFrame;



public class ConnectFour 
{

	JFrame frame;
	IntroPanel introPanel;
	Board board;
	MainPanel mainPanel;

	private String player1Name;
	private String player2Name;
	private int p1wins, p2wins;
	private boolean player1Turn;
	private boolean gameOver;
	private boolean p1win;
	private boolean isFactorialSystem = false;


	public ConnectFour()
	{
		player1Name = "Player 1";
		player2Name = "Player 2";
		p1wins = 0;
		p2wins = 0;
		player1Turn = true;
		gameOver = false;
		mainPanel = new MainPanel(this);
		p1win = false;
	}

	public static void main (String[] args)
	{
		ConnectFour c4 = new ConnectFour();
		c4.run();
	}

	public void next()
	{
		mainPanel.next();
	}

	public void prev()
	{
		mainPanel.prev();
	}

	public void setWinner(boolean b)
	{
		p1win = b;
	}

	public boolean getWinner()
	{
		return p1win;
	}

	public void setP1Name(String name)
	{
		name+="         ";
		name = name.substring(0,8);
		player1Name = name;
	}

	public void setP2Name(String name)
	{
		name+="         ";
		name = name.substring(0,8);
		player2Name = name;
	}

	public void setP1Color(Color color)
	{
		mainPanel.setP1Color(color);
	}

	public void setP2Color(Color color)
	{
		mainPanel.setP2Color(color);
	}
	
	public void changeSystem()
	{
		isFactorialSystem = !isFactorialSystem;
	}
	
	public void resetScore()
	{
		p1wins = 0;
		p2wins = 0;
	}

	public void addWins(boolean b, int numWins)
	{
		if(!isFactorialSystem)
		{
			numWins = (int)(Math.pow(2,numWins-1));
		}
		else
		{
			if(numWins==3)
				numWins = 6;
			else if(numWins==4)
				numWins = 24;
			else if(numWins==5)
				numWins = 120;
			else if(numWins==6)
				numWins = 720;
			else if(numWins==7)
				numWins = 5040;
			else if(numWins==8)
				numWins = 40320;
			else if(numWins==9)
				numWins = 362880;
			else if(numWins==10)
				numWins = 3628800;
		}
		if(b)
			p1wins+=numWins;
		else
			p2wins+=numWins;
	}

	public void setGameOver(boolean b)
	{
		gameOver = b;
	}

	public boolean getGameOver()
	{
		return gameOver;
	}

	public void setTurn(boolean b)
	{
		player1Turn = b;
	}

	public void switchTurn()
	{
		player1Turn = !player1Turn;
	}

	public boolean getTurn()
	{
		return player1Turn;
	}

	public int getWins(int player)
	{
		if(player==1)
			return p1wins;
		else 
			return p2wins;
	}

	public String getName(int player)
	{
		if(player==1)
			return player1Name;
		else
			return player2Name;
	}

	public void run()
	{
		frame = new JFrame("Connect 4"); //new jframe object
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit program when closed

		// Create JPanel and add to frame

		//introPanel = new IntroPanel(); //new panel object
		board = new Board(this);
		frame.add(mainPanel, BorderLayout.CENTER); // add panel to frame

		//frame.setResizable(false);
		frame.setLocation(150,10);
		frame.setSize(647, 718);  // 638*659
		frame.setVisible(true);  // makes frame visible
	}
}
