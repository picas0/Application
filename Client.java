import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;

public class Client {
	static InputStreamReader in;
	static PrintWriter out;
	static LoginDetails dialog;
	static Socket s;
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String serverAddress = JOptionPane.showInputDialog(
            "Enter IP Address of a machine that is\n" +
            "running the date service on port 9090:");
        s = new Socket(serverAddress, 9090);
        out = new PrintWriter(s.getOutputStream());
        in = new InputStreamReader(s.getInputStream());
        login();
        s.close();
    }
    
    static void login() {
    	dialog = new LoginDetails();
    	String l=dialog.getDetails();
    	out.write(l+'\n');
		out.flush();
    	recieveData(new BufferedReader(in));
    }
    
    static void recieveData(BufferedReader br) {
    	try {
			String str;
			while((str=br.readLine())==null);
			if(str.charAt(0)==' ') {
				JOptionPane.showMessageDialog(null, str);
				System.exit(0);
			}else {
				for(User u : parseAllEvents(str))
					new UserView(u,out);
			}
    	} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    static LinkedList<User> parseAllEvents(String s) {
    	String events[]=s.split("=");
    	LinkedList<User> l = new LinkedList<User>();
    	for(int i=0;i<events.length;i++)
    		l.add(parseUserEvents(events[i]));
    	return l;
    }
    
    static User parseUserEvents(String s) {
    	String events[]=s.split(" ");
    	LinkedList<Event> l = new LinkedList<Event>();
    	for(int i=1;i<events.length;i++)
    		l.add(parseEvent(events[i]));
    	return new User(events[0],l,
    			events[0].compareTo(dialog.details.split(" ")[2])==0);
    }
    
    static Event parseEvent(String s) {
    	String event[]=s.split(":");
    	return new Event(event[0],Double.parseDouble(event[1]));
    }
}