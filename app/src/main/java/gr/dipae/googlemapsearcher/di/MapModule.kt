package gr.dipae.googlemapsearcher.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.components.SingletonComponent
import gr.dipae.googlemapsearcher.BuildConfig
import gr.dipae.googlemapsearcher.data.*
import gr.dipae.googlemapsearcher.util.ConnectivityInterceptor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapModule {

    @Singleton
    @Provides
    fun provideGooglePlacesClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        connectionSpec: ConnectionSpec,
        connectivityInterceptor: ConnectivityInterceptor
    ): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(connectivityInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectionSpecs(listOf(connectionSpec))

        if (BuildConfig.DEBUG) {
            okHttpClient.addNetworkInterceptor(httpLoggingInterceptor)
        }

        return okHttpClient.build()
    }

    @Singleton
    @Provides
    fun provideGooglePlacesApi(
        moshiConverterFactory: MoshiConverterFactory,
        okHttpClient: OkHttpClient
    ): GooglePlacesApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(moshiConverterFactory)
            .build()
            .create(GooglePlacesApi::class.java)
    }
}

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@Module
@InstallIn(ActivityRetainedComponent::class)
interface MapBindsModule {

    @Binds
    fun bindMapSearcherDataSource(dataSource: MapSearcherDataSourceImpl): MapSearcherDataSource

    @Binds
    fun bindMapSearcherRepository(repository: MapSearcherRepositoryImpl): MapSearcherRepository
}