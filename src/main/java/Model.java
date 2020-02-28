
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.time.LocalDateTime;
import java.util.*;

public class Model {

    int Layout = 0; // 0: grid layout; 1: list layout
    public void changeLayout() {
        Layout = 1 - Layout;
        notifyObservers();
    }
    public int getLayout() {
        return Layout;
    }

    int star = 0; // 0: no filter
    public void changeStar(int newstar) {
        star = newstar;
    }
    public int getStar() {
        return star;
    }

    int num_images = 0;
    public void setNumImages(int len) { num_images = len; }
    public int getNumImages() { return num_images; }


    ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();;
    public void setImages(ArrayList<BufferedImage> imglist, int length) {
        for (int i=0; i<length; ++i) {
            images.add(imglist.get(i));
        }
    }
    public ArrayList<BufferedImage> getImages() {
        return images;
    }
    public void addImage(BufferedImage img) {
        images.add(img);
    }

    ArrayList<String> names = new ArrayList<>();
    public void setNames(ArrayList<String> namelist, int length) {
        for (int i=0; i<length; ++i) {
            names.add(namelist.get(i));
        }
    }
    public ArrayList<String> getNames() { return names; }

    ArrayList<LocalDateTime> creations = new ArrayList<>();
    public void setCreations(ArrayList<LocalDateTime> creationlist, int length) {
        for (int i=0; i<length; ++i) {
            creations.add(creationlist.get(i));
        }
        notifyObservers();
    }
    public ArrayList<LocalDateTime> getCreations() { return creations; }


    int imageCounter = 0;
    public void changeImageCounter(int i) {
        imageCounter = i;
    }
    public int getImageCounter() {
        return imageCounter;
    }


    Map<Integer, Integer> ratings = new HashMap<>(); // K: image Counter; V: rating
    public int getRating(int counter) {
        if (ratings.containsKey(counter)) {
            return ratings.get(counter);
        } else {
            return -1;
        }
    }
    public void addRating(int counter, int rating) {
        ratings.put(counter, rating);
    }
    public void deleteRating(int counter) {
        ratings.remove(counter);
    }
    public void changeRating(int counter, int rating) {
        ratings.remove(counter);
        ratings.put(counter, rating);
    }

    public void filter() {
        notifyObservers();
    }






    private List<Observer> observers;

    public Model() {
        this.observers = new ArrayList();
    }

    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer: this.observers) {
            observer.update(this);
        }
    }
}
