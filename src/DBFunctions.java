public interface DBFunctions {
	public void viewTable(String tableName);
	public boolean isAdminThere(String username,String password);
	public boolean addVoter(String voterName);
	public int getTableSize(String tableName);
	public void addCandidate();
	public boolean deleteVoter(int voter_id);
	public boolean deleteCandidate();
	public boolean doVote(int voter_id,String president,String vicePresident,String secretary);
}
