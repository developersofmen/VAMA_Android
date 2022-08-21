import io.realm.kotlin.types.RealmObject

class Feed : RealmObject {
    var title: String? = null
    var id: String? = null
    var author: Author? = null
    var links: List<Links>? = null
    var copyright: String? = null
    var country: String? = null
    var icon: String? = null
    var updated: String? = null
    var results: List<Results>? = null
}