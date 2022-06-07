package com.codepath.android.booksearch.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ShareCompat;
import androidx.core.view.MenuItemCompat;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.android.booksearch.R;
import com.codepath.android.booksearch.models.Book;

import org.parceler.Parcel;
import org.parceler.Parcels;


public class BookDetailActivity extends AppCompatActivity {
    private ImageView ivBookCover;
    private TextView tvTitle;
    private TextView tvAuthor;
    private ShareActionProvider miShareAction;
    private Intent shareIntent;
    private Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);



        // Fetch views
        ivBookCover = (ImageView) findViewById(R.id.ivBookCover);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvAuthor = (TextView) findViewById(R.id.tvAuthor);

        // Extract book object from intent extras
        book = (Book) Parcels.unwrap(getIntent().getParcelableExtra("book"));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setTitle(book.getTitle());
        tvTitle.setText(book.getTitle());
        tvAuthor.setText(book.getAuthor());
        Glide.with(this).load(book.getCoverUrl()).into(ivBookCover);




        // Checkpoint #5
        // Reuse the Toolbar previously used in the detailed activity by referring to this guide
        // Follow using a Toolbar guide to set the Toolbar as the ActionBar.
        // Change activity title to reflect the book title by referring to the Configuring The ActionBar guide.
        // (Bonus) Get additional book information like publisher and publish_year from the Books API and display in details view.
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_book_detail, menu);
        // Checkpoint #6
        // Add Share Intent
        MenuItem item = menu.findItem(R.id.menu_item_share);
        miShareAction = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        String imageLink = book.getCoverUrl();
        String yourShareText = "Share image" + imageLink;
        shareIntent = ShareCompat.IntentBuilder.from(this)
                .setType("text/plain").setText(yourShareText).getIntent();
        // Set the share Intent
        miShareAction.setShareIntent(shareIntent);
        return true;
        // see http://guides.codepath.org/android/Sharing-Content-with-Intents#shareactionprovider
        // (Bonus) Share book title and cover image using the same intent.

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
}
