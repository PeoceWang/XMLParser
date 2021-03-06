package com.example.provinceandcityforcn;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * 解析XML，获取城市
 * @author zzp
 *
 */
public class CityPullParse {

    public static ArrayList<City> ParseXml(XmlPullParser parser){
        ArrayList<City> CityArray = new ArrayList<City>();
        City CityTemp = null;
        int provinceId = 0;
        int cityId;
        String cityName;

        try {
            //开始解析事件
            int eventType = parser.getEventType();

            //处理事件，不碰到文档结束就一直处理
            while (eventType != XmlPullParser.END_DOCUMENT) {
                //因为定义了一堆静态常量，所以这里可以用switch
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;

                    case XmlPullParser.START_TAG:

                        //给当前标签起个名字
                        String tagName = parser.getName();
                        //  Log.d("", "====XmlPullParser.START_TAG=== tagName: " + tagName);

                        if(tagName.equals("province")){
                            provinceId = Integer.parseInt(parser.getAttributeValue(0));
                        }else if(tagName.equals("item")){
                            CityTemp = new City();
                        }else if(tagName.equals("id")){
                            cityId = Integer.parseInt(parser.nextText());                            
                            parser.next();
                            cityName = parser.nextText();
                            
                            Log.v("", "id getText: "+cityId);
                            Log.v("", "name getText: "+cityName);                            
                            Log.e("", "=========================");

                            if (CityTemp != null) {
                                CityTemp.setProvinceId(provinceId);
                            }
                            if (CityTemp != null) {
                                CityTemp.setCityId(cityId);
                            }
                            if (CityTemp != null) {
                                CityTemp.setCityName(cityName);
                            }

                            CityArray.add(CityTemp);
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        break;
                    case XmlPullParser.END_DOCUMENT:
                        break;
                }

                //别忘了用next方法处理下一个事件，忘了的结果就成死循环#_#
                eventType = parser.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return CityArray;
    }
}
