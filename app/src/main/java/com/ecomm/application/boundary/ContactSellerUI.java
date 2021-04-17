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

import java.util.Arrays;
import java.util.List;

public class ContactSellerUI extends AppCompatActivity {

    private RecyclerView mMessageRecycler;
    private com.ecomm.application.control.MessageListAdapter MessageListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_seller_u_i);

        List<String> MessageList = Arrays.asList();

        mMessageRecycler = (RecyclerView) findViewById(R.id.recycler_gchat);

        EditText TypeMessage = (EditText) findViewById(R.id.edit_gchat_message);
        Button Send = (Button) findViewById(R.id.button_gchat_send);
        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageList.add("Hello.");
            }
        });

        MessageListAdapter = new MessageListAdapter(this, MessageList);
        mMessageRecycler.setAdapter(MessageListAdapter);
        mMessageRecycler.setLayoutManager(new LinearLayoutManager(this));

    }
}