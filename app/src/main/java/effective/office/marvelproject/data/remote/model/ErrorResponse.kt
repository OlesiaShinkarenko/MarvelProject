package effective.office.marvelproject.data.remote.model

import effective.office.marvelproject.R


data class ErrorResponse(val description: Int) {
    companion object {
        fun getResponse(code: Int): ErrorResponse {
            val description = when (code) {
                404 -> R.string.not_found
                in 400..499 -> R.string.error_client
                in 500..599 -> R.string.error_server
                else -> R.string.unknown_error
            }
            return ErrorResponse(description)
        }
    }
}