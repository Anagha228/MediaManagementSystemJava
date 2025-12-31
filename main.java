public class MediaA3 {
    public static void main(String[] args) {
        // Create library
        MediaLibrary library = new MediaLibrary();

        // Add some media items
        library.add(new Book("1984", "George Orwell", 328, 4.8));
        library.add(new Book("Brave New World", "Aldous Huxley", 288, 4.5));
        library.add(new Movie("Inception", "Christopher Nolan", 148, 4.9));
        library.add(new Movie("The Matrix", "The Wachowskis", 136, 4.7));
        library.add(new Album("Abbey Road", "The Beatles", 47, 5.0));
        library.add(new Album("Thriller", "Michael Jackson", 42, 4.9));

        // Show library size
        System.out.println("Total items in library: " + library.size());

        // Titles by a creator
        System.out.println("\nTitles by George Orwell: " + library.titlesByCreator("George Orwell"));

        // Top 3 titles by rating
        System.out.println("\nTop 3 titles by rating: " + library.topTitlesByRating(3));

        // Count by type
        System.out.println("\nItem counts by type: " + library.countByType());

        // Average length for type
        System.out.println("\nAverage length of Movies: " + library.averageLengthForType("Movie"));

        // Filter items by rating >= 4.8
        System.out.println("\nItems with rating >= 4.8:");
        for(MediaItem item : library.filterByRating(4.8)) {
            System.out.println(item.getTitle() + " (" + item.getRating() + ")");
        }

        // Iterate over all items
        System.out.println("\nAll items in library:");
        for(MediaItem item : library) {
            System.out.println(item.getTitle() + " by " + item.getCreator() + " | Length: " + item.getLength() + " | Rating: " + item.getRating());
        }
    }
}
