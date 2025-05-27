define([
     "dojo/_base/declare",
     "ecm/widget/layout/_LaunchBarPane",
     "dojo/data/ItemFileWriteStore",
     "ecm/widget/dialog/MessageDialog"
     "ecm/model/Request"
     "dojo/_base/lang",
     "dojo/_base/connect",
     "dojo/text!./templates/SearchDocuments.html",
     "dijit/layout/BorderContainer",
     "dijit/layout/ContentPane",
     "dijit/form/TextBox",
     "dijit/form/Button",
     "gridx/Grid"
] , function(declare,_LaunchBarPane,ItemFileWriteStore,MessageDialog,Request,lang,connect,template) {
    return declare("learns.widget.SearchDocuments",[ _LaunchBarPane ] ,{
        templateString : template,
        widgetsInTemplate : true,
        postCreate : function() {
            this.inherited(arguments);
        },
        loadContent: function() {
            if (!this.isLoaded) {
                this.isLoaded =  true;
                this.needReset = false;
            }
        }
    });
});