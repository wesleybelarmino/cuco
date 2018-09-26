package com.app.cuco.data.di;

import com.app.cuco.data.DataManager;
import dagger.Component;

@Component(modules = DataModule.class) public interface DataComponent {
    void inject(DataManager dataManager);
}
