package com.example.appmucsic.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmucsic.Adapter.SearchBaiHatAdapter;
import com.example.appmucsic.Model.Baihat;
import com.example.appmucsic.R;
import com.example.appmucsic.Service.APIService;
import com.example.appmucsic.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Tim_Kiem extends Fragment {
    View view;
    Toolbar toolbar;
    RecyclerView recyclerViewsearchbaihat;
    TextView txtkhongcodulieu;
    SearchBaiHatAdapter searchBaiHatAdapter;
    LinearLayout linearLayoutfrag;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_tim_kiem, container, false);
        toolbar = view.findViewById(R.id.toolbarsearchbaihat);
        recyclerViewsearchbaihat = view.findViewById(R.id.recyclerviewsearchbaihat);
        linearLayoutfrag= view.findViewById(R.id.linerlayoutfrag);
        txtkhongcodulieu = view.findViewById(R.id.textviewkhongcodulieu);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("");
        setHasOptionsMenu(true);


        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_view,menu);
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        SearchView  searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchTuKhoaBaiHat(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }
        private  void SearchTuKhoaBaiHat(String query){
            Dataservice dataservice = APIService.getService();
            Call<List<Baihat>> callback = dataservice.GetSearchBaiHat(query);
            callback.enqueue(new Callback<List<Baihat>>() {
                @Override
                public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                    ArrayList<Baihat> mangbaihat = (ArrayList<Baihat>) response.body();
                    if (mangbaihat.size() >0 ){
                        searchBaiHatAdapter = new SearchBaiHatAdapter(getActivity(),mangbaihat);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                        recyclerViewsearchbaihat.setLayoutManager(linearLayoutManager);
                        recyclerViewsearchbaihat.setAdapter(searchBaiHatAdapter);
                        txtkhongcodulieu.setVisibility(View.GONE);
                        linearLayoutfrag.setVisibility(View.GONE);
                        recyclerViewsearchbaihat.setVisibility(View.VISIBLE);

                    }else {
                        recyclerViewsearchbaihat.setVisibility(View.GONE);
                        txtkhongcodulieu.setVisibility(View.VISIBLE);
                        linearLayoutfrag.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(Call<List<Baihat>> call, Throwable t) {

                }
            });
        }

    }
