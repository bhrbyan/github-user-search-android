package id.quranesia.android.core.di.qualifier

import javax.inject.Qualifier

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class LocalSource

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RemoteSource