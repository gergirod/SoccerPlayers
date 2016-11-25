package germangirod.soccerteam.data.api;

import germangirod.soccerteam.data.model.Team;
import rx.Observable;

/**
 * Created by germangirod on 11/26/16.
 */

public interface Request {

    Observable<Team> getTeamData();

    Observable<String> getPlayerData(int teamId, int playerId, String firstName, String lastName);

}
