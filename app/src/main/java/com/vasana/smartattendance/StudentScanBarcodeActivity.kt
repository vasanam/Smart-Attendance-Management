package com.vasana.smartattendance

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.SurfaceHolder
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import com.vasana.smartattendance.databinding.ActivityStudentScanBarcodeBinding
import com.vasana.smartattendance.models.PostAttendanceRequest
import com.vasana.smartattendance.models.PostAttendanceResponse
import com.vasana.smartattendance.models.Student
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class StudentScanBarcodeActivity : BaseActivity() {
    lateinit var binding: ActivityStudentScanBarcodeBinding
    private val requestCodeCameraPermission = 1001
    private lateinit var cameraSource: CameraSource
    private lateinit var barcodeDetector: BarcodeDetector
    private var scannedValue = ""
    lateinit var student: Student
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_scan_barcode)
        binding = ActivityStudentScanBarcodeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        student = intent.getSerializableExtra("student") as Student

        if (ContextCompat.checkSelfPermission(
                baseContext, android.Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            askForCameraPermission()
        } else {
            setupControls()
        }

        val aniSlide: Animation =
            AnimationUtils.loadAnimation(this, R.anim.scanner_animation)
        binding.barcodeLine.startAnimation(aniSlide)
    }


    private fun setupControls() {
        barcodeDetector =
            BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.ALL_FORMATS).build()

        cameraSource = CameraSource.Builder(this, barcodeDetector)
            .setRequestedPreviewSize(1920, 1080)
            .setAutoFocusEnabled(true) //you should add this feature
            .build()

        binding.cameraSurfaceView.holder.addCallback(object : SurfaceHolder.Callback {
            @SuppressLint("MissingPermission")
            override fun surfaceCreated(holder: SurfaceHolder) {
                try {
                    //Start preview after 1s delay
                    cameraSource.start(holder)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }

            @SuppressLint("MissingPermission")
            override fun surfaceChanged(
                holder: SurfaceHolder,
                format: Int,
                width: Int,
                height: Int
            ) {
                try {
                    cameraSource.start(holder)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                cameraSource.stop()
            }
        })


        barcodeDetector.setProcessor(object : Detector.Processor<Barcode> {
            override fun release() {
                Toast.makeText(applicationContext, "Scanner has been closed", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun receiveDetections(detections: Detector.Detections<Barcode>) {
                val barcodes = detections.detectedItems
                if (barcodes.size() == 1) {
                    scannedValue = barcodes.valueAt(0).rawValue
                    //Don't forget to add this line printing value or finishing activity must run on main thread
                    runOnUiThread {
                        cameraSource.stop()
                        postAttendance(scannedValue);
                    }
                } else {
                    runOnUiThread {
//                        Toast.makeText(
//                            this@StudentScanBarcodeActivity,
//                            "value- else",
//                            Toast.LENGTH_SHORT
//                        ).show()
                    }
                }
            }
        })
    }

    private fun postAttendance(scannedValue: String) {
        showLoading()
        val opts = scannedValue.split("==")
        if (System.currentTimeMillis() > opts[2].toLong() + opts[3].toLong()) {
            shout("Qr expired please reach out to the professor..")
        } else
            api.postAttendance(
                PostAttendanceRequest(
                    student.id, opts[0], opts[1]
                )
            ).enqueue(object :
                Callback<PostAttendanceResponse> {
                override fun onResponse(
                    call: Call<PostAttendanceResponse>,
                    response: Response<PostAttendanceResponse>
                ) {
                    hideLoading()
                    shout(response.body()?.message)
                    finish()
                }

                override fun onFailure(call: Call<PostAttendanceResponse>, t: Throwable) {
                    shout(t.localizedMessage)
                    hideLoading()
                    finish()
                }

            })

    }

    private fun askForCameraPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.CAMERA),
            requestCodeCameraPermission
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == requestCodeCameraPermission && grantResults.isNotEmpty()) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setupControls()
            } else {
                Toast.makeText(applicationContext, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraSource.stop()
    }
}