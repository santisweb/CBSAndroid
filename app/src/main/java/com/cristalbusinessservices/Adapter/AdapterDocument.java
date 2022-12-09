package com.cristalbusinessservices.Adapter;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonObject;
import com.cristalbusinessservices.Fragment.Frm_My_Document;
import com.cristalbusinessservices.Home;
import com.cristalbusinessservices.Model.Document.ResultDocument;
import com.cristalbusinessservices.R;
import com.cristalbusinessservices.Retrofit.APIUtils;
import com.cristalbusinessservices.Retrofit.ApiService;
import com.cristalbusinessservices.Retrofit.QTSHelp;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterDocument extends RecyclerView.Adapter<AdapterDocument.RecyclerViewHolder> {// Recyclerview will extend to
    // recyclerview adapter
    private List<ResultDocument> arrayList;
    private Context context;
    private Frm_My_Document frm_my_document;

    public AdapterDocument(Context context, List<ResultDocument> arrayList, Frm_My_Document frm_my_document) {
        this.context = context;
        this.arrayList = arrayList;
        this.frm_my_document = frm_my_document;
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);

    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        try{
            holder.tv_name.setText(arrayList.get(position).getTitle());
            String pattern_date0 = "yyyy-MM-dd'T'HH:mm:ss";
            String strDateFormat0 = "MM-dd-yyyy";
            String reformattedStr0 = "";
            try {
                reformattedStr0 = new SimpleDateFormat(strDateFormat0).format(new SimpleDateFormat(pattern_date0).parse(arrayList.get(position).getCreatedDateUtc()));
                holder.tv_date_upload.setText("Uploaded on "+reformattedStr0);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            holder.cv_download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Build.VERSION.SDK_INT >= 21 && (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                            || ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                        ActivityCompat.requestPermissions(((Home)context),
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                1);
                    } else {
                        ((Home)context).showLoading();
                        APIDownload("v1/documents/"+arrayList.get(position).getTaxDocumentId()+"/downloaddocument");
                    }
                }
            });
            holder.img_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                    builder1.setMessage("Are you sure to delete?");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    ((Home)context).showLoading();
                                    APIDelete("v1/documents/"+arrayList.get(position).getTaxDocumentId());
                                }
                            });

                    builder1.setNegativeButton(
                            "No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void APIDelete(String url) {
        try {
            ApiService mAPIService = APIUtils.getAPIService();
            mAPIService.deleteDocument(
                    url,
                    "bearer " + QTSHelp.getToken(context)
            ).enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    try {
                        JSONObject inputJson = null;
                        if (response.isSuccessful() && response.code() == 200) {
                            inputJson = new JSONObject(String.valueOf(response.body()));
                            if (inputJson.optBoolean("isSuccess")){
                                QTSHelp.showToast2(context, inputJson.optString("notification"));
                            }else {
                                QTSHelp.showToast2(context, inputJson.optString("notification"));
                            }
                        } else {
                            ((Home)context).hideLoading();
                        }
                        frm_my_document.APIDocument();
                    } catch (Exception e) {
                        ((Home)context).hideLoading();
                        frm_my_document.APIDocument();
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    try {
                        ((Home)context).hideLoading();
                        frm_my_document.APIDocument();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }catch (Exception e){
            ((Home)context).hideLoading();
            e.printStackTrace();
        }
    }

    private void APIDownload(String url){
        try {
            ApiService mAPIService = APIUtils.getAPIService();
            mAPIService.downloadDocument(
                    url,
                    "bearer " + QTSHelp.getToken(context)
            ).enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    try {
                        JSONObject inputJson = null;
                        if (response.isSuccessful() && response.code() == 200) {
                            inputJson = new JSONObject(String.valueOf(response.body()));
                            if (inputJson.optString("result").contains(".pdf") || inputJson.optString("result").contains(".PDF")){
                                ((Home)context).hideLoading();
                                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(inputJson.optString("result")));
                                context.startActivity(browserIntent);
                            }else {
                                new SaveWallpaperAsync(context).execute(inputJson.optString("result"));
                            }
                        } else {
                            ((Home)context).hideLoading();
                        }
                    } catch (Exception e) {
                        ((Home)context).hideLoading();
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    try {
                        ((Home)context).hideLoading();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }catch (Exception e){
            ((Home)context).hideLoading();
            e.printStackTrace();
        }
    }

    //
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        // This method will inflate the custom layout and return as viewholder
        LayoutInflater mInflater = LayoutInflater.from(viewGroup.getContext());
        ViewGroup mainGroup = (ViewGroup) mInflater.inflate(R.layout.line_my_document, viewGroup, false);
        return new RecyclerViewHolder(mainGroup);

    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_date_upload)
        AppCompatTextView tv_date_upload;
        @BindView(R.id.tv_name)
        AppCompatTextView tv_name;
        @BindView(R.id.cv_download)
        CardView cv_download;
        @BindView(R.id.img_delete)
        AppCompatImageView img_delete;

        RecyclerViewHolder(View view) {
            super(view);
            // Find all views ids
            ButterKnife.bind(this, itemView);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }

    class SaveWallpaperAsync extends AsyncTask<String, String, String> {
        private Context context;
        String image_url;
        URL ImageUrl;
        String myFileUrl1;
        Bitmap bmImg = null;

        public SaveWallpaperAsync(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub

            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... args) {
            // TODO Auto-generated method stub

            InputStream is = null;

            try {

                ImageUrl = new URL(args[0]);
                // myFileUrl1 = args[0];

                HttpURLConnection conn = (HttpURLConnection) ImageUrl
                        .openConnection();
                conn.setDoInput(true);
                conn.connect();
                is = conn.getInputStream();

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.RGB_565;
                bmImg = BitmapFactory.decodeStream(is, null, options);

            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                File dir = new File(Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                        .getAbsolutePath());
                dir.mkdirs();
                String fileName[] = args[0].split("filename=");
                File file = new File(dir, fileName[1]);
                FileOutputStream fos = new FileOutputStream(file);
                bmImg.compress(Bitmap.CompressFormat.JPEG, 75, fos);
                fos.flush();
                fos.close();
                File imageFile = file;
                MediaScannerConnection.scanFile(context,
                        new String[] { imageFile.getPath() },
                        new String[] { "image/jpg" }, null);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (Exception e) {
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String args) {
            // TODO Auto-generated method stub
            if (bmImg == null) {

            } else {
                ((Home)context).hideLoading();
                Toast.makeText(context, "Saved to Photo Gallery", Toast.LENGTH_SHORT).show();
            }
        }

    }

}

