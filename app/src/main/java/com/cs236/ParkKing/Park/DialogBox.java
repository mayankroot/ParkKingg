package com.cs207.ParkKing.Park;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DialogBox extends AppCompatActivity {
    final Context context =this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_box);
        Intent intent = getIntent();
        String amount = intent.getStringExtra("amount");
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.activity_user_information);
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        // params.height = WindowManager.LayoutParams.FILL_PARENT;
        params.width= WindowManager.LayoutParams.FILL_PARENT;
        dialog.getWindow().setAttributes((WindowManager.LayoutParams) params);
        final EditText namecard = (EditText) dialog.findViewById(R.id.nameoncard);
        final EditText creditcard = (EditText) dialog.findViewById(R.id.creditcard);
        final EditText cvvno = (EditText) dialog.findViewById(R.id.cvv);
        final EditText valid = (EditText) dialog.findViewById(R.id.vt1);
        final EditText valid1 = (EditText) dialog.findViewById(R.id.vt2);

        Button order = (Button) dialog.findViewById(R.id.completeorder);
        order.setText("Complete Order ( Total " + amount + ")");
        // if button is clicked, close the custom dialog
        order.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if ((namecard.getText().toString().isEmpty()) || (creditcard.getText().toString().isEmpty()) || (cvvno.getText().toString().isEmpty()) || (valid.getText().toString().isEmpty())) {
                    Toast.makeText(getApplicationContext(), "Please insert the data", Toast.LENGTH_SHORT).show();
                }
                    else
                    {
                    Intent intent;
                    intent = new Intent(DialogBox.this, Confirmation.class);
                    startActivity(intent);
                    dialog.dismiss();
                }
            }
        });
        dialog.show();

    }
}
