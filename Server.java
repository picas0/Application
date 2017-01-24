import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
	static String accFile = "UserData.txt";
	static String buy = "UserItems.txt";
	static File accounts = new File(accFile);
	static HashMap<String, String> acc = new HashMap<String,String>(),
			userItems = new HashMap<String, String>();
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket listener = new ServerSocket(9090);
        loadAccounts();
        loadItems();
        try {
        	while (true) {
        		Socket socket = listener.accept();
        		final Socket s = socket;
        		new Thread(new Runnable() {
        			public void run() {
        				try {
        					String details[]=getLoginDetails(s);
        					OutputStreamWriter out = 
        							new OutputStreamWriter(s.getOutputStream());
        					respondToClientRequest(details, out, s);

        				} catch (IOException e){
        					e.printStackTrace();
        				}finally {
        					try {
        						s.close();
        					} catch (IOException e) {
        						e.printStackTrace();
        					}
        				}
        			}
        		}).start();
        	}
        }
        finally {
            listener.close();
        }
    }

	private static void respondToClientRequest(String[] details,
			OutputStreamWriter out, Socket s) throws IOException {
		if(validDetails(details)) {
			if(details[0].compareTo("0")==0) sendItemsToAdmin(out, details, s);
			else sendItemsToUser(out, details, s);
		}else {
			if(details[1].compareTo("1")==0) out.write(" Username you entered already exists!"+'\n');
			else out.write(" No such "+((details[0].compareTo("0")==0)?"Admin":"User")+" found!"+'\n');
			out.flush();
		}
	}
	
	private static void sendItemsToAdmin(OutputStreamWriter o,
			String d[], Socket s) throws IOException {
		String ret="";
		for(Map.Entry<String, String> e : userItems.entrySet())
			ret+="="+e.getValue();
		o.write(ret+'\n');
		o.flush();
	}
	private static void sendItemsToUser(OutputStreamWriter o,
			String d[], Socket s) throws IOException {
		InputStreamReader in = new InputStreamReader(s.getInputStream());
		BufferedReader br = new BufferedReader(in);
		if(userItems.containsKey(d[2])) {
			o.write(userItems.get(d[2])+'\n');
			o.flush();
		}
		String line= br.readLine();
		while(line==null)
			line=br.readLine();
		System.out.println(line);
	}
	
	private static boolean validDetails(String[] details) {
		int access = Integer.parseInt(details[0]),
				action = Integer.parseInt(details[1])-1;
		switch ((access<<1)+action) {
		case 0: return adminSignUp(details[2],details[3]);
		case 1: return adminLogin(details[2],details[3]);
		case 2: return userSignUp(details[2],details[3]);
		case 3: return userLogin(details[2],details[3]);
		}
		return false;
	}
	
	private static void loadAccounts() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(accounts));
        String line;
        while((line=br.readLine())!=null) {
        	String info[]=line.split(" ",2);
        	acc.put(info[0], info[1]);
        }
        br.close();
	}
	
	private static void loadItems() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(buy));
        String line;
        while((line=br.readLine())!=null) {
        	String info[]=line.split(" ",2);
        	userItems.put(info[0], info[0]+" "+info[1]);
        }
        br.close();
	}
	
	private static boolean adminSignUp(String u,String p) {
		if(acc.containsKey(u)) return false;
		acc.put(u, p+" admin");
		writeInFile(u,p,"admin");
		return true;
	}
	
	private static boolean userSignUp(String u,String p) {
		if(acc.containsKey(u)) return false;
		acc.put(u, p+" user");
		writeInFile(u,p,"user");
		return true;
	}
	
	private static boolean adminLogin(String u,String p) {
		if(!acc.containsKey(u)) return false;
		if(acc.get(u).split(" ")[0].compareTo(p)!=0) return false;
		if(acc.get(u).split(" ")[1].compareTo("admin")!=0) return false;
		return true;
	}
	
	private static void writeInFile(String u,
			String p,String s) {
		try {
			FileWriter fw = new FileWriter(accounts, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw);
			out.write(u+" "+p+" "+s+"\n");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static boolean userLogin(String u,String p) {
		if(!acc.containsKey(u)) return false;
		if(acc.get(u).split(" ")[0].compareTo(p)!=0) return false;
		if(acc.get(u).split(" ")[1].compareTo("user")!=0) return false;
		return true;
	}
	
	private static String[] getLoginDetails(Socket socket){
		try {
			InputStreamReader in = 
					new InputStreamReader(socket.getInputStream());
			BufferedReader br = new BufferedReader(in);
			String line=br.readLine();
			return line.split(" ");
		}catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
