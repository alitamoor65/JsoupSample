package com.example.alitamoor.jsoup.datamodels;

public class DMatchLiveDetailsItem {

    private String matchPlayerName;
    private String matchPlayerRunsOvers;
    private String matchPlayerBallsMedian;
    private String matchPlayerFoursRuns;
    private String matchPlayerSixWictkets;
    private String matchPlayerStrikeRateEconomy;

    public String getMatchPlayerName() {
        return matchPlayerName;
    }

    public void setMatchPlayerName(String matchPlayerName) {
        this.matchPlayerName = matchPlayerName;
    }

    public String getMatchPlayerRunsOvers() {
        return matchPlayerRunsOvers;
    }

    public void setMatchPlayerRunsOvers(String matchPlayerRunsOvers) {
        this.matchPlayerRunsOvers = matchPlayerRunsOvers;
    }

    public String getMatchPlayerBallsMedian() {
        return matchPlayerBallsMedian;
    }

    public void setMatchPlayerBallsMedian(String matchPlayerBallsMedian) {
        this.matchPlayerBallsMedian = matchPlayerBallsMedian;
    }

    public String getMatchPlayerFoursRuns() {
        return matchPlayerFoursRuns;
    }

    public void setMatchPlayerFoursRuns(String matchPlayerFoursRuns) {
        this.matchPlayerFoursRuns = matchPlayerFoursRuns;
    }

    public String getMatchPlayerSixWictkets() {
        return matchPlayerSixWictkets;
    }

    public void setMatchPlayerSixWictkets(String matchPlayerSixWictkets) {
        this.matchPlayerSixWictkets = matchPlayerSixWictkets;
    }

    public String getMatchPlayerStrikeRateEconomy() {
        return matchPlayerStrikeRateEconomy;
    }

    public void setMatchPlayerStrikeRateEconomy(String matchPlayerStrikeRateEconomy) {
        this.matchPlayerStrikeRateEconomy = matchPlayerStrikeRateEconomy;
    }

    @Override
    public String toString() {
        return "Name: " + matchPlayerName
        +"\nR/O: " + matchPlayerRunsOvers
                +"\nB/M: " + matchPlayerBallsMedian
                +"\n4s/R: " + matchPlayerFoursRuns
                +"\n6s/W: " + matchPlayerSixWictkets
                +"\nSR/Enc: " + matchPlayerStrikeRateEconomy;
    }
}
