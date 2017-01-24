import java.util.*;

public class User {
	LinkedList<Event> events;
	String userName;
	boolean owner;
	User(String s, LinkedList<Event> l,boolean o){
		events = l;
		userName =s;
		owner = o;
	}
	public String toString(){
		return userName+"\n"+events;
	}
}
