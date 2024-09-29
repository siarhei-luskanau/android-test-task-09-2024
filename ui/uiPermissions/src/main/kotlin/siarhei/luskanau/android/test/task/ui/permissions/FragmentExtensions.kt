package siarhei.luskanau.android.test.task.ui.permissions

import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import siarhei.luskanau.android.test.task.core.permissions.ContinuationStartActivityForResult
import siarhei.luskanau.android.test.task.core.permissions.ContinuationStartActivityInput

fun launchActivityResult(
    activityResultLauncher: ActivityResultLauncher<ContinuationStartActivityInput>
): suspend (Intent) -> ActivityResult = { intent ->
    suspendCoroutine { continuation ->
        activityResultLauncher.launch(
            ContinuationStartActivityInput(
                intent = intent,
                continuation = continuation
            )
        )
    }
}

fun Fragment.registerForStartActivityResult():
    ActivityResultLauncher<ContinuationStartActivityInput> =
    this.registerForActivityResult(
        ContinuationStartActivityForResult()
    ) { result ->
        result.continuation?.resume(result.result)
    }
