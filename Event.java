public class Event{
	String date;
	double cost;
	Event(String d,double c){
		date = d;
		cost = c;
	}
	public String toString(){
		return "\tDate: "+date+"\tCost: "+cost;
	}
}
