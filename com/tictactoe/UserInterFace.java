package com.tictactoe;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;



public class UserInterFace extends JFrame{
	
	private JPanel panel;
	private JPanel pan;
	public JButton buttons[];
	private NewGameButton newGameBtn;
	private JButton playAgainBtn;
	private JButton exitBtn;
	private JSplitPane sp;
	private TextArea textArea;
	public byte value;
	GameInterface gameInterface;
	
	public void initializeUI() {
		panel = new JPanel();
	    pan = new JPanel();
		buttons = new JButton[9];
		newGameBtn = new NewGameButton("New Game");
		playAgainBtn = new JButton("Play Again");
		exitBtn = new JButton("Exit");
	    sp = new JSplitPane();
	    textArea = newGameBtn.getTextArea();
		value = 0;
	}
	
	public UserInterFace(GameInterface gameInterface) {
		super("UserInterFace");
		System.out.println("about to create user interface");
		initializeUI();
		
		this.gameInterface = gameInterface;
		
		setSize(450,450);
		setResizable(false);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		panel.setLayout(new GridLayout(3,3));
		
		for(int i=0;i<9;i++) {
			buttons[i] = new JButton();
			buttons[i].addActionListener(new ButtonAction(buttons[i], i));
			panel.add(buttons[i]);
		}
		sp.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		sp.setDividerLocation(250);
		sp.setLeftComponent(panel);
		sp.setRightComponent(pan);
		pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
		pan.add(newGameBtn);
		pan.add(newGameBtn.getTextArea());
		pan.add(playAgainBtn);
		pan.add(exitBtn);
		add(sp);
		setVisible(true);
		textArea.setText("");
		
		newGameBtn.addActionListener(new ButtonAction(newGameBtn));
		playAgainBtn.addActionListener(new ButtonAction(playAgainBtn));
		exitBtn.addActionListener(new ButtonAction(exitBtn));
		
	}
	
	public void setButtonText( int buttonIndex, String signature) {
		buttons[buttonIndex - 1].setText(signature);
	}
	
	private class ButtonAction implements ActionListener{
		private JButton jbutton;
		
		private int action;
		public ButtonAction(JButton jbutton, int action) {
			this.action = action;
			this.jbutton = jbutton;
			
		}
		public ButtonAction(JButton jbutton) {
			this.jbutton = jbutton;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(jbutton.getActionCommand().equalsIgnoreCase("New Game")) {
				gameInterface.processNewGame();
			}
			else if(jbutton.getActionCommand().equalsIgnoreCase("exit")) {
				System.exit(0);
			}
			else if(jbutton.getActionCommand().equalsIgnoreCase("play again")) {
				gameInterface.processPlayAgain();
			}
			else {
				
				System.out.println("action : " + action);
				gameInterface.processPlayerTurn(action + 1);
				

			}
		
			}
		}
	
	//function to write on display board
	public void println(String message) {
			 textArea.append("\n" + message);
	}
	
	//function to clear display board
	public void clearScreen() {
		textArea.setText("");
	}
	
	//function to clear button
	public void clearButton() {
		for(int i=0;i<9;i++) {
			buttons[i].setText("");
		}
	}
	
}

