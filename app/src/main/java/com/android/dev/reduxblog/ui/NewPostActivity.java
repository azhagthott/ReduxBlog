package com.android.dev.reduxblog.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.dev.reduxblog.R;
import com.android.dev.reduxblog.data.CreateNewPostTask;
import com.android.dev.reduxblog.model.Post;

public class NewPostActivity extends AppCompatActivity {

    private TextInputEditText tieTitle;
    private TextInputEditText tieCategory;
    private TextInputEditText tieContent;
    private ImageView ivArrowBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        tieTitle = findViewById(R.id.tie_title);
        tieCategory = findViewById(R.id.tie_category);
        tieContent = findViewById(R.id.tie_content);

        final Post post = new Post();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                post.setPostId(0);
                post.setPostTitle(tieTitle.getText().toString());
                post.setPostCategory(tieCategory.getText().toString());
                post.setPostContent(tieContent.getText().toString());

                new CreateNewPost().execute(post);
            }
        });

        ivArrowBack = findViewById(R.id.iv_arrow_back);
        ivArrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public class CreateNewPost extends CreateNewPostTask {
        @Override
        protected void onPostExecute(Post post) {
            super.onPostExecute(post);
            Toast.makeText(NewPostActivity.this, R.string.new_post_added, Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    onBackPressed();
                    finish();
                }
            }, 1000);
        }
    }
}
