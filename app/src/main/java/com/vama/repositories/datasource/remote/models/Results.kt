import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class Results : RealmObject {
    @PrimaryKey
    var id: Int? = null
    var artistName: String? = null
    var name: String? = null
    var releaseDate: String? = null
    var kind: String? = null
    var artistId: Int? = null
    var artistUrl: String? = null
    var contentAdvisoryRating: String? = null
    var artworkUrl100: String? = null
    var genres: List<Genres>? = null
    var genresName: String? = null
    var url: String? = null
}