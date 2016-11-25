package germangirod.soccerteam.data.presenters;

/**
 * Created by germangirod on 11/26/16.
 */

public interface PlayerPresenter {

    void onPlayerResponseSuccesfully(String response);

    void onPLayerResponseError(String error);

}
