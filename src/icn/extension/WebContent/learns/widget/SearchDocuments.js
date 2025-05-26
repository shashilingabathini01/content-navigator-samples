define([
     "dojo/_base/declare",
     "ecm/widget/layout/_LaunchBarPane",
     "dojo/text!./templates/SearchDocuments.html"
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