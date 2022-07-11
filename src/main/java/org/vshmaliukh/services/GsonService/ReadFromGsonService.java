package org.vshmaliukh.services.GsonService;

import com.google.gson.*;
import org.vshmaliukh.bookshelf.Shelf;
import org.vshmaliukh.bookshelf.bookshelfObjects.Book;
import org.vshmaliukh.bookshelf.bookshelfObjects.Literature;
import org.vshmaliukh.bookshelf.bookshelfObjects.Magazine;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.vshmaliukh.constants.ConstantValues.*;

public class ReadFromGsonService {

    PrintWriter printWriter;

    public ReadFromGsonService(PrintWriter printWriter){
        this.printWriter = printWriter;
    }
    
    public Shelf readShelfFromGson(String fileName, int typeOfWorkWithFiles) throws IOException {
        switch (typeOfWorkWithFiles){
            case SAVE_READ_ONE_FILE:
                return readShelfFromOneFile(fileName+FILE_TYPE);
            case SAVE_READ_TWO_FILES:
                return readShelfFromTwoFiles(fileName);
            default:
                return new Shelf(printWriter);
        }
    }

    public Shelf readShelfFromOneFile(String fileName) throws FileNotFoundException {
        if(Files.exists(Paths.get(fileName))){
            FileReader fr = new FileReader(fileName);
            Gson gson = new Gson();

            List<Literature> literatureList = new ArrayList();
            JsonArray jsonArray;
            try{
                jsonArray = gson.fromJson(fr, JsonArray.class);
            }
            catch (JsonSyntaxException e){
                informAboutErr(System.err, "Problem to read shelf from file");
                return new Shelf(printWriter);
            }
            if(jsonArray != null){
                for (JsonElement element : jsonArray) {
                    JsonObject itemObject = element.getAsJsonObject().getAsJsonObject("Literature");
                    String typeOfClass;
                    try {
                        typeOfClass = element.getAsJsonObject().get("ClassType").getAsString();
                    }
                    catch (NullPointerException e){
                        informAboutErr(System.err, "Problem to read shelf from file");
                        return new Shelf(printWriter);
                    }
                    List<Book> books = new ArrayList<>();
                    List<Magazine> magazines = new ArrayList<>();

                    if (typeOfClass.equals(Book.class.toString())) {
                        books.add(gson.fromJson(itemObject, Book.class));
                    }
                    else if (typeOfClass.equals(Magazine.class.toString())) {
                        magazines.add(gson.fromJson(itemObject, Magazine.class));
                    }
                    literatureList.addAll(books);
                    literatureList.addAll(magazines);
                }
                return new Shelf(literatureList, printWriter);
            }
        }
        else{
            informAboutErr(System.err, "File not found");
            return new Shelf(printWriter);
        }
        return new Shelf(printWriter);
    }

    public Shelf readShelfFromTwoFiles(String fileName) throws FileNotFoundException {
        if(Files.exists(Paths.get(fileName + "Books.json")) && Files.exists(Paths.get(fileName + "Magazines.json"))){
            return new Shelf(getBooksFromGsonFile(fileName+"Books.json"),getMagazinesFromGsonFile(fileName+"Magazines.json"), printWriter);
        }
        informAboutErr(System.err, "Problem to read shelf from file");
        return new Shelf(printWriter);
    }

    public Shelf readShelfFromTwoFiles(String fileNameBooks, String fileNamesMagazines) throws FileNotFoundException {
        if(Files.exists(Paths.get(fileNameBooks)) && Files.exists(Paths.get(fileNamesMagazines))){
            return new Shelf(getBooksFromGsonFile(fileNameBooks),getMagazinesFromGsonFile(fileNamesMagazines), printWriter);
        }
        informAboutErr(System.err, "Problem to read shelf from file");
        return new Shelf(printWriter);
    }

    public List<Magazine> getMagazinesFromGsonFile(String fileName) throws FileNotFoundException {
        FileReader fr = new FileReader(fileName);
        List<Magazine> magazineList = new ArrayList<>();

        String name;
        Integer pages;
        Boolean isBorrowed;

        Gson gson = new Gson();
        JsonArray jsonArray;
        try{
            jsonArray = gson.fromJson(fr, JsonArray.class);
        }
        catch (JsonSyntaxException e){
            informAboutErr(System.err, "Problem to read shelf from file");
            return magazineList;
        }
        if(jsonArray != null){
            for (JsonElement jsonElement : jsonArray) {
                try {
                    name = jsonElement.getAsJsonObject().getAsJsonPrimitive("Name").getAsString();
                    pages = jsonElement.getAsJsonObject().getAsJsonPrimitive("Number of pages").getAsInt();
                    isBorrowed = jsonElement.getAsJsonObject().getAsJsonPrimitive("Borrowed").getAsBoolean();

                    magazineList.add(new Magazine(name, pages, isBorrowed));
                }
                catch (JsonParseException e){
                    informAboutErr(System.err, "Problem to read shelf from file");
                }
                catch (NullPointerException nullPointerException){
                    informAboutErr(System.err, "Problem to read shelf from file");
                }
            }
        }
        return magazineList;
    }

    public List<Book> getBooksFromGsonFile(String fileName) throws FileNotFoundException {
        FileReader fr = new FileReader(fileName);
        List<Book> bookList = new ArrayList<>();

        String name;
        Integer pages;
        Boolean isBorrowed;
        String author;
        Date dateOfIssue;

        Gson gson = new Gson();

        JsonArray jsonArray;
        try{
            jsonArray = gson.fromJson(fr, JsonArray.class);
        }
        catch (JsonSyntaxException e){
            informAboutErr(System.err, "Problem to read shelf from file");
            return bookList;
        }
        if(jsonArray != null){
            for (JsonElement jsonElement : jsonArray) {
                try {
                    JsonObject asJsonObject = jsonElement.getAsJsonObject();
                    name = asJsonObject.getAsJsonPrimitive("Name").getAsString();
                    pages = asJsonObject.getAsJsonPrimitive("Number of pages").getAsInt();
                    isBorrowed = asJsonObject.getAsJsonPrimitive("Borrowed").getAsBoolean();
                    author = asJsonObject.getAsJsonPrimitive("Author").getAsString();
                    dateOfIssue = new Date(asJsonObject.getAsJsonPrimitive("Date of issue").getAsString());
                    bookList.add(new Book(name, pages, isBorrowed, author, dateOfIssue));
                }
                catch (Exception e){
                    informAboutErr(System.err, "Problem to read shelf from file");
                }
            }
        }
        return bookList;
    }

    private void informAboutErr(PrintStream err, String problem) {
        err.println(problem);
    }
}