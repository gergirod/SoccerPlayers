package germangirod.soccerteam.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import germangirod.soccerteam.R;
import germangirod.soccerteam.data.model.Player;
import germangirod.soccerteam.data.model.Team;
import germangirod.soccerteam.data.presenters.PlayerData;
import germangirod.soccerteam.data.presenters.PlayerPresenter;
import germangirod.soccerteam.data.presenters.TeamData;
import germangirod.soccerteam.data.presenters.TeamPresenter;
import germangirod.soccerteam.ui.adapter.PlayersAdapter;
import germangirod.soccerteam.ui.util.AlertUtil;
import germangirod.soccerteam.ui.util.GridItemDecoration;
import germangirod.soccerteam.ui.util.PlayerClick;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by germangirod on 11/26/16.
 */

public class TeamFragment extends Fragment implements TeamPresenter, PlayerClick, PlayerPresenter{

    @InjectView(R.id.my_recycler_view) RecyclerView myList;
    @InjectView(R.id.progress_bar) ProgressBar progressBar;
    private PlayerData playerData = new PlayerData(this);

    private PlayersAdapter playersAdapter;
    private static final int COLUMNS = 3;
    private List<Player> playerList = new ArrayList<>();
    private Team team;
    private static final String TEAM = "team";

    @Override public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(TEAM, team);
        super.onSaveInstanceState(outState);
    }

    @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.team_fragment_layout, container, false);
        ButterKnife.inject(this, view);
        setViewData(savedInstanceState);
        return view;
    }

    @Override public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getTeam();
    }

    private void setViewData(Bundle bundle){
        setList();
        if(bundle != null){
            team = bundle.getParcelable(TEAM);
            if(playersAdapter != null){
                playersAdapter.setPlayersList(team.getPlayers(),team.getSettings().getHighlightColor());

            }
        }
    }

    private void setList(){

        myList.setHasFixedSize(true);
        myList.setLayoutManager(new GridLayoutManager(getActivity(), COLUMNS));
        playersAdapter = new PlayersAdapter(playerList, this);
        myList.setAdapter(playersAdapter);
        myList.addItemDecoration(new GridItemDecoration(10, 10));

    }

    private void getTeam(){
        progressBar.setVisibility(View.VISIBLE);
        TeamData teamData = new TeamData(this);
        teamData.getTeamData();

    }

    @Override public void onResponseSuccesfully(Team team) {
        progressBar.setVisibility(View.GONE);
        this.team = team;
        playersAdapter.setPlayersList(team.getPlayers(), team.getSettings().getHighlightColor());
        getActivity().setTitle(team.getName());

    }

    @Override public void onPlayerResponseSuccesfully(String response) {

        AlertUtil.createOkDialog(getActivity(), response);

    }

    @Override public void onPLayerResponseError(String error) {
        AlertUtil.createErrorDialog(getActivity(), error);
    }

    @Override public void onError(Throwable throwable) {
        Toast.makeText(getActivity(), throwable.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override public void onPlayerClick(Player player) {

        playerData.getPlayerData(team.getId(), player.getId(), player.getPerson().getFirstName(), player.getPerson().getLastName());

    }
}
