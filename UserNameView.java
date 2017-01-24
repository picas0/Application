import java.awt.*;
import javax.swing.*;

public class UserNameView extends JPanel {
	
	private static final long serialVersionUID = 1L;
	User u;
	
	UserNameView(User u){
		this.u = u;
		this.setPreferredSize(new Dimension(100,50));
	}
	
	protected void paintComponent(Graphics g){
		g.setFont(new Font("Courier New",Font.BOLD,20));
		FontMetrics fm = g.getFontMetrics();
		g.drawString(u.userName+":\n",fm.getAscent(), fm.getAscent());
	}
}
