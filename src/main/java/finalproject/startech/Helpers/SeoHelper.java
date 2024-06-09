package finalproject.startech.Helpers;

public class SeoHelper {

    public String seoUrlHelper(String text)
    {
        String[] change = text.toLowerCase()
                .replaceAll("ə", "e")
                .replaceAll("ç", "c")
                .replaceAll("ö", "o")
                .replaceAll("ö", "o")
                .replaceAll("ş", "s")
                .split(" ");
        String result = String.join("-",change);
        return result.replaceAll("[^a-z0-9-]","");
    }
}
