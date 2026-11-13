package com.example.jollyphonics;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class LetterAdapter extends BaseAdapter {
    private Context context;
    private List<LetterModel> letterList;
    private LayoutInflater inflater;

    public LetterAdapter(Context context, List<LetterModel> letterList) {
        this.context = context;
        this.letterList = letterList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return letterList.size();
    }

    @Override
    public Object getItem(int position) {
        return letterList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_letter, parent, false);
            holder = new ViewHolder();
            holder.layoutBg = convertView.findViewById(R.id.layoutLetterBg);
            holder.tvLetterPair = convertView.findViewById(R.id.tvLetterPair);
            holder.tvWordMini = convertView.findViewById(R.id.tvWordMini);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        LetterModel model = letterList.get(position);
        holder.tvLetterPair.setText(model.getUppercase() + " " + model.getLowercase());
        holder.tvWordMini.setText(model.getExampleWord() + " " + model.getEmoji());
        holder.layoutBg.setBackgroundColor(context.getResources().getColor(model.getBackgroundColorRes()));

        return convertView;
    }

    static class ViewHolder {
        LinearLayout layoutBg;
        TextView tvLetterPair;
        TextView tvWordMini;
    }
}
