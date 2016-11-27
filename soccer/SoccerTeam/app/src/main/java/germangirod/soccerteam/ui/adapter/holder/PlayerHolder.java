package germangirod.soccerteam.ui.adapter.holder;

import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.image.ImageInfo;
import germangirod.soccerteam.R;
import germangirod.soccerteam.ui.util.ViewClick;

/**
 * Created by germangirod on 11/26/16.
 */

public class PlayerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @InjectView(R.id.player_first_name) TextView playerName;
    @InjectView(R.id.player_last_name) TextView playerLastName;
    @InjectView(R.id.player_image) SimpleDraweeView playerImage;
    @InjectView(R.id.player_jersey_number) TextView playerJerseyNumber;
    private ViewClick viewClick;

    public PlayerHolder(View itemView, ViewClick viewClick) {
        super(itemView);
        ButterKnife.inject(this, itemView);
        itemView.setOnClickListener(this);
        this.viewClick = viewClick;
    }

    public void setPlayerName(String firstName){

        playerName.setText(firstName);

    }

    public void setPlayerLastName(String lastName){

        playerLastName.setText(lastName);

    }

    public void setPlayerJerseyNumber(String jerseyNumber){

        playerJerseyNumber.setText(jerseyNumber);

    }

    public void setPlayerImage(String imageUrl){
        final Uri uri = Uri.parse(imageUrl);

        // Listen to Download events
        ControllerListener controllerListener = new BaseControllerListener<ImageInfo>() {
            @Override
            public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable anim) {
                // Image Loaded

                // Check if image loaded from cache or not.
                ImagePipeline imagePipeline = Fresco.getImagePipeline();
                boolean inMemoryCache = imagePipeline.isInBitmapMemoryCache(uri);

                //Toast.makeText(context, "Image Loaded, Cached ? " + inMemoryCache, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String id, Throwable throwable) {
                // Failure happened
                //Toast.makeText(context, "Error loading: " + id, Toast.LENGTH_SHORT).show();
            }
        };

        // Initialize a controller and attach the listener to it.
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setControllerListener(controllerListener)
                .build();

       playerImage.setController(controller);
    }

    @Override public void onClick(View view) {

        viewClick.onViewClick(getAdapterPosition());

    }
}
