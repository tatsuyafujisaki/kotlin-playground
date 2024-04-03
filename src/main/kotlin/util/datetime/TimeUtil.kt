package util.datetime

object TimeUtil {
    private fun convertToHoursMinutesSeconds(totalSeconds: Long): Triple<Long, Long, Long> {
        val hours = totalSeconds / 3600
        val minutes = (totalSeconds % 3600) / 60
        val seconds = totalSeconds % 60
        return Triple(hours, minutes, seconds)
    }

    fun hhMmSs(totalSeconds: Long): String {
        val (hours, minutes, seconds) = convertToHoursMinutesSeconds(totalSeconds)
        return "%02d:%02d:%02d".format(hours, minutes, seconds)
    }

    fun mmSs(totalSeconds: Long): String {
        val (_, minutes, seconds) = convertToHoursMinutesSeconds(totalSeconds)
        return "%02d:%02d".format(minutes, seconds)
    }

    fun hhMmSsOrMmSs(totalSeconds: Long): String {
        val (hours, minutes, seconds) = convertToHoursMinutesSeconds(totalSeconds)
        return if (hours > 0) {
            "%02d:%02d:%02d".format(hours, minutes, seconds)
        } else {
            "%02d:%02d".format(minutes, seconds)
        }
    }
}
