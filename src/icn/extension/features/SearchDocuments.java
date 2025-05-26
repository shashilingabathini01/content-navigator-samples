package icn.extension.features;

import com.ibm.ecm.extension.PluginFeature;

import java.util.Locale;

public class SearchDocuments extends PluginFeature {
    @Override
    public String getId() {
        return "SearchDocuments";
    }

    @Override
    public String getName(Locale locale) {
        return "Search Documents";
    }

    @Override
    public String getDescription(Locale locale) {
        return "Plugin feature for searching documents";
    }

    @Override
    public String getIconUrl() {
        return null;
    }

    @Override
    public String getFeatureIconTooltipText(Locale locale) {
        return null;
    }

    @Override
    public String getPopupWindowTooltipText(Locale locale) {
        return null;
    }

    @Override
    public String getContentClass() {
        return "learns.widget.SearchDocuments";
    }

    @Override
    public String getPopupWindowClass() {
        return null;
    }

    @Override
    public boolean isPreLoad() {
        return false;
    }
}
