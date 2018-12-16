package ChessInterface;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Desktop;
import java.awt.Font;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TutorialPage {

	private JFrame frame;
	private ChessBoxMainPage my_main = null;	

	/**
	 * Create the application.
	 */
	public TutorialPage(ChessBoxMainPage main_page) {
		this.my_main = main_page;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 487, 326);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		//Set back ground
		try {
			BufferedImage myImage = ImageIO.read(TutorialPage.this.getClass().getResource("/learn.jpg"));
			ImagePanel imagePanel = new ImagePanel(myImage);
			frame.setContentPane(imagePanel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Background picture lost");
		}
		
		JLabel lblNewLabel = new JLabel("Tutotials For Chess Games");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		lblNewLabel.setBounds(128, 11, 234, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("INT Chess");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				URL url = null;
				try {
					url = new URL("https://www.youtube.com/watch?v=NegUzPHd-xs");
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				openWebpage(url);
				
			}
		});
		btnNewButton.setBounds(52, 92, 137, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Chinese Chess");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				URL url = null;
				try {
					url = new URL("https://www.youtube.com/watch?v=0UuDiL9CLPI");
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				openWebpage(url);
			}
		});
		btnNewButton_1.setBounds(265, 92, 137, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Shogi");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				URL url = null;
				try {
					url = new URL("https://www.youtube.com/watch?v=wditZ3EPX3Y");
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				openWebpage(url);
			}
		});
		btnNewButton_2.setBounds(52, 150, 137, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Jungle");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				URL url = null;
				try {
					url = new URL("https://www.youtube.com/watch?time_continue=74&v=3Uh2jrucELE");
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				openWebpage(url);
			}
		});
		btnNewButton_3.setBounds(265, 150, 137, 23);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Back");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setFrame(false);
				my_main.setFrame(true);
			}
			
		});
		btnNewButton_4.setBounds(169, 240, 137, 23);
		frame.getContentPane().add(btnNewButton_4);
		
		JLabel lblNewLabel_1 = new JLabel("Thanks for AncientChess's wonderful tutorial videos");
		lblNewLabel_1.setBounds(117, 40, 258, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		
		
	}
	
	public void setFrame(boolean display) {
		frame.setVisible(display);
	}
	
	public static boolean openWebpage(URI uri) {
	    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
	    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
	        try {
	            desktop.browse(uri);
	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    return false;
	}
	
	public static boolean openWebpage(URL url) {
	    try {
	        return openWebpage(url.toURI());
	    } catch (URISyntaxException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
}
