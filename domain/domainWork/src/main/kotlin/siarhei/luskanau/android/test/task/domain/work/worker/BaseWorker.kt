package siarhei.luskanau.android.test.task.domain.work.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters

internal abstract class BaseWorker(context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams) {

    abstract suspend fun doWorkDelegate(): Result

    override suspend fun doWork(): Result = try {
        doWorkDelegate()
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
