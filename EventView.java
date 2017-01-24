import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import javax.swing.*;


public class EventView extends JPanel{
	
	private static final long serialVersionUID = 1L;
	static Event e;
	static UserView u;
	JButton edit,delete;
	static PrintWriter out;
	
	EventView(Event e, UserView u, PrintWriter o){
		EventView.e = e;EventView.out = o;EventView.u = u;
		this.setPreferredSize(new Dimension(100,50));
		this.add(edit=new JButton("Edit"));
		this.add(delete=new JButton("Delete"));
		editButton();
		deleteButton();
	}
	
	private void editButton() {
		edit.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e1) {
				if(u.me.owner) {
					new EditDialog(e, out, u.me.userName);
				}else JOptionPane.showMessageDialog(null, "You do not have authorization!");
			}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
		});
	}
	
	private void deleteButton() {
		delete.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e1) {
				if(u.me.owner)
					JOptionPane.showMessageDialog(null, "Not yet implemented!");
				else JOptionPane.showMessageDialog(null, "You do not have authorization!");
			}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
		});
	}
	
	protected void paintComponent(Graphics g){
		g.setFont(new Font("Courier New",Font.BOLD,15));
		FontMetrics fm = g.getFontMetrics();
		g.drawString("\t"+e.date+":\n\t\t$"+e.cost, fm.getAscent(), fm.getAscent());
	}

}
