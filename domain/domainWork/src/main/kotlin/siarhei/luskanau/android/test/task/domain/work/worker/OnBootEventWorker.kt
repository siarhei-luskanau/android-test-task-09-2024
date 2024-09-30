package siarhei.luskanau.android.test.task.domain.work.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import kotlin.time.Duration.Companion.minutes
import kotlinx.coroutines.delay
import org.koin.core.component.KoinComponent
import siarhei.luskanau.android.test.task.domain.notifications.AppNotificationService

internal class OnBootEventWorker(context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams),
    KoinComponent {

    private val appNotificationService: AppNotificationService = getKoin().get()

    override suspend fun getForegroundInfo(): ForegroundInfo = ForegroundInfo(
        appNotificationService.getBootInfoNotificationId(),
        appNotificationService.getBootInfoNotification()
    )

    override suspend fun doWork(): Result = try {
        delay(duration = 1.minutes)
        Result.success()
    } catch (throwable: Throwable) {
        Result.failure(
            Data.Builder()
                .putString(
                    OUTPUT_KEY_THROWABLE,
                    "${throwable.javaClass.simpleName}: ${throwable.message}"
                ).build()
        )
    }

    companion object {
        private const val OUTPUT_KEY_THROWABLE = "throwable"
    }
}
