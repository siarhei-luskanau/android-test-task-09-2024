package siarhei.luskanau.android.test.task.domain.work.worker

import android.content.Context
import androidx.work.WorkerParameters
import org.koin.core.component.KoinComponent
import siarhei.luskanau.android.test.task.domain.work.WorkService

internal class PeriodicNotificationWork(context: Context, workerParams: WorkerParameters) :
    BaseWorker(context = context, workerParams = workerParams),
    KoinComponent {

    private val workService: WorkService = getKoin().get()

    override suspend fun doWorkDelegate(): Result {
        workService.enqueueWorkWithNotification()
        return Result.success()
    }
}
