package effective.office.marvelproject.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import effective.office.marvelproject.data.local.MarvelAppDatabase
import effective.office.marvelproject.data.remote.services.MarvelApiService
import effective.office.marvelproject.data.repository.MarvelRepositoryImpl
import effective.office.marvelproject.domain.repository.MarvelRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideMarvelRepository(
        marvelAppDatabase: MarvelAppDatabase,
        marvelApiService: MarvelApiService,
    ): MarvelRepository {
        return MarvelRepositoryImpl(
            marvelAppDatabase,
            marvelApiService
        )
    }
}