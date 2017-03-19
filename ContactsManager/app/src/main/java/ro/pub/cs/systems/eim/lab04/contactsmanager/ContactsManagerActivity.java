package ro.pub.cs.systems.eim.lab04.contactsmanager;

import android.content.ContentValues;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class ContactsManagerActivity extends AppCompatActivity {

    private static final String TAG = ContactsManagerActivity.class.getName();
    private LinearLayout details;
    private Button showAdditionalBtn;

    private EditText nameET, phoneET, emilET, addressET;
    private Button saveBtn, cancelBtn;

    private ButtonClickListener lister = new ButtonClickListener();

    private class ButtonClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.cancelBtn:
                    finish();
                    break;

                case R.id.saveBtn:

                    String name = nameET.getText().toString();
                    String phone = phoneET.getText().toString();
                    String email = emilET.getText().toString();
                    String address = addressET.getText().toString();

                    Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                    intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                    if (name != null) {
                        intent.putExtra(ContactsContract.Intents.Insert.NAME, name);
                    }
                    if (phone != null) {
                        intent.putExtra(ContactsContract.Intents.Insert.PHONE, phone);
                    }
                    if (email != null) {
                        intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email);
                    }
                    if (address != null) {
                        intent.putExtra(ContactsContract.Intents.Insert.POSTAL, address);
                    }

                    startActivity(intent);
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_manager);

        details = (LinearLayout) findViewById(R.id.additionalFields);
        showAdditionalBtn = (Button) findViewById(R.id.showFieldsButton);

        nameET = (EditText) findViewById(R.id.nameEt);
        phoneET = (EditText) findViewById(R.id.numberPhoneEt);
        emilET = (EditText) findViewById(R.id.emailEt);
        addressET = (EditText) findViewById(R.id.addressEt);

        saveBtn = (Button) findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(lister);

        cancelBtn = (Button) findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(lister);

        Intent intent = getIntent();
        if (intent!= null){
            String phoneNumber = intent.getStringExtra("phone");
            phoneET.setText(phoneNumber);
        }
    }

    public void showAdditionalFields(View view) {

        String buttonTxt = (String) showAdditionalBtn.getText();
        Log.i(TAG, "showAdditionalFields: "+ buttonTxt);

        if (buttonTxt.contains("Show")){
            showAdditionalBtn.setText(R.string.hideAdditionalField);
            if (!details.isShown())
                details.setVisibility(View.VISIBLE);
        }

        if (buttonTxt.contains("Hide")){
            showAdditionalBtn.setText(R.string.showAdditionalField);
            if(details.isShown())
                details.setVisibility(View.GONE);
        }
    }
}
