package com.kusofan.demo.base.net;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by heming on 2017/12/13.
 */

public class ErrorHandlingCallAdapter {
    private static final String TAG = "ErrorHandlingCallAdapter";

    public ErrorHandlingCallAdapter() {
    }

    static class MyCallAdapter<T> implements SimpleCall<T> {
        private final Call<T> call;

        MyCallAdapter(Call<T> call) {
            this.call = call;
        }

        public void cancel() {
            this.call.cancel();
        }

        public void enqueue(final ErrorHandlingCallBack<T> callback) {
            this.call.enqueue(new Callback<T>() {
                public void onResponse(Call<T> call, Response<T> response) {
                    RequestManager.logResponse(response);
                    int code = response.code();
                    if(code >= 200 && code < 300) {
                        callback.success(response);
                    } else if(code == 401) {
                        callback.unauthenticated(response);
                    } else if(code >= 400 && code < 500) {
                        callback.clientError(response);
                    } else if(code >= 500 && code < 600) {
                        callback.serverError(response);
                    } else {
                        callback.unexpectedError(new RuntimeException("Unexpected response " + response));
                    }

                }

                public void onFailure(Call<T> call, Throwable t) {
                    if(t instanceof IOException) {
                        callback.networkError((IOException)t);
                    } else {
                        callback.unexpectedError(t);
                    }

                }
            });
        }

        public SimpleCall<T> clone() {
            return new ErrorHandlingCallAdapter.MyCallAdapter(this.call.clone());
        }
    }

    public static class ErrorHandlingCallAdapterFactory extends CallAdapter.Factory {
        public ErrorHandlingCallAdapterFactory() {
        }

        public CallAdapter<SimpleCall<?>> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
            if(getRawType(returnType) != SimpleCall.class) {
                return null;
            } else if(!(returnType instanceof ParameterizedType)) {
                throw new IllegalStateException("MyCall must have generic type (e.g., MyCall<ResponseBody>)");
            } else {
                final Type responseType = getParameterUpperBound(0, (ParameterizedType)returnType);
                return new CallAdapter<SimpleCall<?>>() {
                    public Type responseType() {
                        return responseType;
                    }

                    public <R> SimpleCall<R> adapt(Call<R> call) {
                        return new ErrorHandlingCallAdapter.MyCallAdapter(call);
                    }
                };
            }
        }
    }
}
