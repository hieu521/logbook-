package com.example.logbook3lan2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cloudinary.Transformation;
import com.cloudinary.android.MediaManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<String>  student_id, student_name, student_email, student_dob,  student_image;

    CustomAdapter(Context context,
                  ArrayList<String>  student_id,
                  ArrayList<String>  student_name,
                  ArrayList<String>  student_email,
                  ArrayList<String>  student_dob,
                  ArrayList<String>  student_image) {
        this.context = context;
        MediaManager.init(context);
        this.student_id = student_id;
        this.student_name = student_name;
        this.student_email = student_email;

        this.student_dob = student_dob;
        this.student_image = student_image;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String imagePath = student_image.get(position).toString();
        String imageUrl = MediaManager.get().url().transformation(new Transformation().fetchFormat("auto")).generate(imagePath);
        Picasso.get().load(imageUrl).into(holder.imageView);

        //       Picasso.get().load(imagePath).into(holder.imageView);


        holder.idTextView.setText(student_id.get(position).toString());
        holder.nameTextView.setText(student_name.get(position).toString());
        holder.emailTextView.setText(student_email.get(position).toString());
        holder.dobTextView.setText(student_dob.get(position).toString());
    }


    @Override
    public int getItemCount() {
        return student_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView idTextView,nameTextView, emailTextView, dobTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.student_image);
            idTextView= itemView.findViewById(R.id.student_id_txt);
            nameTextView = itemView.findViewById(R.id.student_name_txt);
            emailTextView = itemView.findViewById(R.id.student_email_txt);
            dobTextView = itemView.findViewById(R.id.student_dob_txt);
        }
    }
}
