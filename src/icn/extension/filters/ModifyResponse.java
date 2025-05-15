package icn.extension.filters;

import com.ibm.ecm.extension.PluginResponseFilter;
import com.ibm.ecm.extension.PluginServiceCallbacks;
import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;


import javax.servlet.http.HttpServletRequest;

public class ModifyResponse extends PluginResponseFilter {
    @Override
    public String[] getFilteredServices() {
        return new String[] {"/p8/openFolder"};
    }

    @Override
    public void filter(String s, PluginServiceCallbacks pluginServiceCallbacks, HttpServletRequest httpServletRequest, JSONObject jsonObject) throws Exception {
        System.out.println("ModifyResponse filter is called "+ jsonObject);
        if(jsonObject != null) {
                JSONArray rows = (JSONArray) jsonObject.get("rows");
                JSONArray newRows  = new JSONArray();
                JSONObject doc1 =  null;
                JSONObject doc2 = null;
                System.out.println("rows size is "+ rows.size());
                if(rows.size() > 0) {
                    String templateName =  (String) ((JSONObject)rows.get(0)).get("template");
                    if(templateName.equals("Folder")) {
                        return;
                    }
                    for (int x = 0 ; x < rows.size(); x++) {
                        JSONObject document =  (JSONObject) rows.get(x);
                        String documenetName = (String) document.get("name");
                        System.out.println("document Name is "+ documenetName);
                        if(documenetName.equals("BPMDE(3).properties"))
                            doc2 = document;
                        else
                            doc1 =  document;
                    }
                }
                newRows.add(doc1);
                newRows.add(doc2);
                // replace the existing rows
                jsonObject.put("rows",newRows);
        }
    }
}
