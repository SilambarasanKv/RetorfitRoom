package com.ahaguru.schools.retorfitroom.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ahaguru.schools.retorfitroom.DiffUtils.MyDiffUtils;
import com.ahaguru.schools.retorfitroom.Entity.Nasa;
import com.ahaguru.schools.retorfitroom.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{

    List<Nasa> nasaList;
    private Context context;

    public RecyclerAdapter(List<Nasa> nasaList, Context context) {
        this.nasaList =nasaList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        return new MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_nasa,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerAdapter.MyViewHolder holder, int position) {
        Nasa nasa = nasaList.get(position);

        holder.title.setText(nasa.getTitle());
        holder.url.setText(nasa.getUrl());

        holder.url.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW);
            browserIntent.setData(Uri.parse(nasa.getUrl()));
            context.startActivity(browserIntent);
        });

        holder.more.setOnClickListener(v -> {



            AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
            View view = LayoutInflater.from(context).inflate(R.layout.details_nasa,null);

            TextView dialogTitle, dialogUrl, dialogDescription;

            dialogTitle = view.findViewById(R.id.detailTitle);
            dialogUrl = view.findViewById(R.id.detailUrl);
            dialogDescription = view.findViewById(R.id.detailDescription);

            dialogTitle.setText(nasa.getTitle());

            dialogUrl.setClickable(true);
            dialogUrl.setText(nasa.getUrl());
            dialogUrl.setMovementMethod(LinkMovementMethod.getInstance());

            dialogUrl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                    browserIntent.setData(Uri.parse(nasa.getUrl()));
                    context.startActivity(browserIntent);
                }
            });
            dialogDescription.setText(nasa.getExplanation());

            builder.setView(view);

            builder.setNegativeButton("back", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            final AlertDialog alertDialog=builder.create();
            alertDialog.show();
            Window window = alertDialog.getWindow();
            window.setLayout(RecyclerView.LayoutParams.FILL_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        });
    }

    @Override
    public int getItemCount() {
        return  nasaList.size();
    }

    public void getAllNasaDatas(List<Nasa> nasaList)
    {
        this.nasaList = nasaList;
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title, url;
        Button more;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.itemTitle);
            url = itemView.findViewById(R.id.itemUrl);
            more = itemView.findViewById(R.id.btnMore);
        }
    }

    public void insertdata(List<Nasa> insertList){

        MyDiffUtils diffUtilCallback = new MyDiffUtils(nasaList,insertList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffUtilCallback);
        nasaList.addAll(insertList);
        diffResult.dispatchUpdatesTo(this);
    }

    public void updateList(List<Nasa> newList){

        MyDiffUtils diffUtilCallback = new MyDiffUtils(this.nasaList,newList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffUtilCallback);
        diffResult.dispatchUpdatesTo(this);
    }
}
