package com.krikelin.spotify.watcher;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;


public class ContentLoader {
	
	private Context _SourceContext;
	public ContentLoader(Context _Con)
	{
		_SourceContext=_Con;
	}
	public String GetValue(Element D,String Tag,String DefaultValue)
	{
		String val = D.getElementsByTagName(Tag).item(0).getChildNodes().item(0).getNodeValue();
		return val != "" ? val : DefaultValue;
	}
 
	
	public ArrayList<Song> Songs = new ArrayList<Song>();
	public void FetchContent() throws ParserConfigurationException, SAXException, IOException
	{
		
		DocumentBuilderFactory _Factory = DocumentBuilderFactory.newInstance();
		SharedPreferences Df = PreferenceManager.getDefaultSharedPreferences(_SourceContext);
		
		DocumentBuilder X = _Factory.newDocumentBuilder();
		URL D;
		try {
			D = new URL("http://ws.spotify.com/search/1/track?q=year:0-3000");
			Document Ddf2 = X.parse(new InputSource(D.openStream()));
			Ddf2.getDocumentElement().normalize();
			try
			{
				Song.COUNT_SONGS = Integer.valueOf(Ddf2.getDocumentElement().getChildNodes().item(1).getChildNodes().item(0).getNodeValue());
			}
			catch(Exception e)
			{
				Song.COUNT_SONGS = 100000;
			}
			D = new  URL("http://ws.spotify.com/search/1/track?q=artist:\""+Uri.encode(Df.getString("Artist","Dr. Sounds"))+"\"");
			try {
				Songs = new ArrayList<Song>();
				Document Ddf = X.parse(new InputSource(D.openStream()));
				Ddf.getDocumentElement().normalize();
				
				Songs = new ArrayList<Song>();
				NodeList d = Ddf.getElementsByTagName("track");
				int x=0 ;
				for(int i=0; i < d.getLength(); i++)
				{
					Element _Elm = (Element)d.item(i);
					/**
					 * 
					 * Popularity genereated:
					 * Pop = plays = / COUNT_SONGS 
					 * Month = 
					 */
					Song CurrentSong = new Song();
					CurrentSong.Name = GetValue(_Elm,"name","");
					CurrentSong.Length = Double.valueOf(GetValue(_Elm,"length","0"));
					try	
					{
						//	CurrentSong.popularity= 0.8d;
						CurrentSong.popularity = Double.valueOf(GetValue(_Elm,"popularity","0"));
					}
					catch(Exception e)
					{
						CurrentSong.popularity = 0d;
					}
					CurrentSong.GenerateStats();
					CurrentSong.href = _Elm.getAttribute("href");
					CurrentSong.setArtistLink(((Element)_Elm.getElementsByTagName("artist").item(0)).getAttribute("href"));
					CurrentSong.setAlbumLink(((Element)_Elm.getElementsByTagName("album").item(0)).getAttribute("href"));
					
					Songs.add(CurrentSong);
				}
				
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
