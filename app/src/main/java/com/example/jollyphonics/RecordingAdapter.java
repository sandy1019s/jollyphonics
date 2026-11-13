package com.example.jollyphonics;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class RecordingAdapter extends BaseAdapter {
    private Context context;
    private List<RecordingModel> recordingList;
    private LayoutInflater inflater;
    private OnRecordingActionListener listener;

    public interface OnRecordingActionListener {
        void onPlay(RecordingModel recording);
        void onDelete(RecordingModel recording);
    }

    public RecordingAdapter(Context context, List<RecordingModel> recordingList, OnRecordingActionListener listener) {
        this.context = context;
        this.recordingList = recordingList;
        this.listener = listener;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return recordingList.size();
    }

    @Override
    public Object getItem(int position) {
        return recordingList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_recording, parent, false);
            holder = new ViewHolder();
            holder.tvBadgeLetter = convertView.findViewById(R.id.tvItemBadgeLetter);
            holder.tvTitle = convertView.findViewById(R.id.tvItemTitle);
            holder.tvDate = convertView.findViewById(R.id.tvItemDate);
            holder.btnPlay = convertView.findViewById(R.id.btnItemPlay);
            holder.btnDelete = convertView.findViewById(R.id.btnItemDelete);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        RecordingModel model = recordingList.get(position);
        holder.tvBadgeLetter.setText(model.getLetter());
        holder.tvTitle.setText("Letter " + model.getLetter() + " - " + model.getExampleWord());
        holder.tvDate.setText(model.getDate());

        holder.btnPlay.setOnClickListener(v -> {
            if (listener != null) {
                listener.onPlay(model);
            }
        });

        holder.btnDelete.setOnClickListener(v -> {
            if (listener != null) {
                listener.onDelete(model);
            }
        });

        return convertView;
    }

    static class ViewHolder {
        TextView tvBadgeLetter;
        TextView tvTitle;
        TextView tvDate;
        ImageButton btnPlay;
        ImageButton btnDelete;
    }
}
