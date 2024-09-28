package siarhei.luskanau.android.test.task.domain.work

import android.os.Build
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val domainWorkModule = module {

    single<WorkService> {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            WorkService26Impl(context = androidContext())
        } else {
            WorkService24Impl(context = androidContext())
        }
    }
}
