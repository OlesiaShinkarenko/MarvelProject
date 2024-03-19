package effective.office.marvelproject.network.model


data class ErrorResponse(val description: String) {
    companion object {
        fun getResponse(code: Int): ErrorResponse {
            val description = when (code) {
                404 -> "Не найдено"
                in 400..499 -> "Ошибка клиента"
                in 500..599 -> "Ошибка сервера"
                else -> "Неизвестная ошибка"
            }
            return ErrorResponse(description)
        }
    }
}