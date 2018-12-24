package com.example.alitamoor.jsoup.datamodels;

public class DMatchItem {

   /* Log.i(TAG, "Date: " + element.select("span.cscore_date").text());
                    Log.i(TAG, "Overview: " + element.select("div.cscore_info-overview").text());

                    Log.i(TAG, "Home: " + element.select("li.cscore_item.cscore_item--home").text());
                    Log.i(TAG, "Home Abbrev: " + element.select("li.cscore_item.cscore_item--home").select("span.cscore_name.cscore_name--abbrev").text());
                    Log.i(TAG, "Home Long: " + element.select("li.cscore_item.cscore_item--home").select("span.cscore_name.cscore_name--long").text());
                    Log.i(TAG, "Home Scores: " + element.select("li.cscore_item.cscore_item--home").select("div.cscore_score").text());
                    Log.i(TAG, "Home Scores: " + element.select("li.cscore_item.cscore_item--home").select("span.cscore_overs").text());

                    Log.i(TAG, "Away: " + element.select("li.cscore_item.cscore_item--away").text());
                    Log.i(TAG, "Away Abbrev: " + element.select("li.cscore_item.cscore_item--away").select("span.cscore_name.cscore_name--abbrev").text());
                    Log.i(TAG, "Away Long: " + element.select("li.cscore_item.cscore_item--away").select("span.cscore_name.cscore_name--long").text());
                    Log.i(TAG, "Away Scores: " + element.select("li.cscore_item.cscore_item--away").select("div.cscore_score").text());

    string += element.select("li.cscore_item.cscore_item--home").select("div.cscore_score").select("span.cscore_overs").text() + "\n";
    string += element.select("li.cscore_item.cscore_item--away").select("div.cscore_score").select("span.cscore_overs").text() + "\n";

                    Log.i(TAG, "Button link: " + element.select("a.cscore_details").attr("href"));*/

    private String matchDate;
    private String matchOverview;

    private String matchHome;
    private String matchHomeLogo;
    private String matchHomeAbbriv;
    private String matchHomeLong;
    private String matchHomeScores;
    private String matchHomeOvers;
    private String matchAway;
    private String matchAwayLogo;
    private String matchAwayAbbriv;
    private String matchAwayLong;
    private String matchAwayScores;
    private String matchAwayOvers;
    private String matchLongDesc;
    private String matchDetailsLink;

    public String getMatchHomeLogo() {
        return matchHomeLogo;
    }

    public void setMatchHomeLogo(String matchHomeLogo) {
        this.matchHomeLogo = matchHomeLogo;
    }

    public String getMatchAwayLogo() {
        return matchAwayLogo;
    }

    public void setMatchAwayLogo(String matchAwayLogo) {
        this.matchAwayLogo = matchAwayLogo;
    }

    public String getMatchAway() {
        return matchAway;
    }

    public void setMatchAway(String matchAway) {
        this.matchAway = matchAway;
    }

    public String getMatchLongDesc() {
        return matchLongDesc;
    }

    public void setMatchLongDesc(String matchLongDesc) {
        this.matchLongDesc = matchLongDesc;
    }

    public String getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }

    public String getMatchOverview() {
        return matchOverview;
    }

    public void setMatchOverview(String matchOverview) {
        this.matchOverview = matchOverview;
    }

    public String getMatchHome() {
        return matchHome;
    }

    public void setMatchHome(String matchHome) {
        this.matchHome = matchHome;
    }

    public String getMatchHomeAbbriv() {
        return matchHomeAbbriv;
    }

    public void setMatchHomeAbbriv(String matchHomeAbbriv) {
        this.matchHomeAbbriv = matchHomeAbbriv;
    }

    public String getMatchHomeLong() {
        return matchHomeLong;
    }

    public void setMatchHomeLong(String matchHomeLong) {
        this.matchHomeLong = matchHomeLong;
    }

    public String getMatchHomeScores() {
        return matchHomeScores;
    }

    public void setMatchHomeScores(String matchHomeScores) {
        this.matchHomeScores = matchHomeScores;
    }

    public String getMatchHomeOvers() {
        return matchHomeOvers;
    }

    public void setMatchHomeOvers(String matchHomeOvers) {
        this.matchHomeOvers = matchHomeOvers;
    }

    public String getMatchAwayAbbriv() {
        return matchAwayAbbriv;
    }

    public void setMatchAwayAbbriv(String matchAwayAbbriv) {
        this.matchAwayAbbriv = matchAwayAbbriv;
    }

    public String getMatchAwayLong() {
        return matchAwayLong;
    }

    public void setMatchAwayLong(String matchAwayLong) {
        this.matchAwayLong = matchAwayLong;
    }

    public String getMatchAwayScores() {
        return matchAwayScores;
    }

    public void setMatchAwayScores(String matchAwayScores) {
        this.matchAwayScores = matchAwayScores;
    }

    public String getMatchAwayOvers() {
        return matchAwayOvers;
    }

    public void setMatchAwayOvers(String matchAwayOvers) {
        this.matchAwayOvers = matchAwayOvers;
    }

    public String getMatchDetailsLink() {
        return matchDetailsLink;
    }

    public void setMatchDetailsLink(String matchDetailsLink) {
        this.matchDetailsLink = matchDetailsLink;
    }
}
