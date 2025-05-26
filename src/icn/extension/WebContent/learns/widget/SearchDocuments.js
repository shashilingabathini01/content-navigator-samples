define([
     "dojo/_base/declare",
     "ecm/widget/layout/_LaunchBarPane",
     "dojo/text!./templates/SearchDocuments.html",
     "dijit/layout/BorderContainer",
     "dijit/layout/ContentPane",
     "dijit/form/TextBox"
] , function(declare,_LaunchBarPane,template) {
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