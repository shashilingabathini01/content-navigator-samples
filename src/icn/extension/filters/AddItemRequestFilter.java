package icn.extension.filters;

import com.ibm.ecm.extension.PluginRequestFilter;
import com.ibm.ecm.extension.PluginServiceCallbacks;
import com.ibm.json.java.JSONArtifact;
import com.ibm.json.java.JSONObject;
import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;
import org.apache.struts.upload.MultipartRequestHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Hashtable;

public class AddItemRequestFilter extends PluginRequestFilter {
    @Override
    public String[] getFilteredServices() {
        return new String[] { "/p8/addItem"};
    }

    @Override
    public JSONObject filter(PluginServiceCallbacks pluginServiceCallbacks, HttpServletRequest httpServletRequest, JSONArtifact jsonArtifact) throws Exception {
        ActionForm actionForm = pluginServiceCallbacks.getRequestActionForm();
        System.out.println("action form is "+ actionForm);
        if(actionForm != null) {
           MultipartRequestHandler multipartRequestHandler = actionForm.getMultipartRequestHandler();
            System.out.println("multipartRequestHandler is "+ multipartRequestHandler);
            if(multipartRequestHandler != null) {
               Hashtable fileElements =  multipartRequestHandler.getFileElements();
                System.out.println("fileElements  is "+ fileElements);
                if(fileElements != null) {
                    FormFile formFile = (FormFile) fileElements.get("file");
                    if(formFile != null) {
                       String mimeType =  formFile.getContentType();
                        System.out.println("mimeType is "+mimeType);
                    }
                }
            }
        }
        return  null;
    }
}
