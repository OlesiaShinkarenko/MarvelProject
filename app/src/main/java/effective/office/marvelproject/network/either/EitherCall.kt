package effective.office.marvelproject.network.either

import effective.office.marvelproject.R
import effective.office.marvelproject.network.model.ErrorResponse
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.lang.reflect.Type


class EitherCall<R>(
    private val delegate: Call<R>,
    private val successType: Type
) : Call<Either<ErrorResponse, R>> {

    override fun enqueue(callback: Callback<Either<ErrorResponse, R>>) = delegate.enqueue(
        object : Callback<R> {

            override fun onResponse(call: Call<R>, response: Response<R>) {
                callback.onResponse(this@EitherCall, Response.success(response.toEither()))
            }

            private fun Response<R>.toEither(): Either<ErrorResponse, R> {
                if (!isSuccessful) {
                    return Either.Fail(ErrorResponse.getResponse(code()))
                }

                body()?.let { body -> return Either.success(body) }

                return if (successType == Unit::class.java) {
                    @Suppress("UNCHECKED_CAST")
                    Either.success(Unit) as Either<ErrorResponse, R>
                } else {
                    @Suppress("UNCHECKED_CAST")
                    Either.fail(UnknownError("Response body was null")) as Either<ErrorResponse, R>
                }
            }

            override fun onFailure(call: Call<R>, throwable: Throwable) {
                val error = when (throwable) {
                    is IOException -> ErrorResponse(effective.office.marvelproject.R.string.connection_error)
                    else -> ErrorResponse(effective.office.marvelproject.R.string.unknown_error)
                }
                callback.onResponse(this@EitherCall, Response.success(Either.Fail(error)))
            }
        }
    )

    override fun clone(): Call<Either<ErrorResponse, R>> {
        TODO("Not yet implemented")
    }

    override fun execute(): Response<Either<ErrorResponse, R>> {
        TODO("Not yet implemented")
    }

    override fun isExecuted(): Boolean {
        TODO("Not yet implemented")
    }

    override fun cancel() {
        TODO("Not yet implemented")
    }

    override fun isCanceled(): Boolean {
        TODO("Not yet implemented")
    }

    override fun request(): Request {
        TODO("Not yet implemented")
    }

    override fun timeout(): Timeout {
        TODO("Not yet implemented")
    }
}