package advisor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean isAuthorised = false;

        while (true) {
            String response = scanner.nextLine();

            if (response.equals("exit")) {
                System.out.println("---GOODBYE!---");
                break;
            }

            if (response.contains("playlists")) {
                if (isAuthorised) {
                    String[] parts = response.split(" ");
                    String category = parts[1];
                    showPlaylist(category);
                } else {
                    System.out.println("Please, provide access for application.");
                }
            }

            switch (response) {
                case "featured":
                    if (isAuthorised) {
                        showFeatured();
                    } else {
                        System.out.println("Please, provide access for application.");
                    }
                    break;
                case "new":
                    if (isAuthorised) {
                        showNew();
                    } else {
                        System.out.println("Please, provide access for application.");
                    }
                    break;
                case "categories":
                    if (isAuthorised) {
                        showCategories();
                    } else {
                        System.out.println("Please, provide access for application.");
                    }
                    break;
                case "auth":
                    isAuthorised = true;
                    showAuth();
            }
        }
    }

    private static void showAuth() {
        String api = "https://accounts.spotify.com/authorize?client_id=9cb56016f11244088c69cf209d5737b2&redirect_uri=http://localhost:8080&response_type=code";
        System.out.println(api);
        System.out.println("---SUCCESS---");
    }

    private static void showPlaylist(String category) {
        System.out.println("---" + category.toUpperCase() + " PLAYLISTS---");
        System.out.println("Sunday Stroll");
        System.out.println("Walk Like A Badass ");
    }

    private static void showCategories() {
        System.out.println("---CATEGORIES---");
        System.out.println("Top Lists");
        System.out.println("Pop");
        System.out.println("Mood");
    }

    private static void showNew() {
        System.out.println("---NEW RELEASES---");
        System.out.println("Mountains [Sia, Diplo, Labrinth]");
        System.out.println("The Greatest Show [Panic! At The Disco]");
    }

    private static void showFeatured() {
        System.out.println("---FEATURED---");
        System.out.println("Mellow Morning");
        System.out.println("Wake Up and Smell the Coffee");
    }
}

