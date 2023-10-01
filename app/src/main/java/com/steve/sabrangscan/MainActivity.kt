package com.steve.sabrangscan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.steve.sabrangscan.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val cameraPermission = android.Manifest.permission.CAMERA
    private lateinit var binding : ActivityMainBinding

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){
            isGranted ->if(isGranted){
        //start Scanner
    }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //////
        binding.button.setOnClickListener{
            requestCameraAndStartScanner()
        }
    }


    private fun requestCameraAndStartScanner() {
        if(isPermissionGranted(cameraPermission)){
            //Start scanner

        }

        else {
            requestCameraPermission()
        }

    }

    private fun requestCameraPermission() {
        when {
            shouldShowRequestPermissionRationale(cameraPermission) ->{ // premission has been denied previously
                cameraPermissionRequest {
                    openPermissionSetting()
                }
            }
            else -> {
                requestPermissionLauncher.launch(cameraPermission)
            }

        }
    }
}