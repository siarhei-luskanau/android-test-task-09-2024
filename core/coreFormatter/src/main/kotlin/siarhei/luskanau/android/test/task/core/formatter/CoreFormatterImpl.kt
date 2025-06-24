package siarhei.luskanau.android.test.task.core.formatter

import kotlin.time.ExperimentalTime
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.format.byUnicodePattern
import kotlinx.datetime.toInstant
import org.koin.core.annotation.Single

@Single
internal class CoreFormatterImpl : CoreFormatter {

    private val uiDateFormat: DateTimeFormat<LocalDateTime> by lazy {
        LocalDateTime.Format {
            byUnicodePattern("dd/MM/yyyy")
        }
    }

    @OptIn(ExperimentalTime::class)
    override fun formatNotificationMessage(date1: LocalDateTime?, date2: LocalDateTime?): String =
        when {
            date1 == null && date2 == null -> "No boots detected"
            date1 != null && date2 == null ->
                "The boot was detected = $date1"
            date1 != null && date2 != null -> {
                val timeBetween2LastBootEvents = date1.toInstant(TimeZone.currentSystemDefault()) -
                    date2.toInstant(TimeZone.currentSystemDefault())
                "Last boots time delta = $timeBetween2LastBootEvents"
            }
            else -> "No boots detected"
        }

    override fun formatUiMessage(list: Map<LocalDateTime, Int>): String = when {
        list.isEmpty() -> "No boots detected"
        else ->
            list.map { info ->
                val dateString = uiDateFormat.format(info.key)
                "$dateString - ${info.value}"
            }.joinToString(separator = "\n")
    }
}
