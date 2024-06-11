package effective.office.marvelproject.data.network

import effective.office.marvelproject.data.remote.model.ErrorResponse
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import javax.inject.Inject

internal class EitherCallAdapterFactory @Inject constructor() : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != Call::class.java) return null
        check(returnType is ParameterizedType) { "Return type must be a parameterized type." }

        val responseType = getParameterUpperBound(0, returnType)
        if (getRawType(responseType) != Either::class.java) return null
        check(responseType is ParameterizedType) { "Response type must be a parameterized type." }

        val leftType = getParameterUpperBound(0, responseType)
        if (getRawType(leftType) != ErrorResponse::class.java) return null

        val rightType = getParameterUpperBound(1, responseType)
        return EitherCallAdapter<Any>(rightType)
    }

    class EitherCallAdapter<R>(
        private val successType: Type
    ) : CallAdapter<R, Call<Either<ErrorResponse, R>>> {

        override fun adapt(call: Call<R>): Call<Either<ErrorResponse, R>> =
            EitherCall(call, successType)

        override fun responseType(): Type = successType
    }

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

        override fun isExecuted() = delegate.isExecuted

        override fun cancel() = delegate.cancel()

        override fun isCanceled() = delegate.isCanceled

        override fun request() = delegate.request()

        override fun timeout() = delegate.timeout()
    }
}