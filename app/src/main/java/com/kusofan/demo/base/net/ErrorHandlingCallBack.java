package com.kusofan.demo.base.net;

import java.io.IOException;

import retrofit2.Response;

/**
 * Created by heming on 2017/12/13.
 */

public interface ErrorHandlingCallBack<T> {
    void success(Response<T> var1);

    void unauthenticated(Response<?> var1);

    void clientError(Response<?> var1);

    void serverError(Response<?> var1);

    void networkError(IOException var1);

    void unexpectedError(Throwable var1);
}
