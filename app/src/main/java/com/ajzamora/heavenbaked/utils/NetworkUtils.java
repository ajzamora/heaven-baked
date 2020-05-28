package com.ajzamora.heavenbaked.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public final class NetworkUtils {
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();
    public static final String GET_REQUEST = "GET";
    private static volatile int STATUS_CODE = -1;
    public static final String UTF_8_CHARSET = "UTF-8";
    public static final int READ_TIMEOUT = 10000;
    public static final int CONNECTION_TIMEOUT = 15000;
    public static final String ERR_IO_MESSAGE = "Problem retrieving the JSON results.";


    public static String getResponseFromHttpUrl(URL url) throws IOException {
        String jsonResponse = "";
        if (url == null) return jsonResponse;
        HttpURLConnection urlConnection = null;
        try {
            InputStream inputStream = null;
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(GET_REQUEST);
            urlConnection.setReadTimeout(READ_TIMEOUT);
            urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);
            urlConnection.connect();
            STATUS_CODE = urlConnection.getResponseCode();
            switch (STATUS_CODE) {
                case HttpURLConnection.HTTP_OK:
                    inputStream = urlConnection.getInputStream();
                    jsonResponse = readFromStream(inputStream);
                    break;
                case HttpURLConnection.HTTP_UNAUTHORIZED:
                default:
                    Log.e(LOG_TAG, "HTTPresponse: " + STATUS_CODE);
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, ERR_IO_MESSAGE, e);
        } finally {
            assert urlConnection != null;
            urlConnection.disconnect();
        }
        return jsonResponse;
    }

    public static int getLastStatusCode() {
        return STATUS_CODE;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName(UTF_8_CHARSET));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                output.append(line);
                line = bufferedReader.readLine();
            }
        }
        inputStream.close();
        return output.toString();
    }


    public static URL buildUrl(final String BASE_URL, String stringPaths) {
        Uri.Builder uriBuilder = Uri.parse(BASE_URL).buildUpon();
        String[] paths = stringPaths.split("/");
        for (String path: paths) {
            uriBuilder.appendPath(path);
        }
        Uri builtUri = uriBuilder.build();
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException exception) {
            Log.e(LOG_TAG, "Error with creating URL", exception);
        }

        Log.v(LOG_TAG, "Built URI " + url);
        return url;
    }

    public static URL buildUrl(final String BASE_URL, String[] orderedPaths) {
        Uri.Builder uriBuilder = Uri.parse(BASE_URL).buildUpon();
        for (String path: orderedPaths) {
            uriBuilder.appendPath(path);
        }

        Uri builtUri = uriBuilder.build();
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException exception) {
            Log.e(LOG_TAG, "Error with creating URL", exception);
        }

        Log.v(LOG_TAG, "Built URI " + url);
        return url;
    }

    public static boolean isOnline(Activity activity) {
        ConnectivityManager connMgr = (ConnectivityManager)
                activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
}
