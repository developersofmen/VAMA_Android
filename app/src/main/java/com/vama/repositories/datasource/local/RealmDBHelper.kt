package com.vama.repositories.datasource.local

import Genres
import Results
import com.vama.utils.AppConstant.MUSIC
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query

object RealmDBHelper {
    private fun getConfiguration(): RealmConfiguration {
        return RealmConfiguration.Builder(schema = setOf(Results::class))
            .deleteRealmIfMigrationNeeded()
            .build()
    }

    private fun realmOpen(): Realm {
        return Realm.open(getConfiguration())
    }

    fun realmStore(resultList: List<Results>?) {
        val realm: Realm = realmOpen()
        realm.writeBlocking {
            val writeTransactionTasks = query<Results>().find()
            if (writeTransactionTasks.size > 0) delete(writeTransactionTasks)
            resultList?.let {
                for (item in resultList) {
                    item?.genresName = item?.genres?.let { it1 -> getGenreName(it1) }
                    copyToRealm(Results().apply {
                        artistName = item?.artistName
                        id = item?.id
                        artworkUrl100 = item?.artworkUrl100
                        name = item?.name
                        releaseDate = item?.releaseDate
                        url = item?.url
                        genresName = item?.genresName
                    })
                }
            }
        }
    }

    private fun getGenreName(genres: List<Genres>): String? {
        var genreName: String? = null
        for (item in genres) {
            if (!item.name.endsWith(MUSIC)) {
                genreName = item.name
                break
            }
        }
        return genreName
    }

    fun realmFetchAllRecords(): List<Results> {
        val realm: Realm = realmOpen()
        return realm.query<Results>().find()
    }
}