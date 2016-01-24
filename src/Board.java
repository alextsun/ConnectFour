import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.*;

public class Board extends JPanel implements KeyListener, MouseListener, MouseMotionListener
{
	private boolean[][] isVisible;
	private boolean[][] isP1Color;
	private boolean[][] isHighlighted;
	private boolean[] isYellow;
	private ConnectFour c4;
	private int currentLoc;
	private Color p1Color, p2Color;
	private boolean showWinMessage, showTieMessage;
	private int NumWins = 0;
	private boolean p1win = false;
	private boolean cheatsVisible = false;

	public Board(ConnectFour connect4)
	{
		c4 = connect4;
		isVisible = new boolean[7][6];
		isP1Color = new boolean[7][6];
		isHighlighted = new boolean[7][6];
		isYellow = new boolean[7];
		for(int i = 0; i < 7; i++)
		{
			isYellow[i] = true;
		}
		isYellow[3] = false;
		currentLoc = 3;
		p1Color = Color.red;
		p2Color = Color.blue;
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		showWinMessage = false;
		showTieMessage = false;
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		requestFocus();
		setBackground(Color.yellow);
		g.setColor(Color.orange);
		g.fillRect(currentLoc*90, 0, 90, 630);
		isYellow[currentLoc] = true;

		for(int i = 0; i < 7; i++)
		{
			for(int j = 0; j < 6; j++)
			{
				if(isHighlighted[i][j])
				{
					g.setColor(Color.cyan);
					g.fillRect(90*i, 90*j+90, 90, 90);
				}
			}
		}

		g.setColor(Color.black);
		for(int i = 0; i <= 7; i++)
		{
			g.drawLine(90*i,0,90*i,630);
		}

		for(int j = 0; j <= 7; j++)
		{
			g.drawLine(0,90*j,630,90*j);
		}

		g.setColor(Color.white);
		g.fillRect(0,0,637,90);

		for(int a = 0; a < 7; a++)
		{
			for(int b = 0; b < 6; b++)
			{
				if(!isVisible[a][b])
				{
					g.setColor(Color.LIGHT_GRAY);
					g.fillOval(10+90*a,10+90*(b+1),70,70);
				}
				else
				{
					if(isP1Color[a][b])
						g.setColor(p1Color);
					else
						g.setColor(p2Color);
					g.fillOval(10+90*a,10+90*(b+1),70,70);
					g.setColor(Color.white);
					g.setFont(new Font("Harlow Solid Italic", Font.ITALIC, 25));
					g.drawString("C4",10+90*a+20,10+90*(b+1)+42);
				}
			}
		}

		if(c4.getTurn())
			g.setColor(p1Color);
		else
			g.setColor(p2Color);
		
		g.fillOval(10+90*currentLoc,10,70,70);
		g.setColor(Color.white);
		g.setFont(new Font("Harlow Solid Italic", Font.ITALIC, 25));
		g.drawString("C4",10+90*currentLoc+21,52);
		g.setFont(new Font("Harlow Solid Italic", Font.ITALIC, 20));
		String wins = "Wins: "  + c4.getName(1).trim() + ": " + c4.getWins(1) + "    " +
                c4.getName(2).trim() + ": " + c4.getWins(2);
		g.setColor(Color.white);
		g.fillRect(0, 631, 633, 70);
		g.setColor(Color.black);
		g.setFont(new Font("Harlow Solid Italic", Font.BOLD, 35));
		g.drawString(wins,20,667);
		g.setColor(Color.black);
		g.setFont(new Font("Harlow Solid Italic", Font.BOLD, 80));
		if(c4.getGameOver())
		{
			String msg = "";
			if(showWinMessage)
			{
				if(c4.getWinner())
					msg = "Player 1 Wins!";
				else
					msg = "Player 2 Wins!";
				g.drawString(msg, 30, 350);
			}
			else if(showTieMessage)
			{
				msg = "It's a Tie!";
				g.drawString(msg, 120, 350);
			}
		}
		
		if(cheatsVisible)
		{
			g.setFont(new Font("Times New Roman", Font.BOLD, 25));
			g.drawString("Z: reset score",700,50);
			g.drawString("V: cheats visible",700,150);
			g.drawString("q: quit",700,250);
			g.drawString("r: add wins to player 1",700,350);
			g.drawString("C: change scoring system",700,450);
			g.drawString("A: reset board",700,550);
			g.drawString("b: back",700,650);
		}
	}
	
	public void setP1Color(Color color)
	{
		p1Color = color;
	}

	public void setP2Color(Color color)
	{
		p2Color = color;
	}

