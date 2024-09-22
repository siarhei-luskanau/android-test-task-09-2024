package siarhei.luskanau.android.test.task.core.formatter

import org.koin.core.annotation.Single
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date

@Single
internal class CoreFormatterImpl : CoreFormatter {

    private val dateFormat: DateFormat by lazy { SimpleDateFormat("dd/MM/yyyy") }

    override fun formatNotificationMessage(date1: Date?, date2: Date?): String = ""

    override fun formatUiMessage(list: Map<Date, Int>): String = when {
        list.isEmpty() -> "No boots detected"
        else ->
            list.map { info ->
                val dateString = dateFormat.format(info.key)
                "$dateString - ${info.value}"
            }.joinToString(separator = "\n")
    }
}
