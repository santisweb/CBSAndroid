package com.cristalbusinessservices.Retrofit;

public class APIUtils {
    public static String stringLanguage = "en";
    public static String tokenPublic = "BizHub_Public_Token tbp22d2f625-9380-45c4-8e4b-r17fd13fdf99";
    public static String tokenPublic2 = "BizHub_Public_Token tbp22d2f625-9380-45c4-8e4b-r17fd13fdf99";
    private APIUtils() {}

    public static ApiService getAPIService() {
        return RetrofitClient.getClient("https://api.tbppro.com/").create(ApiService.class);
    }

}
