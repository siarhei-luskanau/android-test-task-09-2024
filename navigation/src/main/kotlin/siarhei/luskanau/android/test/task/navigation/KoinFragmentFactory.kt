package siarhei.luskanau.android.test.task.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import org.koin.core.Koin
import org.koin.core.parameter.parametersOf

class KoinFragmentFactory(private val koin: Koin) : FragmentFactory() {

    @Suppress("TooGenericExceptionCaught")
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment =
        try {
            val clazz = loadFragmentClass(classLoader, className)
            koin.get(
                clazz = clazz.kotlin,
                qualifier = null,
                parameters = { parametersOf() },
            )
        } catch (koinThrowable: Throwable) {
            try {
                super.instantiate(classLoader, className)
            } catch (_: Throwable) {
                throw koinThrowable
            }
        }
}
