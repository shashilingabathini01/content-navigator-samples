package icn.extension.service;

import com.filenet.api.collection.RepositoryRowSet;
import com.filenet.api.core.ObjectStore;
import com.filenet.api.query.RepositoryRow;
import com.filenet.api.query.SearchSQL;
import com.filenet.api.query.SearchScope;
import com.filenet.api.util.UserContext;
import com.ibm.ecm.extension.PluginResponseUtil;
import com.ibm.ecm.extension.PluginService;
import com.ibm.ecm.extension.PluginServiceCallbacks;
import com.ibm.ecm.json.JSONResponse;
import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;

public class SearchDocumentsService extends  PluginService{
    @Override
    public String getId() {
        return "SearchDocuments";
    }

    @Override
    public void execute(PluginServiceCallbacks pluginServiceCallbacks, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        JSONResponse jsonResponse = new JSONResponse();
        try {
            String searchText = httpServletRequest.getParameter("searchText");
            String repo_id = pluginServiceCallbacks.getRepositoryId();
            if (repo_id == null) {
                repo_id = "ECM";
            }
            Subject subject = pluginServiceCallbacks.getP8Subject(repo_id);
            UserContext.get().pushSubject(subject);
            ObjectStore objectStore = pluginServiceCallbacks.getP8ObjectStore(repo_id);
            JSONObject documents =  getDocuments(searchText,objectStore);

            jsonResponse.put("columns", documents.get("columns"));
            jsonResponse.put("rows", documents.get("rows"));
            jsonResponse.put("error", null);
            jsonResponse.put("status","success");

        } catch (Exception e) {
            jsonResponse.put("error", e.getLocalizedMessage());
            jsonResponse.put("status","failed");
            UserContext.get().popSubject();
        }
        PluginResponseUtil.writeJSONResponse(httpServletRequest, httpServletResponse, jsonResponse, pluginServiceCallbacks, "SearchDocuments");
    }

    private JSONObject getDocuments(String searchText,ObjectStore objectStore) {
        JSONObject documents  = new JSONObject();
        documents.put("columns", getColumns());
        documents.put("rows" , getRows(searchText,objectStore));
        return  documents;
    }

    private  JSONArray getRows(String searchText, ObjectStore objectStore) {
        JSONArray rows = new JSONArray();
        SearchSQL searchSQL = new SearchSQL("SELECT DocumentTitle, Id , Creator , DateCreated , PersonName , PersonId from Person WHERE DocumentTitle LIKE '%"+searchText+"%'");
        SearchScope searchScope = new SearchScope(objectStore);
        RepositoryRowSet repositoryRowSet =  searchScope.fetchRows(searchSQL,Integer.MAX_VALUE , null , Boolean.FALSE);
        Iterator<RepositoryRow> rowIterator = repositoryRowSet.iterator();
        while (rowIterator.hasNext()) {
            RepositoryRow row =  rowIterator.next();
            JSONObject rowObject = new JSONObject();
            rowObject.put("documentTitle",row.getProperties().getStringValue("DocumentTitle"));
            rowObject.put("createdBy",row.getProperties().getStringValue("Creator"));
            rowObject.put("createdOn",row.getProperties().getObjectValue("DateCreated"));
            rowObject.put("personName",row.getProperties().getStringValue("PersonName"));
            rowObject.put("personId",row.getProperties().getStringValue("PersonId"));
            rowObject.put("documentId",row.getProperties().getIdValue("Id").toString());
        }
        return  rows;
    }

    private  JSONArray getColumns() {
        JSONArray columns = new JSONArray();

        JSONObject title = new JSONObject();
        title.put("displayName","Document Title");
        title.put("value","documentTitle");
        columns.add(title);

        JSONObject documentId = new JSONObject();
        title.put("displayName","Id");
        title.put("value","documentId");
        columns.add(title);

        JSONObject createdBy = new JSONObject();
        title.put("displayName","Created By");
        title.put("value","createdBy");
        columns.add(createdBy);

        JSONObject createdOn = new JSONObject();
        title.put("displayName","Date Created");
        title.put("value","createdOn");
        columns.add(createdOn);


        JSONObject employeeName = new JSONObject();
        title.put("displayName","Person Name");
        title.put("value","personName");
        columns.add(createdOn);


        JSONObject employeeId = new JSONObject();
        title.put("displayName","Person Id");
        title.put("value","personId");
        columns.add(employeeId);

        return  columns;
    }
}
