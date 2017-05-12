package services;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <p>
 *  This service generator is mainly use in modules package,
 *  and created for Tests, you can also use this service generator
 *  or you can take a look {@link AppService}
 * </p>
 *
 * @author John Paul Cas
 */
public class ServiceGenerator {

    private static final String BASE_URL = AppInterface.ENDPOINT;

    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

    private static OkHttpClient.Builder httpClientBuilder =
            new OkHttpClient.Builder()
                    .connectTimeout(20, TimeUnit.MINUTES)
                    .readTimeout(20, TimeUnit.MINUTES);

    private static HttpLoggingInterceptor loggingInterceptor =
            new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY);

    private static Retrofit retrofit = retrofitBuilder.build();

    public static <S> S createApiService(Class<S> serviceClass) {

        if (!httpClientBuilder.interceptors().contains(loggingInterceptor)) {
            httpClientBuilder.addInterceptor(loggingInterceptor);

            retrofitBuilder.client(httpClientBuilder.build());
            retrofit = retrofitBuilder.build();
        }

        return retrofit.create(serviceClass);
    }

}
