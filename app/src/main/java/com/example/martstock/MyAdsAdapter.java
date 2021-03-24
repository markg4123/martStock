package com.example.martstock;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdsAdapter extends RecyclerView.Adapter<MyAdsAdapter.ViewHolder>{
    private ArrayList<Ad> ads;
    Uri myUri;
    String uri;
    String title,age,breed,desc,date,price1,mart,key;
    int price;




    public MyAdsAdapter(ArrayList<Ad> ads) {
        this.ads = ads;

    }
    @NonNull
    @Override
    public MyAdsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.myadslayout, parent, false);

        return new MyAdsAdapter.ViewHolder(v);
    }



    @Override
    public void onBindViewHolder(@NonNull final MyAdsAdapter.ViewHolder holder, final int position) {
                Ad ad = ads.get(position);

                holder.adTitleText.setText(ad.getAdTitle() + "\n");
                holder.priceText.setText("Price \n €" + ad.getPrice() + "\n");
                holder.martText.setText(ad.getMart() + " " + ad.getDate() + "\n");
                holder.breedText.setText("Breed: " + ad.getBreed() + "\n");
                holder.ageText.setText("Age: " + ad.getAge() + "\n");
                holder.descText.setText( ad.getDescription() + "\n");

                uri = ad.getUrl();
                myUri = Uri.parse(uri);
                Picasso.get().load(myUri).into(holder.image);




                holder.editButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Ad ad = ads.get(position);

                        uri = ad.getUrl();
                        myUri = Uri.parse(uri);
                        title = ad.getAdTitle();
                        price = ad.getPrice();
                        breed = ad.getBreed();
                        age = ad.getAge();
                        desc = ad.getDescription();
                        date = ad.getDate();
                        mart = ad.getMart();
                        key = ad.getKey();

                        Picasso.get().load(myUri).into(holder.image);

                        Intent i = new Intent(v.getContext(), UpdateAd.class);
                        i.putExtra("title", title);
                        price1 = Integer.toString(price);
                        i.putExtra("price", price1);
                        i.putExtra("breed", breed);
                        i.putExtra("key", key);
                        age = age.replace("Months", "").replace("Years", "");
                        i.putExtra("age", age);
                        i.putExtra("desc", desc);
                        i.putExtra("date", date);
                        i.putExtra("uri", myUri);


                        v.getContext().startActivity(i);

                    }
                });

                holder.soldButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Ad ad = ads.get(position);
                        title = ad.getAdTitle();
                        desc = ad.getDescription();
                        key = ad.getKey();
                        price = ad.getPrice();

                        Intent ii = new Intent(v.getContext(), SoldActivity.class);
                        ii.putExtra("title", title);
                        ii.putExtra("desc", desc);
                        price1 = Integer.toString(price);
                        ii.putExtra("prices", price1);
                        ii.putExtra("key", key);
                        v.getContext().startActivity(ii);

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
        public Button soldButton, editButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            adTitleText = (TextView) itemView.findViewById(R.id.adTitleText);
            priceText = (TextView) itemView.findViewById(R.id.priceText);
            martText = (TextView) itemView.findViewById(R.id.martText);
            breedText = (TextView) itemView.findViewById(R.id.breedText);
            ageText = (TextView) itemView.findViewById(R.id.ageText);
            descText = (TextView) itemView.findViewById(R.id.descText);
            image = (ImageView) itemView.findViewById(R.id.image);
            soldButton = (Button) itemView.findViewById(R.id.soldButton);
            editButton = (Button) itemView.findViewById(R.id.editButton);




        }


        @Override
        public void onClick(View v) {
            int position = this.getLayoutPosition();

        }
    }
}
