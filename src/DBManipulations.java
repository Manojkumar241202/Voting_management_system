import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

class DBManipulations implements DBFunctions{
	public Connection con;
	public Statement stmt;
	Scanner sc=new Scanner(System.in);
	DBManipulations(){
		
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/OnlineVotingSystem","root","Manojkumar_12");
			stmt=con.createStatement();
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	public void viewTable(String tableName){
		new printTable().printResult(tableName);
	}
	public boolean isAdminThere(String username,String password) {
		try {
			String query="SELECT * FROM admin WHERE username='"+username+"' and password = '"+password+"';";
			ResultSet res=stmt.executeQuery(query);
			return res.next();
			
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		return false;
	}
	
	public boolean addVoter(String voterName) {
		String query="Insert into voter (name) values(\""+voterName+"\")";
		try {
			stmt.execute(query);
			viewTable("voter");
			return true;
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		return false;
	}
	public int getTableSize(String tableName) {
		String query="Select COUNT(*) FROM "+tableName;
		int size=0;
		try {
			ResultSet rs=stmt.executeQuery(query);
			if(rs.next())
				size=rs.getInt(1);
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
		return size;
	}
	public void addCandidate() {
		
		System.out.print("Candidate name: ");
		String candidate=sc.next();
		System.out.println("Candidate party: ");
		String party=sc.next();
		System.out.println("[A] - president");
		System.out.println("[B]- vice president");
		System.out.println("[C] - Secretary");
		System.out.println("Select position: ");
		char pos=Character.toUpperCase(sc.next().charAt(0));
		String position=new String[] {"president","vice president","secretary"}[pos-'A'];
		String query="select * from candidates where party=\'"+party+"\'";
		try {
			ResultSet rs=stmt.executeQuery(query);
			if(!rs.next()) {
				query="insert into candidates (name,position,party) values(\'"+candidate+"\',\'"+party+"\',\'"+position+"\')";
				stmt.execute(query);
				System.out.println("New "+position+" added");
				viewTable("candidates");
			}
			else {
				System.out.println("Can\'t add candidate with the same party and position");
			}
			
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
	}
	public boolean deleteVoter(int voter_id) {
		String query="Delete from voting_information where voter_id="+voter_id; 
		try {
			stmt.execute(query);
			
			query="Delete from voter where voter_id="+voter_id;
			stmt.execute(query);
			viewTable("voter");
			viewTable("voting_information");
			return true;
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		return false;
	}
	public boolean deleteCandidate() {
		System.out.println("Enter candidate id: ");
		String candid=sc.next();
		System.out.println("candid "+candid);
		String query="Delete from candidates where candidate_id="+candid;
		try {
			stmt.execute(query);
			viewTable("candidates");
			return true;
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		return false;
	}
	public boolean doVote(int voter_id,String president,String vicePresident,String secretary) {
		
		try {
			System.out.println("Invalid voteStarting");
			PreparedStatement query=con.prepareStatement("INSERT INTO voting_information VALUES(?,?,?,?)");
			PreparedStatement valid=con.prepareStatement("SELECT * FROM candidates where name=? and position=?");
			valid.setString(1,president);
			valid.setString(2, "president");
			ResultSet rs=valid.executeQuery();
			if(!rs.next()) {
				return false;
			}
			valid.setString(1,vicePresident);
			valid.setString(2, "vice president");
			if(!rs.next()) {
				return false;
			}
			valid.setString(1,secretary);
			valid.setString(2, "secretary");
			rs=valid.executeQuery();
			if(!rs.next()) {
				return false;
			}
			query.setInt(1,voter_id);
			query.setString(2,president);
			query.setString(3,vicePresident);
			query.setString(4,secretary);
			if(query.execute()) {
				return false;
			}
			return true;
		}
		catch(Exception e) {
			System.out.println(e.toString());
			System.out.println("Invalid vote");
		}
		return true;
	}
	public void finalize() {
		sc.close();
	}
}
