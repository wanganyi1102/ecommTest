package com.ecomm.application.boundary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ecomm.application.R;
import com.ecomm.application.control.CartListAdapter;
import com.ecomm.application.control.MessageListAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContactSellerUI extends AppCompatActivity {

    private RecyclerView mMessageRecycler;
    private com.ecomm.application.control.MessageListAdapter MessageListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_seller_u_i);

        List<String> MessageList = new ArrayList<String>();

        mMessageRecycler = (RecyclerView) findViewById(R.id.recycler_gchat);

        EditText TypeMessage = (EditText) findViewById(R.id.edit_gchat_message);
        Button Send = (Button) findViewById(R.id.button_gchat_send);
        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userMessage = TypeMessage.getText().toString();
                System.out.println(userMessage);
                MessageList.add(userMessage);
                MessageList.add("[This is an auto generated message. ] \nDear Customer, thank you for contacting us. We will get back to you as soon as possible. For urgent matters, please contact +65 91233456.");
                MessageListAdapter = new MessageListAdapter(ContactSellerUI.this, MessageList);
                mMessageRecycler.setAdapter(MessageListAdapter);
                mMessageRecycler.setLayoutManager(new LinearLayoutManager(ContactSellerUI.this));
                TypeMessage.setText(""); // clears text bar
            }
        });


        //back button
        Button backButton = (Button) findViewById(R.id.Backbutton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}