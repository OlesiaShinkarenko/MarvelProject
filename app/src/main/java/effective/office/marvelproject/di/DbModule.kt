package effective.office.marvelproject.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import effective.office.marvelproject.data.local.CharacterDao
import effective.office.marvelproject.data.local.MarvelAppDatabase
import effective.office.marvelproject.utils.Constant
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DbModule {

    @Provides
    @Singleton
    internal fun provideMarvelDb(@ApplicationContext context: Context): MarvelAppDatabase {
        return Room.databaseBuilder(context, MarvelAppDatabase::class.java, Constant.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    internal fun provideMarvelDa0(marvelAppDatabase: MarvelAppDatabase): CharacterDao {
        return marvelAppDatabase.characterDao()
    }
}