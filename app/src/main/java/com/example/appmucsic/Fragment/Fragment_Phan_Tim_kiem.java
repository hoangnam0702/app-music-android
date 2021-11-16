package com.example.appmucsic.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmucsic.Adapter.BaihathotAdapter;
import com.example.appmucsic.Adapter.PhantimkiembaihatAdapter;
import com.example.appmucsic.Model.Baihat;
import com.example.appmucsic.R;
import com.example.appmucsic.Service.APIService;
import com.example.appmucsic.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Phan_Tim_kiem extends Fragment {
    View view;
    RecyclerView recyclerViewphantimkiem;
    PhantimkiembaihatAdapter phantimkiembaihatAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_phan_tim_kiem,container,false);
        recyclerViewphantimkiem = view.findViewById(R.id.recyclerviewbaihathotphantimkiem);

        GetData();

        return view;
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callback = dataservice.GetBaiHatHot();
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                ArrayList<Baihat> baihatArrayList = (ArrayList<Baihat>) response.body();
                phantimkiembaihatAdapter = new PhantimkiembaihatAdapter(getActivity(),baihatArrayList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
                recyclerViewphantimkiem.setLayoutManager(linearLayoutManager);
                recyclerViewphantimkiem.setAdapter(phantimkiembaihatAdapter);
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }
}
