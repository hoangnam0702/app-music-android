package com.example.appmucsic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmucsic.Activity.PlayNhacActivity;
import com.example.appmucsic.Model.Baihat;
import com.example.appmucsic.R;
import com.example.appmucsic.Service.APIService;
import com.example.appmucsic.Service.Dataservice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchBaiHatAdapter extends RecyclerView.Adapter<SearchBaiHatAdapter.ViewHolder> {

    Context context;
    ArrayList<Baihat> mangbaihat;

    public SearchBaiHatAdapter(Context context, ArrayList<Baihat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_search_bai_hat,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Baihat baihat = mangbaihat.get(position);
        holder.txtTenbaihat.setText(baihat.getTenbaihat());
        holder.txtcasi.setText(baihat.getCasi());
        Picasso.with(context).load(APIService.getbaihat+baihat.getHinhbaihat()).into(holder.imgbaihat);



    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

            TextView txtTenbaihat, txtcasi;
            ImageView imgbaihat, imgluotthich;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                txtTenbaihat = itemView.findViewById(R.id.textviewsearchtenbaihat);
                txtcasi = itemView.findViewById(R.id.textviewsearchcasi);
                imgbaihat = itemView.findViewById(R.id.imageviewSearchbaihat);
                imgluotthich = itemView.findViewById(R.id.imageviewSearchluotthich);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, PlayNhacActivity.class);
                        intent.putExtra("cakhuc",mangbaihat.get(getPosition()));
                        context.startActivity(intent);
                    }
                });
                imgluotthich.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        imgluotthich.setImageResource(R.drawable.iconloved);
                        Dataservice dataservice = APIService.getService();
                        Call<String> callback = dataservice.UpdateLuotThich("1",mangbaihat.get(getPosition()).getIdbaihat());
                        callback.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                String ketqua = response.body();
                                if (ketqua.equals("Success")){
                                    Toast.makeText(context,"Like", Toast.LENGTH_SHORT).show();

                                }else {
                                    Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                            }
                        });
                        imgluotthich.setEnabled(false);
                    }
                });
            }
        }
}
