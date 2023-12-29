import java.io.*;
import java.util.HashMap;
import java.util.Map;
 
 import java.util.Scanner;

public class LinkShortener {
    private static LinkShortener instance;
    private Map<String, String> urlMap;

    private static final String MAPPING_FILE = "link_mappings.txt";

    private LinkShortener() {
        this.urlMap = new HashMap<>();
        loadMappingsFromFile();
    }

    public static LinkShortener getInstance() {
        if (instance == null) {
            instance = new LinkShortener();
        }
        return instance;
    }

    public String shortenUrl(String longUrl) {
        if (longUrl == null || longUrl.isEmpty()) {
            throw new IllegalArgumentException("Long URL cannot be null or empty.");
        }

        String shortUrl = generateShortUrl(longUrl);

        while (urlMap.containsKey(shortUrl)) {
            shortUrl = generateShortUrl(longUrl);
        }

        urlMap.put(shortUrl, longUrl);
        saveMappingsToFile();

        return shortUrl;
    }

    public String expandUrl(String shortUrl) {
        if (shortUrl == null || shortUrl.isEmpty() || !urlMap.containsKey(shortUrl)) {
            throw new IllegalArgumentException("Invalid short URL.");
        }

        return urlMap.get(shortUrl);
    }

    private String generateShortUrl(String longUrl) {
        return Integer.toString(longUrl.hashCode(), 36);
    }

    private void loadMappingsFromFile() {
        // Implementation remains the same
    }

    private void saveMappingsToFile() {
        // Implementation remains the same
    }

    public static void main(String[] args) {
        LinkShortener linkShortener = LinkShortener.getInstance();
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter a long URL: ");
            String longUrl = scanner.nextLine();

            String shortUrl = linkShortener.shortenUrl(longUrl);
            System.out.println("Short URL: " + shortUrl);

            String expandedUrl = linkShortener.expandUrl(shortUrl);
            System.out.println("Expanded URL: " + expandedUrl);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
