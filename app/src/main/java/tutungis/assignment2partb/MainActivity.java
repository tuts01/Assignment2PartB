package tutungis.assignment2partb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.SearchEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    Button loadImage;
    Button doubleColButton;
    Button singleColButton;
    EditText inputSearch;
    FrameLayout frameLayout;
    ProgressBar progressBar;
    ViewMode viewMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadImage = findViewById(R.id.loadImage);
        doubleColButton = findViewById(R.id.doubleColButton);
        singleColButton = findViewById(R.id.singleColButton);
        inputSearch = findViewById(R.id.inputSearch);
        frameLayout = findViewById(R.id.frameLayout);
        progressBar = findViewById(R.id.progressBar);

        viewMode = ViewMode.SINGLE; //Default mode is single
        progressBar.setVisibility(View.INVISIBLE);

        loadImage.setOnClickListener(view -> {
            frameLayout.setVisibility(View.INVISIBLE);
            searchImage();
        });

        doubleColButton.setOnClickListener(view -> {

        });

        singleColButton.setOnClickListener(view -> {

        });
    }

    private void searchImage()
    {
        progressBar.setVisibility(View.VISIBLE);

        SearchTask searchTask = new SearchTask(this, inputSearch.getText().toString());
    }
}