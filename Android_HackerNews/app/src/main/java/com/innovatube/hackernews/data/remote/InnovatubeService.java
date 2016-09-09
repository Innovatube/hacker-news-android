package com.innovatube.hackernews.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.innovatube.hackernews.data.model.Story;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface InnovatubeService {
    @GET("topstories.json")
    Observable<List<Long>> getTopStoryId();

    @GET("newstories.json")
    Observable<List<Long>> getNewStoryId();

    @GET("item/{itemId}.json")
    Observable<Story> getStoryItem(@Path("itemId") String itemId);

    class Creator {
        private static final String BASE_URL = "https://hacker-news.firebaseio.com/v0/";

        public static Retrofit newRetrofitInstance() {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build();
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:SSS'Z'")
                    .create();

            return new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(client)
                    .build();

        }
    }
}
