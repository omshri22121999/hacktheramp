package com.omshri.hacktheramp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

class DressAdapter extends RecyclerView.Adapter<DressAdapter.MyViewHolder> {

    ArrayList<Dresses> d;
    Context c;

    public DressAdapter(ArrayList<Dresses> d, Context c) {
        this.d = d;
        this.c = c;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView dressnameTV;
        public ImageView dressIV;
        public TextView dresspriceTV;
        public Button btnExchange;
        public Button btnNoExchange;
        public MyViewHolder(View v) {
            super(v);

            dressnameTV = v.findViewById(R.id.dress_name_tv);
            dresspriceTV = v.findViewById(R.id.dress_price_tv);
            dressIV = v.findViewById(R.id.dress_iv);
            btnExchange = v.findViewById(R.id.exchangeButton);
            btnNoExchange = v.findViewById(R.id.noexchangeButton);
        }
    }


    @NonNull
    @Override
    public DressAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_dress_view, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final DressAdapter.MyViewHolder holder, final int position) {
        holder.dresspriceTV.setText("₹ "+d.get(position).getPrice());
        holder.dressnameTV.setText(d.get(position).getDressName());
        Glide.with(c).load(d.get(position).getImgUrl()).into(holder.dressIV);

        holder.dressIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog nagDialog = new Dialog(c,android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                nagDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                nagDialog.setCancelable(false);
                nagDialog.setContentView(R.layout.preview_image);
                Button btnClose = (Button)nagDialog.findViewById(R.id.btnIvClose);
                ImageView ivPreview = (ImageView)nagDialog.findViewById(R.id.iv_preview_image);
                Glide.with(c).load(d.get(position).getImgUrl()).into(ivPreview);

                btnClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {
                        nagDialog.dismiss();
                    }
                });

                nagDialog.show();
            }
        });
        holder.btnExchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),DetailsActivity.class);
                intent.putExtra("price",d.get(position).getPrice());
                c.startActivity(intent);
            }
        });
        holder.btnNoExchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),PaymentActivity.class);
                intent.putExtra("price",d.get(position).getPrice());
                c.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return d.size();
    }
}

