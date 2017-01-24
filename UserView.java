import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;


public class UserView implements Observer{
	
	JPanel panel;
	User me;
	JScrollPane scroller;
	JFrame frame;
	PrintWriter out;
	int x=100,y=100,width=600,height=600;
	LinkedList<EventView> eventUI = 
			new LinkedList<EventView>();
	
	
	UserView(User u, PrintWriter out){
		panel = new JPanel();
		me = u;
		this.out = out;
		panel.setBounds(x, y, width, height);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		setScreenProperties();
	}
	
	private void setScreenProperties() {
		panel.setVisible(true);
		panel.add(new UserNameView(me));
		for(Event e : me.events) {
			EventView ev;
			panel.add((ev = new EventView(e,this,out)));
			eventUI.add(ev);
		}
		setFrame();
	}
	
	void setFrame(){
		frame = new JFrame();
		frame.add(scroller = new JScrollPane(panel));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setBounds(new Rectangle(0,0,width,height));
	}
	
	@Override
	public void update(Observable o, Object arg) {
		LinkedList<EventView> evr=new LinkedList<EventView>();
		for(EventView ev: eventUI)
			if(!arg.equals(ev)) evr.add(ev); 
		eventUI = evr;
	}
}

