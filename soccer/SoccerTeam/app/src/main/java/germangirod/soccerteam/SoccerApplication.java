package germangirod.soccerteam;

import android.app.Application;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by germangirod on 11/26/16.
 */

public class SoccerApplication extends Application {

    @Override public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
