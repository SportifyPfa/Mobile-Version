package com.app.entity.di

import com.app.entity.network.RetrofitServiceInterface
import com.app.entity.utils.ConstUtil.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun createRetrofitInstanceEntity(@Named("Entity") retrofit: Retrofit):
            RetrofitServiceInterface =
        retrofit.create(RetrofitServiceInterface::class.java)


    @Provides
    @Singleton
    @Named("Entity")
    fun getRetrofitInstanceEntity(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @ApplicationScopeEntity
    @Provides
    @Singleton
    fun provideApplicationScopeEntity() = CoroutineScope(SupervisorJob())
}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class ApplicationScopeEntity