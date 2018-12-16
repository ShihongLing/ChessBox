package ChessInterface;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import ChessUtility.*;
import ChessUtility.ChessGame.Side;
import InternationalChess.*;
import Xiangqi.*;
import Jungle.*;
import Shogi.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ChessBoxGamePage {

	private JFrame frame;
	private String enemy_type;
	private String game_type;
	private GameMenuPage menu = null;
	private JLabel countdownlabel = null;
	private JLabel countuplabel = null;
	private GameTimer timer = null;
	private GameTimer2 recorder = null;
	
	/**
	 * Create the application.
	 */
	public ChessBoxGamePage(String enemy_type, String game_type) {
		this.game_type = game_type;
		this.enemy_type = enemy_type;
		menu = new GameMenuPage(this);
		this.countdownlabel = new JLabel("01:00");
		this.timer = new GameTimer(this.countdownlabel);
		this.countuplabel = new JLabel("00:00");
		this.recorder = new GameTimer2(this.countuplabel);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1063, 713);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		//Set back ground
		try {
			if(game_type.equals("International")) {
				BufferedImage myImage = ImageIO.read(ChessBoxGamePage.this.getClass().getResource("/1.jpg"));
				frame.setContentPane(new ImagePanel(myImage));
			}
			else if(game_type.equals("Chinese")){
				BufferedImage myImage = ImageIO.read(ChessBoxGamePage.this.getClass().getResource("/2.jpg"));
				frame.setContentPane(new ImagePanel(myImage));
			}
			else if(game_type.equals("Shogi")){
				BufferedImage myImage = ImageIO.read(ChessBoxGamePage.this.getClass().getResource("/3.jpg"));
				frame.setContentPane(new ImagePanel(myImage));
			}else {
					
				BufferedImage myImage = ImageIO.read(ChessBoxGamePage.this.getClass().getResource("/4.jpg"));
				frame.setContentPane(new ImagePanel(myImage));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Background picture lost");
		}
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 42, 621, 621);
		frame.getContentPane().add(panel);
		panel.setLayout(new BorderLayout());
		if(game_type.equals("International")) {
			IChess international = new IChess(enemy_type.equals("AI"));			
			international.setSide(Side.SOUTH);
			panel.add(international.getBoard().getContentPane(),BorderLayout.CENTER);
		}
		else if(game_type.equals("Chinese")){
			Xiangqi international = new Xiangqi(enemy_type.equals("AI"));			
			panel.add(international.getBoard().getContentPane(),BorderLayout.CENTER);			
		}
		else if(game_type.equals("Shogi")){
			Shogi international = new Shogi(enemy_type.equals("AI"));			
			panel.add(international.getBoard().getContentPane(),BorderLayout.CENTER);
			
		}else {
			Jungle international = new Jungle(enemy_type.equals("AI"));			
			panel.add(international.getBoard().getContentPane(),BorderLayout.CENTER);
		}
		
		JButton btnNewButton = new JButton("Menu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setFrame(false);
				if(enemy_type.equals("AI")) {
					recorder.pause();
				}else if(enemy_type.equals("Player")) {
					timer.pause();
				}
				menu.setFrame(true);
			}
		});
		btnNewButton.setBounds(41, 11, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		System.out.println(enemy_type);
		
		Icon enemy_icon = null;
		if(enemy_type.equals("AI")) {
			BufferedImage img = null;
			try {
				img = ImageIO.read(ChessBoxGamePage.this.getClass().getResource("/AI_icon.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			enemy_icon = new ImageIcon(img);	
		}else if(enemy_type.equals("Player")){
			BufferedImage img = null;
			try {
				img = ImageIO.read(ChessBoxGamePage.this.getClass().getResource("/enemy_icon.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			enemy_icon = new ImageIcon(img);
		}
		JLabel lblNewLabel = new JLabel(enemy_icon);
		lblNewLabel.setBounds(756, 95, 180, 180);
		frame.getContentPane().add(lblNewLabel);
		
		Icon player_icon = new ImageIcon(ChessBoxGamePage.this.getClass().getResource("/player_icon.png"));	
		JLabel lblNewLabel_1 = new JLabel(player_icon);
		lblNewLabel_1.setBounds(756, 404, 180, 180);
		frame.getContentPane().add(lblNewLabel_1);
	    

		//need timer
	    JPanel panel_1 = new JPanel();
		panel_1.setBounds(756, 321, 180, 41);
		frame.getContentPane().add(panel_1);
		if(enemy_type.equals("AI")) {
			panel_1.add(countuplabel);
		}else if(enemy_type.equals("Player")) {
			panel_1.add(countdownlabel);
		}
		
		
		//change turn
		JButton btnNewButton_1 = new JButton("Finished");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					countdownlabel.setText("01:00");
					timer.start();
			}
		});
		if(enemy_type.equals("AI")) {
			btnNewButton_1.setVisible(false);
			btnNewButton_1.setEnabled(false);
		}else if(enemy_type.equals("Player")) {
			btnNewButton_1.setVisible(true);
			btnNewButton_1.setEnabled(true);
		}
		
		btnNewButton_1.setBounds(804, 622, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		if(enemy_type.equals("AI")) {
			recorder.start();
		}else if(enemy_type.equals("Player")) {
			timer.start();
		}
		
		
	}
	
	public void setFrame(boolean display) {
		this.frame.setVisible(display);
	}
	
	public GameTimer getTimer() {
		return this.timer;
	}
	
	public GameTimer2 getRecorder() {
		return this.recorder;
	}
	
	public String getEnemy() {
		return this.enemy_type;
	}
}
