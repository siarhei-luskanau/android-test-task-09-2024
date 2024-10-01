package siarhei.luskanau.android.test.task.domain.work

import android.annotation.SuppressLint
import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.OutOfQuotaPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import java.time.Duration
import org.koin.core.annotation.Single
import siarhei.luskanau.android.test.task.domain.work.worker.PeriodicNotificationWork
import siarhei.luskanau.android.test.task.domain.work.worker.ShowNotificationWorker

@Single
internal class WorkServiceImpl(private val context: Context) : WorkService {

    @SuppressLint("NewApi")
    override fun onBootEventReceive() {
        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            PeriodicNotificationWork::class.java.simpleName,
            ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE,
            PeriodicWorkRequest.Builder(
                workerClass = PeriodicNotificationWork::class.java,
                repeatInterval = Duration.ofMinutes(15)
            )
                .addTag("all")
                .build()
        )
    }

    override fun enqueueWorkWithNotification() {
        WorkManager.getInstance(context).enqueueUniqueWork(
            ShowNotificationWorker::class.java.simpleName,
            ExistingWorkPolicy.REPLACE,
            OneTimeWorkRequest.Builder(ShowNotificationWorker::class.java)
                .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
                .addTag("all")
                .build()
        )
    }

    override fun onOnNotificationDismissedReceive() {
    }
}
