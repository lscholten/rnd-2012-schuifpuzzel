package com.researchanddevelopment.mainpackage;

import java.io.IOException;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.res.XmlResourceParser;
import android.graphics.PointF;

/**
 * Model van het board
 * 
 * @author Paranoid Android Thom Wiggers - 4119444 Luuk Scholten - 4126424 Koen
 *         Basten - 4119657
 * 
 */
public class Board {
	/**
	 * Size of the board
	 */
	public static final int BOARD_SIZE = 6;

	/**
	 * Lijst met autos
	 */
	private ArrayList<Auto> autos;

	/**
	 * View van board
	 */
	private BoardView boardView;

	/**
	 * activity welke erbij hoort
	 */
	private Activity act;

	/**
	 * welk level er gespeelt wordt tbv highscore
	 */
	private int level;

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
	 * @param xml
	 *            XMLResourceParser to use
	 */
	public Board(XmlResourceParser xml, Activity act, int level) {
		this.act = act;
		this.level = level;
		this.autos = new ArrayList<Auto>();

		int type;
		try {
			while ((type = xml.next()) != XmlResourceParser.END_DOCUMENT) {
				if (type == XmlResourceParser.START_TAG) {

					if (xml.getName().equals("car")) {
						int length = 0, x = 0, y = 0;
						Auto.Orientation orientatie = null;
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
								if (xml.getAttributeValue(i).equals("NS"))
									orientatie = Auto.Orientation.VERTICAAL;
								else if (xml.getAttributeValue(i).equals("WE"))
									orientatie = Auto.Orientation.HORIZONTAAL;

							} else if (attr.equals("goalcar"))
								goalcar = true;
							else
								throw new XmlPullParserException("XML faal");

						}

						autos.add(new Auto(new PointF(x, y), length, orientatie, goalcar));
					}
				}

			}

		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			System.err.println("Fix your xml!");
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
	 * @param boardView
	 *            the boardView to set
	 */
	public void setBoardView(BoardView boardView) {
		this.boardView = boardView;
		boardView.addAutos(autos);
		boardView.setOnTouchListener(new AutoListener(this.autos, act, level, this.boardView));
	}

}
