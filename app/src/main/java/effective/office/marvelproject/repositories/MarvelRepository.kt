package effective.office.marvelproject.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import effective.office.marvelproject.data.MarvelMediator
import effective.office.marvelproject.data.db.MarvelAppDatabase

class MarvelRepository(
    private val database: MarvelAppDatabase
) {
    private val characterDao = database.characterDao()
    @ExperimentalPagingApi
    fun getCharacterPage() = Pager(
        config = PagingConfig(
            pageSize = 100,
            prefetchDistance = (0.10 * 100).toInt()
        ),
        remoteMediator =  MarvelMediator(database)
    ){
        characterDao.getCharacters()
    }.flow
}