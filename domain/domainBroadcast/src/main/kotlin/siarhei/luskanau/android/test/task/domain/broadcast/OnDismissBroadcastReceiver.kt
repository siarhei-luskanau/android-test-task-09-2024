package siarhei.luskanau.android.test.task.domain.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import org.koin.core.component.KoinComponent
import siarhei.luskanau.android.test.task.domain.work.WorkService

class OnDismissBroadcastReceiver :
    BroadcastReceiver(),
    KoinComponent {

    private val workService: WorkService by lazy { getKoin().get() }

    override fun onReceive(context: Context, intent: Intent?) {
        workService.onOnNotificationDismissedReceive()
    }
}
