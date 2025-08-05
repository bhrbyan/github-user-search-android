package id.assessment.core.di.dispatcher

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CoreDispatcherModule {

    @Singleton
    @Binds
    abstract fun bindCoreDispatcher(coreDispatcher: CoreDispatcherImpl): CoreDispatcher

}