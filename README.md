# Application
How to run:
  [javac *.java]    # compiles all java files #
  [java Server]     # sets up the Server running #
  [java Client]     # starts the client #
  
What it does? 
  Server loads all accounts in file UserData.txt into a HashMap.
  Server loads all account info from file UserIterms.txt into a HashMap.
  Each time a client tries to connect with the Server, server creates & assigns it a new thread that communicates solely with it.
  This allows running multiple clients without startvation, since all are communicating through seperate threads.
  Clients prompts for which host user would like to connect.
  If the field is empty, it connects with the host/server running on the same machine as client.
  Then client asks wether user is admin or regular and wether he wants to sign up or log in. 
  User can reverse his choices here by clicking on 'back'.
  Then clients aks for username and password. 
  If username & password are not valid i.e. length<6 or and all chars aren't lowercase alphabets), it shows a message and reprompts for info.
  After client has the access (admin/regular), action (signUp/login), username and password - it packs it as a String and sends it to the server.
  If user tries to login, then server verifies his username, password & access.
  If user tries to signup, then server verifies if the uniqueness of his username. If there are no conflicts, it creates a new account and updates the file UserData.txt.
  On successful login/signup, server sends apporpriate data (depending on wether user is admin or regular) residing in file UserItems to the client.
  Client recieves info sent by server. 
  It creates a Frame where each expense (referred to as 'event' in code) is panel. This allows for independent adding/deletion/editing of events.
  Expenses are put on Scrollpane with horizontal and vertical scrollers.
  If user is regular, then only window pops up and he's authorized to make any changes he'd like.
  If user is admin, then windows for all users pop up -- I know this is aweful but I had to 
  demonstrate that admin can only make edits to his account while he can read expenses of all accounts. 
  I could have embeded all windows into a super window but at that time I had higher priority tasks at hand.
  At this point, I hit a roadblock and wasn't able to move forward. Edit Dialog is used to make edits. 
  On clicking 'Ok', its supposed to send updated information to the server.
  It sends the right information but server never recieves it.
  Mostly likely what's happening is that Edit dialog is called from an actionListener that requires arguments to be either final or static 
  and on casting output stream (that is connected to socket) to final, it doesnt work. It works just before its casted and doesnt work after it.
 
 
 
  (My experience with swing and socket is very limited and I couldn't figure out a way to get around this before time ran out.
  It was a fun experience nonetheless, especially because I learnt a lot about servers, clients and threads.)
  
