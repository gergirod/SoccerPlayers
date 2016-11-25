package germangirod.soccerteam.data.presenters;

import germangirod.soccerteam.data.api.ApiRequest;
import germangirod.soccerteam.data.model.Team;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by germangirod on 11/26/16.
 */

public class TeamData {

    private ApiRequest apiRequest;
    private TeamPresenter teamPresenter;

    public TeamData(TeamPresenter teamPresenter){

        apiRequest = new ApiRequest();
        this.teamPresenter = teamPresenter;

    }

    public void getTeamData(){

        apiRequest.getTeamData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Team>() {
                    @Override public void call(Team team) {
                        teamPresenter.onResponseSuccesfully(team);
                    }
                }, new Action1<Throwable>() {
                    @Override public void call(Throwable throwable) {
                        teamPresenter.onError(throwable);
                    }
                });

    }

}
