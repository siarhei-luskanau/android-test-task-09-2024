package siarhei.luskanau.android.test.task.core.formatter

import kotlinx.datetime.LocalDateTime

interface CoreFormatter {
    fun formatNotificationMessage(date1: LocalDateTime?, date2: LocalDateTime?): String
    fun formatUiMessage(list: Map<LocalDateTime, Int>): String
}
