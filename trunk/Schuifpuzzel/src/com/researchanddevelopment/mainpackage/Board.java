package com.researchanddevelopment.mainpackage;

import java.io.IOException;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParserException;
import android.content.res.XmlResourceParser;
import android.graphics.Point;

public class Board {
	public static final int BOARD_SIZE = 6;

	private ArrayList<Auto> autos;
	private BoardView boardView;

	/**
	 * Creates a board from a list of cars
	 * 
	 * @param autos
	 */
	public Board(ArrayList<Auto> autos) {
		this.autos = autos;

	}
	
	/**
	 * Creates a new Board from an xml file
	 * 
	 * @param xml XMLResourceParser to use
	 */
	public Board(XmlResourceParser xml) {
		this.autos = new ArrayList<Auto>();

		int type;
		try {
			while ((type = xml.next()) != XmlResourceParser.END_DOCUMENT) {
				if (type == XmlResourceParser.START_TAG) {

					if (xml.getName().equals("car")) {
						int length = 0, x = 0, y = 0;
						Auto.Orientation orientatie = Auto.Orientation.HORIZONTAAL;
						boolean goalcar = false;

						int count = xml.getAttributeCount();
						for (int i = 0; i < count; i++) {
							String attr = xml.getAttributeName(i);
							if (attr.equals("length"))
								length = xml.getAttributeIntValue(i, 1);
							else if (attr.equals("x"))
								x = xml.getAttributeIntValue(i, 0);
							else if (attr.equals("y"))
								y = xml.getAttributeIntValue(i, 0);
							else if (attr.equals("orientation")) {
								if (xml.getAttributeValue(i).equals("NZ"))
									orientatie = Auto.Orientation.VERTICAAL;
								else
									orientatie = Auto.Orientation.HORIZONTAAL;

							} else if (attr.equals("goalcar"))
								goalcar = true;
							else
								throw new XmlPullParserException("XML faal");

						}

						autos.add(new Auto(new Point(x, y), length, orientatie,
								goalcar));
					}
				}

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
	public ArrayList<Auto> getAutos() {
		return autos;
	}

	/**
	 * @param autos
	 *            the autos to set
	 */
	public void setAutos(ArrayList<Auto> autos) {
		this.autos = autos;
	}

	/**
	 * @return the boardView
	 */
	public BoardView getBoardView() {
		return boardView;
	}

	/**
	 * @param boardView the boardView to set
	 */
	public void setBoardView(BoardView boardView) {
		this.boardView = boardView;
		boardView.addAutos(autos);
		
		
	}
}
