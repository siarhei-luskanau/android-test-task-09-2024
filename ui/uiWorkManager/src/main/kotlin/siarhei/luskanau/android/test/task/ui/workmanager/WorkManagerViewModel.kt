package siarhei.luskanau.android.test.task.ui.workmanager

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.work.WorkInfo
import kotlinx.coroutines.flow.Flow

abstract class WorkManagerViewModel : ViewModel() {
    abstract fun getWorkStatusListFlow(context: Context): Flow<List<WorkInfo>>
}
