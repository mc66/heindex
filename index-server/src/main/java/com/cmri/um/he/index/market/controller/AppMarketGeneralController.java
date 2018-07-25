package com.cmri.um.he.index.market.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.market.service.AppMarketGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class AppMarketGeneralController extends ZRestController {

    @Autowired
    private AppMarketGeneralService appMarketGeneralService;

    /**
     * 查询次月存留率
     * @return
     */
    @RequestMapping(value = "app-test",method = RequestMethod.GET)
    public ResponseMessage test(){
        List<Map<String, Object>> test = appMarketGeneralService.test();

        String[] array=new String[13];
        List<String[]> list1 =new ArrayList<String[]>();
        List<String> list2 =new ArrayList<String>();
        List<String> list3 =new ArrayList<String>();
        for(int i=0;i<test.size();i++){
            Map<String, Object> map = test.get(i);
            double month_num = (double)map.get("月活跃用户");
            double keep_rate = (double)map.get("次月存留率");
            String value1 = String.valueOf(month_num);
            String value2 = String.valueOf(keep_rate);
            String[] arr=new String[13];
            int m=i+1;
            for (int j=0;j<arr.length;j++) {
                if(j!=m){
                    arr[j]="";
                }
            }
            arr[m]=value2;
            String month = (String)map.get("month");
            list1.add(arr);
            list2.add(month);
            list3.add(value1);

        }
        List<String> list =new ArrayList<String>();
        list.add("时间");
        list.add("次月存留率");
        for (int i=0;i<test.size();i++) {
            int n=i+1;
            list.add("第"+n);
        }
        ResponseMessage responseMessage=this.genResponseMessage();
        responseMessage.set("test",test);
        responseMessage.set("list",list);
        responseMessage.set("keep_rate",list1);
        responseMessage.set("month",list2);
        responseMessage.set("month_num",list3);
        return responseMessage;
    }

    /**
     * 查询月活跃用户
     * @param app
     * @param month1
     * @param month2
     * @return
     */
    @RequestMapping(value = "app-market-num",method = RequestMethod.GET)
    public ResponseMessage getUserNumber(Integer app,String month1,String month2){
        List<Map<String, Object>> list=appMarketGeneralService.getUserNumber(app,month1,month2);
        List<Integer> list1=new ArrayList();
        List<Double> list2=new ArrayList();
        List<String> list3=new ArrayList();
        for (Map<String, Object> stringObjectMap : list) {
            Integer new_user =(Integer) stringObjectMap.get("new_user");
            double mau_number =(double) stringObjectMap.get("mau_number");
            String month =(String) stringObjectMap.get("month");
            list1.add(new_user);
            list2.add(mau_number);
            list3.add(month);
        }
        ResponseMessage responseMessage=this.genResponseMessage();
        responseMessage.set("list",list);
        responseMessage.set("new_user",list1);
        responseMessage.set("mau_number",list2);
        responseMessage.set("month",list3);
        return responseMessage;
    }

    @RequestMapping(value = "/queryMonthData",method = RequestMethod.GET)
    public ResponseMessage queryMonthData(@RequestParam int category,@RequestParam int app, @RequestParam String month){
        List<Map<String, Object>> queryMonthDataList = appMarketGeneralService.getAppMarketList(category,app,month);
        ResponseMessage responseMessage = this.genResponseMessage();
        if (queryMonthDataList!=null&&queryMonthDataList.size()>0){
            responseMessage.set("marketList",queryMonthDataList);
        }else {
            responseMessage.setMsg("没有当月数据！");
        }
        return responseMessage;
    }

    @RequestMapping(value = "/queryCumulative",method = RequestMethod.GET)
    public ResponseMessage queryCumulative(@RequestParam int app, @RequestParam String month1,@RequestParam String month2) {
        List<Map<String, Object>> list;
        if (month1.equals("null")) {
            list = appMarketGeneralService.getCumulative(app, month2);
        } else {
            list = appMarketGeneralService.getCumulativeList(app, month1, month2);
        }
        List<Integer> list1 = new ArrayList();
        List<String> list2 = new ArrayList();
        for ( Map<String, Object> map :list) {
           Integer total_user = (Integer) map.get("total_user");
           String month = (String) map.get("month");
           list1.add(total_user);
           list2.add(month);
        }
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("total_user",list1);
        responseMessage.set("month",list2);
        return responseMessage;
    }

}
