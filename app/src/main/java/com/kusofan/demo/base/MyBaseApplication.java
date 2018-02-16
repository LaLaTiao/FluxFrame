package com.kusofan.demo.base;

import android.app.Application;

import com.kusofan.demo.flux.Dispatcher;
import com.kusofan.demo.home.actions.ActionsCreator;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.PersistentCookieStore;
import com.zhy.http.okhttp.https.HttpsUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;

/**
 * Created by xujian on 16/2/3.
 */
public class MyBaseApplication extends BaseApplication {

    public static Dispatcher sDispatcher;
    public static ActionsCreator sActionsCreater;
    public static Application sAppContext;
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMddHHmmss");

    @Override
    public void onCreate() {
        super.onCreate();
        initConfig();
        initDependencies();
    }

    @Override
    protected void getHttpClient() {
        //配置支持Https请求
        //配置Https证书
        try {
            //支持指定证书认证的Https请求
//            HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(new InputStream[
//                    ]{getAssets().open("https.cer")}, null, null);
            //支持所有的Https请求
            HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
            CookieJarImpl cookieJar = new CookieJarImpl(new PersistentCookieStore(getApplicationContext()));
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new LoggerInterceptor("TAG"))
                    .connectTimeout(5000L, TimeUnit.MILLISECONDS)
                    .readTimeout(5000L, TimeUnit.MILLISECONDS)
                    .cookieJar(cookieJar)
                    .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                    //其他配置
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    })
                    .build();
            OkHttpUtils.initClient(okHttpClient);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Override
//    protected OkHttpClient getHttpClient() {
//        return new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                //                final String date = SDF.format(new Date());
//                Request request = chain.request()
//                        .newBuilder()
//                        .addHeader("token", AppStore.getInstance().getFastToken())
//                        .addHeader("mobile", AppStore.getInstance().getFastMobile())
//                        .removeHeader("User-Agent")
//                        .addHeader("User-Agent", getUserAgent())
//                        .build();
//                RequestManager.logRequest(request);
//                return chain.proceed(request);
//            }
//        }).build();
//        return new OkHttpClient.Builder().build();
//    }


    private void initConfig() {
        sConfigManager.mDebugOn = Constants.DEBUG;
        sConfigManager.mBaseUrl = Constants.getEndPoint();

        refreshConfig();
    }

    private void initDependencies() {
        sDispatcher = Dispatcher.getInstance();
        sActionsCreater = ActionsCreator.getInstance();
    }

}
