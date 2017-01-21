package com.example.johnny.anything;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextRecognizer;

public class MainActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 1888;
    ImageView mimageView;
    TextRecognizer textRecognizer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mimageView = (ImageView) this.findViewById(R.id.image_from_camera);
        Button button = (Button) this.findViewById(R.id.take_image_from_camera);
        textRecognizer = new TextRecognizer.Builder(this).build();
        textRecognizer.setProcessor(new OcrDetectorProcessor());
        if (textRecognizer.isOperational()==true){
            TextView thing = (TextView) findViewById(R.id.Viral);
            thing.setText("SHIT'S LIT FAM");
        }

    }

    public void takeImageFromCamera(View view) {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap mphoto = (Bitmap) data.getExtras().get("data");
            Frame outputFrame = new Frame.Builder().setBitmap(mphoto).build();
            textRecognizer.detect(outputFrame);
            findText(outputFrame);
            mimageView.setImageBitmap(mphoto);
        }
    }

    private void findText(Frame frame){
        textRecognizer.detect(frame);
    }


}
