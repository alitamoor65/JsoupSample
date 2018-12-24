package com.example.alitamoor.jsoup.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.alitamoor.jsoup.R;
import com.example.alitamoor.jsoup.activities.MatchDetailActivity;
import com.example.alitamoor.jsoup.datamodels.DMatchItem;

import java.util.ArrayList;

public class MatchListAdapter extends RecyclerView.Adapter<MatchListAdapter.ViewHolder> {

    ArrayList<DMatchItem> arrayListLocal;
    Context context;


    public MatchListAdapter(ArrayList<DMatchItem> arrayListParam, Context contextParam) {
        arrayListLocal = arrayListParam;
        context = contextParam;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.math_overview_card,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.datE.setText(arrayListLocal.get(i).getMatchDate());

        viewHolder.homE.setText(arrayListLocal.get(i).getMatchHomeAbbriv());
        viewHolder.homeScore.setText(arrayListLocal.get(i).getMatchHomeScores().split("\\(")[0]);
        viewHolder.homeOvers.setText(arrayListLocal.get(i).getMatchHomeOvers());
        Glide.with(context).load(arrayListLocal.get(i).getMatchHomeLogo()).into(viewHolder.homeLogo);

        viewHolder.away.setText(arrayListLocal.get(i).getMatchAwayAbbriv());
        viewHolder.awayScore.setText(arrayListLocal.get(i).getMatchAwayScores().split("\\(")[0]);
        viewHolder.awayOvers.setText(arrayListLocal.get(i).getMatchAwayOvers());
        Glide.with(context).load(arrayListLocal.get(i).getMatchAwayLogo()).into(viewHolder.awayLogo);

        viewHolder.overview.setText(arrayListLocal.get(i).getMatchOverview());
        viewHolder.longDesc.setText(arrayListLocal.get(i).getMatchLongDesc());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context.getApplicationContext(), MatchDetailActivity.class).putExtra("URL",arrayListLocal.get(i).getMatchDetailsLink()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayListLocal.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView datE,homE,away,homeScore,homeOvers,awayScore,awayOvers,overview,longDesc;
        ImageView homeLogo,awayLogo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            datE = itemView.findViewById(R.id.matchDatetextView);

            homE = itemView.findViewById(R.id.matchHomeAbrtextView);
            homeLogo = itemView.findViewById(R.id.matchHomeLogoID);
            homeScore = itemView.findViewById(R.id.matchHomeScreetextView);
            homeOvers = itemView.findViewById(R.id.matchHomeOverstextVieID);

            away = itemView.findViewById(R.id.matchAwayAbrtextView);
            awayLogo = itemView.findViewById(R.id.matchAwayLogoId);
            awayScore = itemView.findViewById(R.id.matchAwayScoretextView);
            awayOvers = itemView.findViewById(R.id.matchAwayOversTextViewID);

            overview = itemView.findViewById(R.id.matchOverviewtextView);
            longDesc = itemView.findViewById(R.id.matchLongDescriptiontextView);

        }
    }
}
