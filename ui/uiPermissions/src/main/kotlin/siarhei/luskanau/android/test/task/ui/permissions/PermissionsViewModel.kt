package siarhei.luskanau.android.test.task.ui.permissions

import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.lifecycle.ViewModel

abstract class PermissionsViewModel : ViewModel() {
    abstract fun onResumed()
    abstract fun onRequestClicked(launchActivityResult: suspend (Intent) -> ActivityResult)
}
