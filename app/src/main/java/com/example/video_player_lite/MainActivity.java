package com.example.video_player_lite;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.documentfile.provider.DocumentFile;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Uri> videoUri;

    Toolbar toolbar;
RecyclerView recyclerView;
FloatingActionButton fab;
VideoAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        SystemHelper systemHelper = new SystemHelper(this);
        systemHelper.setSystemBars(R.color.black,R.color.black,false);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = findViewById(R.id.fabOpenFolder);
        recyclerView = findViewById(R.id.recyclerVideos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
         videoUri= new ArrayList<>();
         adapter = new VideoAdapter(videoUri, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        fab.setOnClickListener(v->{

            // Animate FAB for fun
            fab.animate().scaleX(0.9f).scaleY(0.9f).setDuration(50).withEndAction(() ->
                    fab.animate().scaleX(1f).scaleY(1f).setDuration(50)
            );

            // Add a new dummy video
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
            startActivityForResult(intent, 1001);

            recyclerView.scrollToPosition(0); // scroll to see new item

            Toast.makeText(this, "Folder loaded 📂", Toast.LENGTH_SHORT).show();
        });

        ViewCompat.setOnApplyWindowInsetsListener(fab, (view, insets) -> {
            // Get bottom inset (navigation bar height)
            int navBarHeight = insets.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom;

            // Add some extra margin if you want
            int extraMargin = 16; // dp converted to pixels later
            float scale = view.getResources().getDisplayMetrics().density;
            int bottomMargin = navBarHeight + (int)(extraMargin * scale + 0.5f);

            view.setTranslationY(-bottomMargin); // moves FAB above nav bar

            return insets; // pass insets down
        });





    }
    //Menu to toolbar linker
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.manu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_search) {
            Toast.makeText(this, "Search clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1001 && resultCode == RESULT_OK && data != null) {
            Uri treeUri = data.getData();
            if (treeUri != null) {
                // Persist access permission
                getContentResolver().takePersistableUriPermission(
                        treeUri, Intent.FLAG_GRANT_READ_URI_PERMISSION
                );

                // Load videos
                loadVideosFromFolder(treeUri);
            }
        }
    }

///Load video from folder

private void loadVideosFromFolder(Uri treeUri) {
    DocumentFile folder = DocumentFile.fromTreeUri(this, treeUri);

    if (folder != null && folder.isDirectory()) {

        videoUri.clear(); // clear old videos

        for (DocumentFile file : folder.listFiles()) {
            if (file.isFile() && file.getName() != null) {

                String name = file.getName().toLowerCase();

                if (name.endsWith(".mp4") || name.endsWith(".mkv") || name.endsWith(".avi")) {
                    videoUri.add(file.getUri());
                }
            }
        }

        adapter.notifyDataSetChanged();
        Toast.makeText(this,
                "Loaded " + videoUri.size() + " videos",
                Toast.LENGTH_SHORT).show();
    }
}






}