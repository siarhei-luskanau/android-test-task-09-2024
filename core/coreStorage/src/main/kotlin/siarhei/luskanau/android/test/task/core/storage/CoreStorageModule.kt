package siarhei.luskanau.android.test.task.core.storage

import android.content.Context
import androidx.room.Room
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import siarhei.luskanau.android.test.task.core.storage.room.AppDatabase

@Module
@ComponentScan
class CoreStorageModule {
    @Single
    internal fun appDatabase(context: Context): AppDatabase = Room.databaseBuilder(
        context = context,
        klass = AppDatabase::class.java,
        name = "database-name"
    ).build()
}
