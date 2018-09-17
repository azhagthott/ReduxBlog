package com.android.dev.reduxblog.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.android.dev.reduxblog.R;
import com.android.dev.reduxblog.adapter.AllPostAdapter;
import com.android.dev.reduxblog.adapter.SwipeToDeleteCallback;
import com.android.dev.reduxblog.data.DeletePostByIdTask;
import com.android.dev.reduxblog.data.GetAllPostTask;
import com.android.dev.reduxblog.model.Post;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView allPostRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private AllPostAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        swipeRefreshLayout = findViewById(R.id.sw_refresh);
        allPostRecyclerView = findViewById(R.id.rv_main);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), NewPostActivity.class));
            }
        });

        new GetAllPost().execute();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new GetAllPost().execute();
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        new GetAllPost().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public class GetAllPost extends GetAllPostTask {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            swipeRefreshLayout.isRefreshing();
        }

        @Override
        protected void onPostExecute(List<Post> posts) {
            super.onPostExecute(posts);
            linearLayoutManager = new LinearLayoutManager(getApplicationContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            allPostRecyclerView.setLayoutManager(linearLayoutManager);

            mAdapter = new AllPostAdapter();
            enableSwipeToDelete(allPostRecyclerView, posts);
            allPostRecyclerView.setAdapter(mAdapter);
            mAdapter.updatePostList(posts);
            swipeRefreshLayout.setRefreshing(false);
        }

        private void enableSwipeToDelete(RecyclerView recyclerView, final List<Post> posts) {
            SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(getApplicationContext()) {
                @Override
                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

                    final int position = viewHolder.getAdapterPosition();
                    mAdapter.removePost(position);
                    Post post = posts.get(position);
                    new DeletePost().execute(post.id);
                }
            };
            ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
            itemTouchhelper.attachToRecyclerView(recyclerView);
        }
    }

    public class DeletePost extends DeletePostByIdTask {
        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean) {
                mAdapter.notifyDataSetChanged();
            }
        }
    }
}
