import javax.swing.*;


public class LoginDetails{
	private String account[]= {"Back","Sign up","Log In"},
			access[]= {"Admin","User"},
			popUp = "Please select one of the following:",
			uNameError = "Username & Password must be 6 or more characters\nlong and"
			+ " contain lowecase English alphabets only.";
	
	public String details = null;
	LoginDetails(){
		details = null;
	}
	String getDetails(){
		if(details==null) getAccessType();
		return details;
	}
	
	private void getAccessType() {
		details = JOptionPane.showOptionDialog(null, popUp, 
				"Admin or User", JOptionPane.DEFAULT_OPTION,
				JOptionPane.PLAIN_MESSAGE,
		        null, access, null)+" ";
		getAccountType();
	}
	
	private void getAccountType() {
		int val = JOptionPane.showOptionDialog(null, popUp, 
				"LogIn or SignUp", JOptionPane.DEFAULT_OPTION,
				JOptionPane.PLAIN_MESSAGE,
		        null, account, null);
		if(val==0) getAccessType();
		else details += val+" "+getCredentials();
	}
	
	private String getCredentials(){		
		JTextField k[]= {new JTextField("username"),
				new JPasswordField("password")};
		JOptionPane.showMessageDialog(null,k);
		if(!validate(k[0].getText()) || !validate(k[1].getText())) {
			JOptionPane.showMessageDialog(null, uNameError);
			return getCredentials();
		}
		return k[0].getText() + " " + k[1].getText();
	}
	
	private boolean validate(String u) {
		if(u.length()<6) return false;
		for(int i=0;i<u.length();i++)
			if(u.charAt(i)<'a' || u.charAt(i)>'z')
				return false;
		return true;
	}
	
}
