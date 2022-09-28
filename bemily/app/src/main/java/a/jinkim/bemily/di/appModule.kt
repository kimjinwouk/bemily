package kr.co.tapplace.di

import a.jinkim.bemily.retofit.RetrofitService
import a.jinkim.bemily.util.constants.Companion.SERVER_ADDRESS
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class appModule {

    // Retorfit 추가.
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(SERVER_ADDRESS)
            .addConverterFactory(GsonConverterFactory.create())

            .client(
                OkHttpClient.Builder().addInterceptor(
                    HttpLoggingInterceptor().setLevel(
                        HttpLoggingInterceptor.Level.BODY
                    )
                ).build()
            )
            .build()
    }

    // Retorfit 추가.
    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): RetrofitService {
        return retrofit.create(RetrofitService::class.java)
    }


}