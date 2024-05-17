package m.eight.ipl.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import m.eight.ipl.data.repository.PlRepositoryImpl
import m.eight.ipl.data.source.remote.ApiService
import m.eight.ipl.domain.repository.PlRepository
import m.eight.ipl.domain.usecase.GetCurrentSeasonUseCase
import m.eight.ipl.domain.usecase.GetMatchesUseCase
import m.eight.ipl.domain.usecase.GetStandingsUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService =
        Retrofit.Builder().baseUrl(ApiService.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create(ApiService::class.java)

    @Provides
    @Singleton
    fun providePlRepository(apiService: ApiService): PlRepository = PlRepositoryImpl(apiService)

    @Provides
    @Singleton
    fun provideGetCurrentSeasonUseCase(repository: PlRepository): GetCurrentSeasonUseCase = GetCurrentSeasonUseCase(repository)

    @Provides
    @Singleton
    fun provideGetStandingsUseCase(repository: PlRepository): GetStandingsUseCase = GetStandingsUseCase(repository)

    @Provides
    @Singleton
    fun provideGetMatchesUseCase(repository: PlRepository): GetMatchesUseCase = GetMatchesUseCase(repository)
}