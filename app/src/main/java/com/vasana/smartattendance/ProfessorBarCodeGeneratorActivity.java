package com.vasana.smartattendance;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.zxing.WriterException;
import com.vasana.smartattendance.uitl.qrcode.QRGContents;
import com.vasana.smartattendance.uitl.qrcode.QRGEncoder;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class ProfessorBarCodeGeneratorActivity extends AppCompatActivity {

    @BindView(R.id.qr)
    ImageView qr;
    String classid,subid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_bar_code_generator);
        ButterKnife.bind(this);
        classid= getIntent().getStringExtra("classid");
        subid= getIntent().getStringExtra("subid");
        genBarCode();

    }
    private Bitmap bitmap;

    private void genBarCode(){
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        int height = point.y;
        int smallerDimension = width < height ? width : height;
        String inputValue =classid+"=="+subid+"=="+System.currentTimeMillis()+"=="+ 30*60*1000;
        QRGEncoder qrgEncoder = new QRGEncoder(inputValue, null, QRGContents.Type.TEXT, smallerDimension);
        qrgEncoder.setColorBlack(getResources().getColor(R.color.colorPrimaryDark));
        qrgEncoder.setColorWhite(Color.WHITE);
        // Getting QR-Code as Bitmap
        bitmap = qrgEncoder.getBitmap();
        // Setting Bitmap to ImageView
        qr.setImageBitmap(bitmap);
    }
}