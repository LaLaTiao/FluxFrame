package com.kusofan.demo.base.net;

import android.os.Handler;

import com.google.gson.Gson;
import com.kusofan.demo.utils.LogUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;
import okio.Buffer;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by heming on 2017/12/13.
 */

public class RequestManager {
    private static final String TAG = "RequestManager";
    private static RequestManager sInstance;
    private Handler mHandler;
//    private OkHttpClient mClient;
    private String mDefaultBaseUrl;
    private Map<String, Retrofit> mRetrofitMap = new HashMap();

//    public static RequestManager getInstance(OkHttpClient client) {
//        if (sInstance == null) {
//            sInstance = new RequestManager(client);
//        }
//        return sInstance;
//    }

    //    public RequestManager(OkHttpClient client) {
//        this.refreshConfig();
//        this.mClient = client.newBuilder().cookieJar(new JavaNetCookieJar(new CookieManager((CookieStore)null, CookiePolicy.ACCEPT_ORIGINAL_SERVER))).build();
//        this.mHandler = new Handler();
//        this.getRetrofit(this.mDefaultBaseUrl);
//    }
    public static RequestManager getInstance() {
        if (sInstance == null) {
            sInstance = new RequestManager();
        }
        return sInstance;
    }

    public RequestManager() {
        this.refreshConfig();
        this.mHandler = new Handler();
        this.getRetrofit(this.mDefaultBaseUrl);
    }

    public void refreshConfig() {
        ConfigManager cm = ConfigManager.getInstance();
        this.mDefaultBaseUrl = cm.mBaseUrl;
    }

    private Retrofit getRetrofit(String endPoint) {
        if (this.mRetrofitMap.containsKey(endPoint)) {
            return (Retrofit) this.mRetrofitMap.get(endPoint);
        } else {
//            Retrofit retrofit = (new Retrofit.Builder()).baseUrl(endPoint).client(this.mClient).addCallAdapterFactory(new ErrorHandlingCallAdapter.ErrorHandlingCallAdapterFactory()).addConverterFactory(GsonConverterFactory.create()).build();
            Retrofit retrofit = (new Retrofit.Builder()).baseUrl(endPoint).client(OkHttpUtils.getInstance().getOkHttpClient()).addCallAdapterFactory(new ErrorHandlingCallAdapter.ErrorHandlingCallAdapterFactory()).addConverterFactory(GsonConverterFactory.create()).build();
            this.mRetrofitMap.put(endPoint, retrofit);
            return retrofit;
        }
    }

    public <R> R getService(Class<R> c) {
        return this.getService(this.mDefaultBaseUrl, c);
    }

    public <R> R getService(String endPoint, Class<R> c) {
        return this.getRetrofit(endPoint).create(c);
    }

    public <R> void addRequest(SimpleCall call, final SimpleCallBack<R> callBack, final RequestStateListener listener) {
        if (listener != null) {
            listener.onStart();
        }

        call.enqueue(new ErrorHandlingCallBack<R>() {
            public void success(final Response<R> response) {
                RequestManager.this.mHandler.post(new Runnable() {
                    public void run() {
                        if (callBack != null) {
                            callBack.onSuccess(response.body());
                        }

                        if (listener != null) {
                            listener.onFinish();
                        }

                    }
                });
            }

            public void unauthenticated(final Response<?> response) {
                RequestManager.this.mHandler.post(new Runnable() {
                    public void run() {
                        if (callBack != null) {
                            callBack.onFailure(response.body());
                        }

                        if (listener != null) {
                            listener.onFinish();
                        }

                    }
                });
            }

            public void clientError(final Response<?> response) {
                RequestManager.this.mHandler.post(new Runnable() {
                    public void run() {
                        if (callBack != null) {
                            callBack.onFailure(response.body());
                        }

                        if (listener != null) {
                            listener.onFinish();
                        }

                    }
                });
            }

            public void serverError(final Response<?> response) {
                RequestManager.this.mHandler.post(new Runnable() {
                    public void run() {
                        if (callBack != null) {
                            callBack.onFailure(response.body());
                        }

                        if (listener != null) {
                            listener.onFinish();
                        }

                    }
                });
            }

            public void networkError(final IOException e) {
                e.printStackTrace();
                RequestManager.this.mHandler.post(new Runnable() {
                    public void run() {
                        if (callBack != null) {
                            callBack.onError(e);
                        }

                        if (listener != null) {
                            listener.onFinish();
                        }

                    }
                });
            }

            public void unexpectedError(final Throwable t) {
                t.printStackTrace();
                RequestManager.this.mHandler.post(new Runnable() {
                    public void run() {
                        if (callBack != null) {
                            callBack.onError(new Exception(t));
                        }

                        if (listener != null) {
                            listener.onFinish();
                        }

                    }
                });
            }
        });
    }

    public static void logRequest(Request request) {
        if (request == null) {
            LogUtil.w("RequestManager", "Request is null!");
        } else {
            LogUtil.v("RequestManager", "Start Request: " + request.toString());
            if (request.headers() == null) {
                LogUtil.v("RequestManager", "Start Request: headers: EMPTY");
            } else {
                LogUtil.v("RequestManager", "Start Request: headers: " + request.headers().toString());
            }

            if (request.body() == null) {
                LogUtil.v("RequestManager", "Start Request: body: EMPTY");
            } else {
                Buffer buffer = new Buffer();

                try {
                    request.body().writeTo(buffer);
                } catch (IOException var3) {
                    var3.printStackTrace();
                }

                LogUtil.v("RequestManager", "Start Request: body: " + buffer.readString(Charset.forName("UTF-8")));
            }
        }

    }

    public static void logResponse(Response response) {
        if (response == null) {
            LogUtil.w("RequestManager", "Response is null!");
        } else {
            LogUtil.v("RequestManager", "Get Response: " + response.raw().request().url());
            if (response.headers() == null) {
                LogUtil.v("RequestManager", "Get Response: headers: EMPTY");
            } else {
                LogUtil.v("RequestManager", "Get Response: headers: " + response.headers().toString());
            }

            LogUtil.v("RequestManager", "Get Response: code: " + response.code());
            LogUtil.v("RequestManager", "Get Response: message: " + response.message());
            if (response.body() == null) {
                LogUtil.v("RequestManager", "Get Response: body: EMPTY");
            } else {
                try {
                    String body = (new Gson()).toJson(response.body());
                    LogUtil.v("RequestManager", "Get Response: body: " + body);
                } catch (Exception var2) {
                    var2.printStackTrace();
                }
            }
        }

    }
}
