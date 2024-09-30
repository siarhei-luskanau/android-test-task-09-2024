package siarhei.luskanau.android.test.task.domain.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import siarhei.luskanau.android.test.task.core.storage.CoreStorage
import siarhei.luskanau.android.test.task.domain.work.WorkService

class OnBootReceiver :
    BroadcastReceiver(),
    KoinComponent {

    private val coreStorage: CoreStorage by lazy { getKoin().get() }
    private val workService: WorkService by lazy { getKoin().get() }

    override fun onReceive(context: Context, intent: Intent?) {
        if (intent?.action == Intent.ACTION_BOOT_COMPLETED) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    coreStorage.saveBootEvent()
                    workService.onBootEventReceive()
                } finally {
                    cancel()
                }
            }
        }
    }
}
