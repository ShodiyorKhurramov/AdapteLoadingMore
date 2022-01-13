package com.example.adapteloadingmore.Activity.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.adapteloadingmore.Activity.Adapter.CustomAdapter;
import com.example.adapteloadingmore.Activity.Model.Member;
import com.example.adapteloadingmore.Activity.listener.OnBottomReachedLestener;
import com.example.adapteloadingmore.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private RecyclerView recyclerView;
    private OnBottomReachedLestener lestener;
    private CustomAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        List<Member> members=prepareMemberList();
        refreshAdapter((ArrayList<Member>) members);
    }
    void initViews(){
        recyclerView=findViewById(R.id.recycler);
        recyclerView.setLayoutManager( new GridLayoutManager(context,1));
    }

    void refreshAdapter(ArrayList<Member> members){
        CustomAdapter adapter= new CustomAdapter(context, members, new OnBottomReachedLestener() {
            @Override
            public void onBottomReachedLestener(int position) {
                Log.d("qwe","position"+position);

            }
        });


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                GridLayoutManager layoutManager = GridLayoutManager.class.cast(recyclerView.getLayoutManager());
                int totalItemCount = layoutManager.getItemCount();
                int lastVisible = layoutManager.findLastVisibleItemPosition();

                boolean endHasBeenReached = lastVisible + 5 >= totalItemCount;
                if (totalItemCount > 0 && endHasBeenReached) {
                    //you have reached to the bottom of your recycler view

                    //adapterLoadingMore.setMembers(prepareHeaderAndFooterList());
                    adapter.setMembers(prepareMemberList());
                }
            }
        });
        recyclerView.setAdapter(adapter);

    }


    public List<Member>  prepareMemberList(){
        List<Member> members= new ArrayList<>();
        members.add( new Member());

        for(int i=0; i<30; i++){
            if(i == 3 || i==8){
                members.add(new Member("Xurramov","Shodiyor",false));
            }else{
                members.add(new Member("Xurramov","Shodiyor",true));
            } }

        members.add( new Member());

        return members;
    }
}