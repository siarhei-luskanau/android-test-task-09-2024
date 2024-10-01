package siarhei.luskanau.android.test.task.domain.work.worker

import android.content.Context
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import kotlin.time.Duration.Companion.minutes
import kotlinx.coroutines.delay
import org.koin.core.component.KoinComponent
import siarhei.luskanau.android.test.task.domain.notifications.AppNotificationService

internal class ShowNotificationWorker(context: Context, workerParams: WorkerParameters) :
    BaseWorker(context = context, workerParams = workerParams),
    KoinComponent {

    private val appNotificationService: AppNotificationService = getKoin().get()

    override suspend fun getForegroundInfo(): ForegroundInfo = ForegroundInfo(
        appNotificationService.getBootInfoNotificationId(),
        appNotificationService.getBootInfoNotification()
    )

    override suspend fun doWorkDelegate(): Result {
        delay(duration = 1.minutes)
        return Result.success()
    }
}
