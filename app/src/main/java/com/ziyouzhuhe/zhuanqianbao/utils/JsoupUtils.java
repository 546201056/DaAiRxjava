package com.ziyouzhuhe.zhuanqianbao.utils;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
/**
		* JSOUP
		*/
public class JsoupUtils {
	String url = "http://www.xicidaili.com/nn/";

	public void getIpLists(){
		Connection conn = Jsoup.connect(url);
		Document doc;
		try {
			doc = conn.get();
			Element tab_elmt=doc.getElementById("ip_list");
			Elements trs=tab_elmt.getElementsByTag("tr");
			for (int i = 1; i < trs.size(); i++) {
				String s = trs.get(i).text();
				String[]q =  s.split(" ");
				String ip = 	q[0];
				String port = 	q[1];
				System.out.println("1-------------"+ip+"-----"+port);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
