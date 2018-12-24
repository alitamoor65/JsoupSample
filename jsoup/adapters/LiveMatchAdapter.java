package com.example.alitamoor.jsoup.adapters;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alitamoor.jsoup.R;
import com.example.alitamoor.jsoup.datamodels.DMatchLiveDetailsItem;

import java.util.ArrayList;

public class LiveMatchAdapter extends RecyclerView.Adapter<LiveMatchAdapter.ViewHolder> {

    ArrayList<DMatchLiveDetailsItem>liveDetailsItem;

    public LiveMatchAdapter(ArrayList<DMatchLiveDetailsItem> liveDetailsItem) {
        this.liveDetailsItem = liveDetailsItem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.match_content_table_item,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.playerEco.setText(liveDetailsItem.get(i).getMatchPlayerStrikeRateEconomy());
        viewHolder.playerName.setText(liveDetailsItem.get(i).getMatchPlayerName());
        viewHolder.playerBalls.setText(liveDetailsItem.get(i).getMatchPlayerBallsMedian());
        viewHolder.playerFours.setText(liveDetailsItem.get(i).getMatchPlayerFoursRuns());
        viewHolder.playerSixs.setText(liveDetailsItem.get(i).getMatchPlayerSixWictkets());
        viewHolder.playerRuns.setText(liveDetailsItem.get(i).getMatchPlayerRunsOvers());
        if(i == 0 || i == 3){
            viewHolder.itemView.setBackgroundColor(Color.parseColor("#91BF52"));
            viewHolder.playerEco.setTextColor(Color.parseColor("#FFFFFF"));
            viewHolder.playerName.setTextColor(Color.parseColor("#FFFFFF"));
            viewHolder.playerBalls.setTextColor(Color.parseColor("#FFFFFF"));
            viewHolder.playerFours.setTextColor(Color.parseColor("#FFFFFF"));
            viewHolder.playerSixs.setTextColor(Color.parseColor("#FFFFFF"));
            viewHolder.playerRuns.setTextColor(Color.parseColor("#FFFFFF"));
        }
    }

    @Override
    public int getItemCount() {
        return liveDetailsItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView playerName,playerRuns,playerBalls,playerFours,playerSixs,playerEco;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            playerName = itemView.findViewById(R.id.batsManHeadTextView);
            playerRuns = itemView.findViewById(R.id.runTitleHeadtextView);
            playerBalls = itemView.findViewById(R.id.ballsTitleHeadtextView);
            playerFours = itemView.findViewById(R.id.foursHeadtextView);
            playerSixs = itemView.findViewById(R.id.sixTitleHeadtextView);
            playerEco = itemView.findViewById(R.id.srTitleHeadtextView);
        }
    }
}
