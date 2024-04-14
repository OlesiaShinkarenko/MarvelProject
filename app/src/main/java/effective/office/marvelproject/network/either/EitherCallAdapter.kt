package effective.office.marvelproject.network.either

import effective.office.marvelproject.network.model.ErrorResponse
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class EitherCallAdapter<R>(
    private val successType: Type
) : CallAdapter<R, Call<Either<ErrorResponse, R>>> {

    override fun adapt(call: Call<R>): Call<Either<ErrorResponse, R>> =
        EitherCall(call, successType)

    override fun responseType(): Type = successType
}