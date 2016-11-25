package germangirod.soccerteam.data.presenters;

import germangirod.soccerteam.data.api.ApiRequest;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by germangirod on 11/26/16.
 */

public class PlayerData {

    private ApiRequest apiRequest;
    private PlayerPresenter playerPresenter;

    public PlayerData(PlayerPresenter playerPresenter){

        apiRequest = new ApiRequest();
        this.playerPresenter = playerPresenter;

    }

    public void getPlayerData(int teamId, int playerId, String firstName, String lastName){

        apiRequest.getPlayerData(teamId, playerId, firstName, lastName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override public void call(String s) {
                        if(s.equals("OK")){
                            playerPresenter.onPlayerResponseSuccesfully(s);
                        }else {
                            playerPresenter.onPLayerResponseError(s);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override public void call(Throwable throwable) {
                        playerPresenter.onPLayerResponseError(throwable.getLocalizedMessage());
                    }
                });
    }

}
