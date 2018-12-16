package ChessInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

public class GameTimer extends Thread {
	
	private Timer t = null;
	
	private JLabel countdownlabel = null;
	
	public GameTimer(JLabel counterdownlabel) {
		this.countdownlabel = counterdownlabel;
	}
	
	private static String format(int i) {
        String result = String.valueOf(i);
        if (result.length() == 1) {
            result = "0" + result;
        }
        return result;
    }
	
	@Override 
	public void start() {
		t = new Timer(1000, new ActionListener() {
            int time = 60;
            @Override
            public void actionPerformed(ActionEvent e) {
                time--;
                countdownlabel.setText(format(time / 60) + ":" + format(time % 60));
                if (time == 0) {
                    final Timer timer = (Timer) e.getSource();
                    timer.stop();
                }
            }
        });
		t.start();
	}    
	

	
	public void pause() {
		t.stop();
	}
	
	public void restart() {
		t.start();
	}
}
