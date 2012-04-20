package com.researchanddevelopment.mainpackage;

import java.io.IOException;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParserException;
import android.content.res.XmlResourceParser;
import android.graphics.Point;

public class Board {
	public static final int BOARD_SIZE = 7;

	private Auto[] autos;

	public Board(Auto[] autos) {
		this.autos = autos;

	}

	public Board(XmlResourceParser xml) {
		int type;
		ArrayList<Auto> autoList = new ArrayList<Auto>();
		try {
			while ((type = xml.next()) == XmlResourceParser.END_DOCUMENT) {
				if (type == XmlResourceParser.START_TAG) {
					if (xml.getName().equals("car")) {
						int length = 0, x = 0, y = 0;
						Auto.Orientation orientatie = Auto.Orientation.HORIZONTAAL;

						int count = xml.getAttributeCount();
						for (int i = 0; i < count; i++) {
							if (xml.getAttributeName(i).equals("lenght"))
								length = xml.getAttributeIntValue(i, 1);
							else if (xml.getAttributeName(i).equals("x"))
								x = xml.getAttributeIntValue(i, 0);
							else if (xml.getAttributeName(i).equals("y"))
								y = xml.getAttributeIntValue(i, 0);
							else if (xml.getAttributeName(i).equals(
									"orientation")) {
								if (xml.getAttributeValue(i) == "NZ")
									orientatie = Auto.Orientation.VERTICAAL;
								else
									orientatie = Auto.Orientation.HORIZONTAAL;
							} else
								throw new XmlPullParserException("XML faal");

						}

						autoList.add(new Auto(new Point(x, y), length,
								orientatie));
					}
				}
				this.autos = (Auto[]) autoList.toArray();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return the autos
	 */
	public Auto[] getAutos() {
		return autos;
	}

	/**
	 * @param autos the autos to set
	 */
	public void setAutos(Auto[] autos) {
		this.autos = autos;
	}
}