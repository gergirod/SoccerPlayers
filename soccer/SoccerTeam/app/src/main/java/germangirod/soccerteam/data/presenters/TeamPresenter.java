package germangirod.soccerteam.data.presenters;

import germangirod.soccerteam.data.model.Team;

/**
 * Created by germangirod on 11/26/16.
 */

public interface TeamPresenter {

    void onResponseSuccesfully(Team team);

    void onError(Throwable throwable);

}
