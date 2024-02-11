import java.util.*;
import java.sql.*;

public class Main{
	static void goToAdminPage(String username) {
		AdminPage ap=new AdminPage(username);
	}
	static void goToVoterPage() {
		new VoterPage();
	}
	public static void main(String arg[]) {
		try {

			Scanner sc=new Scanner(System.in);
			DBManipulations dm=new DBManipulations();
			while(true) {
				System.out.println("[A]-Admin\n[B]-Voter\nOther key for exit");
				System.out.println("Login as?");
				char option=sc.next().toLowerCase().charAt(0);
				if(option=='a') {
					System.out.print("Admin username:");
					String username=sc.next();
					System.out.print("Admin password");
					String password=sc.next();
					if(dm.isAdminThere(username, password)) 
					{
						goToAdminPage(username);
					}
					else {
						System.out.println("Invalid username or password.");
					}
				}
				else if(option=='b') {
					goToVoterPage();
				}
				else break;
				
			}
			sc.close();
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
