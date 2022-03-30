package com.example.booksie;


import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {
    //keys
    private static final String ALL_BOOKS_KEY = "all_books";
    private static final String FINISHED_BOOKS_KEY = "finished_books";
    private static final String WISHLIST_KEY ="wishlist_books";
    private static final String CURRENTLY_READING_KEY = "currently_reading_books";
    private static final String FAV_BOOKS_KEY = "fav_books";


    private static Utils instance;
    private SharedPreferences sharedPreferences;
//    private static ArrayList<Book> allBooks;
//    private static ArrayList<Book> finishedBooks;
//    private static ArrayList<Book> wishlistBooks;
//    private static ArrayList<Book> currentlyReadingBooks;
//    private static ArrayList<Book> favBooks;

    private Utils(Context context) {

        sharedPreferences = context.getSharedPreferences("alternative_db", Context.MODE_PRIVATE);


        if (null == getAllBooks()){
            initData();
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        if(null == getFinishedBooks()){
            editor.putString(FINISHED_BOOKS_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

        if(null == getWishlistBooks()){
            editor.putString(WISHLIST_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

        if(null == getCurrentlyReadingBooks()){
            editor.putString(CURRENTLY_READING_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

        if(null == getFavBooks()){
            editor.putString(FAV_BOOKS_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
    }

    private void initData() {

        ArrayList<Book> books = new ArrayList<>();
        //creating string for the long description so that i can add long desc later
        String hellStar =
                "An astronomer in a Japanese observatory, peering into \n" +
                "his telescope one night,becomes the first person \n" +
                "to witness a planet emerging from a wormhole. \n" +
                        "\n" +
                "He names the planet after his daughter,Remina,\n" +
                "and both become world-famous thanks to the \n" +
                "unprecedented discovery.\n " +
                        "\n" +
                 "However, things take a turn for the worse\n" +
                "when the planet enters a collision course with Earth...\n" +
                "and the stars along the way seem to be disappearing...\n" +
                        "\n";
        String nyankees =
                "Protecting your turf, keeping other guys from pawing\n" +
                "at your girl, showing everyone who's boss,\n" +
                "scraping by - it's all in a day's work\n" +
                "when you're a street thug... or a street cat!\n" +
                "\n" +
                "Explore the hidden world of rough-and-tumble\n" +
                "stray cats depicted as delinquents in this\n" +
                "quirky and hilarious series by mangaka Atsushi Okada! \n" +
                        "\n";
        String airGear =
                "Itsuki Minami needs no introduction— \n" +
                "everybody's heard of the 'Babyface'  \n" +
                "of the Eastside. He's the toughest kid at  \n" +
                "Higashi Junior High School,easy on the eyes" +
                "but dangerously tough when he needs to be.\n" +
                "\n" +
                "Plus, Itsuki lives with the mysterious and " +
                "sexy Noyamano \n" +
                "sisters. Life is never dull, but it becomes dangerous\n" +
                "when Itsuki leads his school to victory \n" +
                "over some vindictive Westside punks with gangster \n" +
                "connections. \n" +
                " \n" +
                "Now he stands to lose his school, his friends, \n" +
                "and everything he cares about.\n" +
                "\n" +
                "But in his darkest hour, the Noyamano girls come \n" +
                "to Itsuki's aid. They can teach him a powerful skill \n" +
                "that will save their school from the gangsters' siege \n" +
                "and introduce Itsuki to a thrilling and terrifying new world. \n" +
                "\n";

        String catMassager =
                "Nekoyama, worn out after another long day at the office, \n" +
                "stops at a therapeutic massage parlor only to \n" +
                "discover that it’s run by a cat! Not only that, \n" +
                "but the cat actually does the massaging?!\n" +
                "\n" +
                "Nekoyama is a dog lover, but as this \n" +
                "professional ‘meowsseur’ digs soft toe beans into his \n" +
                "aching muscles, his heart warms and \n" +
                "his worries melt away. \n" +
                "\n" +
                "This is only the beginning, as he and \n" +
                "other world-weary workers are about to \n" +
                "meet the other cat (and kitten!) professionals \n" +
                "who have mastered pawfully cute techniques for reducing \n" +
                "human stress.\n" +
                "\n";




        books.add(new Book(1, "Hellstar Remina", "Junji Ito", 1350,"https://images-na.ssl-images-amazon.com/images/I/91CcnCmahCL.jpg",
                "The sci-fi masterwork Remina tells the chilling tale of a hell star.", hellStar));
        books.add(new Book(2, "Nyankees", "Atsushi Okada", 250,"https://avt.mkklcdnv6temp.com/1/k/18-1583499115.jpg",
                "Protecting your turf, keeping other guys from pawing at your girl, showing everyone who is boss...it's all in a day's work when you're a street thug-er, a street cat!", nyankees));

        books.add(new Book(3, "Air Gear", "Oh! Great", 358,"https://upload.wikimedia.org/wikipedia/en/5/51/Air_Gear_vol01.jpg",
                "Air Gear is a Japanese manga series written and illustrated by Oh! great.", airGear));

        books.add(new Book(4, "Cat Massager", "Haru Hisakawa", 8,"https://image.mostraveller.com/uploads/images/comics/69136/thumbnail.png",
                "A full-colour manga about purrfectly professional massage therapists: fluffy cats who knead your troubles away.", catMassager));

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        editor.putString(ALL_BOOKS_KEY, gson.toJson(books));
        editor.apply();


    }


    public static Utils getInstance(Context context) {
        if (null != instance) {
            return instance;
        } else{
            instance = new Utils(context);
            return instance;
        }

    }

    public ArrayList<Book> getAllBooks() {
        Gson gson = new Gson();
        //defining a new typetoken, inside the type token justifying object kind and then using
        //get type method in order to have a type
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books =gson.fromJson(sharedPreferences.getString(ALL_BOOKS_KEY, null), type);
        return books;
    }

    public ArrayList<Book> getFinishedBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books =gson.fromJson(sharedPreferences.getString(FINISHED_BOOKS_KEY, null), type);
        return books;
    }

    public ArrayList<Book> getWishlistBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books =gson.fromJson(sharedPreferences.getString(WISHLIST_KEY, null), type);
        return books;
    }

    public ArrayList<Book> getCurrentlyReadingBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books =gson.fromJson(sharedPreferences.getString(CURRENTLY_READING_KEY, null), type);
        return books;
    }

    public ArrayList<Book> getFavBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books =gson.fromJson(sharedPreferences.getString(FAV_BOOKS_KEY, null), type);
        return books;
    }

    public Book getBookById(int id){
        ArrayList<Book> books = getAllBooks();
        if (null != books) {
            for (Book b : books) {
                if (b.getId() == id) {
                    return b;
                }
            }

        }

        return null;



    /**
     * adding books to the different lists
     * @param book
     * @return
     */
//    public boolean addToFinished (Book book) {
//        ArrayList<Book> books = getFinishedBooks();
//        if (null != books){
//            if (books.add(book)){
//                Gson gson = new Gson();
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.remove(FINISHED_BOOKS_KEY);
//                editor.putString(FINISHED_BOOKS_KEY, gson.toJson(books));
//                editor.commit();
//                return true;
//            }
//
//            }
//        return false;
//        }


//    public boolean addToWishlist (Book book){
//            ArrayList<Book> books = getWishlistBooks();
//            if (null != books){
//                if (books.add(book)){
//                    Gson gson = new Gson();
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.remove(WISHLIST_KEY);
//                    editor.putString(WISHLIST_KEY, gson.toJson(books));
//                    editor.commit();
//                    return true;
//                }
//
//            }
//            return false;
//        }
//    public boolean addToFav (Book book){
//            ArrayList<Book> books = getFavBooks();
//            if (null != books){
//                if (books.add(book)){
//                    Gson gson = new Gson();
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.remove(FAV_BOOKS_KEY);
//                    editor.putString(FAV_BOOKS_KEY, gson.toJson(books));
//                    editor.commit();
//                    return true;
//                }
//
//            }
//            return false;
//        }
//    public boolean addToCurrentlyReading(Book book){
//            ArrayList<Book> books = getCurrentlyReadingBooks();
//            if (null != books){
//                if (books.add(book)){
//                    Gson gson = new Gson();
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.remove(CURRENTLY_READING_KEY);
//                    editor.putString(CURRENTLY_READING_KEY, gson.toJson(books));
//                    editor.commit();
//                    return true;
//                }
//
//            }
//            return false;
//        }
/** deleting books from different lists
 */
//    public boolean removeFromFinished(Book book){
//        ArrayList<Book> books = getFinishedBooks();
//        if (null != books){
//            for (Book b: books){
//                if (b.getId() == book.getId()){
//                    if(books.remove(b)){
//                        Gson gson = new Gson();
//                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                        editor.remove(FINISHED_BOOKS_KEY);
//                        editor.putString(FINISHED_BOOKS_KEY, gson.toJson(books));
//                        editor.commit();
//                        return true;
//                    }
//                }
//            }
//        } return false;
//    }
//    public boolean removeFromWishlist(Book book){
//
//    }
//    public boolean removeFromFav(Book book){
//
//    }
//    public boolean removeFromCurrentlyReading(Book book){
//
//    }

}

    public boolean addToFinished(Book book) {
        ArrayList<Book> books = getFinishedBooks();
        if (null != books){
            if (books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(FINISHED_BOOKS_KEY);
                editor.putString(FINISHED_BOOKS_KEY, gson.toJson(books));
                editor.apply();
                return true;
            }

        }
        return false;
    }

    public boolean addToCurrentlyReading(Book book) {
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if (null != books){
            if (books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(CURRENTLY_READING_KEY);
                editor.putString(CURRENTLY_READING_KEY, gson.toJson(books));
                editor.apply();
                return true;
            }

        }
        return false;
    }

    public boolean addToFav(Book book) {
        ArrayList<Book> books = getFavBooks();
        if (null != books){
            if (books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(FAV_BOOKS_KEY);
                editor.putString(FAV_BOOKS_KEY, gson.toJson(books));
                editor.apply();
                return true;
            }

        }
        return false;
    }

    public boolean addToWishlist(Book book) {
        ArrayList<Book> books = getWishlistBooks();
        if (null != books){
            if (books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(WISHLIST_KEY);
                editor.putString(WISHLIST_KEY, gson.toJson(books));
                editor.apply();
                return true;
            }

        }
        return false;
    }

    public boolean removeFromFinished(Book book) {
        ArrayList<Book> books = getFinishedBooks();
        if (null != books){
            for (Book b: books){
                if (b.getId() == book.getId()){
                    if(books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(FINISHED_BOOKS_KEY);
                        editor.putString(FINISHED_BOOKS_KEY, gson.toJson(books));
                        editor.apply();
                        return true;
                    }
                }
            }
        } return false;
    }

    public boolean removeFromWishlist(Book book) {
        ArrayList<Book> books = getWishlistBooks();
        if (null != books){
            for (Book b: books){
                if (b.getId() == book.getId()){
                    if(books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(WISHLIST_KEY);
                        editor.putString(WISHLIST_KEY, gson.toJson(books));
                        editor.apply();
                        return true;
                    }
                }
            }
        } return false;
    }

    public boolean removeFromCurrentlyReading(Book book) {
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if (null != books){
            for (Book b: books){
                if (b.getId() == book.getId()){
                    if(books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(CURRENTLY_READING_KEY);
                        editor.putString(CURRENTLY_READING_KEY, gson.toJson(books));
                        editor.apply();
                        return true;
                    }
                }
            }
        } return false;
    }

    public boolean removeFromFav(Book book) {
        ArrayList<Book> books = getFavBooks();
        if (null != books){
            for (Book b: books){
                if (b.getId() == book.getId()){
                    if(books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(FAV_BOOKS_KEY);
                        editor.putString(FAV_BOOKS_KEY, gson.toJson(books));
                        editor.apply();
                        return true;
                    }
                }
            }
        } return false;
    }
}
