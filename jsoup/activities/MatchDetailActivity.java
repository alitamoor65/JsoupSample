package com.example.alitamoor.jsoup.activities;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.alitamoor.jsoup.adapters.LiveMatchAdapter;
import com.example.alitamoor.jsoup.R;
import com.example.alitamoor.jsoup.datamodels.DMatchItem;
import com.example.alitamoor.jsoup.datamodels.DMatchLiveDetailsItem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

public class MatchDetailActivity extends AppCompatActivity {

    String MAIN_TAG = "MAIN";
    String CONTENT_TAG = "CONTENT";
    String TEXT_TAG = "TEXT";
    private String TAG = "Main";


    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    LiveMatchAdapter liveMatchAdapter;
    ArrayList<DMatchLiveDetailsItem> itemArrayList;
    ReteriveOverViewCardData reteriveOverViewCardData;

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_detail);

        recyclerView = findViewById(R.id.recyclerViewID);
        layoutManager = new LinearLayoutManager(this);
        itemArrayList = new ArrayList<>();

        reteriveOverViewCardData = new ReteriveOverViewCardData();
        reteriveOverViewCardData.execute();
    }

    private class ReteriveOverViewCardData extends AsyncTask<Void, Void, Void>{
        Document doc = null;
        DMatchItem dMatchItem;
        int size;
        ArrayList<DMatchItem> arrayList = new ArrayList<>();
        @Override
        protected Void doInBackground(Void... voids) {

            if(!isCancelled()) {


                String detailURL = getIntent().getStringExtra("URL");
                try {
                    doc = Jsoup.connect(detailURL).header("Accept-Encoding", "gzip, deflate")
                            .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0").get();

                    if (doc == null) {
                        finish();
                    }

                    dMatchItem = new DMatchItem();

                    dMatchItem.setMatchDate(doc.select("div.cscore.cscore--live.cricket").select("div.cscore_date-time").select("span.cscore_date").first().text());
                    dMatchItem.setMatchOverview(doc.select("div.cscore.cscore--live.cricket").select("div.cscore_info-overview").first().text());

                    dMatchItem.setMatchHome(doc.select("div.cscore.cscore--live.cricket").select("div.cscore_details").select("li.cscore_item.cscore_item--home").select("span.cscore_name.cscore_name--abbrev").first().text());
                    dMatchItem.setMatchHomeLogo(doc.select("div.cscore.cscore--live.cricket").select("div.cscore_details").select("li.cscore_item.cscore_item--home").select("a").select("picture").select("img").attr("data-src"));
                    dMatchItem.setMatchHomeLong(doc.select("div.cscore.cscore--live.cricket").select("div.cscore_details").select("li.cscore_item.cscore_item--home").select("span.cscore_name.cscore_name--long").first().text());
                    dMatchItem.setMatchHomeAbbriv(doc.select("div.cscore.cscore--live.cricket").select("div.cscore_details").select("li.cscore_item.cscore_item--home").select("span.cscore_name.cscore_name--abbrev").first().text());
                    dMatchItem.setMatchHomeScores(doc.select("div.cscore.cscore--live.cricket").select("div.cscore_details").select("li.cscore_item.cscore_item--home").select("div.cscore_score").first().text().split("\\(")[0]);
                    if (doc.select("div.cscore.cscore--live.cricket").select("div.cscore_details").select("li.cscore_item.cscore_item--home").select("div.cscore_score").select("span.cscore_overs").first() != null) {
                        dMatchItem.setMatchHomeOvers(doc.select("div.cscore.cscore--live.cricket").select("div.cscore_details").select("li.cscore_item.cscore_item--home").select("div.cscore_score").select("span.cscore_overs").first().text());
                    }

                    dMatchItem.setMatchAway(doc.select("div.cscore.cscore--live.cricket").select("div.cscore_details").select("li.cscore_item.cscore_item--away").select("span.cscore_name.cscore_name--abbrev").first().text());
                    dMatchItem.setMatchAwayLogo(doc.select("div.cscore.cscore--live.cricket").select("div.cscore_details").select("li.cscore_item.cscore_item--away").select("a").select("picture").select("img").attr("data-src"));
                    dMatchItem.setMatchAwayAbbriv(doc.select("div.cscore.cscore--live.cricket").select("div.cscore_details").select("li.cscore_item.cscore_item--away").select("span.cscore_name.cscore_name--abbrev").first().text());
                    dMatchItem.setMatchAwayLong(doc.select("div.cscore.cscore--live.cricket").select("div.cscore_details").select("li.cscore_item.cscore_item--away").select("span.cscore_name.cscore_name--long").first().text());
                    dMatchItem.setMatchAwayScores(doc.select("div.cscore.cscore--live.cricket").select("div.cscore_details").select("li.cscore_item.cscore_item--away").select("div.cscore_score").first().text().split("\\(")[0]);
                    if (doc.select("div.cscore.cscore--live.cricket").select("div.cscore_details").select("li.cscore_item.cscore_item--away").select("div.cscore_score").select("span.cscore_overs").first() != null) {
                        dMatchItem.setMatchAwayOvers(doc.select("div.cscore.cscore--live.cricket").select("div.cscore_details").select("li.cscore_item.cscore_item--away").select("div.cscore_score").select("span.cscore_overs").first().text());
                    }

                    dMatchItem.setMatchLongDesc(doc.select("div.cscore.cscore--live.cricket").select("span.cscore_notes_game").first().text());

                    dMatchItem.setMatchDetailsLink("");
                    size = doc.select("div.content").select("table").select("thead").get(0).select("tr").size();

                    if (size > 2) {
                        Element tableHeadBatting = doc.select("div.content").select("table").select("thead").get(0).select("tr").get(2);
                        Element tableBodyBatting = doc.select("div.content").select("table").select("tbody").get(0);
                        Element playerOneBatting = tableBodyBatting.select("tr").get(0);
                        Element playerTwoBatting = tableBodyBatting.select("tr").get(1);

                        ArrayList<String> battingHeads = new ArrayList<>();
                        ArrayList<String> battingPlayerOne = new ArrayList<>();
                        ArrayList<String> battingPlayerTwo = new ArrayList<>();

                        for (int i = 0; i < tableHeadBatting.select("th").size(); i++) {
                            battingHeads.add(tableHeadBatting.select("th").get(i).text());
                            if(i == 0){
                                battingPlayerOne.add(playerOneBatting.select("td").select("a.long-name").get(i).text());
                                battingPlayerTwo.add(playerTwoBatting.select("td").select("a.long-name").get(i).text());
                            }else {
                                battingPlayerOne.add(playerOneBatting.select("td").get(i).text());
                                battingPlayerTwo.add(playerTwoBatting.select("td").get(i).text());
                            }
                        }
/*
                for (int i = 0; i < battingHeads.size(); i++) {
                    Log.i(CONTENT_TAG, i +": " + battingHeads.get(i) + "|" + battingPlayerOne.get(i) +"|" + battingPlayerTwo.get(i));
                }*/

                        //Element tableHeadBowling = doc.select("div.content").select("table").select("thead").get(1);

                        DMatchLiveDetailsItem liveDetailsItem = new DMatchLiveDetailsItem();
                        liveDetailsItem.setMatchPlayerName(battingHeads.get(0));
                        liveDetailsItem.setMatchPlayerRunsOvers(battingHeads.get(1));
                        liveDetailsItem.setMatchPlayerBallsMedian(battingHeads.get(2));
                        liveDetailsItem.setMatchPlayerFoursRuns(battingHeads.get(3));
                        liveDetailsItem.setMatchPlayerSixWictkets(battingHeads.get(4));
                        liveDetailsItem.setMatchPlayerStrikeRateEconomy(battingHeads.get(5));

                        Log.i(CONTENT_TAG, "doInBackground: " + liveDetailsItem.toString());

                        DMatchLiveDetailsItem liveDetailsItem2 = new DMatchLiveDetailsItem();
                        liveDetailsItem2.setMatchPlayerName(battingPlayerOne.get(0));
                        liveDetailsItem2.setMatchPlayerRunsOvers(battingPlayerOne.get(1));
                        liveDetailsItem2.setMatchPlayerBallsMedian(battingPlayerOne.get(2));
                        liveDetailsItem2.setMatchPlayerFoursRuns(battingPlayerOne.get(3));
                        liveDetailsItem2.setMatchPlayerSixWictkets(battingPlayerOne.get(4));
                        liveDetailsItem2.setMatchPlayerStrikeRateEconomy(battingPlayerOne.get(5));

                        Log.i(CONTENT_TAG, "doInBackground: " + liveDetailsItem2.toString());

                        DMatchLiveDetailsItem liveDetailsItem3 = new DMatchLiveDetailsItem();
                        liveDetailsItem3.setMatchPlayerName(battingPlayerTwo.get(0));
                        liveDetailsItem3.setMatchPlayerRunsOvers(battingPlayerTwo.get(1));
                        liveDetailsItem3.setMatchPlayerBallsMedian(battingPlayerTwo.get(2));
                        liveDetailsItem3.setMatchPlayerFoursRuns(battingPlayerTwo.get(3));
                        liveDetailsItem3.setMatchPlayerSixWictkets(battingPlayerTwo.get(4));
                        liveDetailsItem3.setMatchPlayerStrikeRateEconomy(battingPlayerTwo.get(5));

                        Element tableHeadBowling = doc.select("div.content").select("table").select("thead").get(1);
                        Element tableBodyBowling = doc.select("div.content").select("table").select("tbody").get(1);

                        Element playerOneBowling = tableBodyBowling.select("tr").get(0);
                        Element playerTwoBowling = tableBodyBowling.select("tr").get(1);

                        ArrayList<String> bowlingHeads = new ArrayList<>();
                        ArrayList<String> bowlingPlayerOne = new ArrayList<>();
                        ArrayList<String> bowlingPlayerTwo = new ArrayList<>();

                        for (int i = 0; i < tableHeadBowling.select("th").size(); i++) {
                            bowlingHeads.add(tableHeadBowling.select("th").get(i).text());
                            if( i == 0){
                                bowlingPlayerOne.add(playerOneBowling.select("td").select("a.long-name").get(i).text());
                                bowlingPlayerTwo.add(playerTwoBowling.select("td").select("a.long-name").get(i).text());
                            }else {
                                bowlingPlayerOne.add(playerOneBowling.select("td").get(i).text());
                                bowlingPlayerTwo.add(playerTwoBowling.select("td").get(i).text());
                            }
                        }

               /* for (int i = 0; i < battingHeads.size(); i++) {
                    Log.i(CONTENT_TAG, "" + bowlingHeads.get(i) + ": " + bowlingPlayerOne.get(i) +": " + bowlingPlayerTwo.get(i));
                }*/

                        DMatchLiveDetailsItem liveDetailsItem4 = new DMatchLiveDetailsItem();
                        liveDetailsItem4.setMatchPlayerName(bowlingHeads.get(0));
                        liveDetailsItem4.setMatchPlayerRunsOvers(bowlingHeads.get(1));
                        liveDetailsItem4.setMatchPlayerBallsMedian(bowlingHeads.get(2));
                        liveDetailsItem4.setMatchPlayerFoursRuns(bowlingHeads.get(3));
                        liveDetailsItem4.setMatchPlayerSixWictkets(bowlingHeads.get(4));
                        liveDetailsItem4.setMatchPlayerStrikeRateEconomy(bowlingHeads.get(5));

                        DMatchLiveDetailsItem liveDetailsItem5 = new DMatchLiveDetailsItem();
                        liveDetailsItem5.setMatchPlayerName(bowlingPlayerOne.get(0));
                        liveDetailsItem5.setMatchPlayerRunsOvers(bowlingPlayerOne.get(1));
                        liveDetailsItem5.setMatchPlayerBallsMedian(bowlingPlayerOne.get(2));
                        liveDetailsItem5.setMatchPlayerFoursRuns(bowlingPlayerOne.get(3));
                        liveDetailsItem5.setMatchPlayerSixWictkets(bowlingPlayerOne.get(4));
                        liveDetailsItem5.setMatchPlayerStrikeRateEconomy(bowlingPlayerOne.get(5));

                        DMatchLiveDetailsItem liveDetailsItem6 = new DMatchLiveDetailsItem();
                        liveDetailsItem6.setMatchPlayerName(bowlingPlayerTwo.get(0));
                        liveDetailsItem6.setMatchPlayerRunsOvers(bowlingPlayerTwo.get(1));
                        liveDetailsItem6.setMatchPlayerBallsMedian(bowlingPlayerTwo.get(2));
                        liveDetailsItem6.setMatchPlayerFoursRuns(bowlingPlayerTwo.get(3));
                        liveDetailsItem6.setMatchPlayerSixWictkets(bowlingPlayerTwo.get(4));
                        liveDetailsItem6.setMatchPlayerStrikeRateEconomy(bowlingPlayerTwo.get(5));

                        itemArrayList.add(liveDetailsItem);
                        itemArrayList.add(liveDetailsItem2);
                        itemArrayList.add(liveDetailsItem3);
                        itemArrayList.add(liveDetailsItem4);
                        itemArrayList.add(liveDetailsItem5);
                        itemArrayList.add(liveDetailsItem6);

                /*for (DMatchLiveDetailsItem item : itemArrayList) {
                    Log.i(CONTENT_TAG, "doInBackground: " + item.toString());
                }*/
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("ERROR", "doInBackground: ", e.getCause());
                }
            }
            return null;
        }

        @Override
        protected void onCancelled() {
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            setMatchOverViewCardData(dMatchItem);
            if(size > 2){
                setAdapter();
            }
            handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    reteriveOverViewCardData.cancel(true);
                    reteriveOverViewCardData = null;
                    itemArrayList.clear();
                    dMatchItem = null;
                    reteriveOverViewCardData = new ReteriveOverViewCardData();
                    reteriveOverViewCardData.execute();
                }
            },6000);

        }
    }

    void setMatchOverViewCardData(DMatchItem matchItem){
        TextView datE,homE,away,homeScore,awayScore,overview,longDesc,homeOvers,awayOvers;
        ImageView imageViewHome,imageViewAway;
        imageViewAway = findViewById(R.id.matchAwayLogoId);
        imageViewHome = findViewById(R.id.matchHomeLogoID);
        Glide.with(this).load(matchItem.getMatchHomeLogo()).into(imageViewHome);
        Glide.with(this).load(matchItem.getMatchAwayLogo()).into(imageViewAway);
        datE = findViewById(R.id.matchDatetextView);
        homE = findViewById(R.id.matchHomeAbrtextView);
        away = findViewById(R.id.matchAwayAbrtextView);
        homeScore = findViewById(R.id.matchHomeScreetextView);
        awayScore = findViewById(R.id.matchAwayScoretextView);
        homeOvers = findViewById(R.id.matchHomeOverstextVieID);
        awayOvers = findViewById(R.id.matchAwayOversTextViewID);
        overview = findViewById(R.id.matchOverviewtextView);
        longDesc = findViewById(R.id.matchLongDescriptiontextView);
        datE.setText(matchItem.getMatchDate());
        homE.setText(matchItem.getMatchHomeAbbriv());
        away.setText(matchItem.getMatchAwayAbbriv());
        homeScore.setText(matchItem.getMatchHomeScores());
        homeOvers.setText(matchItem.getMatchHomeOvers());
        awayScore.setText(matchItem.getMatchAwayScores());
        awayOvers.setText(matchItem.getMatchAwayOvers());
        overview.setText(matchItem.getMatchOverview());
        longDesc.setText(matchItem.getMatchLongDesc());
    }

    void setAdapter(){
        recyclerView.setLayoutManager(layoutManager);
        liveMatchAdapter = new LiveMatchAdapter(itemArrayList);
        recyclerView.setAdapter(liveMatchAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        if(reteriveOverViewCardData != null){
            reteriveOverViewCardData.cancel(true);
            reteriveOverViewCardData = null;
        }
        if(handler != null){
            handler.removeCallbacksAndMessages(null);
        }
        finish();
        super.onBackPressed();
    }
}
