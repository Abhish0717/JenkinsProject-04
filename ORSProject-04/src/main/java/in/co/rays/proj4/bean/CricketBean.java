package in.co.rays.proj4.bean;

public class CricketBean extends BaseBean {
	private String playerName;
	private String teamName;
	private int runs;
	private int matches;

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public int getRuns() {
		return runs;
	}

	public void setRuns(int runs) {
		this.runs = runs;
	}

	public int getMatches() {
		return matches;
	}

	public void setMatches(int matches) {
		this.matches = matches;
	}

	@Override
	public String getKey() {
		return teamName;
	}

	@Override
	public String getValue() {
		return teamName;
	}
}
