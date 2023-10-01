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
                //if permission is given
        startScanner() //call this which we created below..

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
    private fun startScanner(){
        ScannerActivity.startScanner(this){
            //here we get scanned values
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