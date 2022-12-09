package com.cristalbusinessservices;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.gson.JsonObject;
import com.cristalbusinessservices.Model.Document_Type.APITypeDocument;
import com.cristalbusinessservices.Model.My_Taxes.APIMyTaxes;
import com.cristalbusinessservices.Model.My_Taxes.ResultMyTaxes;
import com.cristalbusinessservices.Retrofit.APIUtils;
import com.cristalbusinessservices.Retrofit.ApiService;
import com.cristalbusinessservices.Retrofit.CameraPhoto;
import com.cristalbusinessservices.Retrofit.ImageCompression;
import com.cristalbusinessservices.Retrofit.QTSHelp;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.PermissionRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadDocument extends BaseActivity {
    @BindView(R.id.tv_type)
    AppCompatTextView tv_type;
    @BindView(R.id.tv_tax)
    AppCompatTextView tv_tax;
    @BindView(R.id.cv_upload_document)
    CardView cv_upload_document;
    @BindView(R.id.tv_term)
    AppCompatTextView tv_term;
    @BindView(R.id.tv_select_image)
    AppCompatTextView tv_select_image;

    private static final int RC_CAMERA_PERM = 123;
    private static final int RC_WRITE_STORAGE_PERM = 124;
    private static final int RC_READ_STORAGE_PERM = 125;
    private String userChoosenTask, picturePath = "";
    private CameraPhoto cameraPhoto;
    private int PICK_CAMERA_REQUEST = 2;
    private int PICK_IMAGE_REQUEST = 1;
    int posiType = 0, posiTax = 0;
    String[] listType, listTax;
    APITypeDocument dataTypeDocument;
    ArrayList<ResultMyTaxes> arrData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_document);
        ButterKnife.bind(this);
        try {
            showLoading();
            posiTax = getIntent().getIntExtra("posiTax", 0);
            APITypeDocument();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @OnClick({R.id.tv_term, R.id.img_back, R.id.tv_type, R.id.tv_tax, R.id.cv_upload_document, R.id.bt_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_term:
                termP();
                break;
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_type:
                showDialogSelect(listType, "type");
                break;
            case R.id.tv_tax:
                showDialogSelect(listTax, "tax");
                break;
            case R.id.cv_upload_document:
                if (Build.VERSION.SDK_INT >= 21 && (ContextCompat.checkSelfPermission(UploadDocument.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                        || ContextCompat.checkSelfPermission(UploadDocument.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                        || ContextCompat.checkSelfPermission(UploadDocument.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                    ActivityCompat.requestPermissions(UploadDocument.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            1);
                } else {
                    selectImage();
                }
                break;
            case R.id.bt_submit:
                if (tv_type.getText().length()>0){
                    if (tv_tax.getText().length()>0){
                        if (picturePath.length()>0){
                            showLoading();
                            uploadDocument();
                        }else {
                            QTSHelp.showToast2(UploadDocument.this, getString(R.string.upload_doc_required));
                        }
                    }else {
                        QTSHelp.showToast2(UploadDocument.this, getString(R.string.tax_required));
                    }
                }else {
                    QTSHelp.showToast2(UploadDocument.this, getString(R.string.type_required));
                }
                break;
        }
    }

    private void selectImage() {
        final CharSequence[] items = {getString(R.string.take_photo), getString(R.string.choose_library), getString(R.string.cancel)};
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(UploadDocument.this);
        builder.setTitle(getString(R.string.add_photo));
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals(getString(R.string.take_photo))) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        userChoosenTask = "Take Photo";
                        callWrite();
                    } else {
                        CaptureImage();
                    }
                } else if (items[item].equals(getString(R.string.choose_library))) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        userChoosenTask = "Choose from Library";
                        callWrite();
                    } else {
                        PickGalleryImage();
                    }
                } else if (items[item].equals(getString(R.string.cancel))) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @AfterPermissionGranted(RC_WRITE_STORAGE_PERM)
    private void callWrite() {
        if (EasyPermissions.hasPermissions(Objects.requireNonNull(UploadDocument.this), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            // Have permission, do the thing!
            callRead();
        } else {
            // Request one permission
            EasyPermissions.requestPermissions(
                    new PermissionRequest.Builder(this, RC_WRITE_STORAGE_PERM, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            .setRationale(R.string.rationale_call)
                            .setPositiveButtonText(getString(R.string.ok))
                            .setNegativeButtonText(getString(R.string.cancel))
                            .build());
        }
    }

    @AfterPermissionGranted(RC_READ_STORAGE_PERM)
    private void callRead() {
        if (EasyPermissions.hasPermissions(Objects.requireNonNull(UploadDocument.this), Manifest.permission.READ_EXTERNAL_STORAGE)) {
            // Have permission, do the thing!
            switch (userChoosenTask) {
                case "Take Photo":
                    callCamera();
                    break;
                case "Choose from Library":
                    PickGalleryImage();
                    break;
            }
        } else {
            // Request one permission
            EasyPermissions.requestPermissions(
                    new PermissionRequest.Builder(this, RC_READ_STORAGE_PERM, Manifest.permission.READ_EXTERNAL_STORAGE)
                            .setRationale(R.string.rationale_call)
                            .setPositiveButtonText(getString(R.string.ok))
                            .setNegativeButtonText(getString(R.string.cancel))
                            .build());
        }
    }

    @AfterPermissionGranted(RC_CAMERA_PERM)
    private void callCamera() {
        if (EasyPermissions.hasPermissions(Objects.requireNonNull(UploadDocument.this), Manifest.permission.CAMERA)) {
            // Have permission, do the thing!
            switch (userChoosenTask) {
                case "Take Photo":
                    CaptureImage();
                    break;
            }
        } else {
            // Request one permission
            EasyPermissions.requestPermissions(
                    new PermissionRequest.Builder(this, RC_CAMERA_PERM, Manifest.permission.CAMERA)
                            .setRationale(R.string.rationale_call)
                            .setPositiveButtonText(getString(R.string.ok))
                            .setNegativeButtonText(getString(R.string.cancel))
                            .build());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // EasyPermissions handles the request result.
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    /**
     * capture image from camera
     */
    private void CaptureImage() {
        cameraPhoto = new CameraPhoto(UploadDocument.this);
        try {
            startActivityForResult(cameraPhoto.takePhotoIntent(), PICK_CAMERA_REQUEST);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * pick image from gallery
     */
    private void PickGalleryImage() {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_CAMERA_REQUEST && resultCode == RESULT_OK) {
            try {
                cameraPhoto.addToGallery();
                picturePath = cameraPhoto.getPhotoPath();
                picturePath = ImageCompression.compressImage(picturePath);
                tv_select_image.setText("Document selected");
//                convertToBase64(picturePath);
//                uploadImage();
//                Glide.with(UploadDocument.this).load(picturePath).into(imgUserProfile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            try {
                Uri uri = data.getData();
                String[] projection = {MediaStore.Images.Media.DATA};
                Cursor cursor = UploadDocument.this.getContentResolver().query(uri, projection, null, null, null);
                Objects.requireNonNull(cursor).moveToFirst();
                int columnIndex = cursor.getColumnIndex(projection[0]);
                picturePath = cursor.getString(columnIndex); // returns null
                cursor.close();
                tv_select_image.setText("Document selected");
//                convertToBase64(picturePath);
//                uploadImage();
//                picturePath = ImageCompression.compressImage(picturePath);
//                Glide.with(UploadDocument.this).load(picturePath).into(imgUserProfile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void showDialogSelect(String[] animals, String type) {
        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(UploadDocument.this);
        builder.setItems(animals, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    if (type.equals("type")) {
                        posiType = which;
                        tv_type.setText(animals[which]);
                    } else {
                        posiTax = which;
                        tv_tax.setText(animals[which]);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void APITypeDocument() {
        try {
            ApiService mAPIService = APIUtils.getAPIService();
            mAPIService.getTypeDocument(
                    "bearer " + QTSHelp.getToken(UploadDocument.this)
            ).enqueue(new Callback<APITypeDocument>() {
                @Override
                public void onResponse(Call<APITypeDocument> call, Response<APITypeDocument> response) {
                    try {
                        if (response.isSuccessful() && response.code() == 200) {
                            dataTypeDocument = response.body();
                            List<String> arrType = new ArrayList<>();
                            for (int q = 0; q <= response.body().getResult().size() - 1; q++) {
                                arrType.add(response.body().getResult().get(q).getText());
                            }
                            listType = arrType.toArray(new String[0]);
                            tv_type.setText(listType[0]);
                            APITaxYears();
                        } else {
                            hideLoading();
                        }
                    } catch (Exception e) {
                        hideLoading();
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<APITypeDocument> call, Throwable t) {
                    try {
                        hideLoading();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            hideLoading();
            e.printStackTrace();
        }
    }

    private void APITaxYears() {
        try {
            arrData.clear();
            ApiService mAPIService = APIUtils.getAPIService();
            mAPIService.getMyTaxesDocument(
                    "bearer " + QTSHelp.getToken(UploadDocument.this)
            ).enqueue(new Callback<APIMyTaxes>() {
                @Override
                public void onResponse(Call<APIMyTaxes> call, Response<APIMyTaxes> response) {
                    try {
                        hideLoading();
                        if (response.isSuccessful() && response.code() == 200) {
                            arrData.addAll(response.body().getResult());
                            List<String> arrTax = new ArrayList<>();
                            for (int q = 0; q <= response.body().getResult().size() - 1; q++) {
                                arrTax.add(response.body().getResult().get(q).getTitle());
                            }
                            listTax = arrTax.toArray(new String[0]);
                            if (posiTax!=0){
                                tv_tax.setText(listTax[posiTax]);
                            } else {
                                tv_tax.setText(listTax[0]);
                            }
                        } else {

                        }
                    } catch (Exception e) {
                        hideLoading();
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<APIMyTaxes> call, Throwable t) {
                    try {
                        hideLoading();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            hideLoading();
            e.printStackTrace();
        }
    }

    private void uploadDocument() {
        try {
            File file = new File(picturePath);
            MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
            RequestBody taxid = RequestBody.create(MediaType.parse("text/plain"), arrData.get(posiTax).getId().toString());
            RequestBody documenttypeid = RequestBody.create(MediaType.parse("text/plain"), dataTypeDocument.getResult().get(posiType).getValue());
            RequestBody year = RequestBody.create(MediaType.parse("text/plain"), arrData.get(posiTax).getYear().toString());
            ApiService mAPIService = APIUtils.getAPIService();
            mAPIService.postUploadDocument(
                    "bearer " + QTSHelp.getToken(UploadDocument.this),
                    taxid,
                    documenttypeid,
                    year,
                    filePart
            ).enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    try {
                        hideLoading();
                        JSONObject inputJson = null;
                        if (response.isSuccessful() && response.code() == 200) {
                            inputJson = new JSONObject(String.valueOf(response.body()));
                            if (inputJson.optBoolean("isSuccess")) {
//                                QTSHelp.showToast2(UploadDocument.this, inputJson.optString("notification"));
                                startActivity(new Intent(UploadDocument.this, Home.class)
                                        .putExtra("upload_document", "upload_document")
                                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                                finish();
                            } else {
                                QTSHelp.showToast2(UploadDocument.this, inputJson.optString("notification"));
                            }
                        } else {

                        }
                    } catch (Exception e) {
                        hideLoading();
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    Log.e("error",t.getMessage());
                    hideLoading();
                }
            });
        } catch (Exception e) {
            hideLoading();
            e.printStackTrace();
        }
    }
}
