package com.avinnovz.sss.base;

import android.app.Application;

import com.avinnovz.sss.R;
import com.avinnovz.sss.helpers.AppConstants;
import com.avinnovz.sss.helpers.LogHelper;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.concurrent.Executors;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by jayan on 8/27/2016.
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        if (AppConstants.PICASSO == null) {

            File cacheDir = getExternalCacheDir();
            if (cacheDir == null) {
                cacheDir = getCacheDir();
            }
            final Cache cache = new Cache(cacheDir, 10 * 1024 * 1024);

            final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(
                            chain -> {
                                Request request = chain.request();
                                LogHelper.log("api","performing url --> " + request.url());
                                return chain.proceed(request);
                            })
                    .cache(cache)
                    .build();

            /** initialize picasso */
            AppConstants.PICASSO = new Picasso.Builder(this)
                    .executor(Executors.newSingleThreadExecutor())
                    .downloader(new OkHttp3Downloader(okHttpClient)).build();

        }
    }
}
