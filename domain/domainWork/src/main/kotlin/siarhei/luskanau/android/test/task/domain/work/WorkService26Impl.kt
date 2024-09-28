package siarhei.luskanau.android.test.task.domain.work

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import siarhei.luskanau.android.test.task.domain.work.worker.OnBootEventWorker

@RequiresApi(Build.VERSION_CODES.O)
internal class WorkService26Impl(private val context: Context) : WorkService {

    override fun onBootEventReceive() {
        WorkManager.getInstance(context).enqueueUniqueWork(
            OnBootEventWorker::class.java.simpleName,
            ExistingWorkPolicy.KEEP,
            OneTimeWorkRequest.from(OnBootEventWorker::class.java)
        )
    }

    override fun onOnNotificationDismissedReceive() {
    }
}
