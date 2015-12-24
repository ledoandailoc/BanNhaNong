package com.example.uit.bannhanong.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.uit.bannhanong.DTO.Agricultural;
import com.example.uit.bannhanong.R;
import com.example.uit.bannhanong.adapter.AgriculturalInternationalPriceAdapter;
import com.example.uit.bannhanong.adapter.AgriculturalPriceAdapter;
import com.example.uit.bannhanong.base.BaseMainFragment;
import com.example.uit.bannhanong.utils.CommonUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class InternationalPriceFragment extends BaseMainFragment {
    private static String url="http://vietbao.vn/vn/gia-ca-thi-truong/";

    Spinner spinnerProvince;
    SearchView searchView;
    private ListView mLvAgricultural;
    ArrayList<Agricultural> listAgricultual = new ArrayList<>();
    ArrayList<Agricultural> mAgricultualListSearching;
    Document doc;

    public static InternationalPriceFragment newInstance() {
        return new InternationalPriceFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_price_international, container, false);
        return v;
    }

    @Override
    protected String getScreenTitle() {
        return null;
    }

    @Override
    protected void initContentViews(View view) {
        mLvAgricultural = CommonUtils.findViewById(view, R.id.lv_engineer);
        searchView = (SearchView) CommonUtils.findViewById(view, R.id.sv_domestic_price);
    }

    @Override
    protected void initListener(View view) {

    }

    @Override
    protected void initData() {

        LoadPage("");
        AgriculturalInternationalPriceAdapter adapter = new AgriculturalInternationalPriceAdapter(getActivity(), R.layout.item_international_price_agricultural, listAgricultual);
        mLvAgricultural.setAdapter(adapter);

    }


    private class getDataTraTu extends AsyncTask<String,Void,String> {
        Elements el1;
        Elements el2;
        Elements el3;
        Elements el4;
        Element el5;

        @Override
        protected String doInBackground(String... params) {
            try {
                doc = Jsoup.connect(params[0]).validateTLSCertificates(false).get();
                doc.title();
                el3 = doc.getElementsByClass("price");
                for (Element ss : el3) {
                    el2 = ss.getElementsByClass("title-price");
                    if (el2.text().equals("Thị trường thế giới")){
                        el1 = ss.getElementsByTag("tbody");
                        for (Element ssss : el1){
                            el4 = ssss.getElementsByTag("tr");
                            for (int i = 1; i < el4.size(); i ++){
                                el5 = el4.get(i);
                                String value1 = el5.getElementsByTag("td").get(0).text().toString();
                                String value2 = el5.getElementsByTag("td").get(1).text().toString();
                                String value3 = el5.getElementsByTag("td").get(2).text().toString();

                                String value5 = el5.getElementsByTag("td").get(3).text().toString().replace(".","").replace(",",".");
                                String value6 = el5.getElementsByTag("td").get(4).text().toString().replace(".", "").replace(",",".");
                                String value7 =  el5.getElementsByTag("td").get(5).text().toString();

                                Log.i("helo", value5 + value6);
                                Agricultural agricultural = new Agricultural();
                                agricultural.id = (Integer.parseInt(value1.toString()));
                                agricultural.name = (value2.toString());
                                agricultural.unit = (value3.toString());
                                agricultural.province = ("");
                                try {
                                    agricultural.priceTodayInternational = (Double.parseDouble(String.valueOf(value5)));
                                }
                                catch (Exception e){
                                    agricultural.priceTodayInternational = (0);
                                }
                                try {
                                    agricultural.priceYesterdayInternational = (Double.parseDouble(String.valueOf(value6)));
                                }
                                catch (Exception e){
                                    agricultural.priceYesterdayInternational = (0);
                                }
                                agricultural.status = (String.valueOf(value7));

                                listAgricultual.add(agricultural);
                            }
                        }
                    }

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }



    public void LoadPage(String search){
        try {
            String arrLoadData;
            arrLoadData = new getDataTraTu().execute(url).get();
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}