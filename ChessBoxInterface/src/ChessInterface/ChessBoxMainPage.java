package ChessInterface;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChessBoxMainPage {

	private JFrame frame;

	private MediaPlayer bgm_player = null;
	
	private ChessBoxSetting sets = null;
	
	private TutorialPage tutor = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					ChessBoxMainPage window = new ChessBoxMainPage();
					window.setFrame(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ChessBoxMainPage() {
		tutor = new TutorialPage(this);
		sets = new ChessBoxSetting(this);
		JFXPanel fxPanel = new JFXPanel();
		URL bgm = ChessBoxMainPage.this.getClass().getResource("/MainPage.mp3");
		this.bgm_player = new MediaPlayer(new Media(bgm.toString()));
		bgm_player.setOnEndOfMedia(new Runnable() {
		       public void run() {
		         bgm_player.seek(Duration.ZERO);
		       }
		   });
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Chess Box Main Menu");
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBackground(Color.WHITE);
		frame.setBounds(100, 100, 950, 720);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Set back ground
		try {
			System.out.println(ChessBoxMainPage.this.getClass().getResource("/main.jpg"));
			BufferedImage myImage = ImageIO.read(ChessBoxMainPage.this.getClass().getResource("/main.jpg"));
			frame.setContentPane(new ImagePanel(myImage));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Background picture lost");
		}
		
		//Set title
		Icon title = new ImageIcon(ChessBoxMainPage.this.getClass().getResource("/Chess Box.png"));		
		JLabel lblNewLabel = new JLabel(title);
		lblNewLabel.setBounds(300, 11, 382, 185);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Tutorial");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tutor.setFrame(true);
				setFrame(false);
			}
		});
		btnNewButton.setBackground(new Color(220, 220, 220));
		btnNewButton.setBounds(467, 207, 89, 23);
		frame.getContentPane().add(btnNewButton);
		//btnNewButton.setOpaque(false);
		//btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		
		JButton btnNewButton_1 = new JButton("Play");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChessBoxGameSetting new_game = new ChessBoxGameSetting();
				new_game.setFrame(true);
				setFrame(false);
			}
		});
		btnNewButton_1.setBackground(new Color(211, 211, 211));
		btnNewButton_1.setBounds(467, 237, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		//btnNewButton_1.setOpaque(false);
		//btnNewButton.setContentAreaFilled(false);
		btnNewButton_1.setBorderPainted(false);
		
		JButton btnNewButton_3 = new JButton("Settings");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sets.setFrame(true);
			}
		});
		btnNewButton_3.setBackground(new Color(192, 192, 192));
		btnNewButton_3.setBounds(467, 271, 89, 23);
		frame.getContentPane().add(btnNewButton_3);
		//btnNewButton_3.setOpaque(false);
		//btnNewButton.setContentAreaFilled(false);
		btnNewButton_3.setBorderPainted(false);
		
		JButton btnNewButton_4 = new JButton("Exit");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnNewButton_4.setBackground(new Color(169, 169, 169));
		btnNewButton_4.setBounds(467, 305, 89, 23);
		frame.getContentPane().add(btnNewButton_4);
		//btnNewButton_4.setOpaque(false);
		//btnNewButton.setContentAreaFilled(false);
		btnNewButton_4.setBorderPainted(false);		
		
	}
	
	
	public void setFrame(boolean display) {
		if(display) {
			this.frame.setVisible(true);
			this.bgm_player.play();
		}
		else {
			this.frame.setVisible(false);
			this.bgm_player.stop();
			
		}
	}
	
	public MediaPlayer getPlayer() {
		return this.bgm_player;
	}
}
