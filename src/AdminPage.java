public class AdminPage extends DBManipulations{
	String username;
	AdminPage(String user){
		username=user;
		//System.out.println("Welcome buddy lets create new wonderful world "+user);
		votingLiveDetails();
		doProcess();
		
		//char type=sc.n
	}
	
	public AdminPage() {
	}
	public void votingLiveDetails() {
		System.out.println("******************************************************");
		int totVoter=getTableSize("voter"),totCastVotes=getTableSize("voting_information"),remVotes=totVoter-totCastVotes;
		System.out.println("\tTotal voter: "+totVoter);
		System.out.println("\tTotal cast votes: "+totCastVotes);
		System.out.println("\tRemaining votes: "+remVotes);
		System.out.println("******************************************************");
	}
	private void doProcess() {
		char type='1';

		while(type!='h') {
			System.out.println("[A]-Add Voter");
			System.out.println("[B]-Delete Voter");
			System.out.println("[C]-Add Candidate");
			System.out.println("[D]-Delete Candidate");
			System.out.println("[E]-View voters");
			System.out.println("[F]-View Candidates");
			System.out.println("[G]-View Voting Information");
			System.out.println("[H]-view live details");
			System.out.println("[I]-Back");
			System.out.println("Select operation: ");
			type=Character.toUpperCase(sc.next().charAt(0));
			
			switch(type) {
				case 'A':
					System.out.println("Voter's name: ");
					String voter=sc.next();
					addVoter(voter);
					break;
				case 'B':
					System.out.println("Enter voter id to delete: ");
					int id=sc.nextInt();
					
					if(deleteVoter(id)) {
						System.out.println("voter removed successfully.");
					}
					else System.out.println("can\'t delete...voter id doesn't exist!");
					break;
				case 'C':
					addCandidate();
					break;
				case 'D':
					deleteCandidate();
					break;
				case 'E':
					viewTable("voter");
					break;
				case 'F':
					viewTable("candidates");
					break;
				case 'G':
					viewTable("voting_information");
					break;
				case 'H':
					votingLiveDetails();
					break;
				case 'I':
					return;
				default:
					System.out.println("Invalid operation");
					break;
			}
		}
	}
}
