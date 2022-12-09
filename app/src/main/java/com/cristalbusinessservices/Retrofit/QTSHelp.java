package com.cristalbusinessservices.Retrofit;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QTSHelp {

    private static final float MAX_IMAGE_DIMENSION = 1280;

    public static long getTimeStop(Context context) {
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, mode);
        return sharedPreferences.getLong("TimeStop", 0);
    }

    public static void setTimeStop(Context context, long num) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong("TimeStop", num);
        editor.commit();
    }

    public static void setCardName(Context context, String Token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("CardName", Token);
        editor.commit();
    }

    public static String getCardName(Context context) {
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, mode);
        return sharedPreferences.getString("CardName","");
    }

    public static void setCarNumber(Context context, String Token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("CarNumber", Token);
        editor.commit();
    }

    public static String getCarNumber(Context context) {
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, mode);
        return sharedPreferences.getString("CarNumber","");
    }

    public static void setEx(Context context, String Token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Ex", Token);
        editor.commit();
    }

    public static String getEx(Context context) {
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, mode);
        return sharedPreferences.getString("Ex","");
    }

    public static void setCVV(Context context, String Token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("CVV", Token);
        editor.commit();
    }

    public static String getCVV(Context context) {
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, mode);
        return sharedPreferences.getString("CVV","");
    }



    public static void setFontTV(Context context, TextView tv, String font) {
        try {
            Typeface face = Typeface.createFromAsset(context
                    .getAssets(), font);
            tv.setTypeface(face);
        } catch (Exception e) {
//            Log.d("ERROR set FONTS", e.getMessage());
        }
    }

    public static void setFontEDT(Context context, EditText tv, String font) {
        try {
            Typeface face = Typeface.createFromAsset(context
                    .getAssets(), font);
            tv.setTypeface(face);
        } catch (Exception e) {
  //          Log.d("ERROR set FONTS", e.getMessage());
        }
    }

    //set size  for view
    public static void setLayoutView(View view, int width, int height) {
        if (view!=null) {
            view.getLayoutParams().width = width;
            view.getLayoutParams().height = height;
        }
    }

    public static int blendColors(int from, int to, float ratio) {
        final float inverseRatio = 1f - ratio;

        final float r = Color.red(to) * ratio + Color.red(from) * inverseRatio;
        final float g = Color.green(to) * ratio + Color.green(from) * inverseRatio;
        final float b = Color.blue(to) * ratio + Color.blue(from) * inverseRatio;

        return Color.rgb((int) r, (int) g, (int) b);
    }

    public static void setLayoutView2(View view, int width) {
        view.getLayoutParams().width = width;
    }

    public static void showToast2(Context context, String str_message) {
        try {
            Toast toast = Toast.makeText(context, str_message, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 50);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();
        }catch (Exception ignored){

        }
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static Bitmap scaleDown(Bitmap realImage, boolean filter) {
        float ratio = Math.min(
                (float) MAX_IMAGE_DIMENSION / realImage.getWidth(),
                (float) MAX_IMAGE_DIMENSION / realImage.getHeight());
        int width = Math.round((float) ratio * realImage.getWidth());
        int height = Math.round((float) ratio * realImage.getHeight());

        Bitmap newBitmap = Bitmap.createScaledBitmap(realImage, width,
                height, filter);
        return newBitmap;
    }

    public static int checkRotation(Uri uri) throws IOException {
        ExifInterface ei = new ExifInterface(uri.getPath());
        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                return 90;
            case ExifInterface.ORIENTATION_ROTATE_180:
                return 180;
            case ExifInterface.ORIENTATION_ROTATE_270:
                return 270;
            default:
                return 0;
        }
    }

    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix,
                true);
    }

    public static Bitmap scaleImage(Context context, Uri photoUri) throws IOException, FileNotFoundException {
        InputStream is = context.getContentResolver().openInputStream(photoUri);
        BitmapFactory.Options dbo = new BitmapFactory.Options();
        dbo.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(is, null, dbo);
        is.close();

        int rotatedWidth, rotatedHeight;
        int orientation = getOrientation(context, photoUri);

        if (orientation == 90 || orientation == 270) {
            rotatedWidth = dbo.outHeight;
            rotatedHeight = dbo.outWidth;
        } else {
            rotatedWidth = dbo.outWidth;
            rotatedHeight = dbo.outHeight;
        }

        Bitmap srcBitmap;
        is = context.getContentResolver().openInputStream(photoUri);
        if (rotatedWidth > MAX_IMAGE_DIMENSION || rotatedHeight > MAX_IMAGE_DIMENSION) {
            float widthRatio = ((float) rotatedWidth) / ((float) MAX_IMAGE_DIMENSION);
            float heightRatio = ((float) rotatedHeight) / ((float) MAX_IMAGE_DIMENSION);
            float maxRatio = Math.max(widthRatio, heightRatio);

            // Create the bitmap from file
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = (int) maxRatio;
            srcBitmap = BitmapFactory.decodeStream(is, null, options);
        } else {
            srcBitmap = BitmapFactory.decodeStream(is);
        }
        is.close();

        /*
         * if the orientation is not 0 (or -1, which means we don't know), we
         * have to do a rotation.
         */
        if (orientation > 0) {
            Matrix matrix = new Matrix();
            matrix.postRotate(orientation);

            srcBitmap = Bitmap.createBitmap(srcBitmap, 0, 0, srcBitmap.getWidth(),
                    srcBitmap.getHeight(), matrix, true);
        }

        String type = context.getContentResolver().getType(photoUri);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if (type.equals("image/png")) {
            srcBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        } else if (type.equals("image/jpg") || type.equals("image/jpeg")) {
            srcBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        }
        byte[] bMapArray = baos.toByteArray();
        baos.close();
        return BitmapFactory.decodeByteArray(bMapArray, 0, bMapArray.length);
    }

    public static int getOrientation(Context context, Uri photoUri) {
        /* it's on the external media. */
        Cursor cursor = context.getContentResolver().query(photoUri,
                new String[] { MediaStore.Images.ImageColumns.ORIENTATION }, null, null, null);

        if (cursor!=null){
            if (cursor.getCount() != 1) {
                return -1;
            }
        }

        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    public static void setSave(Context context, boolean language) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("mode", language);
        editor.commit();
    }

    public static boolean getSave(Context context) {
        int mode = Activity.MODE_PRIVATE;
        if (context!=null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(
                    QTSConstrains.SHAREPRE_ID, mode);
            return sharedPreferences.getBoolean("mode",false);
        }else {
            return false;
        }
    }

    public static void setLogin(Context context, boolean language) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("Login", language);
        editor.commit();
    }

    public static boolean getLogin(Context context) {
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, mode);
        return sharedPreferences.getBoolean("Login",false);
    }

    public static void setToken(Context context, String Token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Token", Token);
        editor.apply();
        editor.commit();
    }

    public static String getToken(Context context) {
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, mode);
        return sharedPreferences.getString("Token","Token");
    }

    public static void setTokenC(Context context, String Token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("TokenC", Token);
        editor.commit();
    }

    public static String getTokenC(Context context) {
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, mode);
        return sharedPreferences.getString("TokenC","TokenC");
    }

    public static void setInsta(Context context, String Token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Insta", Token);
        editor.commit();
    }

    public static String getInsta(Context context) {
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, mode);
        return sharedPreferences.getString("Insta","Insta");
    }

    public static void setDay(Context context, String Token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Day", Token);
        editor.commit();
    }

    public static String getDay(Context context) {
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, mode);
        return sharedPreferences.getString("Day","Day");
    }

    public static void setDay3(Context context, String Token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Day3", Token);
        editor.commit();
    }

    public static String getDay3(Context context) {
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, mode);
        return sharedPreferences.getString("Day3","Day3");
    }

    public static void setDay4(Context context, String Token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Day4", Token);
        editor.commit();
    }

    public static String getDay4(Context context) {
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, mode);
        return sharedPreferences.getString("Day4","Day4");
    }

    public static void setVersion(Context context, String Token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Version", Token);
        editor.commit();
    }

    public static String getVersion(Context context) {
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, mode);
        return sharedPreferences.getString("Version","Version");
    }

    public static void setDescription(Context context, String Token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Description", Token);
        editor.commit();
    }

    public static String getDescription(Context context) {
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, mode);
        return sharedPreferences.getString("Description","Yuk Masuk Agar Koin Kamu Tidak Terbuang Sia-sia");
    }

    public static void setprogram_min_terms(Context context, String Token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("program_min_terms", Token);
        editor.commit();
    }

    public static String getprogram_min_terms(Context context) {
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, mode);
        return sharedPreferences.getString("program_min_terms","0");
    }

    public static int getprogram_reward(Context context) {
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, mode);
        return sharedPreferences.getInt("program_reward", 0);
    }

    public static void setprogram_reward(Context context, int num) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("program_reward", num);
        editor.commit();
    }

    public static int getid_programs (Context context) {
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, mode);
        return sharedPreferences.getInt("id_programs ", -1);
    }

    public static void setid_programs (Context context, int num) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("id_programs ", num);
        editor.commit();
    }

    public static int getMaxArticle (Context context) {
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, mode);
        return sharedPreferences.getInt("MaxArticle ", 0);
    }

    public static void setMaxArticle (Context context, int num) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("MaxArticle ", num);
        editor.commit();
    }

    public static String getUrlImage (Context context) {
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, mode);
        return sharedPreferences.getString("url_image", "url_image");
    }

    public static void setUrlImage (Context context, String num) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("url_image", num);
        editor.commit();
    }

    public static String getUrlClick (Context context) {
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, mode);
        return sharedPreferences.getString("url_click", "url_click");
    }

    public static void setUrlClick (Context context, String num) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("url_click", num);
        editor.commit();
    }

    public static String getUrlShow (Context context) {
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, mode);
        return sharedPreferences.getString("url_show", "url_show");
    }

    public static void setUrlShow (Context context, String num) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("url_show", num);
        editor.commit();
    }

    public static boolean getShowBaner (Context context) {
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, mode);
        return sharedPreferences.getBoolean("ShowBaner", true);
    }

    public static void setShowBaner (Context context, boolean num) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("ShowBaner", num);
        editor.commit();
    }

    public static void setDay2(Context context, String Token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Day2", Token);
        editor.commit();
    }

    public static String getDay2(Context context) {
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, mode);
        return sharedPreferences.getString("Day2","Day");
    }

    public static void setShowNew(Context context, boolean language) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("ShowNew", language);
        editor.commit();
    }

    public static boolean getShowNew(Context context) {
        int mode = Activity.MODE_PRIVATE;
        if (context!=null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(
                    QTSConstrains.SHAREPRE_ID, mode);
            return sharedPreferences.getBoolean("ShowNew",false);
        }else {
            return false;
        }
    }

    public static void setShowTip(Context context, boolean language) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("setShowTip", language);
        editor.commit();
    }

    public static boolean getShowTip(Context context) {
        int mode = Activity.MODE_PRIVATE;
        if (context!=null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(
                    QTSConstrains.SHAREPRE_ID, mode);
            return sharedPreferences.getBoolean("setShowTip",false);
        }else {
            return false;
        }
    }

