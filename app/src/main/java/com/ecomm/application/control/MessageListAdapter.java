package com.ecomm.application.control;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ecomm.application.R;
import java.util.List;

public class MessageListAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<String> mMessageList;

    public MessageListAdapter(Context context, List<String> messageList) {
        mContext = context;
        mMessageList = messageList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.messagelayout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String message = mMessageList.get(position);
        ((ViewHolder) holder).bind(message);
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
//        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView messageText, timeText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            messageText = (TextView) itemView.findViewById(R.id.text_gchat_message_me);
            //timeText = (TextView) itemView.findViewById(R.id.text_gchat_timestamp_me);
        }

        void bind(String message) {
            messageText.setText(message);
        }
    }

}