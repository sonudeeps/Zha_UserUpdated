package com.velmurugan.nadus.politician.RecyclerViews;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.velmurugan.nadus.politician.Adapters2.dummy2;
import com.velmurugan.nadus.politician.Adapters2.photoAdapter;
import com.velmurugan.nadus.politician.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by HP on 11/16/2017.
 */

public class photoItemAdapter  extends RecyclerView.Adapter<photoItemAdapter.ViewHolder>
{
    private int listItemLayout;
    private ArrayList<photoAdapter> list1;
    static Context context1;
    static String lang;
    static SharedPreferences sharedPreferences;
    public photoItemAdapter(int layout,ArrayList<photoAdapter> list)
    {
        this.list1=list;
        this.listItemLayout=layout;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(listItemLayout, parent, false);
         sharedPreferences = view.getContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = sharedPreferences.edit();

        final ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        TextView t1=holder.tv;
        ImageView t2=holder.imageView;
        Button b1=holder.save;
        t1.setText(list1.get(position).getTitle());
        Glide.with(context1).load(list1.get(position).getSurl()).asBitmap().into(t2);
    }

    @Override
    public int getItemCount()
    {
        return list1==null?0:list1.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {   public TextView tv;
        public ImageView imageView,bitmap;
        public Button save;
        private boolean zoomOut =  false;
        public ViewHolder(final View itemView) {

            super(itemView);

            tv=(TextView)itemView.findViewById(R.id.textView);
            imageView=(ImageView)itemView.findViewById(R.id.imageView);

            save=(Button)itemView.findViewById(R.id.savebutton);
            save.setOnClickListener(this);
            context1=itemView.getContext();
            imageView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    File folder=new File(Environment.getExternalStorageDirectory()+"/Zhagaram/Photos");
                    boolean success=true;
                    System.out.println("Directory Created...."+dummy2.photokeyvalues);

                    if(!folder.exists()){
                        success=folder.mkdirs();
                    }
                    if(success){
                        String key= dummy2.photokeyvalues.get(getLayoutPosition());

                        System.out.println("key" + key);
                        String name=tv.getText().toString();

                        StorageReference storageReference= FirebaseStorage.getInstance().getReference().child("Admin").child("Photos").child(key);

                        String file=Environment.getExternalStorageDirectory()+"/Zhagaram/Photos/"+name+".jpg";
                        File files=new File(file);

                        storageReference.getFile(files).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                System.out.println("Storage Successfull");
                                lang = sharedPreferences.getString("lang","");

                                if(lang.equals("tamil"))
                                {
                                    Toast.makeText(itemView.getContext(), "படம் பதிவிறக்கப்பட்டுவிட்டது", Toast.LENGTH_SHORT).show();
                                }
                                Toast.makeText(itemView.getContext(),"Image Saved",Toast.LENGTH_SHORT).show();

                            }
                        });
                    }

                    return false;
                }
            });


            System.out.println("0000");
            Bitmap bm=((BitmapDrawable)imageView.getDrawable()).getBitmap();
            System.out.println("00Bit"+bm);
        }

        @Override
        public void onClick(final View view) {
            if(view.getId()==R.id.savebutton){


            }
        }
    }
}
