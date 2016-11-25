package germangirod.soccerteam.data.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import germangirod.soccerteam.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by germangirod on 11/26/16.
 */

public class RestClient {

    private static Api REST_Client;

    static {
        setupRestClient();
    }

    private RestClient() {

    }

    public static Api get() {
        return REST_Client;
    }

    private static void setupRestClient() {

        Retrofit builder = new Retrofit.Builder().baseUrl(BuildConfig.API_URL)
                .client(createOkHttpClient())
                .addConverterFactory(buildGsonConverter())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        REST_Client = builder.create(Api.class);
    }

    private static OkHttpClient createOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        return client;
    }

    private static GsonConverterFactory buildGsonConverter() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        return GsonConverterFactory.create(gson);
    }


}
