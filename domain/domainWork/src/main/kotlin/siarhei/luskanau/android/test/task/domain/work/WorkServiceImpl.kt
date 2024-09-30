package siarhei.luskanau.android.test.task.domain.work

import android.content.Context
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkManager
import org.koin.core.annotation.Single
import siarhei.luskanau.android.test.task.domain.work.worker.OnBootEventWorker

@Single
internal class WorkServiceImpl(private val context: Context) : WorkService {

    override suspend fun onBootEventReceive() {
        WorkManager.getInstance(context).enqueueUniqueWork(
            OnBootEventWorker::class.java.simpleName,
            ExistingWorkPolicy.REPLACE,
            OneTimeWorkRequest.Builder(
                OnBootEventWorker::class.java
            )
                .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
                .build()
        )
    }

    override fun onOnNotificationDismissedReceive() {
    }
}
