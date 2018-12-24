package com.example.alitamoor.jsoup.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.example.alitamoor.jsoup.adapters.MatchListAdapter;
import com.example.alitamoor.jsoup.R;
import com.example.alitamoor.jsoup.datamodels.DMatchItem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String TAG = "Main";

    ShimmerRecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    MatchListAdapter matchListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerViewID);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.showShimmerAdapter();
        new ReteriveData().execute();
    }

    void setMatchListAdapter(ArrayList<DMatchItem> arrayList){
        matchListAdapter = new MatchListAdapter(arrayList,this);
        recyclerView.setAdapter(matchListAdapter);
    }

    class ReteriveData extends AsyncTask<Void, Void, String>{
        Document doc = null;
        ArrayList<DMatchItem> arrayList = new ArrayList<>();
        @Override
        protected String doInBackground(Void... voids) {

            String url = "http://www.espncricinfo.com/scores";
            String string = null;
            try {
                doc = Jsoup.connect(url).header("Accept-Encoding", "gzip, deflate")
                        .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0").get();

                for (Element element : doc.select("div#events > div.scoreCollection.cricket")
                        .select("div.scoreCollection__content.cricket").
                                select("div.cscore.cscore--live.cricket.cscore--watchNotes")) {
                    DMatchItem dMatchItem = new DMatchItem();

                    dMatchItem.setMatchDate(element.select("span.cscore_date").text());
                    dMatchItem.setMatchOverview(element.select("div.cscore_info-overview").text());

                    dMatchItem.setMatchHome(element.select("li.cscore_item.cscore_item--home").text());
                    dMatchItem.setMatchHomeLogo(element.select("li.cscore_item.cscore_item--home").select("div.cscore_logo").select("img").attr("data-src"));
                    dMatchItem.setMatchHomeLong(element.select("li.cscore_item.cscore_item--home").select("span.cscore_name.cscore_name--long").text());
                    dMatchItem.setMatchHomeAbbriv(element.select("li.cscore_item.cscore_item--home").select("span.cscore_name.cscore_name--abbrev").text());
                    dMatchItem.setMatchHomeScores(element.select("li.cscore_item.cscore_item--home").select("div.cscore_score").get(0).text());
                    dMatchItem.setMatchHomeOvers(element.select("li.cscore_item.cscore_item--home").select("div.cscore_score").select("span.cscore_overs").text());

                    dMatchItem.setMatchAway(element.select("li.cscore_item.cscore_item--away").text());
                    dMatchItem.setMatchAwayLogo(element.select("li.cscore_item.cscore_item--away").select("div.cscore_logo").select("img").attr("data-src"));
                    Log.i(TAG, "doInBackground: " + element.select("li.cscore_item.cscore_item--away").select("div.cscore_logo").select("img").attr("data-src"));
                    dMatchItem.setMatchAwayAbbriv(element.select("li.cscore_item.cscore_item--away").select("span.cscore_name.cscore_name--abbrev").text());
                    dMatchItem.setMatchAwayLong(element.select("li.cscore_item.cscore_item--home").select("span.cscore_name.cscore_name--long").text());
                    dMatchItem.setMatchAwayScores(element.select("li.cscore_item.cscore_item--away").select("div.cscore_score").get(0).text());
                    dMatchItem.setMatchAwayOvers(element.select("li.cscore_item.cscore_item--away").select("div.cscore_score").select("span.cscore_overs").text());

                    dMatchItem.setMatchLongDesc(element.select("span.cscore_notes_game").first().text());

                    dMatchItem.setMatchDetailsLink( element.select("a.cscore_details").attr("href"));
                    arrayList.add(dMatchItem);
                    //Log.i(TAG, "doInBackground: " + dMatchItem.toString());

                }

            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG, "doInBackground: " + e.getLocalizedMessage());
            }
            return string;
        }

        @Override
        protected void onPostExecute(String aVoid) {
            recyclerView.hideShimmerAdapter();
            setMatchListAdapter(arrayList);
            //this.cancel(true);
            //doc = null;
            /*new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    new ReteriveOverViewCardData().execute();
                }
            },3000);*/
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
        }
    }
}
