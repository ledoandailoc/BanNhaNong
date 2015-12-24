package com.example.uit.bannhanong.fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.uit.bannhanong.DTO.Agricultural;
import com.example.uit.bannhanong.DTO.Preference.AgriculturalPref;
import com.example.uit.bannhanong.DTO.Preference.ListAgriculturalPref;
import com.example.uit.bannhanong.DTO.Preference.ListSearchAgriculturalPref;
import com.example.uit.bannhanong.R;
import com.example.uit.bannhanong.adapter.AgriculturalPriceAdapter;
import com.example.uit.bannhanong.base.BaseMainFragment;
import com.example.uit.bannhanong.utils.CommonUtils;

import org.json.JSONException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class DomesticPriceFragment extends BaseMainFragment {
    private ImageView mBtnSearch;
    private ListView mLvAgricultural;
    private static String url="http://vietbao.vn/vn/gia-ca-thi-truong/";
    Document doc;
    ArrayList<Agricultural> listAgricultual = new ArrayList<>();
    private Spinner spinnerProvince;


    public static DomesticPriceFragment newInstance() {
        return new DomesticPriceFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_price_domestic, container, false);

    }

    @Override
    protected String getScreenTitle() {
        return null;
    }

    @Override
    protected void initContentViews(View view) {
        mLvAgricultural = CommonUtils.findViewById(view, R.id.lv_engineer);
        mBtnSearch = (ImageView) CommonUtils.findViewById(view, R.id.iv_search);
    }

    @Override
    protected void initListener(View view) {

    }

    @Override
    protected void initData() {
        LoadPage("");

        AgriculturalPriceAdapter adapter = new AgriculturalPriceAdapter(getActivity(), R.layout.item_price_agricultural, listAgricultual);
        mLvAgricultural.setAdapter(adapter);

        /*attackSearchView();*/
        attachSearch();
    }

    public void attachSearch(){
        mBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.alert_search);
                Button btn_ok = (Button) dialog.findViewById(R.id.btn_alert_search);
                spinnerProvince = (Spinner) dialog.findViewById(R.id.spiner_province);
                // Spiner
                final ArrayList<String> provinceList = getProvinceList(listAgricultual);
                ArrayAdapter arrayadapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, provinceList);
                arrayadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerProvince.setAdapter(arrayadapter);
                spinnerProvince.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String province = provinceList.get(position);
                        ArrayList<AgriculturalPref> listSpinerAgricultual = null;
                        if (position == 0) {
                            for (Agricultural agriculturalPref : listAgricultual) {
                            }
                        } else
                            for (AgriculturalPref agriculturalPref : listSpinerAgricultual) {
                            }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                btn_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                dialog.show();
            }
        });
    }

    public ArrayList<String> getProvinceList(List<Agricultural> agriculturals) {
        ArrayList<String> provinceList = new ArrayList<>();
        provinceList.add("Tất cả các tỉnh");
        for (Agricultural agricultural : agriculturals) {
            if (!provinceList.contains(agricultural.province))
                provinceList.add(agricultural.province);

        }
        return provinceList;
    }

    public ArrayList<Agricultural> getAgriculturalListByProvince(ArrayList<Agricultural> list, String province) {
        ArrayList<Agricultural> listAgricultural = new ArrayList<>();
        for (Agricultural agricultural : list) {
            if (agricultural.province.equals(province)) listAgricultural.add(agricultural);
        }
        return listAgricultural;
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

                /*el1 = doc.getElementById("Anthem__ctl2__ctl4__ctl0_dtgBaoCao__").getElementsByTag("tr");
                for (int i = 1; i < el1.size(); i++) {
                    Element e = el1.get(i);
                    String a = e.getElementsByTag("td").get(0).text();
                    String b = e.getElementsByTag("td").get(1).text();
                    String c  = e.getElementsByTag("td").get(2).text();

                    Agricultural agricultural = new Agricultural();
                    agricultural.setStatus("increase");
                    agricultural.setName(a.toString());
                    agricultural.setProvince(b.toString());
                    agricultural.setPriceDomestic(c.toString());

                    listAgricultual.add(agricultural);
                }*/

                el3 = doc.getElementsByClass("price");

                for (Element ss : el3) {
                    el2 = ss.getElementsByClass("title-price");
                    if (el2.text().equals("Nông sản") || el2.text().equals("Mặt hàng khác") || el2.text().equals("Thực phẩm")){
                        el1 = ss.getElementsByTag("tbody");
                        for (Element ssss : el1){
                            el4 = ssss.getElementsByTag("tr");
                            for (int i = 1; i < el4.size(); i ++){
                                el5 = el4.get(i);
                                String value1 = el5.getElementsByTag("td").get(0).text().toString();
                                String value2 = el5.getElementsByTag("td").get(1).text().toString();
                                String value3 = el5.getElementsByTag("td").get(2).text().toString();
                                String value4 = el5.getElementsByTag("td").get(3).text().toString();
                                String value5 = el5.getElementsByTag("td").get(4).text().toString().replace(".", "").trim();
                                String value6 = el5.getElementsByTag("td").get(5).text().toString().replace(".", "").trim();
                                String value7 =  el5.getElementsByTag("td").get(6).text().toString();
                                Log.i("helo", value5 + value6);

                                Agricultural agricultural = new Agricultural();
                                agricultural.id = (Integer.parseInt(value1.toString()));
                                agricultural.name = (value2.toString());
                                agricultural.unit = (value3.toString());
                                agricultural.province = (value4.toString());
                                try {
                                    agricultural.priceToDayDomestic = (Double.parseDouble(String.valueOf(value5)));
                                }
                                catch (Exception e){
                                    agricultural.priceToDayDomestic = (0);
                                }
                                try {
                                    agricultural.priceYesterdayDomestic = (Double.parseDouble(String.valueOf(value6)));
                                }
                                catch (Exception e){
                                    agricultural.priceYesterdayDomestic = (0);
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
            arrLoadData=new getDataTraTu().execute(url).get();
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}