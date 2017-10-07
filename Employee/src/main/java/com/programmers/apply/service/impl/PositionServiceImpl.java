package com.programmers.apply.service.impl;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import com.programmers.apply.entities.Position;
import com.programmers.apply.service.PositionService;

@Service
public class PositionServiceImpl implements PositionService {

	@Override
	public Position getLongitudeLatitudeByAdress(String address) {
		try {
			int responseCode = 0;
			String api = "http://maps.googleapis.com/maps/api/geocode/xml?address="
					+ URLEncoder.encode(address, "UTF-8") + "&sensor=true";
			System.out.println("URL : " + api);
			URL url = new URL(api);
			HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
			httpConnection.connect();
			responseCode = httpConnection.getResponseCode();
			if (responseCode == 200) {
				DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				Document document = builder.parse(httpConnection.getInputStream());
				XPathFactory xPathfactory = XPathFactory.newInstance();
				XPath xpath = xPathfactory.newXPath();
				XPathExpression expr = xpath.compile("/GeocodeResponse/status");
				String status = (String) expr.evaluate(document, XPathConstants.STRING);
				if (status.equals("OK")) {
					expr = xpath.compile("//geometry/location/lat");
					String latitude = (String) expr.evaluate(document, XPathConstants.STRING);
					expr = xpath.compile("//geometry/location/lng");
					String longitude = (String) expr.evaluate(document, XPathConstants.STRING);
					return new Position(latitude, longitude);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
