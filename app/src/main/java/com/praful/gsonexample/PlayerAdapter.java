package com.praful.gsonexample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Praful Dhabekar on 7/18/2017.
 */

public class PlayerAdapter extends RecyclerView.Adapter {

    Context ctx;
    APIResponse apiResponse;
    public PlayerAdapter(Context context,APIResponse apiResponse)
    {
        this.ctx = context;
        this.apiResponse = apiResponse;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(ctx).inflate(R.layout.row, null, false);
        PlayerViewHolder playerHolder = new PlayerViewHolder(inflate);
        return playerHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        List<APIResponse.Players> list = apiResponse.getPlayers();
        APIResponse.Players players = list.get(5);
        PlayerViewHolder playerViewHolder = (PlayerViewHolder)holder;
        playerViewHolder.playerName.setText(players.getName());
        playerViewHolder.playerRuns.setText(players.getRuns());
        Picasso.with(ctx).load(players.getPhoto()).into(playerViewHolder.playerPic);
    }

    @Override
    public int getItemCount() {
        return 15;
    }

    class PlayerViewHolder extends RecyclerView.ViewHolder{

        ImageView playerPic;
        TextView playerName,playerRuns;
        public PlayerViewHolder(View itemView) {
            super(itemView);
            playerPic = (ImageView)itemView.findViewById(R.id.playersImg);
            playerName = (TextView)itemView.findViewById(R.id.playersName);
            playerRuns = (TextView)itemView.findViewById(R.id.playersRuns);
        }
    }
}
