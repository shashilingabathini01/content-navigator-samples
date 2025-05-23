define([
    "dojo/_base/declare",
    "ecm/widget/layout/_LaunchBarPane",
    "dojo/text!./templates/SearchDocuments.html",
    "dijit/layout/ContentPane",
    "dijit/layout/BorderContainer"
], function(declare, _LaunchBarPane, template) {

    return declare("learns.widget.SearchDocuments",[_LaunchBarPane],{
        templateString : template,
        widgetsInTemplate : true,

        postCreate: function() {
            this.inherited(arguments);
            console.log('postCreate is loaded');
        },

        loadContent: function() {
            if(!this.isLoaded) {
                console.log("loading content");
                    this.isLoaded  = true;
                    this.needReset = false;
            }
        }
    })
})