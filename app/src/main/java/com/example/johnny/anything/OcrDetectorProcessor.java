package com.example.johnny.anything;

import android.util.Log;
import android.util.SparseArray;

import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;

/**
 * Created by Johnny on 2017-01-21.
 */

public class OcrDetectorProcessor implements Detector.Processor<TextBlock> {

    OcrDetectorProcessor(){

    }
    @Override
    public void release() {

    }

    @Override
    public void receiveDetections(Detector.Detections<TextBlock> detections) {
        Log.d("Processor", "TESTINGGGGGGGGGGG");
        SparseArray<TextBlock> items = detections.getDetectedItems();
        for (int i = 0; i < items.size(); ++i) {
            TextBlock item = items.valueAt(i);
            if (item != null && item.getValue() != null) {
                Log.d("Processor", "Text detected! " + item.getValue());
            }
        }
    }
}
