package com.ahaguru.schools.retorfitroom.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ahaguru.schools.retorfitroom.DiffUtils.MyDiffUtils;
import com.ahaguru.schools.retorfitroom.Entity.Nasa;
import com.ahaguru.schools.retorfitroom.R;
import com.ahaguru.schools.retorfitroom.databinding.ItemsNasaBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{

    List<Nasa> nasaList;
    private Context context;

    public RecyclerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_nasa,parent,false);
        final MyViewHolder myViewHolder = new MyViewHolder(v);
        final Nasa nasa = nasaList.get(viewType);

        myViewHolder.url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse(nasa.getUrl()));
                context.startActivity(browserIntent);
            }
        });

        myViewHolder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                View view = LayoutInflater.from(context).inflate(R.layout.details_nasa, null);

                TextView tvTitle = (TextView) view.findViewById(R.id.detailTitle);
                tvTitle.setText(nasa.getTitle());
                TextView tvUrl = (TextView) view.findViewById(R.id.detailUrl);
                tvUrl.setText(nasa.getUrl());
                tvUrl.setMovementMethod(LinkMovementMethod.getInstance());
                tvUrl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                        browserIntent.setData(Uri.parse(nasa.getUrl()));
                        context.startActivity(browserIntent);
                    }
                });
                TextView tvexplan = (TextView) view.findViewById(R.id.detailDescription);
                tvexplan.setText(nasa.getExplanation());
                tvexplan.setMovementMethod(new ScrollingMovementMethod());

                builder.setView(view);

                builder.setNegativeButton("back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                Window window = alertDialog.getWindow();
                window.setLayout(RecyclerView.LayoutParams.FILL_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);

            }
        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Nasa nasa = nasaList.get(position);

        holder.title.setText(nasa.getTitle());
        holder.url.setText(nasa.getUrl());

    }

    @Override
    public int getItemCount() {
        return  nasaList.size();
    }

    public void getAllNasaDatas(List<Nasa> nasaList)
    {
        this.nasaList = nasaList;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title, url;
        Button more;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.itemTitle);
            url = itemView.findViewById(R.id.itemUrl);
            more = itemView.findViewById(R.id.btnMore);
        }
    }

    public void updateList(List<Nasa> newList){

        MyDiffUtils diffUtilCallback = new MyDiffUtils(this.nasaList,newList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffUtilCallback);
        diffResult.dispatchUpdatesTo(this);
    }
}