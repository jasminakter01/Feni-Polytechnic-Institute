package com.example.firebaseproject1;

import android.content.Context;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MayAddapter2 extends RecyclerView.Adapter<MayAddapter2.MyViewHolder> implements View.OnClickListener,View.OnCreateContextMenuListener {
Context context;
List <Upload2> uploadList;
private OnItemClickLisener lisener;

    public MayAddapter2(Context context, List<Upload2> uploadList) {
        this.context = context;
        this.uploadList = uploadList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
View view=layoutInflater.inflate(R.layout.item_layout2,viewGroup,false);

 return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
Upload2 upload=uploadList.get(i);
myViewHolder.textView.setText(upload.getImagename());
        myViewHolder.textView2.setText(upload.getImagename2());

        Picasso.with(context)
                .load(upload.imageurl).
                placeholder(R.mipmap.ic_launcher_round)
                .fit()
                .centerCrop()

                .into(myViewHolder
                        .imageView);


    }

    @Override
    public int getItemCount() {
        return uploadList.size();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener , MenuItem.OnMenuItemClickListener {
        TextView textView;
        ImageView imageView;
    TextView textView2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.ttt1);
            textView2=itemView.findViewById(R.id.ttt22);
            imageView=itemView.findViewById(R.id.imm1);

            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
        }



        @Override
        public void onClick(View v) {
            if (lisener!=null){
int position=getAdapterPosition();
if (position!=RecyclerView.NO_POSITION){
lisener.OnItemClick(position);
}

            }

        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Choose an Action ");
            MenuItem doanytask=menu.add(Menu.NONE,1,1,"Do Any Task");
            MenuItem delete=menu.add(Menu.NONE,2,2,"Delete");
                doanytask.setOnMenuItemClickListener(this);
            delete.setOnMenuItemClickListener(this);

        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if (lisener!=null){
                int position=getAdapterPosition();
                if (position!=RecyclerView.NO_POSITION){
                 switch (item.getItemId()){
                     case 1:
                        lisener.Ondoanydask(position);
                       return true;
                     case 2:
                         lisener.Ondelete(position);
                         return true;
                 }
                }
                }

            return false;}
    }
    public  interface OnItemClickLisener{
        void OnItemClick(int position);
        void Ondoanydask(int position);
        void Ondelete(int position);
    }
    void setOnclickLisener(OnItemClickLisener lisener){
this.lisener=lisener;
    }
}
