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
           // this._setEmptyGrid();
        },
//        _setEmptyGrid : function() {
//            var gridData = {
//                    identifier: 'id',
//                    items: []
//            };
//            var store = new ItemFileWriteStore({
//                data: gridData
//            });
//            var COLUMNS = [];
//            this._setGridStructure(COLUMNS);
//            this.documents.setStore(store);
//        },
        loadContent: function() {
            if (!this.isLoaded) {
                this.isLoaded =  true;
                this.needReset = false;
            }
        }
//        _setGridStructure : function(columns) {
//            var gridLayout = [];
//            var colWidth = "120px";
//            for(var x = 0 ; x < columns.length ; x++) {
//                gridLayout.push({id:x + 1 ,field:columns[x].value,name:columns[x].displayName,width:colWidth});
//            }
//            console.dir(gridLayout)
//            this.documents.setColumns(gridLayout);
//        }
    });
});