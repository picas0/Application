import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class Main{
	static int x=100,y=100,width=400,height=400;
	static Color bg = new Color(0,60,60);
	static Color fg = new Color(0,60,60);
	public static void main(String args[]){
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				JFrame screen = new JFrame();
				screen.setBounds(x, y, width, height);
				screen.setResizable(false);
				screen.setLayout(new GridLayout());
				screen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				screen.setVisible(true);
				JPanel box = new JPanel();
				box.setBounds(x, y, width, height);
				box.setBackground(bg);
				screen.add(box);
			}			
		});
	}
}
