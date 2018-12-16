package ChessInterface;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSlider;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChessBoxSetting {

	private JFrame frame;

	private ChessBoxMainPage my_main = null;

	/**
	 * Create the application.
	 */
	public ChessBoxSetting(ChessBoxMainPage main_page) {
		this.my_main = main_page;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame("Setting Page");
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 14));
		frame.setBounds(100, 100, 333, 432);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		//Set back ground
		try {
			BufferedImage myImage = ImageIO.read(ChessBoxSetting.this.getClass().getResource("/set.jpg"));
			ImagePanel imagePanel = new ImagePanel(myImage);
			frame.setContentPane(imagePanel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Background picture lost");
		}
		
		JLabel lblNewLabel = new JLabel("Settings");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		lblNewLabel.setBounds(125, 11, 78, 26);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Volume:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(44, 89, 58, 26);
		frame.getContentPane().add(lblNewLabel_1);
		
		JSlider slider = new JSlider();
		slider.setBounds(112, 89, 200, 26);
		frame.getContentPane().add(slider);
		slider.setOpaque(false);
		slider.setValue(100);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setFrame(false);
				float new_value = (float)slider.getValue()/100;
				my_main.getPlayer().setVolume(new_value);
			}
		});
		btnNewButton.setBounds(125, 287, 78, 23);
		frame.getContentPane().add(btnNewButton);
		
		
		
		
	}
	
	public void setFrame(boolean display) {
		frame.setVisible(display);
	}
}
