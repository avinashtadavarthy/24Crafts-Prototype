package com.twenty.four.crafts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.enums.EPickType;

public class ClientVerification3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_verification_3);

        ImageButton cross = findViewById(R.id.cross);
        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        ImageButton camera = findViewById(R.id.launchCamera);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PickSetup setup = new PickSetup()
                        .setTitle("Choose Image From")
                        .setFlip(true)
                        .setMaxSize(500)
                        .setPickTypes(EPickType.GALLERY, EPickType.CAMERA)
                        .setIconGravity(Gravity.CENTER)
                        .setButtonOrientation(LinearLayoutCompat.VERTICAL)
                        .setSystemDialog(true);

                PickImageDialog.build(setup).show(ClientVerification3Activity.this);

            }
        });
    }
}
