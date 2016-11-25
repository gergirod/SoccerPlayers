package germangirod.soccerteam.data.api;

import germangirod.soccerteam.data.model.Team;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by germangirod on 11/25/16.
 */

public interface Api {

    @GET("team.json") Observable<Team> getTeamData();

    @GET("tapped.php") Observable<String> getPlayerData(@Query("") int teamId, @Query("") int playerId, @Query("firstName") String firstName,
            @Query("lastname") String lastName);
}
