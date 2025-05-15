package icn.extension.actions;

import com.ibm.ecm.extension.PluginAction;
import java.util.Locale;

public class LearnDocumentAction  extends PluginAction {

    @Override
    public String getId() {
        return "LearnDocumentAction";
    }

    @Override
    public String getName(Locale locale) {
        return "ICN Document Read Properties";
    }

    @Override
    public String getIcon() {
        return "";
    }


    @Override
    public String getPrivilege() {
        return "";
    }

    @Override
    public boolean isMultiDoc() {
        return false;
    }

    @Override
    public String getActionFunction() {
        return "";
    }

    @Override
    public String getServerTypes() {
        return "";
    }

    @Override
    public boolean isGlobal() {
        return true;
    }

    @Override
    public String getActionModelClass() {
        //return "";
        return "learns/action/LearnDocumentAction";
    }

}