// get list count + id article
//    public static void setCountArticle (Context context, ArrayList<ObjectCountAtircle> objectCountAtircle){
//        SharedPreferences appSharedPrefs = PreferenceManager
//                .getDefaultSharedPreferences(context.getApplicationContext());
//        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
//        Gson gson = new Gson();
//        String json = gson.toJson(objectCountAtircle);
//        prefsEditor.putString("ObjectCountAtircle", json);
//        prefsEditor.commit();
//    }
//
//    public static List<ObjectCountAtircle> getCountArticle(Context context){
//        SharedPreferences appSharedPrefs = PreferenceManager
//                .getDefaultSharedPreferences(context.getApplicationContext());
//        Gson gson = new Gson();
//        String json = appSharedPrefs.getString("ObjectCountAtircle", "");
//        Type type = new TypeToken<List<ObjectCountAtircle>>(){}.getType();
//        List<ObjectCountAtircle> countAtircleList = gson.fromJson(json, type);
//        return countAtircleList;
//    }

    public static int getCountArticleInt (Context context) {
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, mode);
        return sharedPreferences.getInt("CountArticle ", 0);
    }

    public static void setCountArticleInt (Context context, int num) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("CountArticle ", num);
        editor.commit();
    }

    public static int getBannerID (Context context) {
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, mode);
        return sharedPreferences.getInt("BannerID ", 0);
    }

    public static void setBannerID (Context context, int num) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                QTSConstrains.SHAREPRE_ID, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("BannerID ", num);
        editor.commit();
    }

    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    public static boolean checkInternet(Context context){
        if (context == null) {
            return false;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static boolean isAppRunning(final Context context, final String packageName) {
        final ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        final List<ActivityManager.RunningAppProcessInfo> procInfos = activityManager.getRunningAppProcesses();
        if (procInfos != null)
        {
            for (final ActivityManager.RunningAppProcessInfo processInfo : procInfos) {
                if (processInfo.processName.equals(packageName)) {
                    return true;
                }
            }
        }
        return false;
    }
}
