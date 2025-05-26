define([
     "dojo/_base/declare",
     "ecm/widget/layout/_LaunchBarPane",
     "ecm/widget/dialog/MessageDialog"
     "ecm/model/Request"
     "dojo/_base/lang",
     "dojo/_base/connect",
     "dojo/text!./templates/SearchDocuments.html",
     "dijit/layout/BorderContainer",
     "dijit/layout/ContentPane",
     "dijit/form/TextBox",
     "dijit/form/Button",
     "gridx/Grid",
     "dojo/data/ItemFileWriteStore",
     "gridx/core/model/cache/Sync",
     "gridx/modules/ColumnResizer"
] , function(declare,_LaunchBarPane , MessageDialog, Request,  lang , connect, template) {
    return declare("learns.widget.SearchDocuments",[ _LaunchBarPane ] ,{

        templateString : template,

        widgetsInTemplate : true,

        postCreate : function() {
            this.inherited(arguments);
            this._setEmptyGrid();
        },

        _setEmptyGrid : function() {
            var gridData = {
                    identifier: 'id',
                    items: []
            };
            var store = new ItemFileWriteStore({
                data: gridData
            });
            var COLUMNS = [];
            this._setGridStructure(COLUMNS);
            this.documents.setStore(store);
        },

        loadContent: function() {
            if (!this.isLoaded) {
                this.isLoaded =  true;
                this.needReset = false;
            }
        },

        _setGridStructure : function() {
            var gridLayout = [];
            var colWidth = "120px";
            for(var x = 0 ; x < columns.length ; x++) {
                gridLayout.push({id:x + 1 ,field:columns[x].value,name:columns[x].displayName,width:colWidth});
            }
            console.dir(gridLayout)
            this.documents.setColumns(gridLayout);
        },

        _setGridStore : function() {
            var gridData = {
                    identifier: 'id',
                    items: []
                };
            var _items = [];
            for(var x = 0; x < rows.length ; x++) {
                var rowData = {};
                Object.keys(rows[x]).forEach(function(key) {
                      rowData[key] = rows[x][key];
                });
                rowData["id"] = x + 1;
                _items.push(rowData);
            }
            gridData.items = _items;
            var store = new ItemFileWriteStore({
                data: gridData
            });
            console.dir(store)
            this.documents.setStore(store);
            var _this = this;
            connect.connect(this.documents, "onRowClick", this, lang.hitch(this,function(event){
                var selectedRowIndex = event.rowId;
                var selectRowData = this.documents.model.byId(selectedRowIndex);
                // set field data here
                console.log(selectRowData);
                // document Id and then open the document
                var actions  = ecm.model.desktop.getActionHandler();
                actions["open"](selectRowData.documentId);
            }));
            this.documents.startup();
            this.documents.resize();
        },

        searchDocuments : function() {
            if (this.searchText.value() == "") {
                // pop up
                var messageDialog = new MessageDialog({
                    text : 'search text can not be empty'
                });
                return;
            }
            var requestParams = {};
            requestParams['searchText'] = this.searchText.value();
            Request.invokePluginService("ICNLearns","SearchDocuments", {
                requestParams : requestParams,
                requestCompleteCallback : lang.hitch(this,function(data) {
                    console.dir(data);
                    if(data.error == null && data.status === "success") {
                        this._setGridStructure(data.results.columns);
                        this._setGridStore(data.results.rows);
                    } else {
                        var message =  new MessageDialog({
                            text : 'An error while fetching details'
                        });
                        message.show();
                    }
                }),
                requestFailedCallback :lang.hitch(this,function(data) {
                    var message =  new MessageDialog({
                        text : 'An error while fetching details'
                    });
                    message.show();
                })
            });
        }
    })
});