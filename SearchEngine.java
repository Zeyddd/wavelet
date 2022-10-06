import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;


class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    ArrayList<String> strArray = new ArrayList<>();

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("Search either 'add?s' or 'search?s'");
        } else if (url.getPath().equals("/add")) {
            String[] parameters = url.getQuery().split("=");
            strArray.add(parameters[1]);
            return String.format("Item added!");
        } else {
            if (url.getPath().contains("/search")) {
                ArrayList<String> output = new ArrayList<>();
                String[] parameters = url.getQuery().split("=");
                for(int i = 0; i < strArray.size(); i++){
                    if(strArray.get(i).contains(parameters[1])){
                        output.add(strArray.get(i));
                        return output.toString();
                    }
                }
                
                }
            }
            return "404 Not Found!";
        }
    }

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
