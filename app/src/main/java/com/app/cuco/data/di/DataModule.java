package com.app.cuco.data.di;

import com.app.cuco.data.network.ApiRequest;
import dagger.Module;
import dagger.Provides;

@Module public class DataModule {

    @Provides ApiRequest providesApiRequest() {
        return new ApiRequest();
    }
}
