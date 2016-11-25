package germangirod.soccerteam.ui;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import germangirod.soccerteam.R;
import germangirod.soccerteam.data.model.Player;
import java.util.List;

/**
 * Created by germangirod on 11/26/16.
 */

public class PlayersAdapter extends RecyclerView.Adapter<PlayerHolder> implements ViewClick {

    private List<Player> players;
    private String backColor;
    private PlayerClick playerClick;

    public PlayersAdapter( List<Player> players, PlayerClick playerClick) {
        this.players = players;
        this.playerClick = playerClick;
    }


    @Override public PlayerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_row, parent, false);
        if(backColor != null){
            v.setBackgroundColor(Color.parseColor(backColor));
        }
        PlayerHolder imageRowHolder = new PlayerHolder(v, this);

        return imageRowHolder;
    }

    @Override public void onBindViewHolder(PlayerHolder holder, int position) {

        Player player = players.get(position);
        holder.setPlayerName(player.getPerson().getFirstName());
        holder.setPlayerImage(player.getPerson().getImageUrl());
        holder.setPlayerLastName(player.getPerson().getLastName());
        holder.setPlayerJerseyNumber(player.getJerseyNumber());

    }

    @Override public int getItemCount() {
        return players.size();
    }

    public void setPlayersList(List<Player> players, String backColor) {
        this.backColor = backColor;
        this.players.clear();
        this.players.addAll(players);
        notifyDataSetChanged();
    }

    @Override public void onViewClick(int position) {
        playerClick.onPlayerClick(players.get(position));
    }
}
