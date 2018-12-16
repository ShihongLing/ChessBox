package ChessInterface;


import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameMenuPage {

	private JFrame frame;
	
	private ChessBoxGamePage myGame = null;


	/**
	 * Create the application.
	 */
	public GameMenuPage(ChessBoxGamePage game) {
		this.myGame = game;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 284, 394);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		//Set back ground
		try {
			BufferedImage myImage = ImageIO.read(GameMenuPage.this.getClass().getResource("/menu.png"));
			ImagePanel imagePanel = new ImagePanel(myImage);
			frame.setContentPane(imagePanel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Background picture lost");
		}
		
		JLabel lblNewLabel = new JLabel("Game Menu");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		lblNewLabel.setBounds(79, 11, 123, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Resume");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myGame.setFrame(true);
				if(myGame.getEnemy().equals("Player")) {
					myGame.getTimer().restart();
				}else {
					myGame.getRecorder().restart();
				}
				setFrame(false);
			}
		});
		btnNewButton.setBounds(89, 96, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});
		btnNewButton_1.setBounds(89, 218, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Go Menu");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChessBoxMainPage new_game = new ChessBoxMainPage();
				new_game.setFrame(true);
				frame.dispose();
			}
		});
		btnNewButton_2.setBounds(89, 154, 89, 23);
		frame.getContentPane().add(btnNewButton_2);
	}
	
	public void setFrame(boolean display) {
		frame.setVisible(display);
	}
}
