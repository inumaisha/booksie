package com.example.booksie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    public static final String BOOK_ID_KEY = "bookId";
    private TextView txtBookName, txtAuthor, txtChapters, txtDescription;
    private Button btnAddWishlist, btnAddFinished, btnAddCurrentlyReadiing, btnAddFav;
    private ImageView bookImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initViews ();

//        String longDescription = "An astronomer in a Japanese observatory, peering into his telescope one night, becomes the first person to witness a planet emerging from a wormhole. He names the planet after his daughter, Remina, and both become world-famous thanks to the unprecedented discovery. However, things take a turn for the worse when the planet enters a collision course with Earth... and the stars along the way seem to be disappearing...";
//
//        //TODO: get data from rec view in here
//        Book book = new Book(1, "Hellstar Remina", "Junji Ito", 1350,"https://images-na.ssl-images-amazon.com/images/I/91CcnCmahCL.jpg",
//                "The sci-fi masterwork Remina tells the chilling tale of a hell star.", longDescription);

        Intent intent = getIntent();
        if (null != intent){
            int bookId = intent.getIntExtra(BOOK_ID_KEY, -1);
            if (bookId != -1){
                Book incomingBook = Utils.getInstance(this).getBookById(bookId);
                if (null != incomingBook){
                    setData(incomingBook);

                    handleFinished(incomingBook); //passing the incoming book itself
                    handleWishlist(incomingBook);
                    handleCurrentlyReading(incomingBook);
                    handleFav(incomingBook);
                }
            }
        }


    }

    //checking if book exists in the CR (currently reading) list
    private void handleCurrentlyReading(final Book book){

        //first getting the CR books from utils class
        ArrayList<Book> currentlyReadingBooks = Utils.getInstance(this).getCurrentlyReadingBooks();
        //define a boolean
        boolean existsInCurrentlyReadingBooks= false;

        //checking if the book exists in the CR list
        for(Book b: currentlyReadingBooks){
            if (b.getId() == book.getId()){
                //if this is true then change value of previous boolean to true
                existsInCurrentlyReadingBooks = true;
            }
        }
        //after this for loop we will know if this book exists in our currently reading list

        // then we can execute the following by disabling the add button so we dont add it again
        if(existsInCurrentlyReadingBooks){
            //disable the button if the book already exists in cr book list
            btnAddCurrentlyReadiing.setEnabled(false);
        }else{ //if the book doesnt exist in CR list then setting onclicklistener
            btnAddCurrentlyReadiing.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //by clicking button the book will be added to CR array list
                    if(Utils.getInstance(BookActivity.this).addToCurrentlyReading(book)){
                        // add toast message to notify user that the book has been added
                        Toast.makeText(BookActivity.this, "Successfully added to Currently Reading", Toast.LENGTH_SHORT).show();
                        /**
                         * navigating the user to the CR activity
                         */
                        Intent intent = new Intent(BookActivity.this, CurrentlyReadingActivity.class);
                        startActivity(intent);
                    }else{ //if something wrong happens then notify user by giving error message
                        Toast.makeText(BookActivity.this, "Oops, please try again.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    private void handleFav(final Book book){
        ArrayList<Book> favBooks = Utils.getInstance(this).getFavBooks();

        boolean existsInFavBooks= false;

        for(Book b: favBooks){
            if (b.getId() == book.getId()){
                existsInFavBooks = true;
            }
        }

        if(existsInFavBooks){
            btnAddFav.setEnabled(false);
        }else{
            btnAddFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance(BookActivity.this).addToFav(book)){
                        Toast.makeText(BookActivity.this, "Successfully added to your favourites.", Toast.LENGTH_SHORT).show();
                        /**
                         * navigating the user
                         */
                        Intent intent = new Intent(BookActivity.this, FavActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "Oops, please try again.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleWishlist(final Book book){
        ArrayList<Book> wishlistBooks = Utils.getInstance(this).getWishlistBooks();

        boolean existsInWishlistBooks = false;

        for(Book b: wishlistBooks){
            if (b.getId() == book.getId()){
                existsInWishlistBooks = true;
            }
        }

        if(existsInWishlistBooks){
            btnAddWishlist.setEnabled(false);
        }else{
            btnAddWishlist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance(BookActivity.this).addToWishlist(book)){
                        Toast.makeText(BookActivity.this, "Successfully added to wishlist.", Toast.LENGTH_SHORT).show();
                        /**
                         * navigating the user
                         */
                        Intent intent = new Intent(BookActivity.this, WishlistBookActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "Oops, please try again.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    /**
     * this is the enable and disable buttons
     * adding the book to finished book arraylist
     * @param book
     */
    private void handleFinished(final Book book){
        ArrayList<Book> finishedBooks = Utils.getInstance(this).getFinishedBooks();

        boolean existsInFinishedBooks = false;

        for(Book b: finishedBooks){
            if (b.getId() == book.getId()){
                existsInFinishedBooks = true;
            }
        }

        if(existsInFinishedBooks){
            btnAddFinished.setEnabled(false);
        }else{
            btnAddFinished.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance(BookActivity.this).addToFinished(book)){
                        Toast.makeText(BookActivity.this, "Successfully added to Finished list.", Toast.LENGTH_SHORT).show();
                        /**
                         * navigating the user
                         */
                        Intent intent = new Intent(BookActivity.this, FinishedBookActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "Oops, please try again.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setData(Book book){
        txtBookName.setText(book.getName());
        txtAuthor.setText(book.getAuthor());
        txtChapters.setText(String.valueOf(book.getChapters()));
        txtDescription.setText(book.getLongDesc());
        Glide.with(this)
                .asBitmap().load(book.getImageUrl())
                .into(bookImage);
    }

    private void initViews (){
        txtAuthor = findViewById(R.id.txtAuthorName);
        txtBookName = findViewById(R.id.txtBookName);
        txtChapters = findViewById(R.id.txtChapters);
        txtDescription = findViewById(R.id.txtDescription);

        btnAddFinished = findViewById(R.id.btnAddFinished);
        btnAddFav = findViewById(R.id.btnAddFav);
        btnAddWishlist = findViewById(R.id.btnAddWishlist);
        btnAddCurrentlyReadiing = findViewById(R.id.btnAddCurrentlyReading);

        bookImage = findViewById(R.id.imgBook);
    }

}