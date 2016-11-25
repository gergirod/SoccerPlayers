package germangirod.soccerteam.data.api;

import germangirod.soccerteam.data.model.Team;
import rx.Observable;

/**
 * Created by germangirod on 11/26/16.
 */

public class ApiRequest implements Request {
    @Override public Observable<Team> getTeamData() {
        return RestClient.get().getTeamData();
    }

    @Override public Observable<String> getPlayerData(int teamId, int playerId, String firstName, String lastName) {
        return RestClient.get().getPlayerData(teamId, playerId, firstName, lastName);
    }
}
