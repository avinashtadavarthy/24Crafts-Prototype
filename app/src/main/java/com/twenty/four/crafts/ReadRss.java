package com.twenty.four.crafts;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

public class ReadRss extends AsyncTask<Void,Void,Void> {

    Context context;
    URL url;
    ProgressDialog progressDialog;
    ArrayList<FeedItem> feedItems;
    RecyclerView recyclerView;
    String address;

    ReadRss(Context context, RecyclerView recyclerView, String chosenURL)
    {
        this.recyclerView = recyclerView;
        this.context = context;
        this.address = chosenURL;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
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

                      /*  else if(current.getNodeName().equalsIgnoreCase("media:thumbnail"))
                        {
                            String url = current.getAttributes().item(0).getTextContent();
                            feedItem.setThumbnailURL(url);
                        }
*/
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

        } catch (Exception e) {
            e.printStackTrace();
        }

        return xmlDoc;
    }
}
