package icn.extension.filters;

import com.ibm.ecm.extension.PluginRequestFilter;
import com.ibm.ecm.extension.PluginRequestUtil;
import com.ibm.ecm.extension.PluginServiceCallbacks;
import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONArtifact;
import com.ibm.json.java.JSONObject;
import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;
import org.apache.struts.upload.MultipartRequestHandler;
import javax.servlet.http.HttpServletRequest;
import java.util.Hashtable;

public class AddItemRequest extends PluginRequestFilter {
    @Override
    public String[] getFilteredServices() {
        return new String[] {"/p8/addItem"};
    }

    @Override
    public JSONObject filter(PluginServiceCallbacks pluginServiceCallbacks, HttpServletRequest httpServletRequest, JSONArtifact jsonArtifact) throws Exception {
        ActionForm actionForm =  pluginServiceCallbacks.getRequestActionForm();
        if (actionForm != null) {
            MultipartRequestHandler  multipartRequestHandler = actionForm.getMultipartRequestHandler();
            if(multipartRequestHandler != null) {
               Hashtable elements =  multipartRequestHandler.getFileElements();
               FormFile formFile = (FormFile) elements.get("file");
               String  mimeType =  formFile.getContentType();
               if(mimeType != null && !mimeType.equals("application/pdf")) {
                   return getError();
               }
            }
        }
        return null;
    }

    private JSONObject getError() {
        JSONObject jsonResponse = new JSONObject();
        JSONArray errors = new JSONArray();
        JSONObject error = new JSONObject();
        error.put("text","Upload failed");
        error.put("message","Invalid Mimetype, accepts only pdf formats");
        errors.add(error);
        jsonResponse.put("errors",errors);
        return  jsonResponse;
    }
}
