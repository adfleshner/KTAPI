import io.research.Server
import io.research.db.ListDataStoreImpl

//Where all of the data comes from
private val dataStore = ListDataStoreImpl()

fun main(args: Array<String>) {
    Server.start(dataStore)
}