	public void keyPressed(KeyEvent e)
	{
		if(!c4.getGameOver())
		{
			if(c4.getTurn())
			{
				char ch = e.getKeyChar();
				if(ch=='a')
					currentLoc--;
				else if(ch=='d')
					currentLoc++;
				else if(ch=='s')
					dropChip();
			}
			else
			{
				int ch = e.getKeyCode();
				if(ch==e.VK_LEFT)
					currentLoc--;
				else if(ch==e.VK_RIGHT)
					currentLoc++;
				else if(ch==e.VK_DOWN)
					dropChip();
			}

			if(currentLoc<0)
				currentLoc = 0;
			else if(currentLoc>6)
				currentLoc = 6;
			isYellow[currentLoc] = false;
			requestFocus();
			repaint();
		}
		char ch = e.getKeyChar();
		if(ch=='A')
			reset();
		if(ch=='b')
			c4.prev();
		if(ch=='C')
			c4.changeSystem();
		if(ch=='r')
			c4.addWins(true, 10);
		if(ch=='q')
			System.exit(0);
		if(ch=='Z')
			c4.resetScore();
		if(ch=='V')
			cheatsVisible = !cheatsVisible;
	}
	
	public void dropChip()
	{
		for(int i = 5; i >= 0; i--)
		{
			if(!isVisible[currentLoc][i])
			{
				isVisible[currentLoc][i] = true;
				isP1Color[currentLoc][i] = c4.getTurn();
				c4.switchTurn();
				break;
			}
		}
		detectWin();
		repaint();
	}

	public void reset()
	{
		for(int i = 0; i < 7; i++)
		{
			for(int j = 0; j < 6; j++)
			{
				isP1Color[i][j] = false;
				isVisible[i][j] = false;
				isHighlighted[i][j] = false;
			}
		}
		c4.setGameOver(false);
		repaint();
	}
	
	public boolean detectTie()
	{
		for(int i = 0; i < 7; i++)
		{
			for(int j = 0; j < 6; j++)
			{
				if(!isVisible[i][j])
					return false;
			}
		}
		return true;
	}
	
	public void detectWin()
	{
		int totalWins = detectHorWin()+detectVerWin()+detectDiaWin();
		if(totalWins>0)
		{
			c4.addWins(p1win, NumWins);
			NumWins = 0;
			c4.setGameOver(true);
			showWinMessage = true;
		}
		else if(detectTie())
		{
			c4.setGameOver(true);
			showTieMessage = true;
		}
	}

	public int detectHorWin()
	{
		int numWins = 0;
		for(int j = 0; j < 6; j++)
		{
			for(int i = 0; i < 4; i++)
			{
				if(isHorFilled(i,j)&&isHorSame(i,j))
				{
					if(isP1Color[i][j])
					{
						c4.setWinner(true);
						p1win = true;
						NumWins++;
						c4.setTurn(false);
					}
					else
					{
						c4.setWinner(false);
						p1win = false;
						NumWins++;
						c4.setTurn(true);
					}
					highlightHorWin(i,j);
					numWins++;
				}
			}
		}
		return numWins;
	}
	
