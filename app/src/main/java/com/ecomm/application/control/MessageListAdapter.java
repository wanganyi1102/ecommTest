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

    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;

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
//        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.messagelayout, parent, false);
//
//        return new ViewHolder(view);

        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.messagelayout, parent, false);
            return new SentMessageHolder(view);
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.replaymessage, parent, false);
            return new ReceivedMessageHolder(view);
        }
        return null;

    }

    // Determines the appropriate ViewType according to the sender of the message.
    @Override
    public int getItemViewType(int position) {
        String message = mMessageList.get(position);

        if (position%2 == 0) {
            // If the current user is the sender of the message
            return VIEW_TYPE_MESSAGE_SENT;
        } else {
            // If some other user sent the message
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String message = mMessageList.get(position);

        switch (holder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((SentMessageHolder) holder).bind(message);
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceivedMessageHolder) holder).bind(message);
        }
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
//        return 1;
    }

//    public class ViewHolder extends RecyclerView.ViewHolder{
//        TextView messageText, timeText;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            messageText = (TextView) itemView.findViewById(R.id.text_gchat_message_me);
//            //timeText = (TextView) itemView.findViewById(R.id.text_gchat_timestamp_me);
//        }
//
//        void bind(String message) {
//            messageText.setText(message);
//        }
//    }
    public class SentMessageHolder extends RecyclerView.ViewHolder{
        TextView messageText, timeText;

        public SentMessageHolder(@NonNull View itemView) {
            super(itemView);
            messageText = (TextView) itemView.findViewById(R.id.text_gchat_message_me);
            //timeText = (TextView) itemView.findViewById(R.id.text_gchat_timestamp_me);
        }

        void bind(String message) {
            messageText.setText(message);
        }
    }

    public class ReceivedMessageHolder extends RecyclerView.ViewHolder{
        TextView messageText, timeText;

        public ReceivedMessageHolder(@NonNull View itemView) {
            super(itemView);
            messageText = (TextView) itemView.findViewById(R.id.text_gchat_message_other);
            //timeText = (TextView) itemView.findViewById(R.id.text_gchat_timestamp_me);
        }

        void bind(String message) {
            messageText.setText(message);
        }
    }

}