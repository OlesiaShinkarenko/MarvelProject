package effective.office.marvelproject.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import effective.office.marvelproject.data.db.MarvelAppDatabase
import effective.office.marvelproject.data.network.services.MarvelApiService
import effective.office.marvelproject.domain.repositories.MarvelRepository
import effective.office.marvelproject.domain.repositories.MarvelRepositoryImpl
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