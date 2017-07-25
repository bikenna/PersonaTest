package com.example.brianobioha.persona;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.SparseArray;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.Frame;

/**
 * Created by brianobioha on 7/24/17.
 */

public class createQRfromPicture extends Activity {

    private Bitmap bitmap;


    public createQRfromPicture(Bitmap bitmap){
        super();
        this.bitmap = bitmap;
    }

    public void readBitMap(){
        BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(createQRfromPicture.this).setBarcodeFormats(Barcode.QR_CODE).build();

        if(barcodeDetector == null){
            Log.v("Error:", "barcode is null");
        }

        if(barcodeDetector.isOperational()) {
            Log.v("yay: ", "Barcode is working");
            Frame myFrame = new Frame.Builder().setBitmap(this.bitmap).build();
            SparseArray<Barcode> barcodes = barcodeDetector.detect(myFrame);

            if(barcodes.size() != 0) {
                Log.v("My QR Code's Data", barcodes.valueAt(0).displayValue);
            }
        }
        else{
            Toast.makeText(this, "Barcode is not working", Toast.LENGTH_SHORT);
        }

        barcodeDetector.release();
    }
}
