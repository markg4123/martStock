package com.example.martstock;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>  {
    private ArrayList<Ad> ads;
    Uri myUri;
    String uri;
    String title,age,breed,desc,date, mart, id,key;
    int price;
    ArrayList<LikedAds> likedAds = new ArrayList<LikedAds>();
    DatabaseReference reference;
    FirebaseAuth mAuth;



    public MyAdapter(ArrayList<Ad> ads) {
        this.ads = ads;

    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.buying_layout, parent, false);

        return new ViewHolder(v);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Ad ad = ads.get(position);


        holder.adTitleText.setText(ad.getAdTitle() + "\n");
        holder.priceText.setText("Price \n €" + ad.getPrice() + "\n");
        holder.martText.setText(ad.getMart() + " " + ad.getDate() + "\n");
        holder.breedText.setText("Breed: " + ad.getBreed() + "\n");
        holder.ageText.setText("Age: " + ad.getAge() + "\n");
        holder.descText.setText(ad.getDescription() + "\n");

        uri = ad.getUrl();
        myUri = Uri.parse(uri);



        Picasso.get().load(myUri).into(holder.image);

        holder.likesText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Ad ad = ads.get(position);
                mAuth = FirebaseAuth.getInstance();

                reference = FirebaseDatabase.getInstance().getReference("LikedAd").push();
                title = ad.getAdTitle();
                price = ad.getPrice();
                breed = ad.getBreed();
                age = ad.getAge();
                desc = ad.getDescription();
                date = ad.getDate();
                mart = ad.getMart();
                id = mAuth.getCurrentUser().getUid();
                key = reference.getKey();

                uri = ad.getUrl();
                myUri = Uri.parse(uri);

                LikedAds likedAd = new LikedAds(title, price, mart, breed, age, desc, uri,date, id,key);
                likedAds.add(likedAd);








                reference.setValue(likedAd).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {

                            holder.likesText.setEnabled(false);
                            Drawable img = v.getContext().getResources().getDrawable(R.drawable.ic_baseline_thumb_up_24_pink);
                            img.setBounds(0, 0, 70, 70);
                            holder.likesText.setCompoundDrawables(img, null, null, null);

                        } else {

                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
            }
        });

    }


    @Override
    public int getItemCount() {
        return ads.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView adTitleText, priceText, martText, breedText, ageText, descText;
        public ImageView image;
        public Button likesText;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            adTitleText = (TextView) itemView.findViewById(R.id.adTitleText);
            priceText = (TextView) itemView.findViewById(R.id.priceText);
            martText = (TextView) itemView.findViewById(R.id.martText);
            breedText = (TextView) itemView.findViewById(R.id.breedText);
            ageText = (TextView) itemView.findViewById(R.id.ageText);
            descText = (TextView) itemView.findViewById(R.id.descText);
            image = (ImageView) itemView.findViewById(R.id.image);
            likesText = (Button) itemView.findViewById(R.id.likesText);


        }

        @Override
        public void onClick(View v) {
            int position = this.getLayoutPosition();

        }
    }
}
