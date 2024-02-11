public class VoterPage extends DBManipulations{
	VoterPage(){
		while(true) {
			System.out.println("[A]-vote now!");
			System.out.println("[B]-Back");
			System.out.println("Remainder:Make sure that the data you enter are valid");
			char type=Character.toLowerCase(sc.next().charAt(0));
			if(type=='a') {
				int voterId;
				String president,vicePresident,secretary;
				System.out.println("voting form");
				System.out.println("Enter your voter id:");
				voterId=sc.nextInt();
				System.out.println("Enter president name:");
				president=sc.next();
				System.out.println("Enter vice president name:");
				vicePresident=sc.next();
				System.out.println("Enter secretary name:");
				secretary=sc.next();
				System.out.println("Submit your vote now? [Y/N]");
				char  agree=Character.toLowerCase(sc.next().charAt(0));
				if(agree=='y') {
					if(doVote(voterId,president,vicePresident,secretary)) {
						System.out.println("your vote has been saved!!!");
					}
					else {
						System.out.println("Try again");
					}
					new AdminPage().votingLiveDetails();
				}
			}
			else break;
		}
	}
	public void finalize() {
		sc.close();
	}
}
