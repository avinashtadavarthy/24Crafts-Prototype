package com.twenty.four.crafts;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by rakesh on 13/1/18.
 */

public class ReadRss extends AsyncTask<Void,Void,Void> implements SwipeRefreshLayout.OnRefreshListener{

    Context context;
    URL url;
    ProgressDialog progressDialog;
    ArrayList<FeedItem> feedItems;
    RecyclerView recyclerView;
    String address;
    SwipeRefreshLayout swipeRefreshLayout;

    ReadRss(Context context, RecyclerView recyclerView, String chosenURL,SwipeRefreshLayout swipeRefreshLayout)
    {
        this.recyclerView = recyclerView;
        this.context = context;
        this.address = chosenURL;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        this.swipeRefreshLayout = swipeRefreshLayout;
    }

    @Override
    protected void onPreExecute() {
        progressDialog.show();
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        ProcessXML(getData());
        return null;
    }

    private void ProcessXML(Document data) {


        if(data!=null) {

            feedItems = new ArrayList<>();

            Element root = data.getDocumentElement();

            Node channel = root.getChildNodes().item(1);
            NodeList items = channel.getChildNodes();

            for(int i=0;i<items.getLength();i++)
            {
                Node currentChild = items.item(i);
                if(currentChild.getNodeName().equalsIgnoreCase("item"))
                {
                    FeedItem feedItem = new FeedItem();
                    NodeList itemChildren = currentChild.getChildNodes();
                    for(int j=0;j<itemChildren.getLength();j++)
                    {
                        Node current = itemChildren.item(j);


                        if(current.getNodeName().equalsIgnoreCase("title"))
                        {
                            feedItem.setTitle(current.getTextContent());
                        }

                        else if(current.getNodeName().equalsIgnoreCase("description"))
                        {
                            feedItem.setDecription(current.getTextContent());

                          /*  List<String> characters=new ArrayList<String>();
                            Pattern pat = Pattern.compile("\\p{L}\\p{M}*");
                            Matcher matcher = pat.matcher(current.getTextContent());
                            while (matcher.find()) {
                                characters.add(matcher.group());
                            }

                            String description = characters.subList(0,50).toString();
                            feedItem.setDecription(description);*/

                        }

                       /* else if(current.getNodeName().equalsIgnoreCase("pubDate"))
                        {
                            feedItem.setPubDate(current.getTextContent());
                        }
*/
                        else if(current.getNodeName().equalsIgnoreCase("link"))
                        {
                            feedItem.setLink(current.getTextContent());
                        }

                      /*  else if(current.getNodeName().equalsIgnoreCase("enclosure"))
                        {
                            String url = current.getAttributes().item(0).getTextContent();
                            feedItem.setThumbnailURL(url);
                        }*/


                        switch(address) {
                            case "http://tamil.thehindu.com/tamilnadu/rss/": feedItem.setThumbnailURL("http://www.thehindu.com/social/article16670585.ece/BINARY/original/thhindu.jpg"); break;
                            default: if(current.getNodeName().equalsIgnoreCase("enclosure"))
                            {
                                String url = current.getAttributes().item(0).getTextContent();
                                feedItem.setThumbnailURL(url);
                            }
                            break;
                        }

                    }

                    feedItems.add(feedItem);
                }

            }
        }
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.dismiss();
        FeedAdapter adapter = new FeedAdapter(context,recyclerView,feedItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setRefreshing(false);



    }

    public Document getData() {
        Document xmlDoc = null;
        try {
            url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            xmlDoc = builder.parse(inputStream);
            Log.e("xmlDoc",xmlDoc + "");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return xmlDoc;
    }

    @Override
    public void onRefresh() {

    }
}
