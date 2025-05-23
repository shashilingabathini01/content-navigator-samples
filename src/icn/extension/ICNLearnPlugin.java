package icn.extension;

import com.ibm.ecm.extension.*;
import icn.extension.actions.LearnDocumentAction;
import icn.extension.features.SearchFeatures;
import icn.extension.filters.AddItemRequest;

import java.util.Locale;

public class ICNLearnPlugin extends Plugin {

    @Override
    public String getScript() {
        return "ICNLearns.js";
    }

    @Override
    public String getDebugScript() {
        return "ICNLearns.js";
    }

    @Override
    public String getCSSFileName() {
        return "ICNLearns.css";
    }

    @Override
    public String getCopyright() {
        return "All rights reserved";
    }

    @Override
    public String getDojoModule() {
        return "learns";
    }

    @Override
    public PluginService[] getServices() {
        return super.getServices();
    }

    @Override
    public PluginAction[] getActions() {
        return  new PluginAction[] {
               new LearnDocumentAction()
        };
    }

    @Override
    public PluginRequestFilter[] getRequestFilters() {
        return  new PluginRequestFilter[] {
                new AddItemRequest()
        };
    }

    @Override
    public PluginResponseFilter[] getResponseFilters() {
        return  new PluginResponseFilter[]{
          //  new ModifyResponse()
        };
    }

    @Override
    public PluginFeature[] getFeatures() {
        return new PluginFeature[] { new SearchFeatures()};
    }

    @Override
    public String getId() {
        return "ICNLearns";
    }

    @Override
    public String getName(Locale locale) {
        return "ICN Navigator Extension Plugin";
    }

    @Override
    public String getVersion() {
        return "0.1";
    }
}
