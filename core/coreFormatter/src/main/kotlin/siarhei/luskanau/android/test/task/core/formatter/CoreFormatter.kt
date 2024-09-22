package siarhei.luskanau.android.test.task.core.formatter

import java.util.Date

interface CoreFormatter {
    fun formatNotificationMessage(date1: Date?, date2: Date?): String
    fun formatUiMessage(list: Map<Date, Int>): String
}
