package siarhei.luskanau.android.test.task.core.formatter

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.time.Duration.Companion.milliseconds
import org.koin.core.annotation.Single

@Single
internal class CoreFormatterImpl : CoreFormatter {

    private val uiDateFormat: DateFormat by lazy { SimpleDateFormat("dd/MM/yyyy") }
    private val notificationDateFormat: DateFormat by lazy {
        SimpleDateFormat("dd/MM/YYYY hh:mm:ss")
    }

    override fun formatNotificationMessage(date1: Date?, date2: Date?): String = when {
        date1 == null && date2 == null -> "No boots detected"
        date1 != null && date2 == null ->
            "The boot was detected = ${notificationDateFormat.format(date1)}"
        date1 != null && date2 != null -> {
            val timeBetween2LastBootEvents = (date1.time - date2.time).milliseconds
            "Last boots time delta = $timeBetween2LastBootEvents"
        }
        else -> "No boots detected"
    }

    override fun formatUiMessage(list: Map<Date, Int>): String = when {
        list.isEmpty() -> "No boots detected"
        else ->
            list.map { info ->
                val dateString = uiDateFormat.format(info.key)
                "$dateString - ${info.value}"
            }.joinToString(separator = "\n")
    }
}
