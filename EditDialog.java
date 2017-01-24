import java.io.*;
import java.util.*;
import javax.swing.*;

public class EditDialog extends Observable{
	JTextField[] details = {new JTextField("Cost"),
			new JTextField("Year"),
			new JTextField("Month"),
			new JTextField("Date")};
	
	EditDialog(Event e, PrintWriter p,String name){
		initializeFields(e);
		JOptionPane.showMessageDialog(null,details);
		sendUpdateToServer(p, name);
	}
	
	void initializeFields(Event e){
		details[0]=new JTextField(e.cost+"");
		String date[] = e.date.split("/");
		details[2]=new JTextField(date[0]);
		details[3]=new JTextField(date[1]);
		details[1]=new JTextField(date[2]);
	}
	
	void sendUpdateToServer(PrintWriter p, String name){
		p.println(name+" "+details[2].getText()+"/"+
				details[3].getText()+"/"+details[1].getText()+
				" "+details[0].getText()+'\n');
		p.flush();
	}
	
}
