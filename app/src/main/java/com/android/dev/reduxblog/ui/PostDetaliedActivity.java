package com.android.dev.reduxblog.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.dev.reduxblog.AppBase;
import com.android.dev.reduxblog.R;
import com.android.dev.reduxblog.data.GetPostByIdTask;
import com.android.dev.reduxblog.model.Post;

public class PostDetaliedActivity extends AppCompatActivity {

    private TextView tvPostId;
    private TextView tvPostTitle;
    private TextView tvPostContent;
    private TextView tvPostCategory;
    private ImageView ivArrowBack;
    private String mTitlePost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detalied);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        tvPostId = findViewById(R.id.tv_post_id);
        tvPostTitle = findViewById(R.id.tv_post_title);
        tvPostContent = findViewById(R.id.tv_post_detailed_content);
        tvPostCategory = findViewById(R.id.tv_post_detailed_category);

        if (getIntent().getExtras() != null) {
            int postId = getIntent().getIntExtra(AppBase.POST_ID, 0);
            mTitlePost = getIntent().getStringExtra(AppBase.POST_TITLE);
            new GetPostDetail().execute(postId);
        }

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

    public class GetPostDetail extends GetPostByIdTask {
        @Override
        protected void onPostExecute(Post post) {
            super.onPostExecute(post);
            tvPostId.setText(String.valueOf(post.id));
            tvPostTitle.setText(mTitlePost);
            tvPostContent.setText(post.content);
            tvPostCategory.setText(post.categories);
        }
    }
}
