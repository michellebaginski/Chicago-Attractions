package edu.uic.cs478.a2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/* This class creates the webview fragment that will be in charge of displaying the correct browser for Restaurants */
public class WebviewFragmentR extends Fragment {
    private WebView webpage;
    private int position;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.web_layout, container, false);
        webpage = (WebView) v.findViewById(R.id.webview);
        webpage.getSettings().setJavaScriptEnabled(true);
        webpage.setWebViewClient(new WebViewClient());
        webpage.loadUrl(getWebsite(position));
        return v;
    }

    // returns the correct website for a given click index
    public String getWebsite(int index) {
        String website = "";

        switch (index) {
            case 0:
                website = "https://www.alinearestaurant.com";
                break;
            case 1:
                website = "https://www.rokaakor.com/chicago/";
                break;
            case 2:
                website = "https://www.yuzuchicago.com";
                break;
            case 3:
                website = "https://www.eataly.com/us_en/stores/chicago/";
                break;
            case 4:
                website = "https://www.beatnikchicago.com";
                break;
            case 5:
                website = "https://greenstreetmeats.com";
                break;
            default:
                website = "";
        }
        return website;
    }

    public void setPosition(int index) {
        position = index;
    }


}
