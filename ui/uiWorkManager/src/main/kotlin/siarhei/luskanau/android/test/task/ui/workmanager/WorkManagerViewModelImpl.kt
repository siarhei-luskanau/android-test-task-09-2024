package siarhei.luskanau.android.test.task.ui.workmanager

import android.content.Context
import androidx.work.WorkInfo
import androidx.work.WorkManager
import kotlinx.coroutines.flow.Flow

class WorkManagerViewModelImpl(
    private val workManagerNavigationCallback: WorkManagerNavigationCallback
) : WorkManagerViewModel() {

    override fun getWorkStatusListFlow(context: Context): Flow<List<WorkInfo>> =
        WorkManager.getInstance(context).getWorkInfosByTagFlow("all")
}
