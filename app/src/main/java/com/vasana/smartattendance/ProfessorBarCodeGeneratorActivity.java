package com.vasana.smartattendance;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;

import com.vasana.smartattendance.models.BaseResponse;
import com.vasana.smartattendance.pojo.FillAttendanceRequest;
import com.vasana.smartattendance.uitl.qrcode.QRGContents;
import com.vasana.smartattendance.uitl.qrcode.QRGEncoder;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfessorBarCodeGeneratorActivity extends BaseActivity {

    @BindView(R.id.qr)
    ImageView qr;

    private String classid, subid;
    private long time = System.currentTimeMillis();
    private long expiry = 30 * 60 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_bar_code_generator);
        ButterKnife.bind(this);
        classid = getIntent().getStringExtra("classid");
        subid = getIntent().getStringExtra("subid");
        fillData(classid,subid);
        genBarCode();
    }

    private Bitmap bitmap;

    private void fillData(String classid, String subid) {
        showLoading();
        api.fill(new FillAttendanceRequest(classid, String.valueOf(time), String.valueOf(expiry), subid)).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                hideLoading();
                shout(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                hideLoading();
                shout(t.getMessage());
            }
        });
    }

    private void genBarCode() {
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        int height = point.y;
        int smallerDimension = width < height ? width : height;
        String inputValue = classid + "==" + subid + "==" + time + "==" + expiry;
        QRGEncoder qrgEncoder = new QRGEncoder(inputValue, null, QRGContents.Type.TEXT, smallerDimension);
        qrgEncoder.setColorBlack(getResources().getColor(R.color.colorPrimaryDark));
        qrgEncoder.setColorWhite(Color.WHITE);
        // Getting QR-Code as Bitmap
        bitmap = qrgEncoder.getBitmap();
        // Setting Bitmap to ImageView
        qr.setImageBitmap(bitmap);
    }


}