package com.example.buisness_application.Tools_TabLayout_Fragments.ToolsList;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.buisness_application.R;

import java.util.HashMap;
import java.util.Map;

public class ToolPirateFunnelActivity extends AppCompatActivity {

    private Button btnSave;
    private EditText editText1, editText2, editText3, editText4, editText5;
    private String userId;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tool_pirate_funnel_activity);

        FirebaseApp.initializeApp(this);
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        btnSave = findViewById(R.id.btnSave);
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);
        editText5 = findViewById(R.id.editText5);

        // Сохраняем данные в /users/{userId}/pirate_funnels
        databaseReference = FirebaseDatabase.getInstance().getReference("users").child(userId).child("pirate_funnels");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDataToFirebase();
            }
        });
    }

    public void saveDataToFirebase() {
        String attractedClientsNum = editText1.getText().toString();
        String goodFirstExperienceNum = editText2.getText().toString();
        String retentionNum = editText3.getText().toString();
        String referralNum = editText4.getText().toString();
        String revenueNum = editText5.getText().toString();

        if (!attractedClientsNum.isEmpty() && !goodFirstExperienceNum.isEmpty() &&
                !retentionNum.isEmpty() && !referralNum.isEmpty() && !revenueNum.isEmpty()) {

            Map<String, Object> dataToSave = new HashMap<>();
            dataToSave.put("attractedClientsNum", attractedClientsNum);
            dataToSave.put("goodFirstExperienceNum", goodFirstExperienceNum);
            dataToSave.put("retentionNum", retentionNum);
            dataToSave.put("referralNum", referralNum);
            dataToSave.put("revenueNum", revenueNum);

            databaseReference.push().setValue(dataToSave)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(ToolPirateFunnelActivity.this, "Данные сохранены!", Toast.LENGTH_SHORT).show();
                            clearFields();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ToolPirateFunnelActivity.this, "Ошибка при сохранении данных: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.e("Firebase", "Error saving data", e);
                        }
                    });

        } else {
            Toast.makeText(this, "Поля не могут быть пустыми", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearFields() {
        editText1.setText("");
        editText2.setText("");
        editText3.setText("");
        editText4.setText("");
        editText5.setText("");
    }
}
