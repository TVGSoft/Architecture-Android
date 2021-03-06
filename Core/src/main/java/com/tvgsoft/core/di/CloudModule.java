package com.tvgsoft.core.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tvgsoft.core.model.services.Configuration;
import com.tvgsoft.core.model.services.IGoogleApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by TVG on 7/19/16.
 */

@Module(includes = { AppModule.class })
public class CloudModule {

    //region Provide methods

    @Provides
    @Singleton
    public IGoogleApiService provideGoogleApiService() {
        Gson gson = createGson();

        Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl(Configuration.GOOGLE_API_URL)
                                        .addConverterFactory(GsonConverterFactory.create(gson))
                                        .build();

        return retrofit.create(IGoogleApiService.class);
    }

    //endregion

    //region Private methods

    private Gson createGson() {
        return new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                                .create();
    }

    //endregion

}