	public int detectVerWin()
	{
		int numWins = 0;
		for(int i = 0; i < 7; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				if(isVerFilled(i,j)&&isVerSame(i,j))
				{
					if(isP1Color[i][j])
					{
						c4.setWinner(true);
						p1win = true;
						NumWins++;
						c4.setTurn(false);
					}
					else
					{
						c4.setWinner(false);
						p1win = false;
						NumWins++;
						c4.setTurn(true);
					}
					highlightVerWin(i,j);
					numWins++;
				}
			}
		}
		return numWins;
	}

	public int detectDiaWin()
	{
		return (detectPosDiaWin()+detectNegDiaWin());
	}

	public int detectPosDiaWin()
	{
		int numWins = 0;
		for(int i = 0; i < 4; i++)
		{
			for(int j = 3; j < 6; j++)
			{
				if(isPosDiaFilled(i,j)&&isPosDiaSame(i,j))
				{
					if(isP1Color[i][j])
					{
						c4.setWinner(true);
						p1win = true;
						NumWins++;
						c4.setTurn(false);
					}
					else
					{
						c4.setWinner(false);
						p1win = false;
						NumWins++;
						c4.setTurn(true);
					}
					highlightPosDiaWin(i,j);
					numWins++;
				}
			}
		}
		return numWins;
	}

	public int detectNegDiaWin()
	{
		int numWins = 0;
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				if(isNegDiaFilled(i,j)&&isNegDiaSame(i,j))
				{
					if(isP1Color[i][j])
					{
						c4.setWinner(true);
						p1win = true;
						NumWins++;
						c4.setTurn(false);
					}
					else
					{
						c4.setWinner(false);
						p1win = false;
						NumWins++;
						c4.setTurn(true);
					}
					highlightNegDiaWin(i,j);
					numWins++;
				}
			}
		}
		return numWins;
	}

	public boolean isPosDiaSame(int i, int j)
	{
		return ((isP1Color[i][j]==isP1Color[i+1][j-1])&&(isP1Color[i+1][j-1]==isP1Color[i+2][j-2])
				&&(isP1Color[i+2][j-2]==isP1Color[i+3][j-3]));
	}

	public boolean isNegDiaSame(int i, int j)
	{
		return((isP1Color[i][j]==isP1Color[i+1][j+1])&&(isP1Color[i+1][j+1]==isP1Color[i+2][j+2])
				&&(isP1Color[i+2][j+2]==isP1Color[i+3][j+3]));
	}

	public boolean isHorSame(int i, int j)
	{
		return((isP1Color[i][j]==isP1Color[i+1][j])&&(isP1Color[i+1][j]==isP1Color[i+2][j])
				&&(isP1Color[i+2][j]==isP1Color[i+3][j]));
	}



	public boolean isVerSame(int i, int j)
	{
		return((isP1Color[i][j]==isP1Color[i][j+1])&&(isP1Color[i][j+1]==isP1Color[i][j+2])
				&&(isP1Color[i][j+2]==isP1Color[i][j+3]));
	}

	public boolean isPosDiaFilled(int i, int j)
	{
		return(isVisible[i][j]&&isVisible[i+1][j-1]&&isVisible[i+2][j-2]
				&&isVisible[i+3][j-3]);
	}

	public boolean isNegDiaFilled(int i, int j)
	{
		return(isVisible[i][j]&&isVisible[i+1][j+1]&&isVisible[i+2][j+2]
				&&isVisible[i+3][j+3]);
	}

	public boolean isHorFilled(int i, int j)
	{
		return(isVisible[i][j]&&isVisible[i+1][j]&&isVisible[i+2][j]
				&&isVisible[i+3][j]);
	}
	
	public boolean isVerFilled(int i, int j)
	{
		return (isVisible[i][j]&&isVisible[i][j+1]&&isVisible[i][j+2]
				&&isVisible[i][j+3]);
	}

	public void highlightPosDiaWin(int i, int j)
	{
		isHighlighted[i][j] = true;
		isHighlighted[i+1][j-1] = true;
		isHighlighted[i+2][j-2] = true;
		isHighlighted[i+3][j-3] = true;
	}

	public void highlightNegDiaWin(int i, int j)
	{
		isHighlighted[i][j] = true;
		isHighlighted[i+1][j+1] = true;
		isHighlighted[i+2][j+2] = true;
		isHighlighted[i+3][j+3] = true;
	}

	public void highlightHorWin(int i,int j)
	{
		isHighlighted[i][j] = true;
		isHighlighted[i+1][j] = true;
		isHighlighted[i+2][j] = true;
		isHighlighted[i+3][j] = true;
	}

	public void highlightVerWin(int i, int j)
	{
		isHighlighted[i][j] = true;
		isHighlighted[i][j+1] = true;
		isHighlighted[i][j+2] = true;
		isHighlighted[i][j+3] = true;
	}

	public void keyTyped(KeyEvent e)
	{
		
	}



	public void keyReleased(KeyEvent e)
	{

	}

	public void mouseDragged(MouseEvent e)
	{
		int x = e.getX();
		if(!c4.getGameOver())
		{
			if(x>=0 && x<=90)
				currentLoc = 0;
			else if(x>90 && x<=180)
				currentLoc = 1;
			else if(x>180 && x<=270)
				currentLoc = 2;
			else if(x>270 && x<=360)
				currentLoc = 3;
			else if(x>360 && x<=450)
				currentLoc = 4;
			else if(x>450 && x<=540)
				currentLoc = 5;
			else if(x>540 && x<=630)
				currentLoc = 6;
			repaint();
		}
	}

	public void mouseMoved(MouseEvent e)
	{
		int x = e.getX();
		if(!c4.getGameOver())
		{
			if(x>=0 && x<=90)
				currentLoc = 0;
			else if(x>90 && x<=180)
				currentLoc = 1;
			else if(x>180 && x<=270)
				currentLoc = 2;
			else if(x>270 && x<=360)
				currentLoc = 3;
			else if(x>360 && x<=450)
				currentLoc = 4;
			else if(x>450 && x<=540)
				currentLoc = 5;
			else if(x>540 && x<=630)
				currentLoc = 6;
			repaint();
		}
	}

	public void mouseClicked(MouseEvent e) 
	{

	}

	public void mouseEntered(MouseEvent e)
	{

	}

	public void mouseExited(MouseEvent e) 
	{

	}

	public void mousePressed(MouseEvent e)
	{
		if(!c4.getGameOver())
			dropChip();
	}

	public void mouseReleased(MouseEvent e) 
	{

	}
}
