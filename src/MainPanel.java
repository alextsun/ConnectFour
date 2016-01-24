import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JPanel;


public class MainPanel extends JPanel 
{
	CardLayout cards;
	ConnectFour c4;
	IntroPanel introPanel;
	Board board;
	
	public MainPanel(ConnectFour connect4)
	{
		c4 = connect4;
		introPanel = new IntroPanel(c4);
		board = new Board(c4);
		cards = new CardLayout();
		setLayout(cards);
		add(introPanel, "Intro");
		add(board, "Board");
	}
	
	public void next()
	{
		cards.next(this);
	}
	
	public void prev()
	{
		cards.previous(this);
	}
	
	public void setP1Color(Color color)
	{
		board.setP1Color(color);
	}
	
	public void setP2Color(Color color)
	{
		board.setP2Color(color);
	}

}
