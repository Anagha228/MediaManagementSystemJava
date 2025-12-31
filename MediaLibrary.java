import java.util.*;
import java.util.stream.*;

// === Students: implement everything per the specification ===

// 1) MediaItem
interface MediaItem {
    String getId();
    String getTitle();
    String getCreator();
    int getLength();
    double getRating();
}

// 2) BaseItem (abstract): fields, static ID counter, validation
abstract class BaseItem implements MediaItem {
    // TODO: 
    private static int counter=0;
    // TODO: private final fields: id, title, creator, length, rating
    private final String id;
    private final String title;
    private final String creator;
    private final int length;
    private final double rating;
    // TODO: constructor with ID assignment & validation
    // TODO: getters
    public BaseItem(String title, String creator, int length, double rating) {
        counter++;
        this.title = title;
        this.creator = creator;
        if(length < 0){
            this.length = 0;
        }else {
            this.length = length;
        }
        if(rating < 0.0){
            this.rating = 0.0;
        }
        else if(rating > 5.0){
            this.rating = 5.0;
        }
        else{
            this.rating = rating;
        }
        this.id = String.format("MI%03d", counter);
    }
    public String getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public String getCreator(){
        return creator;
    }
    public int getLength(){
        return length;
    }
    public double getRating(){
        return rating;
    }
}

// 3) Concrete items
class Book extends BaseItem {
    // TODO: constructor (title, creator, length, rating)
    public Book (String title, String creator, int length, double rating){
        super (title, creator, length, rating);
    }
}
class Movie extends BaseItem {
    // TODO: constructor (title, creator, length, rating)
    public Movie (String title, String creator, int length, double rating){
        super (title, creator, length, rating);
    }
}
class Album extends BaseItem {
    // TODO: constructor (title, creator, length, rating)
    public Album (String title, String creator, int length, double rating){
        super (title, creator, length, rating);
    }
}

// 4) MediaLibrary + custom filtered iterator
class MediaLibrary implements Iterable<MediaItem> {
    // TODO: 
    private ArrayList<MediaItem> items = new ArrayList<>();
    // TODO: add(MediaItem), size(), titlesByCreator(String)
    void add(MediaItem m){
        if(m != null){
            items.add(m);
        }
    }
    int size(){
        return items.size();
    }
    ArrayList<String> titlesByCreator(String creator){
        ArrayList<String> titles = new ArrayList<String>();
        for (int i =0; i<items.size(); i++){
            if (items.get(i).getCreator().equals(creator)){
                titles.add(items.get(i).getTitle());
            }
        }
        return titles;
    }
    // TODO: topTitlesByRating(int), countByType(), averageLengthForType(String)
    ArrayList<String> topTitlesByRating(int k){
        return  items.stream()
                .sorted((a,b)->{
                    if(a.getRating() > b.getRating()){
                    return -1; 
                    }else if(a.getRating() < b.getRating()){
                    return 1;
                    }
                    return a.getTitle().compareTo(b.getTitle());
                })
                .limit(k)
                .map(n-> n.getTitle())
                .collect(Collectors.toCollection(ArrayList::new));
        
    }
    Map<String,Long> countByType(){
        Map<String, Long> counts = new HashMap<>();
        long Bcount =0;
        long Mcount =0;
        long Acount =0;
        for(int i=0; i<items.size(); i++){
            if(items.get(i) instanceof Book){
                Bcount++;
                counts.put("Book", Bcount);
            }
            if(items.get(i) instanceof Movie){
                Mcount++;
                counts.put("Movie", Mcount);
            }
            if(items.get(i) instanceof Album){
                Acount++;
                counts.put("Album", Acount);
            }
        }
        return counts;
    }
    double averageLengthForType(String type){
        double length=0.0;
        double count =0;
        for(int i=0; i<items.size(); i++){
            if (type.equals("Book") && items.get(i) instanceof Book ||
                type.equals("Movie") && items.get(i) instanceof Movie ||
                type.equals("Album") && items.get(i) instanceof Album) {
                count++;
                length= length + items.get(i).getLength();
            }
        }
        if (count != 0){
            return length/count;
        }
        return 0.0;
    }
    // TODO: iterator() : Iterator<MediaItem>   (return items.iterator())
    Iterable<MediaItem> filterByRating(double min){
        return new Iterable<MediaItem>() {
        public Iterator<MediaItem> iterator() {
            return new Iterator<MediaItem>() {
                int index = 0;   // where we are in the items list

                public boolean hasNext() {
                    // skip until itemsrating >= min
                    while(index < items.size()){
                        if (items.get(index).getRating()>=min){
                            return true;
                        }
                        else{
                            index++;
                        }
                    }
                    return false;
                }
                public MediaItem next() {
                    // return the item we found above
                    if(hasNext()==false){
                        throw new NoSuchElementException();
                    }
                    return items.get(index++);
                }
            };
        }
    };

    }
    public Iterator<MediaItem> iterator(){
        return items.iterator();
    }
    // TODO: filterByRating(double) returns Iterable<MediaItem> using a manual Iterator
    
    
}

// Required by CodeRunner; do not remove or make public.
class MediaA3 { }
