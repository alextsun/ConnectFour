import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class IntroPanel extends JPanel
{
	JButton startGame;
	private JRadioButton p1Radio, p2Radio;
	private JRadioButton p1Blue, p1Red, p1Magenta, p1Green, p1Gray, p1Black;
	private JRadioButton p2Blue, p2Red, p2Magenta, p2Green, p2Gray, p2Black;
	private P1ColorPanel p1ColorPanel = new P1ColorPanel();
	private P2ColorPanel p2ColorPanel = new P2ColorPanel();
	private FirstPanel firstPanel = new FirstPanel();
	private JLabel title, p1, p2, first, p1Name, p2Name, chooseColor, eightChars; 
	private JLabel instructions, instructions1, instructions2, instructions3, instructions4;
	private JTextField p1NameArea, p2NameArea;
	private JLabel creator;
	//private CardLayout cards;
	private ConnectFour c4;
	
	public IntroPanel(ConnectFour connect4)
	{
		setBackground(Color.black);
		//cards = new CardLayout();
		title = new JLabel("Connect More");
		title.setFont(new Font("Harlow Solid Italic", Font.ITALIC, 100));
		title.setForeground(Color.red);
		
		p1Name = new JLabel("Player 1 Name:");
		p1Name.setFont(new Font("Harlow Solid Italic", Font.BOLD, 20));
		p1Name.setForeground(Color.white);
		
		p2Name = new JLabel("Player 2 Name:");
		p2Name.setFont(new Font("Harlow Solid Italic", Font.BOLD, 20));
		p2Name.setForeground(Color.white);
		
		p1 = new JLabel("Player 1:");
		p1.setFont(new Font("Harlow Solid Italic", Font.BOLD, 20));
		p1.setForeground(Color.white);
		
		p2 = new JLabel("Player 2:");
		p2.setFont(new Font("Harlow Solid Italic", Font.BOLD, 20));
		p2.setForeground(Color.white);
		
		first = new JLabel("First:");
		first.setFont(new Font("Harlow Solid Italic", Font.BOLD, 20));
		first.setForeground(Color.white);
		
		chooseColor = new JLabel("Choose a chip color and who will go first below:");
		chooseColor.setFont(new Font("Harlow Solid Italic", Font.BOLD, 20));
		chooseColor.setForeground(Color.white);
		
		eightChars = new JLabel("Enter your name (8 characters max)");
		eightChars.setFont(new Font("Harlow Solid Italic", Font.BOLD, 20));
		eightChars.setForeground(Color.white);
		
		String instruct = "Instructions";
		instructions = new JLabel(instruct);
		instructions.setFont(new Font("Harlow Solid Italic", Font.BOLD, 30));
		instructions.setForeground(Color.cyan);
		
		String instruct1 = "Player 1: Use 'a' and 'd', 's' to drop the chip";
		instructions1 = new JLabel(instruct1);
		instructions1.setFont(new Font("Harlow Solid Italic", Font.BOLD, 20));
		instructions1.setForeground(Color.cyan);
		
		String instruct2 = "Player 2: Use left and right arrow keys, down key to drop the chip";
		instructions2 = new JLabel(instruct2);
		instructions2.setFont(new Font("Harlow Solid Italic", Font.BOLD, 20));
		instructions2.setForeground(Color.cyan);
		
		String instruct3 = "Both players may use the mouse, click to drop the chip";
		instructions3 = new JLabel(instruct3);
		instructions3.setFont(new Font("Harlow Solid Italic", Font.BOLD, 20));
		instructions3.setForeground(Color.cyan);
		
		String instruct4 = "After each game, press 'r' to reset the board and start again";
		instructions4 = new JLabel(instruct4);
		instructions4.setFont(new Font("Harlow Solid Italic", Font.BOLD, 20));
		instructions4.setForeground(Color.cyan);
		
		String create = "by Alex Tsun";
		creator = new JLabel(create);
		creator.setFont(new Font("Harlow Solid Italic", Font.BOLD, 20));
		creator.setForeground(Color.cyan);
		
		
		
		p1ColorPanel.setBackground(Color.black);
		p2ColorPanel.setBackground(Color.black);
		firstPanel.setBackground(Color.black);
		
		
		
		p1NameArea = new JTextField("Player 1");
		p2NameArea = new JTextField("Player 2");
		p1NameArea.setFont(new Font("Harlow Solid Italic", Font.BOLD, 28));
		p2NameArea.setFont(new Font("Harlow Solid Italic", Font.BOLD, 28));
		p1NameArea.setForeground(Color.white);
		p2NameArea.setForeground(Color.white);
		p1NameArea.setBackground(Color.black);
		p2NameArea.setBackground(Color.black);
		
		creator.setForeground(Color.cyan);
		
		c4 = connect4;
		setLayout(null);
		startGame = new JButton("Start Game");
		ActionListener startListener = new StartListener();
		startGame.addActionListener(startListener);
		add(firstPanel);
		add(p1ColorPanel);
		add(p2ColorPanel);
		add(startGame);
		add(title);
		add(p1);
		add(p2);
		add(first);
		add(p1Name);
		add(p2Name);
		add(p1NameArea);
		add(p2NameArea);
		add(chooseColor);
		add(eightChars);
		add(instructions);
		add(instructions1);
		add(instructions2);
		add(instructions3);
		add(instructions4);
		add(creator);
		
		startGame.setFont(new Font("Harlow Solid Italic", Font.ITALIC, 40));
		startGame.setBackground(Color.black);
		startGame.setForeground(Color.green);
		
		creator.setBounds(480,90,130,50);
		instructions.setBounds(250,460,630,70);
		instructions1.setBounds(25,500,630,50);
		instructions2.setBounds(25,525,630,50);
		instructions3.setBounds(25,550,630,50);
		instructions4.setBounds(25,575,630,50);
		eightChars.setBounds(25,122,500,50);
		chooseColor.setBounds(25,280,500,50);
		p1Name.setBounds(25,172,500,50);
		p2Name.setBounds(25,222,500,50);
		p1NameArea.setBounds(200,177,300,40);
		p2NameArea.setBounds(200,227,300,40);
		title.setBounds(35,10,600,100);
		p1ColorPanel.setBounds(120,330,500,50);
		p2ColorPanel.setBounds(120,380,500,50);
		p1.setBounds(25,327,500,50);
		p2.setBounds(25,377,500,50);
		first.setBounds(25,427,500,50);
		firstPanel.setBounds(120,427,500,50);
		startGame.setBounds(0, 620, 630, 50);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
	}
	
	class StartListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			c4.setP1Name(p1NameArea.getText());
			c4.setP2Name(p2NameArea.getText());
			c4.next();
		}
	}
	
	class FirstPanel extends JPanel
	{
		public FirstPanel()
		{
			setLayout(new FlowLayout());
			ButtonGroup playerGroup = new ButtonGroup(); //group of buttons
			
			p1Radio = new JRadioButton("Player 1");  // Create a button.
			playerGroup.add(p1Radio);            // Add it to the group.

			p2Radio = new JRadioButton("Player 2"); // Create a button.
			playerGroup.add(p2Radio); // Add it to the group.
			
			p1Radio.setSelected(true);  // Make an initial selection.

			JPanel radioButtonPanel = new JPanel(); //radiobutton panel
			radioButtonPanel.add(p1Radio); //add red button to it
			radioButtonPanel.add(p2Radio); //add green button to it
			add(radioButtonPanel); //add this panel to the control panel
			
			ActionListener rbListener = new PRBListener(); //need an AL

			//add AL rblistener to each radiobutton
			p1Radio.addActionListener(rbListener);
			p2Radio.addActionListener(rbListener);
		}
	}
	
	class PRBListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(p1Radio.isSelected())
				c4.setTurn(true);
			else if(p2Radio.isSelected())
				c4.setTurn(false);
		}
	}
	class P1ColorPanel extends JPanel //radio button panel
	{

		public P1ColorPanel()
		{
			setLayout(new FlowLayout()); //flow layout

			//jradiobutton
			ButtonGroup colorGroup = new ButtonGroup(); //group of buttons

			p1Blue = new JRadioButton("Blue");  // Create a button.
			colorGroup.add(p1Blue);            // Add it to the group.

			p1Red = new JRadioButton("Red"); // Create a button.
			colorGroup.add(p1Red); // Add it to the group.

			p1Magenta = new JRadioButton("Magenta"); // Create a button.
			colorGroup.add(p1Magenta); // Add it to the group.
			
			p1Green = new JRadioButton("Green");  // Create a button.
			colorGroup.add(p1Green);            // Add it to the group.

			p1Gray = new JRadioButton("Gray"); // Create a button.
			colorGroup.add(p1Gray); // Add it to the group.

			//p1Black = new JRadioButton("Black"); // Create a button.
			//colorGroup.add(p1Black); // Add it to the group.

			p1Red.setSelected(true);  // Make an initial selection.

			JPanel radioButtonPanel = new JPanel(); //radiobutton panel
			radioButtonPanel.add(p1Blue); //add red button to it
			radioButtonPanel.add(p1Red); //add green button to it
			radioButtonPanel.add(p1Magenta); //add blue button to it
			radioButtonPanel.add(p1Green); //add red button to it
			radioButtonPanel.add(p1Gray); //add green button to it
			//radioButtonPanel.add(p1Black); //add blue button to it
			add(radioButtonPanel); //add this panel to the control panel
			
			ActionListener rbListener = new P1RBListener(); //need an AL

			//add AL rblistener to each radiobutton
			p1Blue.addActionListener(rbListener);
			p1Red.addActionListener(rbListener);
			p1Magenta.addActionListener(rbListener);
			p1Green.addActionListener(rbListener);
			p1Gray.addActionListener(rbListener);
			//p1Black.addActionListener(rbListener);
		}

	}
	
	class P2ColorPanel extends JPanel //radio button panel
	{

		public P2ColorPanel()
		{
			setLayout(new FlowLayout()); //flow layout

			//jradiobutton
			ButtonGroup colorGroup = new ButtonGroup(); //group of buttons

			p2Blue = new JRadioButton("Blue");  // Create a button.
			colorGroup.add(p2Blue);            // Add it to the group.

			p2Red = new JRadioButton("Red"); // Create a button.
			colorGroup.add(p2Red); // Add it to the group.

			p2Magenta = new JRadioButton("Magenta"); // Create a button.
			colorGroup.add(p2Magenta); // Add it to the group.
			
			p2Green = new JRadioButton("Green");  // Create a button.
			colorGroup.add(p2Green);            // Add it to the group.

			p2Gray = new JRadioButton("Gray"); // Create a button.
			colorGroup.add(p2Gray); // Add it to the group.

			//p2Black = new JRadioButton("Black"); // Create a button.
			//colorGroup.add(p2Black); // Add it to the group.

			p2Blue.setSelected(true);  // Make an initial selection.

			JPanel radioButtonPanel = new JPanel(); //radiobutton panel
			radioButtonPanel.add(p2Blue); //add red button to it
			radioButtonPanel.add(p2Red); //add green button to it
			radioButtonPanel.add(p2Magenta); //add blue button to it
			radioButtonPanel.add(p2Green); //add red button to it
			radioButtonPanel.add(p2Gray); //add green button to it
			//radioButtonPanel.add(p2Black); //add blue button to it
			add(radioButtonPanel); //add this panel to the control panel
			ActionListener rbListener = new P2RBListener(); //need an AL

			//add AL rblistener to each radiobutton
			p2Blue.addActionListener(rbListener);
			p2Red.addActionListener(rbListener);
			p2Magenta.addActionListener(rbListener);
			p2Green.addActionListener(rbListener);
			p2Gray.addActionListener(rbListener);
			//p2Black.addActionListener(rbListener);
		}

	}
	class P1RBListener implements ActionListener //rb listener must implement AL
	{
		public void actionPerformed(ActionEvent e) //required method for AL
		{
			//change color based on selection
			if(p1Blue.isSelected())
				c4.setP1Color(Color.blue);
			else if(p1Red.isSelected())
				c4.setP1Color(Color.red);
			else if(p1Magenta.isSelected())
				c4.setP1Color(Color.magenta);
			else if(p1Green.isSelected())
				c4.setP1Color(Color.green);
			else if(p1Gray.isSelected())
				c4.setP1Color(Color.gray);
			else if(p1Black.isSelected())
				c4.setP1Color(Color.black);
		}
	}
	
	class P2RBListener implements ActionListener //rb listener must implement AL
	{
		public void actionPerformed(ActionEvent e) //required method for AL
		{
			//change color based on selection
			if(p2Blue.isSelected())
				c4.setP2Color(Color.blue);
			else if(p2Red.isSelected())
				c4.setP2Color(Color.red);
			else if(p2Magenta.isSelected())
				c4.setP2Color(Color.magenta);
			else if(p2Green.isSelected())
				c4.setP2Color(Color.green);
			else if(p2Gray.isSelected())
				c4.setP2Color(Color.gray);
			else if(p2Black.isSelected())
				c4.setP2Color(Color.black);
		}
	}
}
